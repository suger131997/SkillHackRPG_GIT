package jp.naclo.skillhack.Sequence;


public abstract class BaseSequence {
	protected BaseSequence mChild;
	protected BaseSequence mParent;

	//このディスプレイを表示
	public abstract BaseSequence show();
	//自分の階層番号を返す
	protected abstract int myLayerNumber();
}
