package kempot.trialcode;

public class NetsProperties {
	//2states
	public static String[][][] isic = new String[14][3][2];
	//public static String[][][] isic = new String[4][3][2];
	//public static String[][][] isic = new String[3][3][2];
	
	//3states
	//public static String[][][] isic = new String[5][4][2];
	//public static String[][][] isic = new String[4][4][2];
	//public static String[][][] isic = new String[3][4][2];
	
	public NetsProperties(){
		isic[0][0][0]="EyeColor";
		isic[0][1][0]="Green";
		isic[0][2][0]="Blue";
//		isic[0][3][0]="Grey";
//		isic[0][4][0]="Blackish";
//		isic[0][5][0]="Brown";
		isic[1][0][0]="SkinColor";
		isic[1][1][0]="Black";
		isic[1][2][0]="White";
//		isic[1][3][0]="Brown";
//		isic[1][4][0]="Coal";
//		isic[1][5][0]="Red";
		isic[2][0][0]="Race";
		isic[2][1][0]="Asian";
		isic[2][2][0]="Caucasian";
//		isic[2][3][0]="Aborigine";
//		isic[2][4][0]="African";
//		isic[2][5][0]="Australasian";
		isic[3][0][0]="TongueTie";
		isic[3][1][0]="Yes";
		isic[3][2][0]="No";
//		isic[3][3][0]="Mild";
//		isic[3][4][0]="NotAvailable";
//		isic[3][5][0]="PreferredNotToSay";
		isic[4][0][0]="Gender";
		isic[4][1][0]="Male";
		isic[4][2][0]="Female";
//		isic[4][3][0]="NotAvailable";
//		isic[4][4][0]="Unknown";
//		isic[4][5][0]="PreferredNotToSay";
		isic[5][0][0]="Occupation";
		isic[5][1][0]="Teacher";
		isic[5][2][0]="Farmer";
//		isic[5][3][0]="Armies";
//		isic[5][4][0]="NotAvailable";
//		isic[5][5][0]="BabySitters";
		isic[6][0][0]="HavingFlu";
		isic[6][1][0]="None";
		isic[6][2][0]="High";
//		isic[6][3][0]="Low";
//		isic[6][4][0]="Declining";
//		isic[6][5][0]="Unknown";
		isic[7][0][0]="Season";
		isic[7][1][0]="Winter";
		isic[7][2][0]="Summer";
//		isic[7][3][0]="Spring";
//		isic[7][4][0]="Fall";
//		isic[7][5][0]="Unknown";
		isic[8][0][0]="Windy";
		isic[8][1][0]="Medium";
		isic[8][2][0]="Low";
//		isic[8][3][0]="No";
//		isic[8][4][0]="High";
//		isic[8][5][0]="Mild";
		isic[9][0][0]="Temperature";
		isic[9][1][0]="Hot";
		isic[9][2][0]="Cold";
//		isic[9][3][0]="Moderate";
//		isic[9][4][0]="Unknown";
//		isic[9][5][0]="Warm";
		isic[10][0][0]="Humidity";
		isic[10][1][0]="High";
		isic[10][2][0]="Mediocre";
//		isic[10][3][0]="Low";
//		isic[10][4][0]="None";
//		isic[10][5][0]="Humid";
		isic[11][0][0]="BloodGroup";
		isic[11][1][0]="A";
		isic[11][2][0]="AB";
//		isic[11][3][0]="B";
		isic[12][0][0]="SexOrientation";
		isic[12][1][0]="MSM";
		isic[12][2][0]="NonMSM";
//		isic[12][3][0]="NotAvailable";
		isic[13][0][0]="LocationToFarm";
		isic[13][1][0]="Close";
		isic[13][2][0]="Far";
//		isic[13][3][0]="Unknown";

		isic[0][0][1]=null;
		isic[0][1][1]="0.23";
		isic[0][2][1]="0.77";
//		isic[0][3][1]="0.11";
//		isic[0][4][1]="0.1";
//		isic[0][5][1]="0.1";
		isic[1][0][1]=null;
		isic[1][1][1]="0.2";
		isic[1][2][1]="0.8";
//		isic[1][3][1]="0.3";
//		isic[1][4][1]="0.1";
//		isic[1][5][1]="0.1";
		isic[2][0][1]=null;
		isic[2][1][1]="0.16";
		isic[2][2][1]="0.84";
//		isic[2][3][1]="0.12";
//		isic[2][4][1]="0.01";
//		isic[2][5][1]="0.01";
		isic[3][0][1]=null;
		isic[3][1][1]="0.55";
		isic[3][2][1]="0.45";
//		isic[3][3][1]="0.12";
//		isic[3][4][1]="0.11";
//		isic[3][5][1]="0.01";
		isic[4][0][1]=null;
		isic[4][1][1]="0.47";
		isic[4][2][1]="0.53";
//		isic[4][3][1]="0.12";
//		isic[4][4][1]="0.02";
//		isic[4][5][1]="0.01";
		isic[5][0][1]=null;
		isic[5][1][1]="0.36";
		isic[5][2][1]="0.64";
//		isic[5][3][1]="0.11";
//		isic[5][4][1]="0.01";
//		isic[5][5][1]="0.01";
		isic[6][0][1]=null;
		isic[6][1][1]="0.24";
		isic[6][2][1]="0.76";
//		isic[6][3][1]="0.21";
//		isic[6][4][1]="0.2";
//		isic[6][5][1]="0.1";
		isic[7][0][1]=null;
		isic[7][1][1]="0.25";
		isic[7][2][1]="0.75";
//		isic[7][3][1]="0.12";
//		isic[7][4][1]="0.02";
//		isic[7][5][1]="0.01";
		isic[8][0][1]=null;
		isic[8][1][1]="0.25";
		isic[8][2][1]="0.75";
//		isic[8][3][1]="0.21";
//		isic[8][4][1]="0.2";
//		isic[8][5][1]="0.1";
		isic[9][0][1]=null;
		isic[9][1][1]="0.47";
		isic[9][2][1]="0.53";
//		isic[9][3][1]="0.12";
//		isic[9][4][1]="0.02";
//		isic[9][5][1]="0.01";
		isic[10][0][1]=null;
		isic[10][1][1]="0.25";
		isic[10][2][1]="0.75";
//		isic[10][3][1]="0.21";
//		isic[10][4][1]="0.1";
//		isic[10][5][1]="0.1";
		isic[11][0][1]=null;
		isic[11][1][1]="0.45";
		isic[11][2][1]="0.55";
//		isic[11][3][1]="0.1";
		isic[12][0][1]=null;
		isic[12][1][1]="0.35";
		isic[12][2][1]="0.65";
//		isic[12][3][1]="0.01";
		isic[13][0][1]=null;
		isic[13][1][1]="0.45";
		isic[13][2][1]="0.55";
//		isic[13][3][1]="0.1";
	}
}
