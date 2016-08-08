package jp.naclo.skillhack;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class DrawFrame {
	private BufferedImage frame_Angle;
	private BufferedImage frame_Body;
	private BufferedImage frame;
	public DrawFrame(BufferedImage angle , BufferedImage body, BufferedImage f){
		frame_Angle = angle;
		frame_Body = body;
		frame = f;
	}

	public void drowFrame(Graphics2D g, int x, int y, int width, int height){
			//描画------未完
			//変型待避
			AffineTransform oldtr = g.getTransform();
			g.drawImage(frame_Angle, null, x, y);
			g.drawImage(frame, x + frame_Angle.getWidth(), y, width - frame_Angle.getWidth() * 2, frame.getHeight(), null);
			g.drawImage(frame_Body, x + frame_Angle.getWidth(), y + frame_Angle.getHeight(), width - frame_Angle.getWidth() * 2, height - frame.getHeight() * 2, null);

			g.rotate(90 / 180.0 * Math.PI, x, y);
			g.drawImage(frame_Angle, null, x, y - width );
			g.drawImage(frame, x + frame_Angle.getWidth(), y - width, height - frame_Angle.getWidth() * 2, frame.getHeight(), null);

			g.rotate(90 / 180.0 * Math.PI, x, y);
			g.drawImage(frame_Angle, null, x - width, y - height);
			g.drawImage(frame, x - width + frame_Angle.getWidth(), y - height, width - frame_Angle.getWidth() * 2, frame.getHeight(), null);

			g.rotate(90 / 180.0 * Math.PI, x, y);
			g.drawImage(frame_Angle, null, x - height, y);
			g.drawImage(frame, x - height + frame_Angle.getWidth(), y, height - frame_Angle.getWidth() * 2, frame.getHeight(), null);
			//変型復帰
			g.setTransform(oldtr);

	}
}
