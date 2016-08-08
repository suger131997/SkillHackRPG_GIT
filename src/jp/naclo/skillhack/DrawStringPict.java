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

	public void drawString(Graphics2D g,String str, int x, int y, int fontSize){	//文字を書く
		int position = 0;
		int half;

		for(int i = 0; i <str.length();i++){
			Point img_position = (Point) fontImages.get(str.charAt(i) + "," + fontSize);
			if(img_position == null){
				System.out.println("文字データ不足" + str.charAt(i));
			}

			if(str.codePointAt(i) <= 255 ){		//半角なら幅が半分 半角なら文字コードは255以下
				half = size[fontSize] /2;
			}else{
				half = 0;
			}

			//描画
			g.drawImage(fontImage, x + position , y, x + position + size[fontSize] - half, y + size[fontSize],
					img_position.x, img_position.y, img_position.x + size[fontSize] - half, img_position.y + size[fontSize], null);

			//描画位置をずらす
			position = position + size[fontSize] - half;

		}
	}

	public void drawStringCenter(Graphics2D g,String str, int x, int y, int fontSize){	//中央ぞろえで文字を書く
		int strWidth = 0;
		int position = 0;
		int half;

		for(int i = 0; i <str.length();i++){//幅取得
			if(str.codePointAt(i) <= 255 ){		//半角なら幅が半分 半角なら文字コードは255以下
				half = 2;
			}else{
				half = 1;
			}
			strWidth = strWidth + size[fontSize] / half;
		}
		x = x - strWidth / 2;
		for(int i = 0; i <str.length();i++){
			Point img_position = (Point) fontImages.get(str.charAt(i) + "," + fontSize);
			if(img_position == null){
				System.out.println("文字データ不足" + str.charAt(i));
			}

			if(str.codePointAt(i) <= 255 ){		//半角なら幅が半分 半角なら文字コードは255以下
				half = size[fontSize] /2;
			}else{
				half = 0;
			}

			//描画
			g.drawImage(fontImage, x + position , y, x + position + size[fontSize] - half, y + size[fontSize],
					img_position.x, img_position.y, img_position.x + size[fontSize] - half, img_position.y + size[fontSize], null);

			//描画位置をずらす
			position = position + size[fontSize] - half;

		}

	}
}
