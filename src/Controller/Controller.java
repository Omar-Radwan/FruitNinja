package Controller;

import java.util.ArrayList;

import gameobjects.Fruit;
import gameobjects.GameObject;
import gui.related.LevelView;
import levelmodels.LevelModel;

public class Controller {

	protected LevelModel levelModel;
	protected LevelView levelView;
	protected int lives;
	protected ArrayList<GameObject> objects;
	protected int score;

	public Controller(LevelModel levelModel, LevelView levelView) {
		this.levelModel = levelModel;
		this.levelView = levelView;
		this.objects = new ArrayList<GameObject>();
		this.score = 0;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public void setLevelModel(LevelModel levelModel) {
		this.levelModel = levelModel;
	}

	public Controller() {
		this.objects = new ArrayList<GameObject>();
		this.score = 0;
	}

	public LevelModel getLevelModel() {
		return levelModel;
	}

	public void setLeveModel(LevelModel levelModel) {
		this.levelModel = levelModel;
	}

	public LevelView getLevelView() {
		return levelView;
	}

	public void setLevelView(LevelView levelView) {
		this.levelView = levelView;
	}

	public GameObject getFruit() {
		objects.add(levelModel.getFruit());
		return objects.get(objects.size() - 1);
	}

	public void sliceFruit(int indx) {
		Fruit x = (Fruit) objects.get(indx);
		if (!objects.get(indx).isSliced()) {
			objects.get(indx).slice();
			score += x.getScore();
		}
	}

}
