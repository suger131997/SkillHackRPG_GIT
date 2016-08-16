package jp.naclo.skillhack;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import jp.naclo.skillhack.Load.Loader;

public class ShareInfo {			//すべてのクラスが共有する情報
	//グラフィックス
	public Graphics2D g;
	//現在のミリ秒数
	public long currenttime;
	//ロード時間
	public short loadSecond = 0;
	//キーステート
	public boolean[][] keystate;	//0:現フレーム 1:前フレーム
	//ローダー
	public Loader myLoader = new Loader();
	//枠
	public DrawFrame DF;
	//矢印
	public BufferedImage arrow;
	//文字を書くクラス
	public DrawStringPict DSP = null;
	//コンストラクタ
	public ShareInfo(){
		this.keystate = new boolean[2][];
		for(int i=0; i < 2; i++){
			this.keystate[i] = new boolean[KEY_STATE.NUMBER];
		}
		for(int j=0; j < 2; j++){
			for(int i=0; i < KEY_STATE.NUMBER; i++){
				this.keystate[j][i] = false;
			}
		}
	}
}
