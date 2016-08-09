package jp.naclo.skillhack.title;

import jp.naclo.skillhack.FONT_SIZE;
import jp.naclo.skillhack.ShareInfo;

public class DrawTitleMenu {
	public void drawMunu_Select(ShareInfo sinfo){
		//描画処理
		sinfo.DF.drawFrame(sinfo.g,70, 80, 180, 100);
		sinfo.DSP.drawString(sinfo.g, "はじめから", 100, 100, FONT_SIZE.SMALL);
		sinfo.DSP.drawString(sinfo.g, "つづきから", 100, 118, FONT_SIZE.SMALL);
		sinfo.DSP.drawString(sinfo.g, "けーむをおわる", 100, 136, FONT_SIZE.SMALL);

	}
	public void drawMunu_Select_arrow(ShareInfo sinfo, int cursor_pos){
		//描画処理
		sinfo.g.drawImage(sinfo.arrow, 80, 100 + 18 * (cursor_pos % 3), null);
	}
	public void drawMunu_Select_Start(ShareInfo sinfo, SimpleSaveData[] saveData){
		//描画処理
		drawMunu_Select(sinfo);
		drawMunu_Select_arrow(sinfo,0);
		sinfo.DF.drawFrame(sinfo.g,250, 80, 500, 100);
		sinfo.DSP.drawString(sinfo.g, "ほうけんのしよ1", 280, 100, FONT_SIZE.SMALL);
		sinfo.DSP.drawString(sinfo.g, "ほうけんのしよ2", 280, 118, FONT_SIZE.SMALL);
		sinfo.DSP.drawString(sinfo.g, "ほうけんのしよ3", 280, 136, FONT_SIZE.SMALL);
		for(int i = 0; i < 3; i++){
			if(saveData[i] != null){
				saveData[i].drawSimpleData(sinfo, 450, 100 + i * 18);
			}else{
				sinfo.DSP.drawString(sinfo.g, "はじめから", 450, 100 + i * 18, FONT_SIZE.SMALL);
			}
		}
	}
	public void drawMunu_Select_Start_arrow(ShareInfo sinfo, int cursor_pos){
		//描画処理
		sinfo.g.drawImage(sinfo.arrow, 260, 100 + 18 * (cursor_pos % 3), null);
	}
	public void drawMunu_Select_Continue(ShareInfo sinfo, SimpleSaveData[] saveData){
		//描画処理
		drawMunu_Select(sinfo);
		drawMunu_Select_arrow(sinfo,1);
		sinfo.DF.drawFrame(sinfo.g,250, 98, 500, 100);
		sinfo.DSP.drawString(sinfo.g, "ほうけんのしよ1", 280, 118, FONT_SIZE.SMALL);
		sinfo.DSP.drawString(sinfo.g, "ほうけんのしよ2", 280, 136, FONT_SIZE.SMALL);
		sinfo.DSP.drawString(sinfo.g, "ほうけんのしよ3", 280, 154, FONT_SIZE.SMALL);
		for(int i = 0; i < 3; i++){
			if(saveData[i] != null){
				saveData[i].drawSimpleData(sinfo, 450, 118 + i * 18);
			}else{
				sinfo.DSP.drawString(sinfo.g, "はじめから", 450, 118 + i * 18, FONT_SIZE.SMALL);
			}
		}
	}
	public void drawMunu_Select_Continue_arrow(ShareInfo sinfo, int cursor_pos){
		//描画処理
		sinfo.g.drawImage(sinfo.arrow, 260, 118 + 18 * (cursor_pos % 3), null);
	}
	public void drawMunu_Select_End(ShareInfo sinfo){
		//描画処理
		drawMunu_Select(sinfo);
		drawMunu_Select_arrow(sinfo,2);
		sinfo.DF.drawFrameCenter(sinfo.g,480, 300, 230, 100);
		sinfo.DSP.drawStringCenter(sinfo.g, "ほんとうにおわりますか", 480, 320, FONT_SIZE.SMALL);
		sinfo.DSP.drawString(sinfo.g, "はい", 410, 350, FONT_SIZE.SMALL);
		sinfo.DSP.drawString(sinfo.g, "いいえ", 500, 350, FONT_SIZE.SMALL);
	}
	public void drawMunu_Select_End_arrow(ShareInfo sinfo, int cursor_pos){
		//描画処理
		sinfo.g.drawImage(sinfo.arrow, 390 + 90 * (cursor_pos % 2), 350, null);
	}
}
