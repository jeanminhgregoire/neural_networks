package fr.ensimag.deep.layers;

import fr.ensimag.deep.serialization.json.GradientAdjustmentParameters;
import org.ejml.simple.SimpleMatrix;

import fr.ensimag.deep.activators.IActivator;
import lombok.Getter;

public abstract class AbstractLayer {
    @Getter protected int layerSize;
    @Getter protected int previousLayerSize;
    @Getter protected int batchSize;
    @Getter protected IActivator activator;

    public AbstractLayer(int layerSize, int previousLayerSize, IActivator activator)
    {
        this.layerSize = layerSize;
        this.previousLayerSize = previousLayerSize;
        this.activator = activator;
    }
    
    /**
     * Calculate the output(s) of the layer corresponding to the associated input(s)
     * The input matrix is of size mxn where m is the previous layer size (number of neurons)
     * and n is the number of input vectors for this layer
     * @param input the input vectors
     * @return the output vectors. It is a m'*n matrix where m' is the layer size
     */
    public abstract void propagate(SimpleMatrix input);

    /**
     * Returns the current activation value of the layer (computed during the last propagate call)
     * @return the current activation
     */
    public abstract SimpleMatrix getActivation();

    /**
     * Returns the weighted error, which will be propagated to the previous layer
     * @return the weigthed error
     */
    public abstract SimpleMatrix getWeightedError();

    /**
     * Specify the batch size, aka, the number of input vectors that will be used for the next
     *  propagate call or the next learning phase
     * @param batchSize the batch size
     */
    public abstract void setBatchSize(int batchSize);

    /**
     * Returns the layer weight matrix. The matrix size depend on the 
     * previous layer and current layer size
     * @return the weights
     */
    public abstract SimpleMatrix getWeights();

    /**
     * Returns the Bias matrix. The matrix size depends on the current layer size
     * @return
     */
    public abstract SimpleMatrix getBias();

    /**
     * Weights setter
     * @param weights the new weights matric
     */
    public abstract void setWeights(SimpleMatrix weights);

    /**
     * Bias setter
     * @param bias the new bias matrix
     */
    public abstract void setBias(SimpleMatrix bias);

    /** 
     * Update the internal parameters of the layer (like weights and bias for instance)
     */
    public abstract void updateParameters();

    /**
     * Performs the backpropagation operation given the weighted errors of the upstream layer.
     * @param layerError
     */
    public abstract void backpropagate(SimpleMatrix layerError);

    public abstract void setGradientAdjustmentParameters(GradientAdjustmentParameters gradientAdjustmentParameters);

    public abstract GradientAdjustmentParameters getGradientAdjustmentParameters();

}


