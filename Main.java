public class Main 
{
    public static void main(String[] args) throws InterruptedException
    {
        //factors of 2 per the Perlin Noise
        int outputSize = 256;

        
        Board b = new Board(outputSize);

        ControlCenter cc = new ControlCenter(b);

        //cc.addRandAnimal(5);

        
        while(true)
        {
            cc.moveAllAnimals();
            Thread.sleep(100);
        }
    }
    
}