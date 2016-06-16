package jp.naclo.skillhack.Sequence;

import jp.naclo.skillhack.DrawStringPict;
import jp.naclo.skillhack.ShareInfo;

public class RootSequence extends BaseSequence{
	//文字を書くクラス
	protected DrawStringPict DSP = null;
	public ShareInfo sinfo;
	public RootSequence(){}
	public RootSequence(BaseSequence parent){
		//mChild = null;
		sinfo = new ShareInfo();
		mChild = new Title(this);
	}
	@Override
	public BaseSequence show() {
		/*if(mChild == null){
			mChild = new Title(this);
		}*/
		BaseSequence next;
		next = mChild.show();
		if(next != mChild){
			mChild = next;
		}
		return null;
	}

	@Override
	protected int myLayerNumber() {
		return 0;
	}

}
