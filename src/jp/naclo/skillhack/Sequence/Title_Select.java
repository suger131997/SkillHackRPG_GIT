package jp.naclo.skillhack.Sequence;

import jp.naclo.skillhack.FONT_SIZE;
import jp.naclo.skillhack.KEY_STATE;
import jp.naclo.skillhack.TITLE_IMAGE_NUMBER;

public class Title_Select extends BaseSequence {
	int cursor_pos = 0;
	int cursor_pos_change = 0;

	public Title_Select(BaseSequence parent) {
		mParent = parent;
		Title_Menu tm =((Title_Menu)mParent);
		Title t =((Title)tm.mParent);
		RootSequence r = ((RootSequence)t.mParent);
		//ロード時間の設定
		r.sinfo.loadSecond = 0;
	}

	@Override
	public BaseSequence show() {
		Title_Menu tm =((Title_Menu)mParent);
		Title t =((Title)tm.mParent);
		RootSequence r = ((RootSequence)t.mParent);

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
		r.sinfo.g.drawImage(t.titleImages[TITLE_IMAGE_NUMBER.TITLE_MENU], 0, 0, null);
		
		r.sinfo.g.drawImage(r.sinfo.arrow, 80, 100 + 18 * (cursor_pos % 3), null);
		r.DSP.drowString(r.sinfo.g, "はじめから", 100, 100, FONT_SIZE.SMALL);
		r.DSP.drowString(r.sinfo.g, "つづきから", 100, 118, FONT_SIZE.SMALL);
		r.DSP.drowString(r.sinfo.g, "けーむをおわる", 100, 136, FONT_SIZE.SMALL);

		if(r.sinfo.keystate[KEY_STATE.ESC] == true){
			System.exit(0);
		}

		return this;

	}
	@Override
	protected int myLayerNumber() {
		return 3;
	}
}

