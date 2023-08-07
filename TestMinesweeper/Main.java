package TestMinesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int row, column;

        System.out.println("Welcome to the Minesweeper!");
        System.out.println("Enter the dimensions you want to play.");
        System.out.print("Enter the row: ");
        row = scan.nextInt();
        System.out.print("Enter the col: ");
        column = scan.nextInt();

        Minesweeper game = new Minesweeper(row, column);
        game.run();

        scan.close();
    }
}
