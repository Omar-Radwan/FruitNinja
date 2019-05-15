package Controller;

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

	public void setLeveModel(LevelModel levelModel) {
		this.levelModel = levelModel;
	}

	public LevelView getLevelView() {
		return levelView;
	}

	public void setLevelView(LevelView levelView) {
		this.levelView = levelView;
	}

}
