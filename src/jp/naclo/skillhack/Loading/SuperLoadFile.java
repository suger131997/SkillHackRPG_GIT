package jp.naclo.skillhack.Loading;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public abstract class SuperLoadFile {
	protected Object myData = null;

	protected String fileName;

	public boolean cancel = false;

	public boolean isReady(){
		return (myData != null);
	}

	public Object getData(){
		return myData;
	}

	public void loadCancel(){
		cancel = true;
	}

	public SuperLoadFile(String fName){
		this.fileName = fName;
	}
	abstract public void FileConverter() throws IOException, SAXException, ParserConfigurationException, URISyntaxException;		//ファイルをそれぞれのインスタンスに
}
