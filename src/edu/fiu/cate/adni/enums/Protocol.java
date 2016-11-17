package edu.fiu.cate.adni.enums;

public enum Protocol {
	ADNI1(0), ADNI2(1), ADNIGO(2);
	public final int protocol;
	private Protocol(int p){
		protocol = p;
	}
	
	public static Protocol getProtocol(int p){
		switch (p) {
		case 0:
			return ADNI1;
		case 1:
			return ADNI2;
		case 2:
			return ADNIGO;
		default:		
			return null;
		}
	}
	
	public static Protocol getProtocol(String p){
		switch (p.trim().toUpperCase()) {
		case "ADNI1":
			return ADNI1;
		case "ADNI2":
			return ADNI2;
		case "ADNIGO":
			return ADNIGO;
		default:
			System.out.println("Unknown protocol: " + p);
			return null;
		}
	}
}
