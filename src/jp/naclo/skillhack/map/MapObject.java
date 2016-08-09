package jp.naclo.skillhack.map;

import java.awt.Graphics2D;

public abstract class MapObject {
	protected int x, y;				//マス目上の位置
	public MapObject(int x2, int y2){
		x = x2;
		y = y2;
	}
	public abstract void draw(Graphics2D g);
	public abstract void mapReflection(int map[][]);
}
