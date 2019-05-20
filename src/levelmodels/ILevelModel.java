package levelmodels;

import java.util.ArrayList;
import gameobjects.GameObject;

public interface ILevelModel {
	public void saveBestScore();
	public void loadBestScore();
	public void decreaseLives();
	public void sliceNonFatalBomb(int indx);
	public void sliceSpecialFruit(int indx);
	public void sliceFruit(int indx);
	public GameObject getFatalBomb();
	public GameObject getNonFatalBomb();
	public GameObject getRandomSpecialFruit();
	public GameObject getRandomFruit();
	public int getBestScore();
	public int getPathNormalDur();
	public int getPathFatalDur();
	public int getPathFruitDur();
	public int getNormaldur();
	public int getFataldur();
	public int getRepeatDur();
	public ArrayList<GameObject> getFruits();
	public int getScore();
	public int getLives();
	public void setDoubleScore(boolean isDoubleScore);
}
