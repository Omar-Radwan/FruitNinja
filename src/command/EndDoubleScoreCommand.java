package command;

import levelmodels.ILevelModel;


public class EndDoubleScoreCommand implements Command {
	ILevelModel levelModel;

	public EndDoubleScoreCommand(ILevelModel levelModel) {
		this.levelModel = levelModel;
	}

	@Override
	public void execute() {
		levelModel.setDoubleScore(false);
	}

}
