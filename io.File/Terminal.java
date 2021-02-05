package dos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class DOSmain {
	static final Scanner SCANNER = new Scanner(System.in);
	static final String NO_SUCH_DIRECTORY = "cd: no such file or directory: ";
	static final String NO_SUCH_COMMAND = "zsh: command not found: ";
	static final String HOME = "{$HOME}";

	public static void ls(File file) {
		File[] files = file.listFiles();

		for(File f: files) {
			if (f.isDirectory()) {
				System.out.println("Dir "+f.getName());
			} else {
				System.out.println("File "+f.getName());
			}
		}
	}

	public static File cd(File ex_file, String name) {
		File new_file = new File(ex_file, name);
		if (!new_file.exists()) {
			System.out.println(NO_SUCH_DIRECTORY+name);
			return ex_file;
		}

		return new_file;
	}

	public static File cdToBefore(File file){
		File parent = file.getParentFile();

		return parent;
	}

	public static void mkdir(File file, String name) {
		File new_file = new File(file, name);
		new_file.mkdirs();
		return;
	}

	public static void copyFile(File dest, File source) {
		FileInputStream in = null;
		FileOutputStream out = null;

		try {
			byte[] arr = new byte[500];
			int count = 0;

			in = new FileInputStream(source);
			out = new FileOutputStream(dest);

			while((count = in.read(arr)) != -1) {
				out.write(arr);
				Arrays.fill(arr, (byte)0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) in.close();
				if (out != null) out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void cp(File dest, File source) {
		File[] sourceFiles = source.listFiles();

		for(File sf: sourceFiles) {
			File newDest = new File(dest, sf.getName());
			if (sf.isDirectory()) {
				newDest.mkdir();
				cp(newDest, sf);
			} else {
				copyFile(newDest, sf);
			}
		}
	}

	public static void main(String[] args) {
		File file = new File(HOME);

		while(true) {
			System.out.print("> ");
			String command = SCANNER.nextLine();
			String[] commands = command.split(" ");

			switch(commands[0]) {
				case "pwd":
					System.out.println(file.getPath());
					break;
				case "ls":
					ls(file);
					break;
				case "cd":
					if (commands.length < 2) {
						file = new File(HOME);
						break;
					}
					if(commands[1].matches("^[\\w][-_.\\\\|\\w]*")) {
						file = cd(file, commands[1]);
					} else if (commands[1].equals("..")) {
						file = cdToBefore(file);
					} else {
						System.out.println(NO_SUCH_DIRECTORY+commands[1]);
					}
					break;
				case "mkdir":
					mkdir(file, commands[1]);
					break;
				case "cp":
					File sourceFiles = new File(commands[1]);
					cp(file, sourceFiles);
					break;
				case "exit":
					return;
				default:
					System.out.println(NO_SUCH_COMMAND+commands[0]);
			}
		}
	}
}
