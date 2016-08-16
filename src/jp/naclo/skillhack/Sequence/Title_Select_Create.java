package jp.naclo.skillhack.Sequence;

import jp.naclo.skillhack.Save.SaveFile_SimpleSaveData;


public class Title_Select_Create  extends BaseSequence {
	int dataNum;
	int target;
	SaveFile_SimpleSaveData simple_Saver;
	public Title_Select_Create(BaseSequence parent, int dataNum, int target) {
		mParent = parent;
		Title_Demo td =((Title_Demo)mParent);
		Title t =((Title)td.mParent);
		RootSequence r = ((RootSequence)t.mParent);
		//ロード時間の設定
		r.sinfo.loadSecond = 10;
	}

	@Override
	public BaseSequence show() {
		Title_Menu tm =((Title_Menu)mParent);
		Title t =((Title)tm.mParent);
		RootSequence r = ((RootSequence)t.mParent);

		//新規データ作成

		//ロードシーケンスへ

		return this;
	}
	@Override
	protected int myLayerNumber() {
		return 3;
	}
}
