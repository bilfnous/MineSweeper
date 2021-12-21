
import java.util.*;


public class MineSweeper {

    private static final int ROW = 9;
    private static final int COLUMN = 9;
    private static final int MINES = 10;

    private int[][] grid = new int[ROW] [COLUMN];

    public MineSweeper() {
        initMines();
        mineIndicator();
    }

    public void setFlag(int row, int col) {
        grid[row][col] = 0;  
    }

    public void setBlock(int row, int col, int val) {
        grid[row][col] = val;
    }

    public int getBlock(int row, int col) {
        return grid[row][col];
    }

    public int random(){
        Random rand = new Random();
        return rand.nextInt(9);
    }

    public void initMines() {
        int i = 0, row = 0, col = 0;
        while(i < MINES){
            row = random();
            col = random();
            if(grid[row][col] != -1){
                grid[row][col] = -1;
                i++;    
            }
        }
    }

    public void mineIndicator() {
        int row = 0, col = 0;
        for (row = 0; row < ROW; row++){
            for (col = 0; col < COLUMN; col++){
                if(grid[row][col] == -1) {
                    if ((col - 1) >= 0)
                        grid[row][col - 1] += 1;
                    if ((col + 1) < COLUMN)
                        grid[row][col + 1] += 1;
                    if((row - 1) >= 0)
                        grid[row - 1][col] += 1;
                    if( ((row - 1) >= 0) && ((col + 1) < COLUMN) )
                        grid[row - 1][col + 1] += 1;
                    if( ((row - 1) >= 0) && ((col - 1) >= 0) )
                        grid[row - 1][col - 1] += 1;
                    if((row + 1) < ROW)
                        grid[row + 1][col] += 1;
                    if( ((row + 1) < ROW) && ((col + 1) < COLUMN) )
                        grid[row + 1][col + 1] += 1; 
                    if( ((row + 1) < ROW) && ((col - 1) >= 0) )
                        grid[row + 1][col - 1] += 1;      
                }
            }
        }
    }


    @Override
    public String toString() {
        System.out.print("\n\n");
        for(int i = 0 ; i < ROW; i++) {
            for(int j = 0 ; j < COLUMN; j++) {
                System.out.print(getBlock(i, j) + "     ");
            }
            System.out.println();
        }
        System.out.print("\n\n");
        return null;
    }

}