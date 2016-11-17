package edu.fiu.cate.adni.enums;

public enum Ethnicity {
	Unknown(-1), Hispanic(0), Not_Hispanic(1);
	public final int eth;
	private Ethnicity(int g){
		eth = g;
	}
	
	public static Ethnicity getEthnicity(int g){
		switch (g) {
		case 0:
			return Hispanic;
		case 1:
			return Not_Hispanic;
		default:		
			return Unknown;
		}
	}
	
	public static Ethnicity getEthnicity(String g){
		switch (g.trim().toUpperCase()) {
		case "UNKNOWN":
			return Unknown;
		case "HISP/LATINO":
			return Hispanic;
		case "NOT HISP/LATINO":
			return Not_Hispanic;
		default:
			return null;
		}
	}
}
