package Controller;

import java.util.HashMap;
import java.util.Random;

import command.Command;
import command.DecreaseLivesCommand;
import command.EndDoubleScoreCommand;
import command.LoadBestScoreCommand;
import command.SaveBestScoreCommand;
import factories.ObjectFactory;
import gameobjects.Bomb;
import gameobjects.Fruit;
import gameobjects.GameObject;
import gameobjects.IGameObject;
import gameobjects.SpecialBanana;
import gameobjects.SpecialMango;
import gui.related.LevelView;
import levelmodels.ILevelModel;
import levelmodels.LevelModel;

public class Controller implements IController {

	protected ILevelModel levelModel;
	protected LevelView levelView;
	protected HashMap<String, Command> commandsMap;
	protected Random r = new Random();
	ObjectFactory objectFactory = ObjectFactory.getInstance();

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

	public IGameObject getFruit() {
		int i = r.nextInt(3);

		GameObject x = null;

		if (i == 0)
			x = objectFactory.getGameObject("banana");
		else if (i == 1) {
			x = objectFactory.getGameObject("apple");
		} else
			x = objectFactory.getGameObject("watermelon");
		levelModel.getFruits().add(x);

		return x;
	}

	public IGameObject getFatalBomb() {
		return objectFactory.getGameObject("fatalbomb");
	}

	public IGameObject getSpecialFruit() {
		int i = r.nextInt(2);

		GameObject x = null;

		if (i == 0) {
			x = objectFactory.getGameObject("SpecialBanana");
		} else {
			x = objectFactory.getGameObject("SpecialMango");
		}
		levelModel.getSpecialFruits().add(x);
		return x;
	}

	public IGameObject getNonFatalBomb() {
		GameObject x = objectFactory.getGameObject("nonfatalbomb");
		levelModel.getNonFatalBombs().add(x);
		return x;
	}

	/*
	 * Slicing functions
	 */

	public void sliceFruit(int indx) {
		Fruit fruit = (Fruit) levelModel.getFruits().get(indx);

		if (!fruit.isSliced()) {
			fruit.slice();
			if (!levelModel.isDoubleScore())
				levelModel.setScore(levelModel.getScore()+1);
			else
				levelModel.setScore(levelModel.getScore()+fruit.getScore() * 2);

			if (levelModel.getScore() > levelModel.getBestScore()) {
				levelModel.setBestScore(levelModel.getScore());
			}
		}
		commandsMap.get("save").execute();
		levelView.updateScore(levelModel.getScore());
		levelView.updateBestScore(levelModel.getBestScore());
	}

	public void sliceSpecialFruit(int indx) {
		levelView.updateLives(levelModel.getLives());
		Fruit fruit = (Fruit) levelModel.getSpecialFruits().get(indx);
		if (!fruit.isSliced()) {
			fruit.slice();
			if (fruit instanceof SpecialBanana) {
				levelModel.setDoubleScore(true);
			} else if (fruit instanceof SpecialMango) {
				levelModel.setLives(levelModel.getLives() + 1);
			}
		}

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
		Bomb bomb = (Bomb) levelModel.getNonFatalBombs().get(indx);
		if (!bomb.isSliced()) {
			bomb.slice();
		}
		levelModel.decreaseLives();
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
