package com.amdocs.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
    
    public static String getFilePath(String filename) {
	Path currentRelativePath = Paths.get("");
	String path = currentRelativePath.toAbsolutePath().toString();
	return path + filename;
    }

    public static String getContentFromPath(String filename) {
	String content = null;
	try {
	    FileInputStream fin = new FileInputStream(filename);
	    ByteArrayOutputStream bout = new ByteArrayOutputStream();
	    copyInputStreamToOutputStream(fin, bout);
	    fin.close();
	    byte[] b = bout.toByteArray();
	    content = new String(b);

	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return content;
    }
    
    public static void copyInputStreamToOutputStream(InputStream in, OutputStream out) throws IOException {

	synchronized (in) {
	    synchronized (out) {
		byte[] buffer = new byte[256];
		while (true) {
		    int bytesRead = in.read(buffer);
		    if (bytesRead == -1)
			break;
		    out.write(buffer, 0, bytesRead);
		}
	    }
	}
    }

}
