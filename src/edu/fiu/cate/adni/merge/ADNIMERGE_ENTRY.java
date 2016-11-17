package edu.fiu.cate.adni.merge;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import edu.fiu.cate.adni.enums.BaselineDX;
import edu.fiu.cate.adni.enums.Diagnostic;
import edu.fiu.cate.adni.enums.Ethnicity;
import edu.fiu.cate.adni.enums.Gender;
import edu.fiu.cate.adni.enums.MaritalStatus;
import edu.fiu.cate.adni.enums.Protocol;
import edu.fiu.cate.adni.enums.Race;

public class ADNIMERGE_ENTRY {	
	public int RID;					//Participant roster ID
	public String PTID;				//Participant ID
	public int VISCODE;				//Visit code
	public int SITE;				//Site
	public int COLPROT;				//Protocol under which data was collected
	public int ORIGPROT;			//Protocol from which subject originated
	public long EXAMDATE = -1;		//Examination Date
	public int DX_bl;				//Baseline Dx
	public float AGE;				//Age at baseline
	public int PTGENDER;			//Sex
	public int PTEDUCAT;			//Education
	public int PTETHCAT;			//Ethnicity
	public int PTRACCAT;			//Race
	public int PTMARRY;				//Marital status at baseline
	public Integer APOE4;			//ApoE4
	public Float FDG = null;				//Average FDG-PET of angular, temporal, and posterior cingulate
	public Float PIB = null;				//Average PIB SUVR of frontal cortex, anterior cingulate, precuneus cortex, and parietal cortex
	public Float AV45 = null;				//Average AV45 SUVR of frontal, anterior cingulate, precuneus, and parietal cortex relative to the cerebellum
	public Float CDRSB = null;				//CDR-SB
	public Float ADAS11 = null;			//ADAS 11
	public Float ADAS13 = null;			//ADAS 13
	public Float MMSE = null;				//MMSE
	public Float RAVLT_immediate = null;
	public Float RAVLT_learning = null;
	public Float RAVLT_forgetting = null;
	public Float RAVLT_perc_forgetting = null;
	public Float FAQ = null;				//FAQ
	public Float MOCA = null;				//MOCA
	public Float EcogPtMem = null;			//Participant ECog - Mem
	public Float EcogPtLang = null;		//Participant ECog - Lang
	public Float EcogPtVisspat = null;		//Participant ECog - Vis/Spat
	public Float EcogPtPlan = null;		//Participant ECog - Plan
	public Float EcogPtOrgan = null;		//Participant ECog - Organ
	public Float EcogPtDivatt = null;		//Participant ECog - Div atten
	public Float EcogPtTotal = null;		//Participant ECog - Total
	public Float EcogSPMem = null;			//Study Partner ECog - Mem
	public Float EcogSPLang = null;		//Study Partner ECog - Lang
	public Float EcogSPVisspat = null;		//Study Partner ECog - Vis/Spat
	public Float EcogSPPlan = null;		//Study Partner ECog - Plan
	public Float EcogSPOrgan = null;		//Study Partner ECog - Organ
	public Float EcogSPDivatt = null;		//Study Partner ECog - Div atten
	public Float EcogSPTotal = null;		//Study Partner ECog - Total
	public Float Ventricles = null;		//UCSF Ventricles
	public Float Hippocampus = null;		//UCSF Hippocampus
	public Float WholeBrain = null;		//UCSF WholeBrain
	public Float Entorhinal = null;		//UCSF Entorhinal
	public Float Fusiform = null;			//UCSF Fusiform
	public Float MidTemp = null;			//UCSF Med Temp
	public Float ICV = null;				//UCSF ICV
	public int DX;					//Dx status
	public float M;					//Months from baseline (to nearest 6 months, as continuous)

	static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public boolean equals(Object o){
		if(o==null) return false;
		if(!o.getClass().equals(this.getClass())) return false;
		if(RID==((ADNIMERGE_ENTRY)o).RID  && 
				VISCODE==((ADNIMERGE_ENTRY)o).VISCODE &&
				SITE==((ADNIMERGE_ENTRY)o).SITE) return true;
		return false;
	}
	
