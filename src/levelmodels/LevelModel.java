package levelmodels;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

import factories.ObjectFactory;
import gameobjects.Bomb;
import gameobjects.Fruit;
import gameobjects.GameObject;
import gameobjects.SpecialBanana;
import gameobjects.SpecialMango;

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
	protected int bestScore;

	protected ArrayList<GameObject> fruits;
	protected ArrayList<GameObject> specialFruits;
	protected ArrayList<GameObject> nonFatalBombs;

	protected boolean isDoubleScore;
	protected ObjectFactory objectFactory = ObjectFactory.getInstance();

	public LevelModel() {
		lives = 3;
		score = 0;
		fruits = new ArrayList<GameObject>();
		specialFruits = new ArrayList<GameObject>();
		nonFatalBombs = new ArrayList<GameObject>();

		isDoubleScore = false;
		loadBestScore();
	}

	public ArrayList<GameObject> getNonFatalBombs() {
		return nonFatalBombs;
	}

	public void setNonFatalBombs(ArrayList<GameObject> nonFatalBombs) {
		this.nonFatalBombs = nonFatalBombs;
	}

	public boolean isDoubleScore() {
		return isDoubleScore;
	}

	public void setDoubleScore(boolean isDoubleScore) {
		this.isDoubleScore = isDoubleScore;
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

	public int getBestScore() {
		return bestScore;
	}

	public void setBestScore(int bestScore) {
		this.bestScore = bestScore;
	}

	/*
	 * GameObject getters
	 */
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
			x = objectFactory.getGameObject("SpecialBanana");
		} else {
			x = objectFactory.getGameObject("SpecialMango");
		}
		specialFruits.add(x);
		return x;

	}

	public GameObject getNonFatalBomb() {
		GameObject x = objectFactory.getGameObject("nonfatalbomb");
		nonFatalBombs.add(x);
		return x;
	}

	public GameObject getFatalBomb() {
		return objectFactory.getGameObject("fatalbomb");
	}

	/*
	 * Slicing functions
	 */

	public void sliceFruit(int indx) {

		Fruit fruit = (Fruit) fruits.get(indx);

		if (!fruit.isSliced()) {
			fruit.slice();
			if (!isDoubleScore)
				score += fruit.getScore();
			else
				score += (fruit.getScore() * 2);

			if (score > bestScore) {
				bestScore = score;
				saveBestScore();
			}
		}

	}

	public void sliceSpecialFruit(int indx) {
		Fruit fruit = (Fruit) specialFruits.get(indx);
		if (!fruit.isSliced()) {
			fruit.slice();
			if (fruit instanceof SpecialBanana) {
				isDoubleScore = true;
			} else if (fruit instanceof SpecialMango) {
				lives++;
			}
		}
	}

	public void sliceNonFatalBomb(int indx) {
		Bomb bomb = (Bomb) nonFatalBombs.get(indx);
		if (!bomb.isSliced()) {
			bomb.slice();
			lives--;
			System.out.println(lives);
		}
	}

	public void decreaseLives() {
		lives--;
	}

	/*
	 * Saving and loading best score
	 */

	public void loadBestScore() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("score.txt")));
			StringTokenizer st;
			try {
				st = new StringTokenizer(bufferedReader.readLine());
				this.bestScore = Integer.parseInt(st.nextToken());
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void saveBestScore() {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("score.txt")));
			bufferedWriter.write(Integer.toString(bestScore));
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
