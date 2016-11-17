package edu.fiu.cate.adni.enums;

public enum Gender {
	MALE(0), FEMALE(1);
	public final int gender;
	private Gender(int g){
		gender = g;
	}
	
	public static Gender getGender(int g){
		switch (g) {
		case 0:
			return MALE;
		case 1:
			return FEMALE;
		default:		
			return null;
		}
	}
	
	public static Gender getGender(String g){
		switch (g.trim().toUpperCase()) {
		case "MALE":
			return MALE;
		case "FEMALE":
			return FEMALE;
		default:
			return null;
		}
	}
}
