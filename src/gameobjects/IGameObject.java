package gameobjects;

import javafx.scene.image.Image;

public interface IGameObject {
	public Image[] getImages();

	public void slice();

	public void setSliced(boolean isSliced);

	public boolean isSliced();
}
