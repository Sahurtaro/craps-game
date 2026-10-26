package com.example.crapsgame262.models;

public class Game {
    private Dice dice1;
    private Dice dice2;
    private int point;
    private int shootCount;
    private int gamesWon;
    private int gamesLost;
    private int state; // 1: Ganó, 2: Perdió, 3: Sigue jugando (Punto)

    public Game() {
        this.dice1 = new Dice();
        this.dice2 = new Dice();
        this.point = 0;
        this.shootCount = 0;
        this.gamesWon = 0;
        this.gamesLost = 0;
        this.state = 0;
    }

    /**
     * Lanza los dados, evalúa la lógica y retorna la suma obtenida.
     */
    public int rollDice() {
        dice1.roll();
        dice2.roll();
        int sum = getSum();
        shootCount++;

        if (point == 0) { // Tiro de salida
            if (sum == 7 || sum == 11) { // Gana automáticamente
                state = 1;
                gamesWon++;
            } else if (sum == 2 || sum == 3 || sum == 12) { // Pierde (Craps)
                state = 2;
                gamesLost++;
            } else { // Establece el punto
                point = sum;
                state = 3;
            }
        } else { // Tiros siguientes tras establecer el punto
            if (sum == point) { // Logró el punto -> Gana
                state = 1;
                gamesWon++;
                resetRound();
            } else if (sum == 7) { // Sacó 7 -> Pierde
                state = 2;
                gamesLost++;
                resetRound();
            } else { // Continúa intentando
                state = 3;
            }
        }
        return sum; // Retorna la suma requerida por el controlador
    }

    /**
     * Método que llama GameController.
     */
    public int throwDice() {
        return rollDice();
    }

    public void resetRound() {
        this.point = 0;
        this.shootCount = 0;
    }

    // Getters para el controlador
    public Dice getDice1() {
        return dice1;
    }

    public Dice getDice2() {
        return dice2;
    }

    public int getSum() {
        return dice1.getValue() + dice2.getValue();
    }

    public int getPoint() {
        return point;
    }

    public int getShootCount() {
        return shootCount;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public int getState() {
        return state;
    }

    public int getStatus() {
        return state;
    }
}