package Controller;

import gameobjects.GameObject;
import gui.related.LevelView;
import levelmodels.LevelModel;

public class Controller {

	protected LevelModel levelModel;
	protected LevelView levelView;

	public Controller(LevelModel levelModel, LevelView levelView) {
		this.levelModel = levelModel;
		this.levelView = levelView;
	}

	public Controller() {
	}

	public LevelModel getLevelModel() {
		return levelModel;
	}

	public void setLevelModel(LevelModel levelModel) {
		this.levelModel = levelModel;
	}

	public LevelView getLevelView() {
		return levelView;
	}

	public void setLevelView(LevelView levelView) {
		this.levelView = levelView;
	}

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

	public void sliceFruit(int indx) {
		levelModel.sliceFruit(indx);
		levelView.updateScore(levelModel.getScore());
	}

	public void sliceSpecialFruit(int indx) {
		levelModel.sliceSpecialFruit(indx);
		levelView.updateScore(levelModel.getScore());
	}

	public void sliceFatalBomb() {
		levelView.endGame();
	}

	public void sliceNonFatalBomb() {
		if (levelModel.getLives() > 1) {
			levelModel.decreaseLives();
			levelView.updateLives();
		} else {
			levelView.endGame();
		}
	}

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

}
