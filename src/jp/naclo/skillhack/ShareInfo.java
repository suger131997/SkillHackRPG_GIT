package jp.naclo.skillhack;

import java.awt.Graphics2D;

import jp.naclo.skillhack.Loading.Loader;

public class ShareInfo {			//すべてのクラスが共有する情報
	//グラフィックス
	public Graphics2D g;
	//現在のミリ秒数
	public long currenttime;
	//キーステート
	public boolean[] keystate;
	//ローダー
	public Loader myLoader = new Loader();

	//コンストラクタ
	public ShareInfo(){
		this.keystate = new boolean[8];
		for(int i=0; i<8; i++){
			this.keystate[i] = false;
		}
	}
}
