package jp.naclo.skillhack.Sequence;

import jp.naclo.skillhack.KEY_STATE;
import jp.naclo.skillhack.title.TITLE_IMAGE_NUMBER;

public class Title_Select_Start extends BaseSequence {
	int cursor_pos = 30000;

	public Title_Select_Start(BaseSequence parent) {
		mParent = parent;
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
		tm.DTM.drawMunu_Select_Start(r.sinfo, t.saveData);
		tm.DTM.drawMunu_Select_Start_arrow(r.sinfo,cursor_pos);
		if(r.sinfo.keystate[0][KEY_STATE.F_0] == true && r.sinfo.keystate[1][KEY_STATE.F_0] == false){
			if(t.saveData[cursor_pos % 3]==null){
				return new Title_Select_Create(mParent, cursor_pos % 3);
			}else{
				//データが存在したとき
				//return new Title_Select_Delete(mParent, cursor_pos % 3);
			}
		}
		if(r.sinfo.keystate[0][KEY_STATE.R_2] == true && r.sinfo.keystate[1][KEY_STATE.R_2] == false){
			return new Title_Select(tm,30000);
		}
		return this;
	}
	@Override
	protected int myLayerNumber() {
		return 3;
	}
}
