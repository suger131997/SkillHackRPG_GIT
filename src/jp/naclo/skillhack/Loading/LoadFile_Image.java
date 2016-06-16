package jp.naclo.skillhack.Loading;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class LoadFile_Image extends SuperLoadFile{


	public LoadFile_Image(String fName) {
		super(fName);
	}

	public void FileConverter() throws IOException {
		URL url=getClass().getClassLoader().getResource("media/title.jpg");
		myData = ImageIO.read(url);
	}

}
