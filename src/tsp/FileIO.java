/*
 * Copyright (c) 2015 SUN XIMENG (Nathaniel). All rights reserved.
 */

package tsp;


import java.nio.file.StandardOpenOption;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;


class FileIO {
	
	protected static void append(String[] data, String path){
		try {
			Path file = Paths.get(path);
			OutputStream output = new BufferedOutputStream(Files.newOutputStream(file, StandardOpenOption.APPEND, StandardOpenOption.CREATE));
			write(data, output);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected static String[] read(String path){
		try{
			Path file = Paths.get(path);
			InputStream input = new BufferedInputStream(Files.newInputStream(file));
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			Vector<String> v = new Vector<String>();
			String line = reader.readLine();
			
			while(line != null){
				v.add(line);
				line = reader.readLine();

			}
			reader.close();
			
			return v.toArray(new String[v.size()]);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private static void write(String[] data, OutputStream output) throws IOException{
		for(String d : data){
			output.write(d.getBytes());
			output.write(System.getProperty("line.separator").getBytes());
			output.flush();
		}
		output.close();
	}
	
	protected static void write(String[] data, String path){
		
		try {
			Path file = Paths.get(path);
			OutputStream output = new BufferedOutputStream(Files.newOutputStream(file, StandardOpenOption.TRUNCATE_EXISTING));
			write(data, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private FileIO(){
		
	}

}
