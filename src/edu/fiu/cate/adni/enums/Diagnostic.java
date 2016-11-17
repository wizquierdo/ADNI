package edu.fiu.cate.adni.enums;

public enum Diagnostic {
	Dementia(0), Dementia_to_MCI(1), MCI(2), MCI_to_Dementia(3), MCI_to_NL(4), NL(5), NL_to_Dementia(6), NL_to_MCI(7), Unknown(-1);
	public final int dx;
	Diagnostic(int dx){
		this.dx = dx;
	}

	public static Diagnostic getDiagnostic(int v){
		switch (v) {
		case 0:
			return Dementia;
		case 1:
			return Dementia_to_MCI;
		case 2:
			return MCI;
		case 3:
			return MCI_to_Dementia;
		case 4:
			return MCI_to_NL;
		case 5:
			return NL;
		case 6:
			return NL_to_Dementia;
		case 7:
			return NL_to_MCI;
		default:		
			return null;
		}
	}
	
	public static Diagnostic getDiagnostic(String v){
		switch (v.trim().toLowerCase()) {
		case "dementia":
			return Dementia;
		case "dementia to mci":
			return Dementia_to_MCI;
		case "mci":
			return MCI;
		case "mci to dementia":
			return MCI_to_Dementia;
		case "mci to nl":
			return MCI_to_NL;
		case "nl":
			return NL;
		case "nl to dementia":
			return NL_to_Dementia;
		case "nl to mci":
			return NL_to_MCI;
		case "":
			return Unknown;
		default:
			System.out.println("Unknown status: "+v);
			return null;
		}
	}
}
