package jp.naclo.skillhack.Sequence;

import jp.naclo.skillhack.DrawStringPict;
import jp.naclo.skillhack.FONT_SIZE;
import jp.naclo.skillhack.Loading.SuperLoadFile;

public class Title_Load extends Title_Demo {
	SuperLoadFile loadDSP;
	public Title_Load(BaseSequence parent) {
		mParent = parent;
		Title_Demo td =((Title_Demo)mParent);
		Title t =((Title)td.mParent);
		RootSequence r = ((RootSequence)t.mParent);
		loadDSP = r.sinfo.myLoader.createFile("fontfile", "FontsData.xml", false);
	}

	@Override
	public BaseSequence show() {
		Title_Demo td =((Title_Demo)mParent);
		Title t =((Title)td.mParent);
		RootSequence r = ((RootSequence)t.mParent);
		if(loadDSP != null){
			if(loadDSP.isReady()){
				r.DSP = (DrawStringPict)loadDSP.getData();
				loadDSP = null;
			}
		}


		if(loadDSP == null){
			r.DSP.drowString(r.sinfo.g, "わたしのこと", 100, 100, FONT_SIZE.SMALL);
			r.DSP.drowString(r.sinfo.g, "わすれたの", 210, 125, FONT_SIZE.SMALL);
		}
		return this;
	}
	@Override
	protected int myLayerNumber() {
		return 3;
	}
}
