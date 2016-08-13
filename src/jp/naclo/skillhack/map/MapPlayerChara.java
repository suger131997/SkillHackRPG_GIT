package jp.naclo.skillhack.map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MapPlayerChara extends MapObject{
	private int char_x, char_y;
	private int charaDirection;
	private BufferedImage moveImage[][];
	public MapPlayerChara(int x, int y, int d) {
		super(x, y);
		char_x = x * MAP_CONST_NUM.MAP_SQUEAR;
		char_y = y * MAP_CONST_NUM.MAP_SQUEAR;
		charaDirection = d;
	}
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(moveImage[charaDirection][char_x / 16 % 4 + char_y / 16 % 4], null, char_x, char_y);
	}

	public void move(int direction, int map[][]) {
		int mx = 0, my = 0;
		if(char_x % 64 != 0 || char_y % 64 != 64){
			switch(charaDirection){
			case MAP_CONST_NUM.LEFT:
				mx = -1;
				my = 0;
			case MAP_CONST_NUM.RIGHT:
				mx = 1;
				my = 0;
			case MAP_CONST_NUM.UP:
				mx = 0;
				my = -1;
			case MAP_CONST_NUM.DOWN:
				mx = 0;
				my = -1;
			}
			if(map[y + my][x + mx] == MAP_CONST_NUM.NONE){
				char_x = char_x + mx;
				char_y = char_y + my;
			}
			return;
		}
		charaDirection = direction;
		switch(direction){
			case MAP_CONST_NUM.LEFT:
				mx = -1;
				my = 0;
			case MAP_CONST_NUM.RIGHT:
				mx = 1;
				my = 0;
			case MAP_CONST_NUM.UP:
				mx = 0;
				my = -1;
			case MAP_CONST_NUM.DOWN:
				mx = 0;
				my = 1;
		}
		if(map[y + my][x + mx] == MAP_CONST_NUM.NONE){
			char_x = char_x + mx;
			char_y = char_y + my;
		}
	}

	public int getChar_x() {
		return char_x;
	}

	public int getChar_y() {
		return char_y;
	}
	@Override
	public void mapReflection(int[][] map) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
