package jp.naclo.skillhack.Loading;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class LoadFile_Image extends SuperLoadFile{


	public LoadFile_Image(String fName) {
		super(fName);
	}

	public void FileConverter(Loader myLoader) throws IOException {
		URL url=getClass().getClassLoader().getResource(fileName);
		myData = ImageIO.read(url);
	}

}
