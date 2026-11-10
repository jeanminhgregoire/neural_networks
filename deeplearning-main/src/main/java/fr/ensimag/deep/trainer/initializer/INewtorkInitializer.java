package fr.ensimag.deep.trainer.initializer;

import fr.ensimag.deep.NeuralNetwork;

public interface INewtorkInitializer {
    /**
     * Weight and bias initialzation of the considering Neural Network
     * @param network : the network to initialize
     */
    public void init(NeuralNetwork network);
}