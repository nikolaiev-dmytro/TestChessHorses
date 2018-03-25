package com.nikolaiev;

import java.util.Objects;

public class ChessCell {
    private int x;
    private int y;

    public ChessCell(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessCell)) return false;
        ChessCell cell = (ChessCell) o;
        return x == cell.x &&
                y == cell.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "ChessCell{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
