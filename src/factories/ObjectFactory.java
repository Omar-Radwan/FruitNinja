package factories;

import gameobjects.Apple;
import gameobjects.Banana;
import gameobjects.FatalBomb;
import gameobjects.GameObject;
import gameobjects.NonFatalBomb;
import gameobjects.SpecialBanana;
import gameobjects.SpecialOrange;
import gameobjects.Watermelon;

public class ObjectFactory {

	static ObjectFactory instance;

	private ObjectFactory() {

	}

	public static ObjectFactory getInstance() {
		if (instance != null) {
			return instance;
		}
		return new ObjectFactory();
	}

	public GameObject getGameObject(String gameObject) {
		if (gameObject.equalsIgnoreCase("Apple"))
			return new Apple();
		else if (gameObject.equalsIgnoreCase("Banana"))
			return new Banana();
		else if (gameObject.equalsIgnoreCase("Watermelon"))
			return new Watermelon();
		else if (gameObject.equalsIgnoreCase("SpecialBanana"))
			return new SpecialBanana();
		else if (gameObject.equalsIgnoreCase("SpecialOrange"))
			return new SpecialOrange();
		else if (gameObject.equalsIgnoreCase("FatalBomb"))
			return new FatalBomb();
		else if (gameObject.equalsIgnoreCase("NonFatalBomb"))
			return new NonFatalBomb();
		else
			return null;
	}
}