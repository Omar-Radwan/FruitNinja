package command;

import levelmodels.LevelModel;

public class LoadBestScoreCommand implements Command {

	LevelModel levelModel;

	public LoadBestScoreCommand(LevelModel levelModel) {
		this.levelModel = levelModel;
	}

	@Override
	public void execute() {
		levelModel.loadBestScore();
	}

}
