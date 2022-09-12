public class Animal {
    private String name;
    private int age, health, speed;
    private int row,col;
    private final int genetic, movement, symbol;
    public Animal(String name, int age, int health, int speed, int genetic, int movement, int symbol)
    {
        this.name = name;
        this.age = age;
        this.health = health;
        this.speed = speed;
        this.genetic = genetic;
        this.movement = movement;
        this.symbol = symbol;
    }
    public Animal(String name, int age, int health, int speed, int genetic, int movement, int symbol, int row, int col)
    {
        this.name = name;
        this.age = age;
        this.health = health;
        this.speed = speed;
        this.genetic = genetic;
        this.movement = movement;
        this.symbol = symbol;
        this.row = row;
        this.col = col;
    }
    public int getGenetic()
    {
        return this.genetic;
    }
    public int getRow()
    {
        return this.row;
    }
    public int getCol()
    {
        return this.col;
    }
    public int getSpeed()
    {
        return this.speed;
    }
    public int getMovement()
    {
        return this.movement;
    }
    public int getAge()
    {
        return this.age;
    }
    public int getSymbol()
    {
        return this.symbol;
    }

    public int getHealth()
    {
        return this. health;
    }

    public String getName()
    {
        return this.name;
    }
    public void setRow(int r)
    {
        this.row = r;
    }

    public void setCol(int c)
    {
        this.col = c;
    }
    
    public void setSpeed(int s)
    {
        this.speed = s;
    }

    public void setHealth(int h)
    {
        this.health = h;
    }

    public void setAge(int a)
    {
        this.age = a;
    }

}
