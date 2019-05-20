package factories;


import java.util.ArrayList;
import java.util.HashMap;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class ImagesFactory {

	private static ImagesFactory instance;
	private HashMap<String, Image[]> ImageMap;

	private ImagesFactory() {
		ImageMap = new HashMap<>();
		fillMap();
	}

	public static ImagesFactory getInstance() {
		if (instance == null) {
			instance = new ImagesFactory();

		}
		return instance;
	}

	private void fillMap() {

		ArrayList<String> types = new ArrayList<>();

		types.add("banana");
		types.add("apple");
		types.add("watermelon");

		types.add("specialbanana");
		types.add("specialmango");

		types.add("fatalbomb");
		types.add("nonfatalbomb");

		for (String type : types) {

			// should contain the image of whole fruit ... image of right part .... image of
			// left part in case of fruit
			// should contain the image of whole bomb ... image of destroyed bomb

			Image[] Images = new Image[3];

			Images[0] = new Image("file:src/gui/related/" + type + ".png");

			ImageMap.put(type, Images);

		}

	}

	public Image[] getWatermelonImages() {
		return ImageMap.get("watermelon");
	}

	public Image[] getBananaImages() {
		return ImageMap.get("banana");
	}

	public Image[] getAppleImages() {
		return ImageMap.get("apple");
	}

	public Image[] getSpecialBananaImages() {
		return ImageMap.get("specialbanana");
	}

	public Image[] getSpecialMangoImages() {
		return ImageMap.get("specialmango");
	}

	public Image[] getFatalBombImages() {
		return ImageMap.get("fatalbomb");
	}

	public Image[] getNonFatalBombImages() {
		return ImageMap.get("nonfatalbomb");
	}
/*
 * Timeline timeline = new Timeline(new KeyFrame(new Duration(controller.getRepeatDur()), new EventHandler() {
			@Override
			public void handle(Event event) {
				setFruits();
				setPath(objects.get(objectsNumb - 1), pathFruitDur);
			}
		}));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.play();

 * */
}
