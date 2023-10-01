package background;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
/**
 * Image loader class
 */
public class LoadImg {
	/**
	 * Loads an image to BufferedImage
	 * @param file - the name of the file of the image
	 * @return BufferedImage, which contains the image
	 */
	public static BufferedImage LoadImage(String file) {
		BufferedImage image=null;
		InputStream in=LoadImg.class.getResourceAsStream(file);
		try {
			image=ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//close the file
		finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return image;
	}
}
