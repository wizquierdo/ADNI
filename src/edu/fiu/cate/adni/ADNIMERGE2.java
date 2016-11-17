package edu.fiu.cate.adni;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ADNIMERGE2 {

	
	public static java.util.ArrayList<ADNIMERGE_ENTRY2> entries = new java.util.ArrayList<>();
			
	public static boolean load(String path){
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			input.readLine();
			while(input.ready()){
				String line = input.readLine();
				entries.add(parseEntry(line));				
//				System.out.println(line);
			}
			input.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static double[][] getVectorData(){
		double[][] out = new double[9][entries.size()];
		for(int i=0;i<entries.size(); i++){
			ADNIMERGE_ENTRY2 e = entries.get(i);
			out[0][i] = e.age;
			out[1][i] = e.education;
			out[2][i] = e.wholeBrain;
			out[3][i] = e.ventriclesNorm;
			out[4][i] = e.hippocampusNorm;
			out[5][i] = e.entorhinalNorm;
			out[6][i] = e.fusiformNorm;
			out[7][i] = e.midTempNorm;
			out[8][i] = e.icvNorm;
		}
		return out;
	}
	
	private static ADNIMERGE_ENTRY2 parseEntry(String line){
		ADNIMERGE_ENTRY2 entry = new ADNIMERGE_ENTRY2();
		String[] fields = line.split(",");
		
		switch(fields[0]){
		case "CN":
			entry.diagnostic = 0;
			break;
		case "EMCI":
			entry.diagnostic = 1;
			break;
		case "LMCI":
			entry.diagnostic = 2;
			break;
		case "AD":
			entry.diagnostic = 3;
			break;
		case "SMC":
			entry.diagnostic = -1;			// CHECK HERE
			break;
		}
		entry.age = Float.parseFloat(fields[1]);
		
		entry.education = Integer.parseInt(fields[3]);
		try{entry.wholeBrain 	= Float.parseFloat(fields[4]);
		}catch(java.lang.NumberFormatException e){}

		
		try{entry.ventriclesNorm 	= Float.parseFloat(fields[5]);
		}catch(java.lang.NumberFormatException e){}
		try{entry.hippocampusNorm 	= Float.parseFloat(fields[6]);
		}catch(java.lang.NumberFormatException e){}
		
		try{entry.entorhinalNorm	= Float.parseFloat(fields[7]);
		}catch(java.lang.NumberFormatException e){}
		try{entry.fusiformNorm 		= Float.parseFloat(fields[8]);
		}catch(java.lang.NumberFormatException e){}
		try{entry.midTempNorm 		= Float.parseFloat(fields[9]);
		}catch(java.lang.NumberFormatException e){}
		try{entry.icvNorm 			= Float.parseFloat(fields[10]);
		}catch(java.lang.NumberFormatException e){}
		
		return entry;
	}
	
	public static class ADNIMERGE_ENTRY2{
		public int diagnostic;
		public float age;
		public int sex;
		public int education;

		public float wholeBrain;
		public float ventriclesNorm;
		public float hippocampusNorm;
		public float entorhinalNorm;
		public float fusiformNorm;
		public float midTempNorm;
		public float icvNorm;
	}
	
	public static int getVisitCode(String vCode){
		vCode = vCode.toLowerCase();
		if(vCode.equals("bl") || vCode.equals("sc")) return 0;
		if(vCode.startsWith("m")) return Integer.parseInt(vCode.substring(1));
		return -1;
	}
	
}
