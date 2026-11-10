package fr.ensimag.deep.trainer;

import org.ejml.simple.SimpleMatrix;

import fr.ensimag.deep.NeuralNetwork;
import fr.ensimag.deep.trainer.costFunction.ICostFunction;
import fr.ensimag.deep.trainer.dataShufflers.IDataShuffler;
import fr.ensimag.deep.trainer.operators.ErrorCommand;
import fr.ensimag.deep.trainer.operators.ICommand;
import fr.ensimag.deep.trainer.operators.LearnCommand;
import fr.ensimag.deep.utils.DataMatrix;
import lombok.Getter;

/** 
 * Helper class to execute a training or a validation phase on a 
 * while set of data. The trainig phase corresponds to an epoch
 * and will be done using the batch size property of he network 
 * (mini batch approach)
 */
public class NetworkTrainer {

    /**
     * The neural network associated to the trainer
     */
    @Getter private NeuralNetwork network;

    /**
     * The cost function that will be used during the 
     * learning phase (its derivative values) or the 
     * evaluation (the function value)
     */ 
    @Getter private ICostFunction costFunction;

    /**
     * Randomly shuffles a data set
     */
    @Getter private IDataShuffler dataShuffler;

    /**
     * The cost matrix corresponding to the learn (derivative of the cost function) 
     * or validation (cost function) phase
     */
    @Getter private SimpleMatrix costMatrix;

    public NetworkTrainer(NeuralNetwork network, ICostFunction costFunction, IDataShuffler dataShuffler)
    {
        this.network = network;
        this.costFunction = costFunction;
        this.dataShuffler = dataShuffler;
    }

    /**
     * Execute one step of training on all the data (one epoch)
     * @param data the data (input and corresponding output)
     */
    public void train(DataMatrix data)
    {
        LearnCommand command = new LearnCommand(network, costFunction);
        this.run(data, command);
    }

    /**
     * Execute one step of validation on all the data  
     * @param data the data (input and output)
     * @return the error value for all the data
     */
    public double validate(DataMatrix data)
    {
        int nbInputs = data.getInputs().getNumCols();
        ErrorCommand command = new ErrorCommand(this.costFunction);
        this.run(data, command);
        return command.getError() / nbInputs;
    }    

    private void run(DataMatrix data, ICommand command)
    {   
        int batchSize = network.getBatchSize();
        DataMatrix shuffledData = this.dataShuffler.shuffle(data);
        SimpleMatrix inputs = shuffledData.getInputs();
        SimpleMatrix outputs = shuffledData.getOutputs();
        int totalInputs = inputs.getNumCols();
        int batchNb = totalInputs / batchSize;
        int remainingBatchSize = totalInputs % batchSize;
        int inputDimension = inputs.getNumRows();
        int outputDimension = outputs.getNumRows();
        this.costMatrix = new SimpleMatrix(outputDimension, batchSize);
        
        for (int i = 0; i < batchNb; i++)
        {
            SimpleMatrix batchMatrix = inputs.extractMatrix(0, inputDimension, i * batchSize, (i+1)*batchSize);
            network.propagate(batchMatrix);
            SimpleMatrix expectedOutputMatrix = outputs.extractMatrix(0, outputDimension, i * batchSize, (i+1)*batchSize);
            SimpleMatrix actualOutputMatrix = network.getOutput();
            command.compute(expectedOutputMatrix, actualOutputMatrix, costMatrix);
        }
        if (remainingBatchSize != 0)
        {
            setBatchSize(remainingBatchSize);
            SimpleMatrix remainingBatchMatrix = inputs.extractMatrix(0, inputDimension, batchNb * batchSize, totalInputs);
            network.propagate(remainingBatchMatrix);
            SimpleMatrix remainingExpectedOutputMatrix = outputs.extractMatrix(0, outputDimension, batchNb * batchSize, totalInputs);
            SimpleMatrix remainingActualOutputMatrix = network.getOutput();
            command.compute(remainingExpectedOutputMatrix, remainingActualOutputMatrix, costMatrix);
            setBatchSize(batchSize);
        }
    }

    private void setBatchSize(int batchSize)
    {
        network.setBatchSize(batchSize);
        this.costMatrix = new SimpleMatrix(network.getInputSize(), batchSize);
    }
}
