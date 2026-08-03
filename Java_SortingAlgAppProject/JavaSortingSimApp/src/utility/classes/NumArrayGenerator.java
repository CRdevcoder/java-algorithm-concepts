package utility.classes;

import java.util.ArrayList;
import java.util.Random;

// Will generate random arrayList for Integer or Double.

public class NumArrayGenerator {

    private Random ranGen;
    private int seed;

    // The range of numbers that will be generated. 
    private int genStartNum;
    private int genEndNum;

    // Constructor
    public NumArrayGenerator(int seed, int min, int max)
    {
        this.seed = seed;
        // creates ranGen using given seed
        this.ranGen = new Random(seed);
        // default number range.
        this.genStartNum = min;
        this.genEndNum = max;

    }

    // Getter and Setter methods
    public int getSeed()
    {
        return seed;
    }

    public void setSeed(int seed)
    {
        this.seed = seed;
        ranGen.setSeed(seed);
    }


    // set the range of numbers that will be generated.
    public void setRange(int min, int max)
    {
        this.genStartNum = min;
        this.genEndNum = max;
    }

    // Get Random number within specific range.
    public int getIntNum(int min, int max)
    {   
        return ranGen.nextInt((max - min) + 1) + min;
    }

    public double getDoubleNum(double min, double max)
    {
        return ranGen.nextDouble((max - min)) + min;
    }

    // generate list of random Integers.
    public ArrayList<Integer> generateIntList(int size)
    {
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i < size; i++)
        {
            list.add(getIntNum(genStartNum,genEndNum));
        }

        return list;
    }

    // generate list of Doubles
    public ArrayList<Double> generateDoubleList(int size)
    {
        ArrayList<Double> list = new ArrayList<>();

        for(int i = 0; i < size; i++)
        {
            list.add(getDoubleNum(genStartNum,genEndNum));
        }
        return list;
    }

}