	public static ADNIMERGE_ENTRY parseEntry(String line){
		String[] fields = line.split(",");
		if(fields.length < 10) return null;
		ADNIMERGE_ENTRY e = new ADNIMERGE_ENTRY();
		
		e.RID 		= Integer.parseInt(fields[0]);
		e.VISCODE 	= getVisitCode(fields[2]);	
		e.SITE		= Integer.parseInt(fields[3]);
		e.COLPROT	= Protocol.getProtocol(fields[4]).protocol;
		e.ORIGPROT	= Protocol.getProtocol(fields[5]).protocol;
		try {
			e.EXAMDATE 	= dateFormat.parse(fields[6]).getTime();
		} catch (ParseException e1) {e1.printStackTrace(); return null;}
		e.DX_bl		= BaselineDX.getDX(fields[7]).dx;
		e.AGE		= Float.parseFloat(fields[8]);
		e.PTGENDER	= Gender.getGender(fields[9]).gender;
		e.PTEDUCAT	= Integer.parseInt(fields[10]);
		e.PTETHCAT	= Ethnicity.getEthnicity(fields[11]).eth;
		e.PTRACCAT	= Race.getRace(fields[12]).race;
		e.PTMARRY	= MaritalStatus.getMaritalStatus(fields[13]).status;
		if(fields[14]!=null && fields[14].length()>0)
			e.APOE4		= Integer.parseInt(fields[14]);
		if(fields[15]!=null && fields[15].length()>0)
			e.FDG = Float.parseFloat(fields[15]);
		if(fields[16]!=null && fields[16].length()>0)
			e.PIB = Float.parseFloat(fields[16]);
		if(fields[17]!=null && fields[17].length()>0)
			e.AV45 = Float.parseFloat(fields[17]);
		if(fields[18]!=null && fields[18].length()>0)
			e.CDRSB = Float.parseFloat(fields[18]);
		if(fields[19]!=null && fields[19].length()>0)
			e.ADAS11 = Float.parseFloat(fields[19]);
		if(fields[20]!=null && fields[20].length()>0)
			e.ADAS13 = Float.parseFloat(fields[20]);
		if(fields[21]!=null && fields[21].length()>0)
			e.MMSE = Float.parseFloat(fields[21]);
		if(fields[22]!=null && fields[22].length()>0)
			e.RAVLT_immediate = Float.parseFloat(fields[22]);
		if(fields[23]!=null && fields[23].length()>0)
			e.RAVLT_learning = Float.parseFloat(fields[23]);
		if(fields[24]!=null && fields[24].length()>0)
			e.RAVLT_forgetting = Float.parseFloat(fields[24]);
		if(fields[25]!=null && fields[25].length()>0)
			e.RAVLT_perc_forgetting = Float.parseFloat(fields[25]);
		if(fields[26]!=null && fields[26].length()>0)
			e.FAQ = Float.parseFloat(fields[26]);
		if(fields[27]!=null && fields[27].length()>0)
			e.MOCA = Float.parseFloat(fields[27]);
		if(fields[28]!=null && fields[28].length()>0)
			e.EcogPtMem = Float.parseFloat(fields[28]);
		if(fields[29]!=null && fields[29].length()>0)
			e.EcogPtLang = Float.parseFloat(fields[29]);
		if(fields[30]!=null && fields[30].length()>0)
			e.EcogPtVisspat = Float.parseFloat(fields[30]);
		if(fields[31]!=null && fields[31].length()>0)
			e.EcogPtPlan = Float.parseFloat(fields[31]);
		if(fields[32]!=null && fields[32].length()>0)
			e.EcogPtOrgan = Float.parseFloat(fields[32]);
		if(fields[33]!=null && fields[33].length()>0)
			e.EcogPtDivatt = Float.parseFloat(fields[33]);
		if(fields[34]!=null && fields[34].length()>0)
			e.EcogPtTotal = Float.parseFloat(fields[34]);
		if(fields[35]!=null && fields[35].length()>0)
			e.EcogSPMem	= Float.parseFloat(fields[35]);
		if(fields[36]!=null && fields[36].length()>0)
			e.EcogSPLang = Float.parseFloat(fields[36]);
		if(fields[37]!=null && fields[37].length()>0)
			e.EcogSPVisspat = Float.parseFloat(fields[37]);
		if(fields[38]!=null && fields[38].length()>0)
			e.EcogSPPlan = Float.parseFloat(fields[38]);
		if(fields[39]!=null && fields[39].length()>0)
			e.EcogSPOrgan = Float.parseFloat(fields[39]);
		if(fields[40]!=null && fields[40].length()>0)
			e.EcogSPDivatt = Float.parseFloat(fields[40]);
		if(fields[41]!=null && fields[41].length()>0)
			e.EcogSPTotal = Float.parseFloat(fields[41]);
		if(fields[44]!=null && fields[44].length()>0)
			e.Ventricles = Float.parseFloat(fields[44]);
		if(fields[45]!=null && fields[45].length()>0)
			e.Hippocampus = Float.parseFloat(fields[45]);
		if(fields[46]!=null && fields[46].length()>0)
			e.WholeBrain = Float.parseFloat(fields[46]);
		if(fields[47]!=null && fields[47].length()>0)
			e.Entorhinal = Float.parseFloat(fields[47]);
		if(fields[48]!=null && fields[48].length()>0)
			e.Fusiform = Float.parseFloat(fields[48]);
		if(fields[49]!=null && fields[49].length()>0)
			e.MidTemp = Float.parseFloat(fields[49]);
		if(fields[50]!=null && fields[50].length()>0)
			e.ICV = Float.parseFloat(fields[50]);
		e.DX = Diagnostic.getDiagnostic(fields[51]).dx;
		e.M = Float.parseFloat(fields[90]);
		return e;
	}
	
