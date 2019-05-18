package Controller;
import gui.related.LevelView;
import levelmodels.LevelModel;

public class Controller {

	protected LevelModel levelModel;
	protected LevelView levelView;
	protected int lives;

	public Controller(LevelModel levelModel, LevelView levelView) {
		this.levelModel = levelModel;
		this.levelView = levelView;
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
