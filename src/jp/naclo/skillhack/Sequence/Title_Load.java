package jp.naclo.skillhack.Sequence;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import jp.naclo.skillhack.DrawFrame;
import jp.naclo.skillhack.DrawStringPict;
import jp.naclo.skillhack.KEY_STATE;
import jp.naclo.skillhack.SimpleSaveData;
import jp.naclo.skillhack.TITLE_IMAGE_NUMBER;
import jp.naclo.skillhack.Loading.Loader;
import jp.naclo.skillhack.Loading.SuperLoadFile;

public class Title_Load extends BaseSequence {
	SuperLoadFile loadDSP;
	SuperLoadFile loadFrame;
	SuperLoadFile loadArrowImage;
	SuperLoadFile loadTitleImage;
	SuperLoadFile loadTitleMuneImage;
	SuperLoadFile loadSimpleSaveData;

	BufferedImage titleLoadImage;
	public Title_Load(BaseSequence parent) {
		mParent = parent;
		Title_Demo td =((Title_Demo)mParent);
		Title t =((Title)td.mParent);
		RootSequence r = ((RootSequence)t.mParent);
		//ロード時間の設定
		r.sinfo.loadSecond = 10;
		//ロード予約
		//全体で使う
		loadDSP = r.sinfo.myLoader.createFile("fontfile", "FontsData.xml", false);
		loadFrame = r.sinfo.myLoader.createFile("frame", null, false);
		loadArrowImage = r.sinfo.myLoader.createFile("imagefile", "data/common/img/Arrow.png", false);
		//タイトルのみで使う
		t.titleImages = new BufferedImage[TITLE_IMAGE_NUMBER.COUNT];
		loadTitleImage = r.sinfo.myLoader.createFile("imagefile", "data/title/img/title.png", false);
		loadTitleMuneImage = r.sinfo.myLoader.createFile("imagefile", "data/title/img/title_munu.png", false);
		//セーブデータ簡易版
		t.saveData = new SimpleSaveData[3];
		loadSimpleSaveData = r.sinfo.myLoader.createFile("simplesavedatafile", "SimpleSaveData.xml", false);
		URL Imgurl = getClass().getClassLoader().getResource("data/title/img/title_load.png");
		try {
			titleLoadImage = ImageIO.read(Imgurl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public BaseSequence show() {
		Title_Demo td =((Title_Demo)mParent);
		Title t =((Title)td.mParent);
		RootSequence r = ((RootSequence)t.mParent);
		//一番素直
		/*if(loadDSP != null){
			if(loadDSP.isReady()){
				r.DSP = (DrawStringPict)loadDSP.getData();
				loadDSP = null;
			}
		}
		if(loadFrameImage != null){
			if(loadFrameImage.isReady()){
				r.sinfo.frame = (BufferedImage)loadFrameImage.getData();
				loadFrameImage = null;
			}
		}
		if(loadArrowImage != null){
			if(loadArrowImage.isReady()){
				r.sinfo.arrow = (BufferedImage)loadArrowImage.getData();
				loadArrowImage = null;
			}
		}*/

		//若干凝ってる
		/*Object myLoadData = null;
		myLoadData = Loader.loadTry(loadDSP);
		if(myLoadData != null){
			r.DSP = (DrawStringPict) myLoadData;
			myLoadData = null;
			loadDSP = null;
		}
		myLoadData = Loader.loadTry(loadFrameImage);
		if(myLoadData != null){
			r.sinfo.frame = (BufferedImage) myLoadData;
			myLoadData = null;
			loadFrameImage = null;
		}
		myLoadData = Loader.loadTry(loadArrowImage);
		if(myLoadData != null){
			r.sinfo.arrow = (BufferedImage) myLoadData;
			myLoadData = null;
			loadArrowImage = null;
		}*/

		//限界
		Object myLoadData;
		if((myLoadData =  Loader.loadTry(loadDSP)) != null){
			r.sinfo.DSP = (DrawStringPict) myLoadData;
			loadDSP = null;
		}
		if((myLoadData = Loader.loadTry(loadFrame)) != null){
			r.sinfo.DF = (DrawFrame)myLoadData;
			loadFrame = null;
		}
		if((myLoadData = (BufferedImage) Loader.loadTry(loadArrowImage)) != null){
			r.sinfo.arrow = (BufferedImage) myLoadData;
			loadArrowImage = null;
		}
		if((myLoadData = (BufferedImage) Loader.loadTry(loadTitleImage)) != null){
			t.titleImages[TITLE_IMAGE_NUMBER.TITLE] = (BufferedImage) myLoadData;
			loadTitleImage = null;
		}
		if((myLoadData = (BufferedImage) Loader.loadTry(loadTitleMuneImage)) != null){
			t.titleImages[TITLE_IMAGE_NUMBER.TITLE_MENU] = (BufferedImage) myLoadData;
			loadTitleMuneImage = null;
		}

		if((myLoadData = (ArrayList<SimpleSaveData>) Loader.loadTry(loadSimpleSaveData)) != null){
			for(int i = 0; i < 3; i++){
				t.saveData[i] = ((ArrayList<SimpleSaveData>) myLoadData).get(i);
			}
			loadSimpleSaveData = null;
		}
		r.sinfo.g.drawImage(titleLoadImage, 0, 0, null);
		if(r.sinfo.keystate[1][KEY_STATE.ESC] == false && r.sinfo.keystate[0][KEY_STATE.ESC] == true){
			System.exit(0);
		}



		if(loadDSP == null && loadFrame == null && loadArrowImage == null && loadTitleImage == null && loadTitleMuneImage == null && loadSimpleSaveData == null){
			return new Title_Wait(mParent);
		}
		return this;
	}
	@Override
	protected int myLayerNumber() {
		return 3;
	}
}
