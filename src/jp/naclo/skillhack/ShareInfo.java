package jp.naclo.skillhack;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import jp.naclo.skillhack.Loading.Loader;

public class ShareInfo {			//すべてのクラスが共有する情報
	//グラフィックス
	public Graphics2D g;
	//現在のミリ秒数
	public long currenttime;
	//ロード時間
	public short loadSecond = 0;
	//キーステート
	public boolean[] keystate;
	//ローダー
	public Loader myLoader = new Loader();
	//枠
	public BufferedImage frame;
	//矢印
	public BufferedImage arrow;
	//コンストラクタ
	public ShareInfo(){
		this.keystate = new boolean[KEY_STATE.NUMBER];
		for(int i=0; i<KEY_STATE.NUMBER; i++){
			this.keystate[i] = false;
		}
	}
}
