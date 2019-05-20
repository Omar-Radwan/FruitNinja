package command;

import levelmodels.ILevelModel;

public class SaveBestScoreCommand implements Command {

	ILevelModel levelModel;

	public SaveBestScoreCommand(ILevelModel levelModel) {
		this.levelModel = levelModel;
	}

	@Override
	public void execute() {
		levelModel.saveBestScore();
	}

}
