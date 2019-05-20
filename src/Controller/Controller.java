package Controller;

import gameobjects.Fruit;
import gameobjects.GameObject;
import gui.related.LevelView;
import levelmodels.LevelModel;

public class Controller implements IController {

	protected LevelModel levelModel;
	protected LevelView levelView;

	public Controller(LevelModel levelModel, LevelView levelView) {
		this.levelModel = levelModel;
		this.levelView = levelView;

	}

	public Controller() {
	}

	public void setLevelModel(LevelModel levelModel) {
		this.levelModel = levelModel;
	}

	public void setLevelView(LevelView levelView) {
		this.levelView = levelView;
	}

	/*
	 * Fruit getters
	 */

	public GameObject getFruit() {
		return levelModel.getRandomFruit();
	}

	public GameObject getFatalBomb() {
		return levelModel.getFatalBomb();
	}

	public GameObject getSpecialFruit() {
		return levelModel.getRandomSpecialFruit();
	}

	public GameObject getNonFatalBomb() {
		return levelModel.getNonFatalBomb();
	}

	/*
	 * Slicing functions
	 */

	public void sliceFruit(int indx) {
		levelModel.sliceFruit(indx);
		levelView.updateScore(levelModel.getScore());
		levelView.updateBestScore(levelModel.getBestScore());

	}

	public void sliceSpecialFruit(int indx) {
		levelView.updateLives(levelModel.getLives());
		levelModel.sliceSpecialFruit(indx);
		levelView.updateScore(levelModel.getScore());
		levelView.updateBestScore(levelModel.getBestScore());
	}

	public void sliceFatalBomb() {
		levelView.GameOverScene();
	}

	public void checkIfIsSliced(int indx) {
		Fruit fruit = (Fruit) levelModel.getFruits().get(indx);
		if (!fruit.isSliced()) {
			levelModel.decreaseLives();
		}
		levelView.updateLives(levelModel.getLives());
	}

	public void sliceNonFatalBomb(int indx) {
		levelModel.sliceNonFatalBomb(indx);
		if (levelModel.getLives() <= 0) {
			levelView.GameOverScene();
		}
		levelView.updateLives(levelModel.getLives());
	}

	/*
	 * Get level parameters
	 */

	public int getRepeatDur() {
		return levelModel.getRepeatDur();
	}

	public int getFataldur() {
		return levelModel.getFataldur();
	}

	public int getNormaldur() {
		return levelModel.getNormaldur();
	}

	public int getPathFruitDur() {
		return levelModel.getPathFruitDur();
	}

	public int getPathFatalDur() {
		return levelModel.getPathFatalDur();
	}

	public int getPathNormalDur() {
		return levelModel.getPathNormalDur();
	}

	/*
	 * updating functions
	 */

	public void endDoubleScore() {
		levelModel.setDoubleScore(false);
	}

}
