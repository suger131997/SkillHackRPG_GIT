package jp.naclo.skillhack.Load;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import jp.naclo.skillhack.DrawFrame;

public class LoadFile_DrawFrame extends SuperLoadFile{

	public LoadFile_DrawFrame(String fName) {
		super(fName);
	}

	public void FileConverter(Loader myLoader) throws IOException {
		URL url=getClass().getClassLoader().getResource("data/common/img/Frame_Angle.png");
		BufferedImage frame_Angle = ImageIO.read(url);
		url=getClass().getClassLoader().getResource("data/common/img/Frame_Body.png");
		BufferedImage frame_Body = ImageIO.read(url);
		url=getClass().getClassLoader().getResource("data/common/img/Frame.png");
		BufferedImage frame = ImageIO.read(url);
		myData = new DrawFrame(frame_Angle ,frame_Body,frame);
	}

}
