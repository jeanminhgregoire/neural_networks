package fr.ensimag.deep.trainer.initializer;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.ejml.simple.SimpleMatrix;

import fr.ensimag.deep.NeuralNetwork;
import fr.ensimag.deep.layers.AbstractLayer;

/**
 * Random initialization of weights
 */
public class GaussianInitializer implements INewtorkInitializer {
    private NormalDistribution distribution = null;

    public GaussianInitializer()
    {
        this.distribution = new NormalDistribution();
    }

    /** 
     * Random initialization of weights and bias of the layers of the network
     * Weights are drawn from a Gaussian distribution with standard deviation 
     * of 1/sqrt(n) where n is the size of the previous layer
     * distribution.sample() draws a number from a gaussian distribution centered on 0
     * with a standard deviation of 1
     * @param neuralNetwork
     */
    public void init(NeuralNetwork neuralNetwork)
     {
        //throw new UnsupportedOperationException("not yet implemented");
         for (AbstractLayer layer : neuralNetwork.getLayers()) {
             int previousLayerSize = layer.getPreviousLayerSize();
             double stddev = 1.0 / Math.sqrt(previousLayerSize);

             SimpleMatrix newWeights = new SimpleMatrix(layer.getWeights().getNumRows(), layer.getWeights().getNumCols());
             SimpleMatrix newBias = new SimpleMatrix(layer.getBias().getNumRows(), layer.getBias().getNumCols());

             for (int i = 0; i < newWeights.getNumElements(); i++) {
                 newWeights.set(i, distribution.sample() * stddev);
             }
             for (int i = 0; i < newBias.getNumElements(); i++) {
                 newBias.set(i, distribution.sample() * stddev);
             }

             layer.setWeights(newWeights);
             layer.setBias(newBias);
         }
     }
}
