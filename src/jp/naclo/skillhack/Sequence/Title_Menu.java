package jp.naclo.skillhack.Sequence;

import jp.naclo.skillhack.DrawTitleMenu;

public class Title_Menu extends BaseSequence{
	protected DrawTitleMenu DTM = new DrawTitleMenu();
	public Title_Menu(BaseSequence parent) {
		mParent = parent;
		mChild = new Title_Select(this, 30000);
	}
	@Override
	public BaseSequence show() {
		BaseSequence next;
		next = mChild.show();

		//階層間移動
		if((this.myLayerNumber()+1) != next.myLayerNumber()){
			return next;
		}
		//階層内移動
		if(next != mChild){
			mChild = next;
		}

		return this;
	}
	@Override
	protected int myLayerNumber() {
		// TODO 自動生成されたメソッド・スタブ
		return 2;
	}
}
