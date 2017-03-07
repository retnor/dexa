package kempot.trialcode;

public class NetsProperties {
	//2states
	//public static String[][][] isic = new String[5][3][2];
	//public static String[][][] isic = new String[4][3][2];
	//public static String[][][] isic = new String[3][3][2];
	
	//3states
	public static String[][][] isic = new String[5][4][2];
	//public static String[][][] isic = new String[4][4][2];
	//public static String[][][] isic = new String[3][4][2];
	
	public NetsProperties(){
		isic[0][0][0]="EyeColor";
		isic[0][1][0]="Green";
		isic[0][2][0]="Blue";
		isic[0][3][0]="Grey";
		isic[1][0][0]="SkinColor";
		isic[1][1][0]="Black";
		isic[1][2][0]="White";
		isic[1][3][0]="Brown";
		isic[2][0][0]="Race";
		isic[2][1][0]="Asian";
		isic[2][2][0]="Caucasian";
		isic[2][3][0]="Aborigine";
		isic[3][0][0]="TongueTie";
		isic[3][1][0]="Yes";
		isic[3][2][0]="No";
		isic[3][3][0]="NotAvailable";
		isic[4][0][0]="Gender";
		isic[4][1][0]="Male";
		isic[4][2][0]="Female";
		isic[4][3][0]="NotAvailable";

		isic[0][0][1]=null;
		isic[0][1][1]="0.02";
		isic[0][2][1]="0.98";
//		isic[0][3][1]="0.04";
		isic[1][0][1]=null;
		isic[1][1][1]="0.2";
		isic[1][2][1]="0.8";
//		isic[1][3][1]="0.7";
		isic[2][0][1]=null;
		isic[2][1][1]="0.15";
		isic[2][2][1]="0.85";
//		isic[2][3][1]="0.2";
		isic[3][0][1]=null;
		isic[3][1][1]="0.45";
		isic[3][2][1]="0.55";
//		isic[3][3][1]="0.20";
		isic[4][0][1]=null;
		isic[4][1][1]="0.35";
		isic[4][2][1]="0.65";
//		isic[4][3][1]="0.17";
	}
}