	public static Integer getVisitCode(String v){
		v = v.trim().toLowerCase();
		if(v.equals("bl")) return 0;
		if(v.startsWith("m")){
			return Integer.parseInt(v.substring(1, v.length()));
		}
		System.out.println("Unknown visit code: "+v);
		return null;
	}

	public String getMySQLInsertStatement(String tableName){
		return "INSERT IGNORE INTO "+tableName+" VALUES ('"+
				RID +"','"+ VISCODE +"','"+ SITE +"','"+ COLPROT +"','"+ ORIGPROT +"',"+ 
				getDateString() +",'"+ DX_bl +"','"+ AGE +"','"+ PTGENDER +"','"+ PTEDUCAT +"','"+ PTETHCAT +"','"+ 
				PTRACCAT +"','"+ PTMARRY +"',"+ getStringVal(APOE4) +","+ getStringVal(FDG) +","+ getStringVal(PIB) +","+ 
				getStringVal(AV45) +","+ getStringVal(CDRSB) +","+ getStringVal(ADAS11)	+","+ getStringVal(ADAS13)	+","+ getStringVal(MMSE) +","+ 
				getStringVal(RAVLT_immediate) +","+ getStringVal(RAVLT_learning) +","+ getStringVal(RAVLT_forgetting) +","+ 
				getStringVal(RAVLT_perc_forgetting) +","+ getStringVal(FAQ) +","+ getStringVal(MOCA) +","+ getStringVal(EcogPtMem) +","+ 
				getStringVal(EcogPtLang) +","+ getStringVal(EcogPtVisspat) +","+ getStringVal(EcogPtPlan) +","+ getStringVal(EcogPtOrgan)	+","+ 
				getStringVal(EcogPtDivatt) +","+ getStringVal(EcogPtTotal) +","+ getStringVal(EcogSPMem) +","+ getStringVal(EcogSPLang) +","+ 
				getStringVal(EcogSPVisspat) +","+ getStringVal(EcogSPPlan) +","+ getStringVal(EcogSPOrgan) +","+ getStringVal(EcogSPDivatt) +","+ 
				getStringVal(EcogSPTotal)  +","+ "NULL" +","+  "NULL" +","+ getStringVal(Ventricles) +","+ getStringVal(Hippocampus) +","+ 
				getStringVal(WholeBrain) +","+ getStringVal(Entorhinal) +","+ getStringVal(Fusiform) +","+ getStringVal(MidTemp) +","+ 
				getStringVal(ICV) +","+ getStringVal(DX) +","+  getStringVal(M) + ");";
	}
	
	public String getDateString(){
		return "'"+(new java.sql.Date(EXAMDATE))+" 00:00:00'";
	}
	
	public String getStringVal(Number v){
		if(v==null) return "NULL";
		return "'"+v.toString()+"'";
	}
}
