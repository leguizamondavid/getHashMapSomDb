package com.amdocs.main;

import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.amdocs.db.JDBCUtil;

public class Database {
	
	public List<HashMap<String, Object>> getResultSet(String statement, HashMap<String, String> fieldList){
	    List<HashMap<String, Object>> resultSet = new ArrayList<HashMap<String, Object>>();
	    ResultSet rs = null;
	    try{

	          Connection conn = JDBCUtil.getSourceSOMDBConnection();
	          Statement stmt = conn.createStatement();
	          
	          HashMap<String, Object> record = null;
	          rs = stmt.executeQuery(statement);
	          ObjectInputStream obj= null;
	          while(rs.next()){
	                record = new HashMap<String, Object>();
	                for (String fieldName : fieldList.keySet()) {
	                      if (fieldList.get(fieldName).equals("String"))      
	                            record.put(fieldName, rs.getString(fieldName));
	                      if (fieldList.get(fieldName).equals("Blob")){
	                            obj = new ObjectInputStream(rs.getBlob(0).getBinaryStream());
	                            record.put(fieldName, obj.readObject());
	                      }
	                }
	                resultSet.add(record);
	          }
	          
	          stmt.close();
	          conn.commit();
	          conn.close();
	          
	    }catch(Exception e){
	          e.printStackTrace();
	    }
	    return resultSet;
	}

}
