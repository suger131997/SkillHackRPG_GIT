package jp.naclo.skillhack.Save;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public abstract class SuperSaveFile {
	protected boolean saveCheck = false;

	protected String fileName;

	public boolean isCheck(){
		return saveCheck;
	}

	public SuperSaveFile(String fName){
		this.fileName = fName;
	}
	abstract public void FileConverter(Saver mySaver) throws IOException, SAXException, ParserConfigurationException, URISyntaxException;		//ファイルをそれぞれのインスタンスに
}
