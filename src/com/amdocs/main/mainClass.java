package com.amdocs.main;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class mainClass {

	public static void main( String[] args) throws Exception, SQLException {

	
			String fileContents = Database.getResultSet(null, null);
		
			System.out.println(fileContents);
			
	}

}
	 