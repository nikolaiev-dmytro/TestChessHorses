package com.nikolaiev;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("There are Chess Board 500x500. You need input X and Y of two horses (from 1 to 500), and result is minimum count of move for each horse to meet");
        int chessBoard[][] = new int[500][500];
        int deltaX[] = {-1, -1, 1, 1, -2, -2, 2, 2};
        int deltaY[] = {2, -2, 2, -2, 1, -1, 1, -1};
        ChessCell[] horses = new ChessCell[2];
        try {
            horses = init();
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input, try again");
            return;
        } catch (OutOfChessBoardException e) {
            System.out.println(e.getMessage());
            return;
        }
        ChessCell firstHorse = horses[0];
        ChessCell secondHorse = horses[1];
        try {
            horseMoving(chessBoard, deltaX, deltaY, firstHorse, secondHorse);
        } catch (ImpossibleMeetException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void horseMoving(int[][] chessBoard, int[] deltaX, int[] deltaY, ChessCell firstHorse, ChessCell secondHorse) throws ImpossibleMeetException {
        if ((firstHorse.getX() + firstHorse.getY()) % 2 == (secondHorse.getX() + secondHorse.getY()) % 2) {
            chessBoard[firstHorse.getY()][firstHorse.getY()] = 0;
            Queue<ChessCell> queue = new LinkedList<>();
            queue.add(new ChessCell(firstHorse.getX(), firstHorse.getY()));
            ChessCell cell;
            while (!queue.isEmpty()) {
                cell = queue.peek();
                int x = cell.getX();
                int y = cell.getY();
                if (x == secondHorse.getX() && y == secondHorse.getY()) break;
                queue.remove();
                for (int i = 0; i < 8; i++) {
                    if (x + deltaX[i] >= 0 && x + deltaX[i] < 500 && y + deltaY[i] >= 0 && y + deltaY[i] < 500 && chessBoard[x+deltaX[i]][y+deltaY[i]]==0) {
                        queue.add(new ChessCell(x + deltaX[i], y + deltaY[i]));
                        chessBoard[x + deltaX[i]][y + deltaY[i]] = chessBoard[x][y] + 1;
                    }
                }
            }
            if (chessBoard[secondHorse.getY()][secondHorse.getY()] % 2 == 0) {
                System.out.println("First horse [" + (firstHorse.getX()+1) + "," + (firstHorse.getY()+1) + "] and Second horse [" + (secondHorse.getX()+1) + "," + (secondHorse.getY()+1) + "] will meet if each of them do " + chessBoard[secondHorse.getX()][secondHorse.getY()] / 2 + " moves");

            } else {
                throw new ImpossibleMeetException();
            }
        } else {
            throw new ImpossibleMeetException();
        }
    }

    private static ChessCell[] init() throws InputMismatchException, OutOfChessBoardException {
        Scanner in = new Scanner(System.in);
        System.out.println("Input X-coordinate for first horse");
        int nx = in.nextInt();
        if (nx < 1 || nx > 500) {
            throw new OutOfChessBoardException("Coordinate is out of ChessBoard, try again");
        }
        System.out.println("Input Y-coordinate for first horse");
        int ny = in.nextInt();
        if (ny < 1 || ny > 500) {
            throw new OutOfChessBoardException("Coordinate is out of ChessBoard, try again");
        }
        System.out.println("Input X-coordinate for second horse");
        int tx = in.nextInt();
        if (tx < 1 || tx > 500) {
            throw new OutOfChessBoardException("Coordinate is out of ChessBoard, try again");
        }
        System.out.println("Input Y-coordinate for second horse");
        int ty = in.nextInt();
        if (ty < 1 || ty > 500) {
            throw new OutOfChessBoardException("Coordinate is out of ChessBoard, try again");
        }
        ChessCell firstHorse = new ChessCell(nx-1, ny-1);
        ChessCell secondHorse = new ChessCell(tx-1, ty-1);
        return new ChessCell[]{firstHorse, secondHorse};
    }
}
