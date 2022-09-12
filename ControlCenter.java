import java.util.ArrayList;
import java.util.Random;

public class ControlCenter {
    private Board b;
    private Random rand;
    private ArrayList<Animal> animalList;
    public ControlCenter(Board b)
    {
        this.b = b;
        this.rand = new Random();
        animalList = new ArrayList<>();
    }
    public Board getBoard()
    {
        return this.b;
    }
    //add animal at specific location
    public void addAnimal(int row, int col, Animal a)
    {
        b.set(row, col, a.getSymbol());
        a.setRow(row);
        a.setCol(col);
        animalList.add(a);
    }
    //add the amount(num) of random animals
    public void addRandAnimal(int num)
    {
        int total = 0;
        if (num%2!=0)
            total= -1;
        for (int i = 0; i< num; i++)
        {
            if(num/2 <= total)
            {
                b.set(rand.nextInt(b.getRow()), rand.nextInt(b.getCol()), 5);
                animalList.add(new Animal("Bunny", 10, 100, 1, 1, 2, 5,b.getRow()/2,b.getCol()/2));
            }
            else
            {
                b.set(rand.nextInt(b.getRow()), b.getCol()/2, 3);
                animalList.add(new Animal("Wolf", 10, 100, 1, 1, 2, 3,b.getRow()/2,b.getCol()/2));
            }
            total++;
        }
        
    }
    public void moveAllAnimals()
    {
        for(Animal a: animalList)
        {
            moveAnimal(a);
        }
    }
    public void moveAnimal(Animal a)
    {
        int var = rand.nextInt(4);
        int row = a.getRow();
        int col = a.getCol();
            switch (var)
        {
            case 0:
            if(col-1>0)
            {
                b.move(row, col-1, a);
                a.setCol(col-1);;
            }
            break;
            case 1:
            if(col+1 != b.getCol())
            {
                b.move(row, col+1, a);
                a.setCol(col+1);
            }
            break;
            case 2:
            if(row-1 != 0)
            {
                b.move(row-1, col, a);
                a.setRow(row-1);
            }
            break;
            case 3:
            if(row+1 != b.getCol())
            {
                b.move(row+1, col, a);
                a.setRow(row+1);
            }
            break;
        }
        
        
    }
}
