package jp.naclo.skillhack.Save;

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

public class SaveFile_SimpleSaveData extends SuperSaveFile{
	public SaveFile_SimpleSaveData(String fName) {
		super(fName);
	}
	int dataNum;
	int target;
	@Override
	public void FileConverter(Saver mySaver) throws SAXException, IOException, ParserConfigurationException, URISyntaxException{
		URL XMLurl = getClass().getClassLoader().getResource("data/title/" + fileName);
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
        	if(dataNumber == target){
        		dataList.item(i).getAttributes().getNamedItem("number").setNodeValue(String.valueOf(dataNum));
        		NodeList saveDataList = dataList.item(i).getChildNodes();
        		//タグごとに処理
        		for (int j = 0; j < saveDataList.getLength(); j++)
        		{
        			//抜き出した子要素の中から順番に取りだし、データごとに初期設定
        			String nodeName = saveDataList.item(j).getNodeName();
        			if (nodeName.equals("name"))		//fontimg
        			{
        				saveDataList.item(j).setTextContent("マオウ");
        			}else if(nodeName.equals("level")){
        				saveDataList.item(j).setTextContent("1");
        			}else if(nodeName.equals("save")){
        				saveDataList.item(j).getAttributes().getNamedItem("spotnumber").setNodeValue("0");
        				saveDataList.item(j).setTextContent("ふういんのほこら");
        			}else if(nodeName.equals("time")){
        				saveDataList.item(j).getAttributes().getNamedItem("hour").setNodeValue("0");
        				saveDataList.item(j).getAttributes().getNamedItem("minute").setNodeValue("0");
        			}
            	}
        		break;
            }
        }
	}

}
