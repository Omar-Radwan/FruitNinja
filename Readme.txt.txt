We divided the work among us as follow :
1 - Omar and mohamed worked on the logic
2 - nour, esraa, and ziad worked on the Gui

Assumptions needed to run the program :
1 - when frutis fall it decreases one life in normal mode but they don't decrease lives in arcade mode
2 - in arcade mode only normal bombs exist and they decrease the score by 5
3 - in normal mode two types of bombs exist, the fatal bomb ends the game instantly and the normal one decrases one life
4 - there are three levels of difficulty in normal mode ,easy, medium and hard 
5 - overall there are three types of normal fruits and two types of special fruits one for doubling the score and the other for increasing lives
6 - we have used five design patterns in the project :
							1- singleton: for having single instance of factory classes
							2- factory: to return instances of gameobjects
							3- mvc:	to easily link between the gui and logic of the game
							4- command: to allow controller to send commands to the level model
							5- strategy: to put instance of ILevelModel in the controller and this instance determines which type of level it belongs to during the runtime
