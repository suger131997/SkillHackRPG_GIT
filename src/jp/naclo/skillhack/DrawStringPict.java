package jp.naclo.skillhack;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.TreeMap;

public class DrawStringPict {		//文字描画クラス
	private int[] size;
	/*キーは 文字,size 例 あ,0 */
	private TreeMap<String,Point2D> fontImages;
	private BufferedImage fontImage;
	public DrawStringPict(int[] s,TreeMap<String,Point2D> fis, BufferedImage fi){
		size = s;
		fontImages = fis;
		fontImage = fi;
	}

	public void drowString(Graphics2D g,String str, int x, int y, int fontSize){	//文字を書く

		for(int i = 0; i <str.length();i++){
			Point img_position = (Point) fontImages.get(str.charAt(i) + "," + fontSize);
			if(img_position == null){
				System.out.println("文字データ不足");
			}
			g.drawImage(fontImage, x + size[fontSize] * i, y, x + size[fontSize]*( i + 1 ), y + size[fontSize],
					img_position.x, img_position.y, img_position.x + size[fontSize], img_position.y + size[fontSize], null);
		}

	}
}
