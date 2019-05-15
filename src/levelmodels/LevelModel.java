package levelmodels;

import java.util.Random;

import gameobjects.Banana;
import gameobjects.FatalBomb;
import gameobjects.GameObject;
import gameobjects.Mango;
import gameobjects.NonFatalBomb;

public abstract class LevelModel {

	protected Random r = new Random();
	protected int repeatDur;
	protected int fataldur;
	protected int normaldur;
	protected int pathFruitDur;
	protected int pathFatalDur;
	protected int pathNormalDur;

	public LevelModel() {

	}

	public GameObject getFruit() {
		int i = r.nextInt(3);

		if (i == 0)
			return new Banana();
		else if (i == 1)
			return new Mango();
		else
			return new Mango(); // remember the bati5a
	}

	public GameObject getNormalBomb() {
		return new NonFatalBomb();
	}

	public GameObject getFatalBomb() {
		return new FatalBomb();
	}

	public Random getR() {
		return r;
	}

	public void setR(Random r) {
		this.r = r;
	}

	public int getRepeatDur() {
		return repeatDur;
	}

	public void setRepeatDur(int repeatDur) {
		this.repeatDur = repeatDur;
	}

	public int getFataldur() {
		return fataldur;
	}

	public void setFataldur(int fataldur) {
		this.fataldur = fataldur;
	}

	public int getNormaldur() {
		return normaldur;
	}

	public void setNormaldur(int normaldur) {
		this.normaldur = normaldur;
	}

	public int getPathFruitDur() {
		return pathFruitDur;
	}

	public void setPathFruitDur(int pathFruitDur) {
		this.pathFruitDur = pathFruitDur;
	}

	public int getPathFatalDur() {
		return pathFatalDur;
	}

	public void setPathFatalDur(int pathFatalDur) {
		this.pathFatalDur = pathFatalDur;
	}

	public int getPathNormalDur() {
		return pathNormalDur;
	}

	public void setPathNormalDur(int pathNormalDur) {
		this.pathNormalDur = pathNormalDur;
	}

}
