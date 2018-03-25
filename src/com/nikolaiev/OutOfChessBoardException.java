package com.nikolaiev;

public class OutOfChessBoardException  extends Exception{
    public OutOfChessBoardException() {
        super();
    }

    public OutOfChessBoardException(String message) {
        super(message);
    }
}
