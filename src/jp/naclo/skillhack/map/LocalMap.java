package jp.naclo.skillhack.map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import jp.naclo.skillhack.KEY_STATE;
import jp.naclo.skillhack.ShareInfo;

public class LocalMap {
	private int mapWidth, mapHeight;
	private BufferedImage mapImage;
	private int mapBoard[][];
	private int nowMapBoard[][];
	private MapPlayerChara myChara;
	private ArrayList<MapObject> objects;
	public int draw(ShareInfo sinfo){
		//マップの画像のコピー
		BufferedImage showMap = new BufferedImage(mapImage.getWidth(), mapImage.getHeight(), mapImage.getType());
		showMap.setData(mapImage.getData());
		Graphics2D g_Image = showMap.createGraphics();

		//マップの更新
		for(int i = 0; i < mapWidth; i++){
			for(int j = 0; j < mapHeight; j++){
				mapBoard[j][i] = nowMapBoard[j][i];
			}
		}

		for(int i = 0; i < objects.size(); i++){
			objects.get(i).mapReflection(nowMapBoard);
		}


		//プレイヤーの移動
		if(sinfo.keystate[0][KEY_STATE.LEFT_A]){
			myChara.move(MAP_CONST_NUM.LEFT, nowMapBoard);
		}
		else if(sinfo.keystate[0][KEY_STATE.RIGHT_D]){
			myChara.move(MAP_CONST_NUM.RIGHT, nowMapBoard);
		}
		else if(sinfo.keystate[0][KEY_STATE.UP_W]){
			myChara.move(MAP_CONST_NUM.UP, nowMapBoard);
		}
		else if(sinfo.keystate[0][KEY_STATE.DOWN_S]){
			myChara.move(MAP_CONST_NUM.DOWN, nowMapBoard);
		}
		myChara.mapReflection(nowMapBoard);
		myChara.draw(g_Image);

		for(int i = 0; i < objects.size(); i++){
			objects.get(i).draw(g_Image);
		}

		sinfo.g.drawImage(showMap, 0 , 0, 960, 720,
				myChara.getChar_x() - 448, myChara.getChar_y() - 328, myChara.getChar_x() + 544, myChara.getChar_y() + 424, null);


		return 1;
	}
}
