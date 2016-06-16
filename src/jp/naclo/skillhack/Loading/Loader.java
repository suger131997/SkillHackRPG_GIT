package jp.naclo.skillhack.Loading;

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
		}

		if(insert){		//割り込みロード
			LoadingFiles.addFirst(myFile);
		}else{
			LoadingFiles.add(myFile);
		}

		return myFile;
	}

	public boolean loadFile(){					//ファイルを頭からロードする
		if(LoadingFiles.isEmpty()){				//ロードするファイルがなければスルーしロード終了
			return false;
		}
		SuperLoadFile nowLoadFile = LoadingFiles.remove();
		if(nowLoadFile.cancel){					//キャンセルされていたらスルー
			return true;
		}


			try {
				nowLoadFile.FileConverter();
			} catch (IOException | SAXException | ParserConfigurationException | URISyntaxException e) {
				JOptionPane.showMessageDialog(null,
						"ファイル読み込みエラー");
			}


		return true;
	}
}
