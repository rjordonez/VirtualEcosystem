import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel{
    int[][] animalList;
    ArrayList<Animal> animalList2;
    
    //size of bock output
    int nOutputSize;
    int unitSize = 2;

    //1d variables
    float fNoiseSeed1D[];
    float fPerlinNoise1D[];

    //2d variables
    float fNoiseSeed2D[];
    float fPerlinNoise2D[];

    //Screen width and height variables
    int nOutputWidth;
    int nOutputHeight;

    //variables for tweaking simulation
    int nOctaveCount = 8;
    float fScalingBias = 2.0f;
    float fMode =1;

    Random rand;
    public GamePanel(int outputSize)
    {
        this.nOutputSize = outputSize;
        nOutputWidth = this.nOutputSize;
        nOutputHeight = this.nOutputSize;
        animalList2 = new ArrayList<>();
        //JPanel configurations
        this.setPreferredSize(new Dimension(nOutputWidth*unitSize*2,nOutputHeight*unitSize));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        //1D declarations
        fNoiseSeed1D = new float[nOutputSize];
        fPerlinNoise1D = new float[nOutputSize];
        Random rand = new Random();
        for (int i = 0; i<nOutputSize; i++)
        {
            
            fNoiseSeed1D[i] = rand.nextFloat();
        }

        //2D declarations
        fNoiseSeed2D = new float[nOutputSize*nOutputHeight];
        fPerlinNoise2D = new float[nOutputSize*nOutputHeight];

        animalList = new int[nOutputSize][nOutputHeight];

        for (int i = 0; i<nOutputWidth*nOutputHeight; i++)
        {
           fNoiseSeed2D[i] = rand.nextFloat();

        }
    }
    public void startGame()
    {
        
    }
    public void addAnimal(int row, int col, int num)
    {
       animalList2.add(animalStats(num));
    }
    public Animal animalStats(int num)
    {
        Animal tempAnimal = new Animal("No Animal", 0, 0, 0, 0, 0, 0); 
        switch(num)
        {
            //case 1: tempAnimal = new Animal("Bunny", 10, 100, 1, 1, 2, 1,rand.nextInt(nOutputSize),rand.nextInt(nOutputSize)); break;
            //case 2: tempAnimal = new Animal("Wolf", 10, 100, 1, 1, 2, 2,rand.nextInt(nOutputSize),rand.nextInt(nOutputSize)); break;
            //case 3: tempAnimal = new Animal("Dragon", 100, 1000, 1, 1, 2, 3,rand.nextInt(nOutputSize),rand.nextInt(nOutputSize)); break;
        }
        return tempAnimal;
    }

    void PerlinNoise1D(int nCount, float[] fSeed, int nOctaves, float[] fOutput, float bias)
    {
        
        for(int x = 0;x<nCount; x++)
        {
            float fNoise = 0.0f;
            float fScale = .1f;
            float fScaleAcc = 0.0f;
            for(int o = 0;o<nOctaves; o++)
            {
                int nPitch = nCount;
                int nSample1 = 0;
                //java null
                if(!(o==0))
                {
                    nPitch = nCount/o;
                    nSample1 = (x/nPitch) * nPitch;
                }
                int nSample2 = (nSample1 + nPitch) % nCount;
                float fBlend = (float)(x-nSample1)/(float)nPitch;
                if(fBlend >1.0f)
                {
                    fBlend = .99f;
                    System.out.println(1);
                }
                float fSample = (1.0f - fBlend) * fSeed[nSample1]+ fBlend*fSeed[nSample2];
                fNoise += fSample *fScale;
                fScaleAcc += fScale;
                fScale = fScale/bias;
            }
            
            fOutput[x] = fNoise/fScaleAcc;
            if(fNoise/fScaleAcc>1.0f)
            {
                fOutput[x] = .99f;
            }
        }
    }

    void PerlinNoise2D(int nWidth,int nHeight, float[] fSeed, int nOctaves, float[] fOutput, float bias)
    {
        for(int x = 0;x<nWidth; x++)
        {
            for(int y = 0;y<nHeight; y++)
            {
                float fNoise = 0.0f;
                float fScale = .1f;
                float fScaleAcc = 0.0f;

                int nPitch = 0;
                int nSampleX1 = 0;
                int nSampleY1 = 0;

                for(int o = 0;o<nOctaves; o++)
                {
                    if(!(o==0))
                    {
                        nPitch = nWidth/o;
                        nSampleX1 = (x/nPitch) * nPitch;
                        nSampleY1 = (y/nPitch) * nPitch;
                    }

                    int nSampleX2 = (nSampleX1 + nPitch) % nWidth;
                    int nSampleY2 = (nSampleY1 + nPitch) % nWidth;

                    float fBlendX = (float)(x-nSampleX1)/(float)nPitch;
                    float fBlendY = (float)(y-nSampleY1)/(float)nPitch;

                    if(fBlendX >1.0f) fBlendX = .99f;            
                    if(fBlendY  >1.0f)  fBlendY = .99f;
        
                    float fSample1 = (1.0f - fBlendX) * fSeed[nSampleY1*nWidth+nSampleX1]+ fBlendX*fSeed[nSampleY1*nWidth+nSampleX2];
                    float fSample2 = (1.0f - fBlendX) * fSeed[nSampleY2*nWidth+nSampleX1]+ fBlendX*fSeed[nSampleY2*nWidth+nSampleX2];

                    fNoise += (fBlendY*(fSample2-fSample1)+fSample1) *fScale;
                    fScaleAcc += fScale;
                    fScale = fScale/bias;
                    
                }
                fOutput[y*nWidth+x] = fNoise/fScaleAcc;
            }
        }

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
      //  drawAnimal(g);
    }

    /* 
     *  
     * IDEA
     * have a list of animals then move then individually through there
     * 
    */

    public void draw(Graphics g)
    {
        if(fMode ==1)
        {
            
            PerlinNoise1D(nOutputSize, fNoiseSeed1D, nOctaveCount, fPerlinNoise1D, fScalingBias);
            for(int x = 0;x<nOutputSize;x++)
            {
                g.setColor(Color.green);
                //should get us a number between mid point and top
                int y = (int)((fPerlinNoise1D[x]*(float)nOutputSize/2.0f));
                //draws 1D image
                for(int f = y; f<nOutputSize/2;f++) g.drawRect(x*unitSize, f*unitSize, unitSize, unitSize);
            }
        }
        else if(fMode ==2)
        {
          PerlinNoise2D(nOutputWidth,nOutputHeight, fNoiseSeed2D, nOctaveCount, fPerlinNoise2D, fScalingBias);
            //loops through entire screen
            for(int x = 0; x<nOutputWidth; x++)
            {
                for(int y = 0; y<nOutputHeight;y++)
                {
                    Color gradient = Color.BLACK;
                    //picks a number with perlin noise between 1-255, formula used to find specific location on axis
                    int pixel_bw = (int)(fPerlinNoise2D[y * nOutputWidth + x] * 255);

                    //color out of bound cases
                    if(pixel_bw>255) pixel_bw=255;
                    else if(pixel_bw<0) pixel_bw=0;

                    //colors for water, sand, grass
                
                    if(pixel_bw<130)  gradient = new Color(pixel_bw,255,pixel_bw);
                    else if(pixel_bw<150) gradient = new Color(194, 178,128);
                    else gradient = new Color(pixel_bw/2,pixel_bw/2,255);



                    //set colors and fill pixels
                    g.setColor(gradient);
                    g.fillRect(x*2, y*2, 2, 2);
                    
                }

            }
         }
    
    }
    //will change with idea because of incredible inefficency
    void drawAnimal(Graphics g)
    {
        for(int x = 0; x<animalList.length; x++)
        {
            for(int y = 0; y<animalList[0].length; y++)
            {
                if(animalList[x][y]==5)
                {
                    g.setColor(Color.blue);
                    g.drawRect(x,y,10,10);
                }
                if(animalList[x][y]==3)
                {
                    g.setColor(Color.red);
                    g.drawRect(x,y,10,10);
                }
            }
        }
        repaint();
    }
    /*Key Checker
     * up, and down -> changes octaves
     * left, and right -> changes bias (blending)
     * Key numbers 1 & 2-> changes modes 1&2
     * R -> randomizes Perlin Noise values
    */
    public class MyKeyAdapter extends KeyAdapter
    {
        
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch(e.getKeyCode())
            {
                
                case KeyEvent.VK_DOWN: nOctaveCount--; break;
                case KeyEvent.VK_UP: nOctaveCount++; break;
                case KeyEvent.VK_LEFT: fScalingBias-=0.1f; break;
                case KeyEvent.VK_RIGHT: fScalingBias+=0.1f; break;
                case KeyEvent.VK_1: fMode=1; break;
                case KeyEvent.VK_2: fMode=2; break;
                case KeyEvent.VK_R:
                    rand = new Random();
                    if(fMode==1)
                    {
                        for (int i = 0; i<nOutputSize; i++)
                        {
                            fNoiseSeed1D[i] = (rand.nextFloat());
                        }
                    }
                    else if(fMode==2)
                    {
                    for (int i = 0; i<nOutputHeight*nOutputWidth; i++)
                        {
                            fNoiseSeed2D[i] =(rand.nextFloat());
                        }
                    }
                    break;
                
               

                    
            }
            
            if(fScalingBias<= 0.1f) fScalingBias = 0.1f;
            if(nOctaveCount==1000 || nOctaveCount==0) nOctaveCount =1;
            
            repaint();
        }
    }
}
    