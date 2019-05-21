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
import gameobjects.IGameObject;
import gameobjects.SpecialBanana;
import gameobjects.SpecialMango;

public abstract class LevelModel implements ILevelModel {

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

	protected ArrayList<IGameObject> fruits;
	protected ArrayList<IGameObject> specialFruits;
	protected ArrayList<IGameObject> nonFatalBombs;

	protected boolean isDoubleScore;
	protected ObjectFactory objectFactory = ObjectFactory.getInstance();

	protected String fileName;

	public LevelModel() {
		lives = 3;
		score = 0;
		fruits = new ArrayList<IGameObject>();
		specialFruits = new ArrayList<IGameObject>();
		nonFatalBombs = new ArrayList<IGameObject>();
		isDoubleScore = false;

	}

	public ArrayList<IGameObject> getNonFatalBombs() {
		return nonFatalBombs;
	}

	public void setNonFatalBombs(ArrayList<IGameObject> nonFatalBombs) {
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

	public ArrayList<IGameObject> getFruits() {
		return fruits;
	}

	public void setFruits(ArrayList<IGameObject> fruits) {
		this.fruits = fruits;
	}

	public ArrayList<IGameObject> getSpecialFruits() {
		return specialFruits;
	}

	public void setSpecialFruits(ArrayList<IGameObject> specialFruits) {
		this.specialFruits = specialFruits;
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



	public void decreaseLives() {
		lives--;
	}

	/*
	 * Saving and loading best score
	 */

	public void loadBestScore() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
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
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(fileName)));
			bufferedWriter.write(Integer.toString(bestScore));
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
