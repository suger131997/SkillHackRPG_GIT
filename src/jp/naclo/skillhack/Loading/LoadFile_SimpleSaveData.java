package jp.naclo.skillhack.Loading;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jp.naclo.skillhack.title.SimpleSaveData;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LoadFile_SimpleSaveData extends SuperLoadFile{

	public LoadFile_SimpleSaveData(String fName) {
		super(fName);
	}

	/*
     * XML仕様
     * <profile>:ファイルの種類(属性type = simplesavedatafile)
     *      <simplesavedatas>:セーブデータの簡易版
     *          <simplesavedata>(属性:number 0ならデータなし)
     *              <name>:名前
     *              <level>:レベル
     *              <save>:セーブした場所(属性:spotnumber セーブポイントの識別番号)
     *              <time>:プレイ時間(属性:hour 時,minute 分)
     */

	@Override
	public void FileConverter(Loader myLoader) throws IOException, SAXException, ParserConfigurationException, URISyntaxException {
		URL XMLurl = getClass().getClassLoader().getResource("data/title/"+fileName);
		Document document = DocumentBuilderFactory.newInstance().
				newDocumentBuilder().parse(new File(XMLurl.toURI()));

        //指定の要素を抜き出す
        NodeList profile = document.getElementsByTagName("profile");
        //ファイルの種類確認
        if(!profile.item(0).getAttributes().getNamedItem("type").getNodeValue().equals("simplesavedatafile")){
        	throw new SAXException();
        }

        NodeList dataList = document.getElementsByTagName("simplesavedata");
        ArrayList<SimpleSaveData> saveList = new ArrayList<SimpleSaveData>();

        for(int i = 0; i < 3; i++){
        	saveList.add(null);
        }
        for(int i = 0; i < dataList.getLength(); i++){
        	int dataNumber = Integer.valueOf(dataList.item(i).getAttributes().getNamedItem("number").getNodeValue());
        	if(dataNumber!=0){
        		NodeList saveDataList = dataList.item(i).getChildNodes();
        		String name = null;
        		int level = 0;
        		int spotNumber = 0;
        		String spotName = null;
        		int hour = 0, minute = 0;
        		//タグごとに処理
        		for (int j = 0; j < saveDataList.getLength(); j++)
        		{
        			//抜き出した子要素の中から順番に取りだし、データごとに処理
        			String nodeName = saveDataList.item(j).getNodeName();
        			if (nodeName.equals("name"))		//fontimg
        			{
        				name = saveDataList.item(j).getTextContent();
        			}else if(nodeName.equals("level")){
        				level = Integer.valueOf(saveDataList.item(j).getTextContent());
        			}else if(nodeName.equals("save")){
        				spotNumber = Integer.valueOf(saveDataList.item(j).getAttributes().getNamedItem("spotnumber").getNodeValue());
        				spotName = saveDataList.item(j).getTextContent();
        			}else if(nodeName.equals("time")){
        				hour = Integer.valueOf(saveDataList.item(j).getAttributes().getNamedItem("hour").getNodeValue());
        				minute = Integer.valueOf(saveDataList.item(j).getAttributes().getNamedItem("minute").getNodeValue());
        			}
            	}
        		saveList.set(dataNumber, new SimpleSaveData(dataNumber, name, level, spotNumber, spotName, hour, minute));
            }
        }

        myData = saveList;
	}

}
