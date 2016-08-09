package jp.naclo.skillhack.Sequence;

import jp.naclo.skillhack.KEY_STATE;
import jp.naclo.skillhack.title.TITLE_IMAGE_NUMBER;

public class Title_Select_End  extends BaseSequence {
	int cursor_pos = 30000;

	public Title_Select_End(BaseSequence parent) {
		mParent = parent;
	}

	@Override
	public BaseSequence show() {
		Title_Menu tm =((Title_Menu)mParent);
		Title t =((Title)tm.mParent);
		RootSequence r = ((RootSequence)t.mParent);

		//カーソルの移動タイミングの調整
		if(r.sinfo.keystate[0][KEY_STATE.RIGHT_D] == true && r.sinfo.keystate[1][KEY_STATE.RIGHT_D] == false){
			cursor_pos++;
		}
		if(r.sinfo.keystate[0][KEY_STATE.LEFT_A] == true && r.sinfo.keystate[1][KEY_STATE.LEFT_A] == false){
			cursor_pos--;
		}

		if( cursor_pos < 0 ){
			cursor_pos = 30000;
		}


		//描画処理
		r.sinfo.g.drawImage(t.titleImages[TITLE_IMAGE_NUMBER.TITLE_MENU], 0, 0, null);
		tm.DTM.drawMunu_Select_End(r.sinfo);
		tm.DTM.drawMunu_Select_End_arrow(r.sinfo,cursor_pos);
		if(r.sinfo.keystate[0][KEY_STATE.F_0] == true && r.sinfo.keystate[1][KEY_STATE.F_0] == false){
			if(cursor_pos % 2 == 0){
				System.exit(0);
			}else if(cursor_pos % 2 == 1){
				return new Title_Select(tm,10001);
			}
		}
		if(r.sinfo.keystate[0][KEY_STATE.R_2] == true && r.sinfo.keystate[1][KEY_STATE.R_2] == false){
			return new Title_Select(tm,10001);
		}
		return this;
	}
	@Override
	protected int myLayerNumber() {
		return 3;
	}
}
