public class Board {
    private int[][] board;
    private int row, col;
    private GameFrame gf;
    public Board(int row)
    {
       gf = new GameFrame(row);
        this.row = row;
        this.col = row;
        board = new int[row][row];
    }

    public int getRow()
    {
        return this.row;
    }
    public int getCol()
    {
        return this.col;
    }

    public void set(int row, int col, int symbol)
    {
        board[row][col] = symbol;
    }
    public void move(int row, int col, Animal a)
    {
        gf.getGamePanel().addAnimal(a.getRow(),a.getCol(), 0);
        gf.getGamePanel().addAnimal(row, col, a.getSymbol());

        
    }
    
}
