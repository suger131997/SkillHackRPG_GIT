package jp.naclo.skillhack.Sequence;

import jp.naclo.skillhack.KEY_STATE;
import jp.naclo.skillhack.title.TITLE_IMAGE_NUMBER;

public class Title_Select extends BaseSequence {
	int cursor_pos = 30000;

	public Title_Select(BaseSequence parent, int cursor) {
		mParent = parent;
		Title_Menu tm =((Title_Menu)mParent);
		Title t =((Title)tm.mParent);
		RootSequence r = ((RootSequence)t.mParent);
		//ロード時間の設定
		r.sinfo.loadSecond = 0;
		cursor_pos = cursor;
	}

	@Override
	public BaseSequence show() {
		Title_Menu tm =((Title_Menu)mParent);
		Title t =((Title)tm.mParent);
		RootSequence r = ((RootSequence)t.mParent);

		//カーソルの移動タイミングの調整
		if(r.sinfo.keystate[0][KEY_STATE.DOWN_S] == true && r.sinfo.keystate[1][KEY_STATE.DOWN_S] == false){
			cursor_pos++;
		}
		if(r.sinfo.keystate[0][KEY_STATE.UP_W] == true && r.sinfo.keystate[1][KEY_STATE.UP_W] == false){
			cursor_pos--;
		}

		if( cursor_pos < 0 ){
			cursor_pos = 30000;
		}


		//描画処理
		r.sinfo.g.drawImage(t.titleImages[TITLE_IMAGE_NUMBER.TITLE_MENU], 0, 0, null);
		tm.DTM.drawMunu_Select(r.sinfo);
		tm.DTM.drawMunu_Select_arrow(r.sinfo,cursor_pos);

		if(r.sinfo.keystate[0][KEY_STATE.F_0] == true && r.sinfo.keystate[1][KEY_STATE.F_0] == false){
			if(cursor_pos % 3 ==0){
				return new Title_Select_Start(mParent);
			}else if(cursor_pos % 3 == 1){
				return new Title_Select_Continue(mParent);
			}else if(cursor_pos % 3 == 2){
				return new Title_Select_End(mParent);
			}

		}
		if(r.sinfo.keystate[0][KEY_STATE.R_2] == true && r.sinfo.keystate[1][KEY_STATE.R_2] == false){
			return new Title(r);
		}
		return this;

	}
	@Override
	protected int myLayerNumber() {
		return 3;
	}
}

