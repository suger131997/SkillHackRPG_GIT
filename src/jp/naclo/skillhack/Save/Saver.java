package jp.naclo.skillhack.Save;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import jp.naclo.skillhack.Load.LoadFile_DrawStringPict;
import jp.naclo.skillhack.Load.LoadFile_SimpleSaveData;
import jp.naclo.skillhack.Load.Loader;
import jp.naclo.skillhack.Load.SuperLoadFile;

import org.xml.sax.SAXException;

public class Saver {
	private LinkedList<SuperLoadFile> savingFiles = new LinkedList<SuperLoadFile>();

	public SuperLoadFile createFile(String profile, String fileName){
		SuperLoadFile myFile = null;
		/*引数のprofileによって作成するインスタンスを変える*/

		switch(profile){
			case "fontfile":
				myFile = new LoadFile_DrawStringPict(fileName);
				break;
			case "simplesavedatafile":
				myFile = new LoadFile_SimpleSaveData(fileName);
				break;
		}

		savingFiles.add(myFile);

		return myFile;
	}

	public boolean saveFile(Loader myLoader){					//ファイルを頭からロードする
		if(savingFiles.isEmpty()){				//ロードするファイルがなければスルーしロード終了
			return false;
		}
		SuperLoadFile nowLoadFile = savingFiles.remove();
		if(nowLoadFile.cancel){					//キャンセルされていたらスルー
			return true;
		}

		try {
			nowLoadFile.FileConverter(myLoader);
		} catch (IOException | SAXException | ParserConfigurationException | URISyntaxException e) {
			JOptionPane.showMessageDialog(null,"ファイル読み込みエラー");
		}

		return true;
	}
}
