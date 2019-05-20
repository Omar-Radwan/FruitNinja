package Controller;

import gameobjects.GameObject;
import gui.related.LevelView;
import levelmodels.ILevelModel;
import levelmodels.LevelModel;

public interface IController {

	public int getRepeatDur();

	public int getFataldur();

	public int getNormaldur();

	public int getPathFruitDur();

	public int getPathFatalDur();

	public int getPathNormalDur();

	public void endDoubleScore();

	public void sliceFruit(int indx);
	
	public void checkIfIsSliced(int index);

	public void sliceSpecialFruit(int indx);

	public void sliceFatalBomb();

	public void sliceNonFatalBomb(int indx);

	public void setLevelView(LevelView levelView);

	public void setLevelModel(ILevelModel levelModel);

	public GameObject getFruit();

	public GameObject getFatalBomb();

	public GameObject getSpecialFruit();

	public GameObject getNonFatalBomb();

}
