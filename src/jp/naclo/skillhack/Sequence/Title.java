package jp.naclo.skillhack.Sequence;

import java.awt.image.BufferedImage;


public class Title extends RootSequence{
	protected BufferedImage titleImages[];
	public Title(){}
	public Title(BaseSequence parent){
		mParent = parent;
		mChild = new Title_Demo(this);
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
		return 1;
	}

}
