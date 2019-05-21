package levelmodels;

import java.util.ArrayList;
import gameobjects.GameObject;
import gameobjects.IGameObject;

public interface ILevelModel {
	public void saveBestScore();
	public void loadBestScore();
	public void decreaseLives();
	public void setLives(int lives);
	public ArrayList<IGameObject> getNonFatalBombs();
	public boolean isDoubleScore();
	public void setScore(int score);
	public void setBestScore(int bestScore);
	public ArrayList<IGameObject> getSpecialFruits();
	public int getBestScore();
	public int getPathNormalDur();
	public int getPathFatalDur();
	public int getPathFruitDur();
	public int getNormaldur();
	public int getFataldur();
	public int getRepeatDur();
	public ArrayList<IGameObject> getFruits();
	public int getScore();
	public int getLives();
	public void setDoubleScore(boolean isDoubleScore);
}
