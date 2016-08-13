package jp.naclo.skillhack.Sequence;


public class Title_Select_Create  extends BaseSequence {
	int cursor_pos = 30000;

	public Title_Select_Create(BaseSequence parent) {
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
