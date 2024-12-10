package com.example.alcatraz_client.data;

public class RestMove {
    private int playerId;
    private int prisonerId;
    private int rowOrCol;
    private int row;
    private int col;

    public RestMove(int playerId, int prisonerId, int rowOrCol, int row, int col) {
        this.col = col;
        this.playerId = playerId;
        this.prisonerId = prisonerId;
        this.row = row;
        this.rowOrCol = rowOrCol;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRowOrCol() {
        return rowOrCol;
    }

    public void setRowOrCol(int rowOrCol) {
        this.rowOrCol = rowOrCol;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getPrisonerId() {
        return prisonerId;
    }

    public void setPrisonerId(int prisonerId) {
        this.prisonerId = prisonerId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
