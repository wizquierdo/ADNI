package edu.fiu.cate.adni.enums;

public enum MaritalStatus {
	Divorced(0), Married(1), Never_Married(2), Unknown(3), Widowed(4);
	public final int status;
	private MaritalStatus(int r){
		status = r;
	}
	
	public static MaritalStatus getMaritalStatus(int v){
		switch (v) {
		case 0:
			return Divorced;
		case 1:
			return Married;
		case 2:
			return Never_Married;
		case 3:
			return Unknown;
		case 4:
			return Widowed;
		default:		
			return null;
		}
	}
	
	public static MaritalStatus getMaritalStatus(String v){
		switch (v.trim().toLowerCase()) {
		case "divorced":
			return Divorced;
		case "married":
			return Married;
		case "never married":
			return Never_Married;
		case "unknown":
			return Unknown;
		case "widowed":
			return Widowed;
		default:
			System.out.println("Unknown status: "+v);
			return null;
		}
	}

}
