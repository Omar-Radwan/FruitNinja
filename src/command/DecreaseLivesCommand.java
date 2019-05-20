package command;

import levelmodels.LevelModel;

public class DecreaseLivesCommand implements Command {
	LevelModel levelModel;

	public DecreaseLivesCommand(LevelModel levelModel) {
		super();
		this.levelModel = levelModel;
	}

	@Override
	public void execute() {
		levelModel.decreaseLives();

	}

}
