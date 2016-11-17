package edu.fiu.cate.adni.enums;

public enum Race {
	Asian(0), Black(1), Hawaiian_OPI(2), Indian_Alaskan(3),  MTO(4), Unknown(5), White(6);
	public final int race;
	private Race(int r){
		race = r;
	}
	
	public static Race getRace(int v){
		switch (v) {
		case 3:
			return Indian_Alaskan;
		case 0:
			return Asian;
		case 1:
			return Black;
		case 2:
			return Hawaiian_OPI;
		case 4:
			return MTO;
		case 5:
			return Unknown;
		case 6:
			return White;
		default:		
			return null;
		}
	}
	
	public static Race getRace(String v){
		switch (v.trim().toLowerCase()) {
		case "am indian/alaskan":
			return Indian_Alaskan;
		case "asian":
			return Asian;
		case "black":
			return Black;
		case "hawaiian/other pi":
			return Hawaiian_OPI;
		case "more than one":
			return MTO;
		case "unknown":
			return Unknown;
		case "white":
			return White;
		default:
			System.out.println("Unknown race: "+v);
			return null;
		}
	}
}
