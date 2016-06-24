package jp.naclo.skillhack.Sequence;

import jp.naclo.skillhack.FONT_SIZE;
import jp.naclo.skillhack.KEY_STATE;
import jp.naclo.skillhack.TITLE_IMAGE_NUMBER;

public class Title_Wait extends Title_Demo {
	int cursor_pos = 0;
	int cursor_pos_change = 0;

	public Title_Wait(BaseSequence parent) {
		mParent = parent;
		Title_Demo td =((Title_Demo)mParent);
		Title t =((Title)td.mParent);
		RootSequence r = ((RootSequence)t.mParent);
		//ロード時間の設定
		r.sinfo.loadSecond = 0;
	}

	@Override
	public BaseSequence show() {
		Title_Demo td =((Title_Demo)mParent);
		Title t =((Title)td.mParent);
		RootSequence r = ((RootSequence)t.mParent);


		r.sinfo.g.drawImage(t.titleImages[TITLE_IMAGE_NUMBER.TITLE], 0, 0, null);
		if(r.sinfo.keystate[KEY_STATE.SPACE] == true){
			System.exit(0);
		}

		//カーソルの移動タイミングの調整
		if(r.sinfo.keystate[KEY_STATE.DOWN_S] == true){
			cursor_pos_change++;

		}
		if(r.sinfo.keystate[KEY_STATE.UP_W] == true){
			cursor_pos_change--;
		}
		if((r.sinfo.keystate[KEY_STATE.DOWN_S] == false && r.sinfo.keystate[KEY_STATE.UP_W] == false)){
			cursor_pos_change = 0;
		}
		if( cursor_pos_change < -4 ){
			cursor_pos--;
			cursor_pos_change = 0;
			if(cursor_pos < 0){
				cursor_pos = 9999;
			}
		}else if( cursor_pos_change > 4){
			cursor_pos++;
			cursor_pos_change = 0;
		}


		//描画処理
		r.sinfo.g.drawImage(r.sinfo.arrow, 82, 100 + cursor_pos % 2 * 25, null);
		r.DSP.drowString(r.sinfo.g, "はしめから", 100, 100, FONT_SIZE.SMALL);
		r.DSP.drowString(r.sinfo.g, "つつきから", 100, 125, FONT_SIZE.SMALL);

		return this;
	}
	@Override
	protected int myLayerNumber() {
		return 3;
	}
}