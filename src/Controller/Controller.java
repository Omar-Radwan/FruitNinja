package Controller;

import java.util.HashMap;

import command.Command;
import command.DecreaseLivesCommand;
import command.EndDoubleScoreCommand;
import command.LoadBestScoreCommand;
import command.SaveBestScoreCommand;
import gameobjects.Fruit;
import gameobjects.GameObject;
import gui.related.LevelView;
import levelmodels.ILevelModel;
import levelmodels.LevelModel;

public class Controller implements IController {

	protected ILevelModel levelModel;
	protected LevelView levelView;
	protected HashMap<String, Command> commandsMap;

	public Controller(ILevelModel levelModel, LevelView levelView) {
		this.levelModel = levelModel;
		this.levelView = levelView;

	}

	public Controller() {
		commandsMap = new HashMap<String, Command>();
	}

	public void setLevelModel(ILevelModel levelModel) {
		this.levelModel = levelModel;
		commandsMap.put("decreaseLives", new DecreaseLivesCommand(levelModel));
		commandsMap.put("endDoubleScore", new EndDoubleScoreCommand(levelModel));
		commandsMap.put("save", new SaveBestScoreCommand(levelModel));
		commandsMap.put("load", new LoadBestScoreCommand(levelModel));
		commandsMap.get("load").execute();
	}

	public void setLevelView(LevelView levelView) {
		this.levelView = levelView;
		levelView.updateBestScore(levelModel.getBestScore());
		levelView.updateLives(levelModel.getLives());

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
		commandsMap.get("save").execute();
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
			commandsMap.get("decreaseLives").execute();
		}
		if (levelModel.getLives() <= 0) {
			levelView.GameOverScene();
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
		commandsMap.get("endDoubleScore").execute();
	}

}
