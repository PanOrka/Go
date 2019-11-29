package Trash;

import UIBuilders.UI_GameFieldBuilder;
import UIComponents.UI_GameBoard;

public class UI_GameFieldDirector {
	
	public UI_GameBoard buildGameField(UI_GameFieldBuilder gameFieldBuilder) {
		
		UI_GameBoard gameBoard = new UI_GameBoard();
		
		gameBoard.setVerFieldAmount(gameFieldBuilder.getVerFieldAmount());
		gameBoard.setHorFieldAmount(gameFieldBuilder.getHorFieldAmount());
		gameBoard.setWindowSizeX(gameFieldBuilder.getWindowSizeX());
		gameBoard.setWindowSizeY(gameFieldBuilder.getWindowSizeY());
		
		return gameBoard;
	}
}
