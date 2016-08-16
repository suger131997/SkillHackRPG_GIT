package jp.naclo.skillhack.Load;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


public class Loader {		//ロードを管理するクラス
	private LinkedList<SuperLoadFile> LoadingFiles = new LinkedList<SuperLoadFile>();

	public SuperLoadFile createFile(String profile, String fileName, boolean insert){
		SuperLoadFile myFile = null;
		/*引数のprofileによって作成するインスタンスを変える*/

		switch(profile){
			case "fontfile":
				myFile = new LoadFile_DrawStringPict(fileName);
				break;
			case "frame":
				myFile = new LoadFile_DrawFrame(null);
				break;
			case "imagefile":
				myFile = new LoadFile_Image(fileName);
				break;
			case "simplesavedatafile":
				myFile = new LoadFile_SimpleSaveData(fileName);
				break;
		}

		if(insert){		//割り込みロード
			LoadingFiles.addFirst(myFile);
		}else{
			LoadingFiles.add(myFile);
		}

		return myFile;
	}

	public boolean loadFile(Loader myLoader){					//ファイルを頭からロードする
		if(LoadingFiles.isEmpty()){				//ロードするファイルがなければスルーしロード終了
			return false;
		}
		SuperLoadFile nowLoadFile = LoadingFiles.remove();
		if(nowLoadFile.cancel){					//キャンセルされていたらスルー
			return true;
		}


			try {
				nowLoadFile.FileConverter(myLoader);
			} catch (IOException | SAXException | ParserConfigurationException | URISyntaxException e) {
				JOptionPane.showMessageDialog(null,
						"ファイル読み込みエラー");
			}


		return true;
	}

	public static Object loadTry(SuperLoadFile targetFile){
		Object myData = null;
		if(targetFile != null){
			if(targetFile.isReady()){
				myData = targetFile.getData();
			}
		}
		return myData;
	}
}
