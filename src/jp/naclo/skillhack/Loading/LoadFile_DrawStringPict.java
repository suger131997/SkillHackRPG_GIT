package jp.naclo.skillhack.Loading;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jp.naclo.skillhack.DrawStringPict;
import jp.naclo.skillhack.FONT_SIZE;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LoadFile_DrawStringPict  extends SuperLoadFile{
	int[] size = new int[3];
	/*キーは 文字,size*/
	private TreeMap<String,Point2D> fontImages = new TreeMap<String,Point2D>();
	private BufferedImage fontImage;

	public LoadFile_DrawStringPict(String fName) {
		super(fName);
	}

	@Override
	public void FileConverter() throws IOException, SAXException, ParserConfigurationException, URISyntaxException {
		URL XMLurl = getClass().getClassLoader().getResource("Data/Font/"+fileName);
		Document document = DocumentBuilderFactory.newInstance().
				newDocumentBuilder().parse(new File(XMLurl.toURI()));

        //指定の要素を抜き出す
        NodeList profile = document.getElementsByTagName("profile");
        //ファイルの種類確認
        if(!profile.item(0).getAttributes().getNamedItem("type").getNodeValue().equals("fontfile")){
        	throw new SAXException();
        }

        NodeList dataList = profile.item(0).getChildNodes();

            //タグごとに処理
            for (int j = 0; j < dataList.getLength(); j++)
            {
                //抜き出した子要素の中から順番に取りだし、データごとに処理
                String nodeName = dataList.item(j).getNodeName();
                if (nodeName.equals("fontimg"))		//fontimg
                {
                    URL Imgurl = getClass().getClassLoader().getResource("Data/Font/"+dataList.item(j).getTextContent());
                    fontImage = ImageIO.read(Imgurl);
                }else if(nodeName.equals("fontsizedatas")){
                	NodeList sizeNodes = dataList.item(j).getChildNodes();
                	for(int k = 0; k < sizeNodes.getLength(); k++){
                		if(!sizeNodes.item(k).getNodeName().equals("fontsize")){
                			continue;
                		}

                		String sizeName = sizeNodes.item(k).getAttributes().getNamedItem("type").getNodeValue();
                		if(sizeName.equals("small")){
                			size[FONT_SIZE.SMALL] = Integer.valueOf(sizeNodes.item(k).getTextContent());
                		}else if(sizeName.equals("middle")){
                			size[FONT_SIZE.MIDDLE] = Integer.valueOf(sizeNodes.item(k).getTextContent());
                		}else if(sizeName.equals("big")){
                			size[FONT_SIZE.BIG] = Integer.valueOf(sizeNodes.item(k).getTextContent());
                		}
                	}
                }else if(nodeName.equals("fonts")){
                	String keyChar = null;
        			int keySize = -1;
        			int x = -1, y = -1;
        			NodeList fontNodes = dataList.item(j).getChildNodes();				//fonts
                	for(int k = 0; k < fontNodes.getLength(); k++){
                		if(!fontNodes.item(k).getNodeName().equals("font")){
                			continue;
                		}
                		NodeList fontDataNodes = fontNodes.item(k).getChildNodes();
                		for(int m = 0; m < fontDataNodes.getLength(); m++){
                			String fontDataName = fontDataNodes.item(m).getNodeName();

                    		if(fontDataName.equals("char")){

                    			keyChar = fontDataNodes.item(m).getTextContent();

                    		}else if(fontDataName.equals("size")){
                    			String fontSizeName = fontDataNodes.item(m).getTextContent();

                    			if(fontSizeName.equals("small")){
                        			keySize = FONT_SIZE.SMALL;
                        		}else if(fontSizeName.equals("middle")){
                        			keySize = FONT_SIZE.MIDDLE;
                        		}else if(fontSizeName.equals("big")){
                        			keySize = FONT_SIZE.BIG;
                        		}

                    		}else if(fontDataName.equals("position")){

                    			NamedNodeMap  positions =fontDataNodes.item(m).getAttributes();
                    			x = Integer.valueOf(positions.getNamedItem("x").getNodeValue());
                    			y = Integer.valueOf(positions.getNamedItem("y").getNodeValue());

                    		}
                		}

                		fontImages.put(keyChar + "," + keySize, new Point(x, y));
                	}
                }else{
                	//System.out.println("errer");
                }

        }
        //データ本体作成
        myData = new DrawStringPict(size, fontImages, fontImage);
	}

}
