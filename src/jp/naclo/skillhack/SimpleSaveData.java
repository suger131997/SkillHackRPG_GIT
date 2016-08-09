package jp.naclo.skillhack;

public class SimpleSaveData {
	private int number;
	private String name;
	private int level;
	private int saveSpot;
	private String saveSpotName;
	private int hour, minute;

	public SimpleSaveData(int number2, String name2, int level2, int spotNumber, String spotName, int hour2, int minute2) {
		number = number2;
		name = name2;
		level = level2;
		saveSpot = spotNumber;
		saveSpotName = spotName;
		hour = hour2;
		minute = minute2;
	}

	public void drawSimpleData(ShareInfo sinfo, int x, int y){
		sinfo.DSP.drawString(sinfo.g,  name + " " + level + " " + saveSpotName + " " + hour + ":" + minute, x, y, FONT_SIZE.SMALL);
	}

	public int getSaveSpot(){
		return saveSpot;
	}
}
