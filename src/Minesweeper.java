/*
 * date of starting -> 02/03/2026
 * last updated -> 02/03/2026
 * date of completion -> NA
 * Author -> Matas Vengalis (me)
 * Version -> 1
 * Main features to implement:
 * -> board generation
 * -> tile clicking
 * -> 3 difficulties
 * -> saving best times
 * -> active timer
 * -> more?
 */

import java.util.Scanner;
import java.util.Random;

class Minesweeper
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String difficulty;
        System.out.println("welcome to minesweeper!");
        System.out.print("enter the difficult level! (easy/normal/expert): ");
        difficulty = scanner.nextLine();

        Grid grid = new Grid(difficulty);

        grid.populateGridMines();
        Tile[][] gameGrid = grid.getGameGrid();
        for (int i=0; i< gameGrid[0].length; i++)
        {
            System.out.println("");
            for (int j = 0; j<gameGrid.length; j++)
            {
                System.out.print("x ");
            }
        }
        scanner.close();
        return;
    }

}

class Grid
{
    private Tile[][] gameGrid;
    int totalMines;

    public Grid(String difficulty)
    {
        if(difficulty.equalsIgnoreCase("easy"))
        {
            totalMines = 10;
            gameGrid = new Tile[9][9];
        }
        else if(difficulty.equalsIgnoreCase("normal"))
        {
            totalMines = 40;
            gameGrid = new Tile[16][16];
        }

        else if(difficulty.equalsIgnoreCase("expert"))
        {
            totalMines = 99;
            gameGrid = new Tile[30][16];
        }

        int rows = gameGrid.length;
        int cols = gameGrid[0].length;

        for(int i = 0; i< rows; i++)
        {
            for(int j = 0; j<cols; j++)
            {
                gameGrid[i][j] = new EmptyTile();
            }
        }

    }

    public Tile[][] getGameGrid()
    {
        return gameGrid;
    }

    public void populateGridMines()
    {
        Random random = new Random();
        int mineCount = 0;
        Mine mine;
        int randomX;
        int randomY;

        while(mineCount != totalMines)
        {
            randomX = random.nextInt(gameGrid.length);
            randomY = random.nextInt(gameGrid[0].length);
            if(gameGrid[randomX][randomY] instanceof EmptyTile)
            {
                mine = new Mine();
                gameGrid[randomX][randomY] = mine;
                mineCount ++;
            }
        }
        return;
    }

    public void populateGridNumbers()
    {

    }

}

// General tile class to be inherited from
abstract class Tile
{

    abstract public void onClick();
    abstract public void onFlag();
    boolean isRevealed;
    boolean isFlagged;

}

//numbered squares 1-8
class NumberedSquare extends Tile
{

    public void onClick(){}
    public void onFlag(){}
}

// mine class
class Mine extends Tile
{

    public void onClick(){}
    public void onFlag(){}
}

// no numbers or mines
class EmptyTile extends Tile
{

    public void onClick(){}
    public void onFlag(){}
}