package levelmodels;

import java.util.ArrayList;
import java.util.Random;

import factories.ObjectFactory;
import gameobjects.Fruit;
import gameobjects.GameObject;

public abstract class LevelModel {

	protected Random r = new Random();
	protected int repeatDur;
	protected int fataldur;
	protected int normaldur;
	protected int pathFruitDur;
	protected int pathFatalDur;
	protected int pathNormalDur;

	protected int lives;
	protected int score;

	protected ArrayList<GameObject> fruits;
	protected ArrayList<GameObject> specialFruits;

	protected ObjectFactory objectFactory = ObjectFactory.getInstance();

	public LevelModel() {
		lives = 3;
		score = 0;
		fruits = new ArrayList<GameObject>();
		specialFruits = new ArrayList<GameObject>();
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<GameObject> getFruits() {
		return fruits;
	}

	public void setFruits(ArrayList<GameObject> fruits) {
		this.fruits = fruits;
	}

	public ArrayList<GameObject> getSpecialFruits() {
		return specialFruits;
	}

	public void setSpecialFruits(ArrayList<GameObject> specialFruits) {
		this.specialFruits = specialFruits;
	}

	public Random getR() {
		return r;
	}

	public void setR(Random r) {
		this.r = r;
	}

	public int getRepeatDur() {
		return repeatDur;
	}

	public void setRepeatDur(int repeatDur) {
		this.repeatDur = repeatDur;
	}

	public int getFataldur() {
		return fataldur;
	}

	public void setFataldur(int fataldur) {
		this.fataldur = fataldur;
	}

	public int getNormaldur() {
		return normaldur;
	}

	public void setNormaldur(int normaldur) {
		this.normaldur = normaldur;
	}

	public int getPathFruitDur() {
		return pathFruitDur;
	}

	public void setPathFruitDur(int pathFruitDur) {
		this.pathFruitDur = pathFruitDur;
	}

	public int getPathFatalDur() {
		return pathFatalDur;
	}

	public void setPathFatalDur(int pathFatalDur) {
		this.pathFatalDur = pathFatalDur;
	}

	public int getPathNormalDur() {
		return pathNormalDur;
	}

	public void setPathNormalDur(int pathNormalDur) {
		this.pathNormalDur = pathNormalDur;
	}

	// ------------------------------------msh getters w
	// setters----------------------------------------------------------
	public void sliceFruit(int indx) {
		Fruit fruit = (Fruit) fruits.get(indx);
		if (!fruit.isSliced()) {
			fruit.slice();
			score += fruit.getScore();
		}
	}

	public GameObject getRandomFruit() {

		int i = r.nextInt(3);

		GameObject x = null;

		if (i == 0)
			x = objectFactory.getGameObject("banana");
		else if (i == 1) {
			x = objectFactory.getGameObject("apple");
		} else
			x = objectFactory.getGameObject("watermelon");
		fruits.add(x);

		return x;
	}

	// eb2a 8aiar al asamy bta3t al special fruits hna
	public GameObject getRandomSpecialFruit() {
		int i = r.nextInt(2);

		GameObject x = null;

		if (i == 0) {
			x = objectFactory.getGameObject("apple");
		} else {
			x = objectFactory.getGameObject("banana");
		}
		return x;

	}

	public GameObject getNonFatalBomb() {
		return objectFactory.getGameObject("nonfatalbomb");
	}

	public GameObject getFatalBomb() {
		return objectFactory.getGameObject("fatalbomb");
	}

	public void sliceSpecialFruit(int indx) {
		Fruit fruit = (Fruit) specialFruits.get(indx);
		if (!fruit.isSliced()) {
			fruit.slice();
			score += fruit.getScore();
		}
	}

	public void decreaseLives() {
		lives--;
	}
}
