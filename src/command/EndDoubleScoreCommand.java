package command;

import levelmodels.LevelModel;

public class EndDoubleScoreCommand implements Command {
	LevelModel levelModel;

	public EndDoubleScoreCommand(LevelModel levelModel) {
		this.levelModel = levelModel;
	}

	@Override
	public void execute() {
		levelModel.setDoubleScore(false);
	}

}
