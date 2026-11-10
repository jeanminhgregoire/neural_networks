package fr.ensimag.deep.examples;

import java.io.IOException;
import org.ejml.simple.SimpleMatrix;

import fr.ensimag.deep.NeuralNetwork;
import fr.ensimag.deep.activators.LeakyRelu;
import fr.ensimag.deep.layers.StandardLayer;

/** 
 * Static creation of a neural network (2 inputs, one output, one hidden layer) 
 * with initialized weights to compute a boolean AND function
 */
public class And {
    public static void main(String args[]) throws IOException
    {
        // NeuralNetwork n = NeuralNetwork.load("Examples/and-trained.json");
        NeuralNetwork n = new NeuralNetwork(2);

        // creating the hidden layer(size : 2 neurons). As the weight matrix has 
        // two row, it means that the previous layer (the input layer 
        // which is not explicitely created) has a dimension of 2

        // Weight matrix for the hidden layer
        double[][] w1 = {
            {
                0.9311704048957733,
                -0.11429868803825326
            },
            {
                0.9302798998973131,
                -0.9761685924220824
            }
        };
        // Bias for the hidden layer
        double[] b1 = {
            -0.5058334472697964,
            -0.000008328442276776526
        };

        SimpleMatrix mw1 = new SimpleMatrix(w1);
        SimpleMatrix mb1 = new SimpleMatrix(b1);
        StandardLayer l1 = new StandardLayer(mw1, mb1, new LeakyRelu());

        // Second layer : the output layer. 

        double[][] w2 = {
            {
                1.1138955620990865
            },
            {
                -0.1531486682736007
            }   
        };
        double[] b2 = {
            -0.5116730749023093
        };

        SimpleMatrix mw2 = new SimpleMatrix(w2);
        SimpleMatrix mb2 = new SimpleMatrix(b2);
        StandardLayer l2 = new StandardLayer(mw2, mb2, new LeakyRelu());

        n.addLayer(l1).addLayer(l2);

        /* test of a bunch of input */
        SimpleMatrix inputs = new SimpleMatrix(new double[][] {
            {0, 0, 1, 1},
            {0, 1, 0, 1}
        } );

        // We compute the 4 test values on 1 propagate call
        n.setBatchSize(4);
        n.propagate(inputs);
        System.out.println(inputs);
        System.out.println(n.getOutput());
    }
}
