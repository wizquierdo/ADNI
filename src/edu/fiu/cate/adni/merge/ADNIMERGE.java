package edu.fiu.cate.adni.merge;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ADNIMERGE {
	private static String schema = "ADNI";
	private static String tableName = schema+".ADNIMERGE";
	
	private Connection dbConnection;
	
	public static java.util.Set<ADNIMERGE_ENTRY> entries = new java.util.TreeSet<>();
	
	static java.util.TreeSet<String> dx = new java.util.TreeSet<>();
	static java.util.TreeSet<String> visitID = new java.util.TreeSet<>();
		
	static{
		// This will load the MySQL driver, each DB has its own driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ADNIMERGE getDatabaseInstance(String user, String pass){
		ADNIMERGE dbI = new ADNIMERGE();
		try {
			// Setup the connection with the DB
			dbI.dbConnection = DriverManager
					.getConnection("jdbc:mysql://192.168.0.2:3306/"+schema+"?"
							+ "user="+user+"&password="+pass);
			return dbI;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean loadDBTable(String filePath){
		try{
			// Setup the connection with the DB
			Connection connect = DriverManager
					.getConnection("jdbc:mysql://192.168.0.2:3306/ADNI?"
							+ "user=root&password=Elech@r2");

			// Statements allow to issue SQL queries to the database
			Statement statement = connect.createStatement();
			
			BufferedReader input = new BufferedReader(new FileReader(filePath));
			input.readLine();
			int c = 0;
			while(input.ready()){
				ADNIMERGE_ENTRY e = ADNIMERGE_ENTRY.parseEntry(input.readLine());
				String q = e.getMySQLInsertStatement(tableName);
				System.out.println(q);
				statement.executeUpdate(q);
				c++;
//				if(c==10) break;
			}
			input.close();
			connect.close();
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
		
		return false;
	}
	
	public static boolean load(String path){
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			input.readLine();
			while(input.ready()){
				String line = input.readLine();
				entries.add(parseEntry(line));				
//				System.out.println(line);
			}
			
			for(String s: visitID)
				System.out.println(s);
			
			int c = 0;
			for(ADNIMERGE_ENTRY e: entries)
				if(c++ < 100)
					System.out.println(e.RID+", "+e.AGE+", "+e.DX_bl+", "+e.Ventricles+", "+e.Hippocampus+", "+e.WholeBrain);
			
			input.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private static ADNIMERGE_ENTRY parseEntry(String line){
		ADNIMERGE_ENTRY entry = new ADNIMERGE_ENTRY();
		String[] fields = line.split("\",\"");
		
		entry.RID = Integer.parseInt(fields[0].substring(1));
		
		entry.VISCODE = getVisitCode(fields[2]);
		entry.SITE = Integer.parseInt(fields[3]);
		
		switch(fields[7]){
		case "CN":
			entry.DX_bl = 0;
			break;
		case "EMCI":
			entry.DX_bl = 1;
			break;
		case "LMCI":
			entry.DX_bl = 2;
			break;
		case "AD":
			entry.DX_bl = 3;
			break;
		case "SMC":
			entry.DX_bl = -1;			// CHECK HERE
			break;
		}
		entry.AGE = Float.parseFloat(fields[8]);
		
		entry.PTEDUCAT = Integer.parseInt(fields[10]);
		

		try{entry.ICV 			= Float.parseFloat(fields[50]);
		}catch(java.lang.NumberFormatException e){}
		
		try{entry.Ventricles 	= Float.parseFloat(fields[44])/entry.ICV;
		}catch(java.lang.NumberFormatException e){}
		try{entry.Hippocampus 	= Float.parseFloat(fields[45])/entry.ICV;
		}catch(java.lang.NumberFormatException e){}
		try{entry.WholeBrain 	= Float.parseFloat(fields[46])/entry.ICV;
		}catch(java.lang.NumberFormatException e){}
		try{entry.Entorhinal	= Float.parseFloat(fields[47])/entry.ICV;
		}catch(java.lang.NumberFormatException e){}
		try{entry.Fusiform 		= Float.parseFloat(fields[48])/entry.ICV;
		}catch(java.lang.NumberFormatException e){}
		try{entry.MidTemp 		= Float.parseFloat(fields[49])/entry.ICV;
		}catch(java.lang.NumberFormatException e){}
		
		return entry;
	}
	
	public static int getVisitCode(String vCode){
		vCode = vCode.toLowerCase();
		if(vCode.equals("bl") || vCode.equals("sc")) return 0;
		if(vCode.startsWith("m")) return Integer.parseInt(vCode.substring(1));
		return -1;
	}
	
}
