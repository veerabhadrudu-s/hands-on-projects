/**
 * 
 */
package com.handson.linux.jcraft.jsch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

/**
 * @author sveera
 *
 */
public class SSHManagerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@DisplayName("testcase for running command")
	public void testSendCommand() throws JSchException, IOException {
		System.out.println("sendCommand");

		/**
		 * YOU MUST CHANGE THE FOLLOWING FILE_NAME: A FILE IN THE DIRECTORY USER: LOGIN
		 * USER NAME PASSWORD: PASSWORD FOR THAT USER HOST: IP ADDRESS OF THE SSH SERVER
		 **/
		String command = "ls /opt/Symantec/";
		String userName = "b.ranjan.pattanaik";
		String password = "Welcome@123";
		String connectionIP = "10.18.17.20";
		SSHManager instance = new SSHManager(userName, password, connectionIP,
				"C:\\Users\\sveera\\workspace\\Test\\src\\known_hosts");
		String expResult = "FILE_NAME\n";
		// call sendCommand for each command and the output
		// (without prompts) is returned
		String result = instance.sendCommand(command);
		// close only after all commands are sent
		System.out.println("Received result " + result);
		assertEquals(expResult, result);
	}

	@Test
	@DisplayName("testcase for copying file between machines")
	public void testCopyFile() throws FileNotFoundException, JSchException, SftpException {

		/**
		 * YOU MUST CHANGE THE FOLLOWING FILE_NAME: A FILE IN THE DIRECTORY USER: LOGIN
		 * USER NAME PASSWORD: PASSWORD FOR THAT USER HOST: IP ADDRESS OF THE SSH SERVER
		 **/
		String userName = "b.ranjan.pattanaik";
		String password = "Welcome@123";
		String connectionIP = "10.18.17.20";
		SSHManager instance = new SSHManager(userName, password, connectionIP,
				"C:\\Users\\sveera\\workspace\\Test\\src\\known_hosts");
		String expResult = "FILE_NAME\n";
		// call sendCommand for each command and the output
		// (without prompts) is returned
		String result = instance.copyFile("C:\\IHI.csv", "/apps/nfs/shareQ/Bulk_Registration/ValidateExtract");
		// close only after all commands are sent
		System.out.println("Received result " + result);
		assertEquals(expResult, result);
	}

}
