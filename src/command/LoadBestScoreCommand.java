package command;

import levelmodels.ILevelModel;

public class LoadBestScoreCommand implements Command {

	ILevelModel levelModel;

	public LoadBestScoreCommand(ILevelModel levelModel) {
		this.levelModel = levelModel;
	}

	@Override
	public void execute() {
		levelModel.loadBestScore();
	}

}
