package jp.naclo.skillhack.Sequence;

import jp.naclo.skillhack.FONT_SIZE;
import jp.naclo.skillhack.KEY_STATE;
import jp.naclo.skillhack.TITLE_IMAGE_NUMBER;

public class Title_Wait extends BaseSequence {

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


		//描画処理
		r.sinfo.g.drawImage(t.titleImages[TITLE_IMAGE_NUMBER.TITLE], 0, 0, null);
		r.DSP.drowStringCenter(r.sinfo.g, "すぺーすではじめる", t.titleImages[TITLE_IMAGE_NUMBER.TITLE].getWidth() / 2, 500, FONT_SIZE.SMALL);

		if(r.sinfo.keystate[0][KEY_STATE.SPACE] == true && r.sinfo.keystate[1][KEY_STATE.SPACE] == false){
			return new Title_Menu(t);
		}
		return this;

	}
	@Override
	protected int myLayerNumber() {
		return 3;
	}
}