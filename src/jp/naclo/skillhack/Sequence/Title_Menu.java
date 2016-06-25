package jp.naclo.skillhack.Sequence;

public class Title_Menu extends BaseSequence{

	public Title_Menu(BaseSequence parent) {
		mParent = parent;
		mChild = new Title_Select(this);
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
