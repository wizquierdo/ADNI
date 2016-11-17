package edu.fiu.cate.adni.enums;

public enum VisitCode {
	bl(0), m06(6), m12(12), m18(18), m24(24), m30(30), m36(36), m42(42), m72(72), m84(84);
	public final int visitCode;
	private VisitCode(int n){
		visitCode = n;
	}
	
	public static VisitCode getVisitCode(int v){
		switch (v) {
		case 0:
			return bl;
		case 6:
			return m06;
		case 12:
			return m12;
		case 18:
			return m18;
		case 24:
			return m24;
		case 30:
			return m30;
		case 36:
			return m36;
		case 42:
			return m42;
		case 72:
			return m72;
		case 84:
			return m84;
		default:		
			return null;
		}
	}
	
	public static VisitCode getVisitCode(String v){
		switch (v.trim().toLowerCase()) {
		case "bl":
			return bl;
		case "m06":
			return m06;
		case "m12":
			return m12;
		case "m18":
			return m18;
		case "m24":
			return m24;
		case "m30":
			return m30;
		case "m36":
			return m36;
		case "m42":
			return m42;
		case "m72":
			return m72;
		case "m84":
			return m84;
		default:
			System.out.println("Unknown code: "+v);
			return null;
		}
	}

}
