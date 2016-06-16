package jp.naclo.skillhack.Sequence;


public abstract class BaseSequence {
	protected long starttime;						//開始時間

	//開始時間設定
	public void setStartTime(long st){
		this.starttime = st;
	}

	protected BaseSequence mChild;
	protected BaseSequence mParent;

	//このディスプレイを表示
	public abstract BaseSequence show();
	//自分の階層番号を返す
	protected abstract int myLayerNumber();
}
