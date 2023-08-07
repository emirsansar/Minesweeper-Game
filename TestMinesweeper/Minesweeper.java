package TestMinesweeper;

import java.util.Random;
import java.util.Scanner;

public class Minesweeper {
    int rowNum, colNum, size, numMines;
    int[][] mineMap;  // Mines are placed on this map. The player cannot see.
    int[][] board;  // The player marks the coordinates on this map.

    Random rand = new Random();
    Scanner scan = new Scanner(System.in);

    public Minesweeper(int rowNum, int colNum){
        this.rowNum=rowNum; this.colNum=colNum;
        this.size=rowNum*colNum;
        this.numMines=size/4;
        this.mineMap = new int[rowNum][colNum];
        this.board = new int[rowNum][colNum];
    }

    public void run(){
        int row, col; // for player's choice
        int success=0;
        prepareGame();

        printBoard(mineMap); // For the developer to test. the player will not see it.

        System.out.println("The game has been started.");

        while(true){
            printBoard(board);
            System.out.print("Choose row number: ");
            row = scan.nextInt();
            System.out.print("Choose col number: ");
            col = scan.nextInt();

            if( !isValidCoordinate(row, col) ){
                System.out.println("Invalid coordinate.");
                continue;
            }

            if( board[row][col] != 0 ){  // In the board array, it is checked whether the board[row][col] cell has been changed before.
                System.out.println("This coordinate has already been marked.");
                continue;
            }

            if( mineMap[row][col] != -1 ){
                check(row,col);
                success++;
                if( success == (size - numMines ) ){
                    System.out.println("Congratulations. You haven't stepped on a mine.");
                    break;
                }
            }
            else{
                System.out.println("Game over! You stepped on a mine!");
                break;
            }
        }
        scan.close();
    }

    public void prepareGame(){  // In this block, mines are randomly placed on the mineMap.
        int randRow, randCol, count=0;

        while (count != numMines ){
            randRow = rand.nextInt(rowNum);
            randCol = rand.nextInt(colNum);

            if(mineMap[randRow][randCol] != -1){
                mineMap[randRow][randCol] = -1;
                count++;
            }
        }
    }

    public void check(int r, int c){
        if( mineMap[r][c] == 0 ){

            int newRow, newCol;
            int[] dx = {0, 0, -1, +1}; // x coordinates
            int[] dy = {1, -1, 0, 0};  // y coordinates

            for(int i=0; i<4; i++){  // In this loop, it controls the four cells adjacent to board[r][c], respectively.
                newRow = r + dx[i];
                newCol = c + dy[i];

                if( isValidCoordinate(newRow, newCol) ){  // Checks if array bounds are exceeded.
                    if( mineMap[newRow][newCol] == -1){
                        board[r][c]++;
                    }
                }
            }

            if( board[r][c] == 0){
                board[r][c] = -2;  // If no mines are found in 4 cells adjacent to board[r][c], it is marked '-2'.
            }
        }
    }

    public void printBoard(int[][] arr){  // To print player's board.
        for(int i=0; i<arr.length; i++){
            for(int k=0; k<arr[0].length; k++){
                if(arr[i][k] >= 0){
                    System.out.print(" ");
                }
                System.out.print(arr[i][k] + " ");
            }
            System.out.println();
        }
    }

    public boolean isValidCoordinate(int row, int col){
        return (row >= 0 && row < rowNum) && (col >= 0 && col < colNum);
    }
}