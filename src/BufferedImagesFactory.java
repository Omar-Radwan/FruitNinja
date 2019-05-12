
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class BufferedImagesFactory {

	private static BufferedImagesFactory instance;
	private HashMap<String, BufferedImage[]> bufferedImageMap;

	private BufferedImagesFactory() {
		bufferedImageMap = new HashMap<>();
		fillMap();
	}

	public static BufferedImagesFactory getInstance() {
		if (instance == null) {
			instance = new BufferedImagesFactory();

		}
		return instance;
	}

	private void fillMap() {

		ArrayList<String> types = new ArrayList<>();

		types.add("banana");
		types.add("mango");
		types.add("watermelon");

		types.add("specialbanana");
		types.add("specialmango");

		types.add("fatalbomb");
		types.add("nonfatalbomb");

		for (String type : types) {
			// should contain the image of whole fruit ... image of right part .... image of
			// left part in case of fruit
			// should contain the image of whole bomb ... image of destroyed bomb
			BufferedImage[] bufferedImages = new BufferedImage[3];

			try {
				bufferedImages[0] = ImageIO.read(new File("src/gui/related/" + type + ".png"));
				// bufferedImages[1] = ImageIO.read(new File(type + "right.png"));
				// bufferedImages[1] = ImageIO.read(new File(type + "left.png"));
				// bufferedImages[1] = ImageIO.read(new File(type + "destoryed.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			bufferedImageMap.put(type, bufferedImages);
		}

	}

	public BufferedImage[] getWatermelonImages() {
		return bufferedImageMap.get("watermelon");
	}

	public BufferedImage[] getBananaImages() {
		return bufferedImageMap.get("banana");
	}

	public BufferedImage[] getMangoImages() {
		return bufferedImageMap.get("mango");
	}

	public BufferedImage[] getSpecialBananaImages() {
		return bufferedImageMap.get("specialbanana");
	}

	public BufferedImage[] getSpecialMangoImages() {
		return bufferedImageMap.get("specialmango");
	}

	public BufferedImage[] getFatalBombImages() {
		return bufferedImageMap.get("fatalbomb");
	}

	public BufferedImage[] getNonFatalBombImages() {
		return bufferedImageMap.get("nonfatalbomb");
	}

}
