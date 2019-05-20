package levelmodels;

import java.util.ArrayList;
import gameobjects.GameObject;
import gameobjects.IGameObject;

public interface ILevelModel {
	public void saveBestScore();
	public void loadBestScore();
	public void decreaseLives();
	public void sliceNonFatalBomb(int indx);
	public void sliceSpecialFruit(int indx);
	public void sliceFruit(int indx);
	public IGameObject getFatalBomb();
	public IGameObject getNonFatalBomb();
	public IGameObject getRandomSpecialFruit();
	public IGameObject getRandomFruit();
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
