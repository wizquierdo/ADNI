package edu.fiu.cate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import pilsner.fastica.BelowEVFilter;
import pilsner.fastica.FastICA;
import pilsner.fastica.FastICAConfig;
import pilsner.fastica.FastICAException;
import pilsner.fastica.TanhCFunction;
import pilsner.fastica.FastICAConfig.Approach;
import edu.fiu.cate.adni.ADNIMERGE2;
import edu.fiu.cate.adni.ADNIMERGE2.ADNIMERGE_ENTRY2;
import edu.fiu.cate.adni.merge.ADNIMERGE;
import edu.fiu.cate.adni.merge.ADNIMERGE_ENTRY;

public class ADNIMain {
	
	public ADNIMain(){
//		ADNIMERGE.loadDBTable("E:/School/Research/ADNI/ADNIMERGE.csv");
//		loadADNIMerge();
	}
	
	public void loadADNIMerge(){
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream("E:/School/Research/ADNI/ADNIMERGE.csv")));
			input.readLine();
			int c = 0;
			while(input.ready()){
				String line = input.readLine();
				ADNIMERGE_ENTRY e = ADNIMERGE_ENTRY.parseEntry(line);
				
//				System.out.println(line);		
//				if(e!=null){
//					System.out.println(e.RID+", "+e.VISCODE+", "+e.DX_bl+", "+e.Ventricles);
//				}
				c++;
//				if(c==100) break;
			}
			System.out.println("There are "+c+" records");	
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void method1(){
//		ADNIMERGE.load("E:/School/Research/ADNI/ADNIMERGE.csv");
		ADNIMERGE2.load("E:/School/Research/ADNI/only_bl/ADNIMERGE_ONLY_BL_CN_vs_EMCI_forICA.csv");
		double[][] data = ADNIMERGE2.getVectorData();
		System.out.println(data[0].length);
		
		double[][] out=null;
		try {
			double[][] mMatrix = new double[data.length][data.length];
			mMatrix = null;
//			for(int v=0;v<data.length;v++){
//				for(int u=0;u<data.length;u++){
//					if(u==v)
//						mMatrix[v][u] = 1;
//					else
//						mMatrix[v][u] = 0;
//					System.out.print(mMatrix[v][u]+"\t");
//				}
//				System.out.println();
//			}
//			
			FastICA ica = new FastICA(data, new FastICAConfig(data.length, Approach.DEFLATION, 1.0, 1.0e-12, 1000, mMatrix), new TanhCFunction(1.0),
					new BelowEVFilter(1.0e-12, false), new ProgressListener());
			
			out = ica.getICVectors();			
		} catch (FastICAException e) {
			e.printStackTrace();
		}
		
		if(out!=null){
			writeData("E:/School/Research/ADNI/only_bl/ADNIMERGE_ONLY_BL_CN_vs_EMCI_ICAoutput.csv", out);
		}	
	}
	
	public static void writeData(String path, double[][] out){
		try {
			PrintWriter output = new PrintWriter(path);
			output.write("DX,IC1,IC2,IC3,IC4,IC5,IC6,IC7,IC8,IC9\n");
			ArrayList<ADNIMERGE_ENTRY2> entries = ADNIMERGE2.entries;
			for(int i=0;i<entries.size(); i++){
				ADNIMERGE_ENTRY2 e = entries.get(i);
				String line="";
				switch(e.diagnostic){
				case 0:
					line = "CN";
					break;
				case 1:
					line = "EMCI";
					break;
				case 2:
					line = "LMCI";
					break;
				case 3:
					line = "AD";
					break;
				case -1:
					line = "SMC";
					break;
				}
				line +=",";
				for(int x=0;x<out.length-1;x++)
					line+=out[x][i]+",";
				line+=out[out.length-1][i]+"\n";
				output.write(line);
			}
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static class ProgressListener implements pilsner.fastica.ProgressListener{
		ComputationState pState = null;
		int pComp= -1;
		@Override
		public void progressMade(ComputationState state, int component,
				int iteration, int maxComps) {
			if(pState != state){
				System.out.print("\n"+state.name());
				pState = state;
				pComp= -1;
			}
			if (pComp != component) {
				System.out.print("\n"+component+": ");
				pComp = component;
			} else {
				System.out.print(".");
			}
		}
		
	}
	
	public static void main(String[] args){
		new ADNIMain();
	}
}
