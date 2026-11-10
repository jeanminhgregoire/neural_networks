package fr.ensimag.deep.examples;

import java.io.IOException;
import org.ejml.simple.SimpleMatrix;

import fr.ensimag.deep.NeuralNetwork;

public class AndFromFile {
    public static void main(String args[]) throws IOException
    {
        // NeuralNetwork n = NeuralNetwork.load("Examples/and-trained.json");
        NeuralNetwork n = NeuralNetwork.fromDescription("Examples/TrainingConsole/and_network.json");

        SimpleMatrix inputs = new SimpleMatrix(new double[][] {
            {0, 0, 1, 1},
            {0, 1, 0, 1}
        } );

        n.setBatchSize(4);
        n.propagate(inputs);
        System.out.println("Inputs:");
        System.out.println(inputs);
        System.out.println("Outputs:");
        System.out.println(n.getOutput());

        // When loading the network from a file works, then
        // implement the save methods and uncomment the following line
        n.save("res.json");
    }
}
