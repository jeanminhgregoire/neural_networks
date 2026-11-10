package fr.ensimag.deep.trainer.operators;

import org.ejml.simple.SimpleMatrix;

import fr.ensimag.deep.NeuralNetwork;
import fr.ensimag.deep.trainer.costFunction.ICostFunction;

public class LearnCommand implements ICommand {

    private NeuralNetwork network;
    private ICostFunction costFunction;

    public LearnCommand(NeuralNetwork network, ICostFunction costFunction)
    {
        this.network = network;
        this.costFunction = costFunction;
    }

    @Override
    public void compute(SimpleMatrix expectedOutput, SimpleMatrix output, SimpleMatrix cost) {
        for (int i=0; i<expectedOutput.getNumRows(); i++)
            for (int j=0; j<expectedOutput.getNumCols(); j++)
                cost.set(i,j,costFunction.derivApply(output.get(i,j), expectedOutput.get(i,j)));
        network.learn(cost);
    }

}
