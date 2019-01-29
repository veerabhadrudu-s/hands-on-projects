/**
 * 
 */
package com.handson.miscellaneous.jsch;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author sveera
 *
 */
public class RuntimeExecuteMain {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Enter source file full path : ");
			String sourcePath = scanner.nextLine();
			System.out.println("Enter destination path : ");
			String destinationPath = scanner.nextLine();
			ProcessBuilder pbuilder = new ProcessBuilder("cp", sourcePath, destinationPath);
			pbuilder.inheritIO();
			Process proc = pbuilder.start();
			int exitCode = proc.waitFor();
			System.out.println("Exit code is " + exitCode);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
