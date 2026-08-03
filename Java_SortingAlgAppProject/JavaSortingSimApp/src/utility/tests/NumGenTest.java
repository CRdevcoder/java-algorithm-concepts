package utility.tests;
import java.util.ArrayList;

import utility.classes.NumArrayGenerator;

public class NumGenTest {


    public static void main(String[] args) {
        System.out.println("TESTING NUMARRAYGENERATOR CLASS:");

        // Create NumArrayGenerator object with seed 1234, range 1 to 100.
        NumArrayGenerator numGen = new utility.classes.NumArrayGenerator(1234, 1, 5);

        // Generate random Integer number
        ArrayList<Integer> ranIntList = numGen.generateIntList(10);
        System.out.println("Random Integer: " + ranIntList);

        // Generate random Double number
        ArrayList<Double> randDoubleList = numGen.generateDoubleList(15);
        System.out.println("Random Double: " + randDoubleList);

    }

}
