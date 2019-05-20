package command;

import levelmodels.LevelModel;

public class SaveBestScoreCommand implements Command {

	LevelModel levelModel;

	public SaveBestScoreCommand(LevelModel levelModel) {
		this.levelModel = levelModel;
	}

	@Override
	public void execute() {
		levelModel.saveBestScore();
	}

}
