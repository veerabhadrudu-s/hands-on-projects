package com.handson.test.ldap.client;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class BruteForceMD5 {

	private static final int DEFAULT_MAX_LENGTH = 20;

	public String findPasswordFromHashCode(final byte[] passwords) throws NoSuchAlgorithmException {
		return findPasswordFromHashCode(passwords, DEFAULT_MAX_LENGTH);
	}

	public String findPasswordFromHashCode(final byte[] hashPassword, int length) throws NoSuchAlgorithmException {

		for (int passwordLengthIndex = 0; passwordLengthIndex < length; passwordLengthIndex++) {
			byte[] newLength = new byte[passwordLengthIndex];
			for (int index = 0; index < 256; index++) {
				isPasswordsMatched(hashPassword, new byte[] { (byte) index });
			}

			isPasswordsMatched(hashPassword, null);
		}
		return null;
	}

	private boolean isPasswordsMatched(final byte[] hashPassword, byte... expectedPassword)
			throws NoSuchAlgorithmException {
		final MessageDigest md = MessageDigest.getInstance("MD5");
		final byte[] encryptedBytes = md.digest(expectedPassword);

		return true;
	}

	

	public static void main(String args[]) {
		if (args.length > 0) {
			try {
				BruteCrack bc = new BruteCrack();
				long start;
				long end;
				String answer;

				start = System.nanoTime();
				answer = bc.crack(args[0]);
				end = System.nanoTime();

				System.out.println("Answer: " + answer);
				System.out.println("Processing Time: " + ((end - start) / 1000000000));
			} catch (Exception e) {
				System.out.println("Exception: " + e.toString());
			}
		}
	}
}
