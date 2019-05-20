package command;


import levelmodels.ILevelModel;


public class DecreaseLivesCommand implements Command {
	ILevelModel levelModel;

	public DecreaseLivesCommand(ILevelModel levelModel) {
		super();
		this.levelModel = levelModel;
	}

	@Override
	public void execute() {
		levelModel.decreaseLives();

	}

}
