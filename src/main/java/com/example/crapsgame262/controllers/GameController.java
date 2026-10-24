package com.example.crapsgame262.controllers;

import com.example.crapsgame262.models.Game;
import com.example.crapsgame262.models.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameController {

    private Player currentPlayer;

    private final Game game = new Game();

    @FXML
    private ImageView dice1ImageView;

    @FXML
    private ImageView dice2ImageView;

    @FXML
    private Label nicknameLabel;

    @FXML
    private Label throwLabel;

    @FXML
    private Label pointLabel;

    @FXML
    private Label gamesWonLabel;

    @FXML
    private Label gamesLostLabel;

    @FXML
    private TextArea gameLogTextArea;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        nicknameLabel.setText(this.currentPlayer.getNickname());
    }

    @FXML
    void onMouseClickedDropButton(MouseEvent event) {
        int throwValue = game.throwDice();

        dice1ImageView.setImage(
                new Image(
                        getClass().getResourceAsStream(
                                game.getDice1().getImagePath()
                        )
                )
        );

        dice2ImageView.setImage(
                new Image(
                        getClass().getResourceAsStream(
                                game.getDice2().getImagePath()
                        )
                )
        );

        refreshGameInfo(throwValue);

        appendLog("Tiro: " + throwValue + " - " + game.getStatus());
    }

    private void refreshGameInfo(int throwValue) {
        throwLabel.setText(String.valueOf(throwValue));
        pointLabel.setText(game.getPoint() > 0 ? String.valueOf(game.getPoint()) : "--");
        gamesWonLabel.setText(String.valueOf(game.getGamesWon()));
        gamesLostLabel.setText(String.valueOf(game.getGamesLost()));
    }

    private void appendLog(String message) {
        if (gameLogTextArea.getText().isEmpty()) {
            gameLogTextArea.setText(message);
        } else {
            gameLogTextArea.appendText(System.lineSeparator() + message);
        }
    }
}
