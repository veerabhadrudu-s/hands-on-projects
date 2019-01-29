package com.handson.miscellaneous.jsch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SSHManager {
	private JSch jschSSHChannel;
	private String strUserName;
	private String strConnectionIP;
	private int intConnectionPort;
	private String strPassword;
	private Session sesConnection;
	private int intTimeOut;

	public SSHManager(String userName, String password, String connectionIP, String knownHostsFileName)
			throws JSchException {
		doCommonConstructorActions(userName, password, connectionIP, knownHostsFileName, 22, 60000);
	}

	public SSHManager(String userName, String password, String connectionIP, String knownHostsFileName,
			int connectionPort) throws JSchException {
		doCommonConstructorActions(userName, password, connectionIP, knownHostsFileName, connectionPort, 60000);
	}

	public SSHManager(String userName, String password, String connectionIP, String knownHostsFileName,
			int connectionPort, int timeOutMilliseconds) throws JSchException {
		doCommonConstructorActions(userName, password, connectionIP, knownHostsFileName, connectionPort,
				timeOutMilliseconds);
	}

	private void doCommonConstructorActions(String userName, String password, String connectionIP,
			String knownHostsFileName, int intConnectionPort, int intTimeOut) throws JSchException {
		jschSSHChannel = new JSch();
		if (knownHostsFileName != null)
			jschSSHChannel.setKnownHosts(knownHostsFileName);
		strUserName = userName;
		strPassword = password;
		strConnectionIP = connectionIP;
	}

	public String sendCommand(String command) throws JSchException, IOException {
		StringBuilder outputBuffer = new StringBuilder();
		try {
			connect();
			Channel channel = sesConnection.openChannel("exec");
			((ChannelExec) channel).setCommand(command);
			InputStream commandOutput = channel.getInputStream();
			channel.connect();
			int readByte = commandOutput.read();
			while (readByte != 0xffffffff) {
				outputBuffer.append((char) readByte);
				readByte = commandOutput.read();
			}
			channel.disconnect();
		} finally {
			close();
		}
		return outputBuffer.toString();
	}

	public String copyFile(String sourcePath, String destinationPath)
			throws FileNotFoundException, JSchException, SftpException {
		StringBuilder outputBuffer = new StringBuilder();
		try {
			connect();
			Channel channel = sesConnection.openChannel("sftp");
			channel.connect();
			ChannelSftp sftp = (ChannelSftp) channel;
			sftp.cd(destinationPath);
			File sourceFile = new File(sourcePath);
			sftp.put(new FileInputStream(sourceFile), sourceFile.getName(), ChannelSftp.OVERWRITE);
			channel.disconnect();
		} finally {
			close();
		}

		return outputBuffer.toString();
	}

	private void connect() throws JSchException {
		sesConnection = getSessionConnectionForHost();
		// UNCOMMENT THIS FOR TESTING PURPOSES, BUT DO NOT USE IN PRODUCTION
		sesConnection.setConfig("StrictHostKeyChecking", "no");
		sesConnection.connect(intTimeOut);
	}

	private Session getSessionConnectionForHost() throws JSchException {
		Session session = jschSSHChannel.getSession(strUserName, strConnectionIP, intConnectionPort);
		session.setPassword(strPassword);
		return session;
	}

	private void close() {
		if (sesConnection != null)
			sesConnection.disconnect();
	}

}