package edu.fiu.cate.adni.enums;

public enum BaselineDX {
	CN(0), EMCI(1), LMCI(2), AD(3), SMC(4);
	public final int dx;
	private BaselineDX(int dx){
		this.dx = dx;
	}

	public static BaselineDX getDX(int c){
		switch (c) {
		case 0:
			return CN;
		case 1:
			return EMCI;
		case 2:
			return LMCI;
		case 3:
			return AD;
		case 4:
			return SMC;
		default:
			return null;
		}
	}
	
	public static BaselineDX getDX(String dx){
		switch (dx.trim().toUpperCase()) {
		case "CN":
			return CN;
		case "EMCI":
			return EMCI;
		case "LMCI":
			return LMCI;
		case "AD":
			return AD;
		case "SMC":
			return SMC;
		default:
			System.out.println("Unknown DX: "+ dx);
			return null;
		}
	}
}
