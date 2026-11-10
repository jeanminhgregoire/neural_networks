package fr.ensimag.deep.layers;

import fr.ensimag.deep.serialization.json.GradientAdjustmentParameters;
import fr.gradientAdjustmentParams.GradientDescentHandler;
import fr.gradientAdjustmentParams.GradientDescentFactory;
import org.ejml.simple.SimpleMatrix;

import fr.ensimag.deep.activators.IActivator;

public class StandardLayer extends AbstractLayer {

    protected SimpleMatrix activation;
    protected SimpleMatrix weights;
    protected SimpleMatrix bias;
    protected SimpleMatrix zetaMatrix;
    protected SimpleMatrix gradientWeights;
    protected SimpleMatrix gradientBias;
    protected GradientDescentHandler handler;
    protected SimpleMatrix weightedError;
    protected SimpleMatrix input;
    protected GradientAdjustmentParameters gradientAdjustmentParameters;

    public StandardLayer(SimpleMatrix initialWeights, SimpleMatrix initialBias, IActivator activator) {
        super(initialBias.getNumRows(), initialWeights.getNumRows(), activator);
        setWeights(initialWeights);
        setBias(initialBias);
    }

    @Override
    public void propagate(SimpleMatrix input) {
        SimpleMatrix onesRow = SimpleMatrix.ones(1, input.getNumCols());
        SimpleMatrix res = (getWeights().transpose().mult(input)).plus(this.getBias().mult(onesRow));
        this.zetaMatrix = res;
        this.input = input;
        this.activation = res;
        // throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public SimpleMatrix getActivation() {
        for (int i = 0; i < this.activation.getNumRows(); i++) {
            for (int j = 0; j < activation.getNumCols(); j++) {
                double value = activation.get(i, j);
                this.activation.set(i, j, activator.phi(value));
            }
        }
        return this.activation;
        // throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public SimpleMatrix getWeightedError() {
        return this.weightedError;
        // throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
        // to be completed
    }

    @Override
    public void updateParameters() {
        SimpleMatrix regTerm = weights.scale(0.01);

        this.weights = this.weights.plus(handler.handleGradient(gradientWeights, true)).minus(regTerm);

        this.bias = this.bias.plus(handler.handleGradient(gradientBias, false));
    }

    @Override
    public void backpropagate(SimpleMatrix upperWeightedError) {

        SimpleMatrix zDerivative = new SimpleMatrix(upperWeightedError.getNumRows(), upperWeightedError.getNumCols());
        for (int i = 0; i < zDerivative.getNumRows(); i++) {
            for (int j = 0; j < zDerivative.getNumCols(); j++) {
                zDerivative.set(i, j, activator.phiPrime(zetaMatrix.get(i, j)));
            }
        }
        SimpleMatrix beta = upperWeightedError.elementMult(zDerivative);
        this.gradientWeights = input.mult(beta.transpose());

        SimpleMatrix onesRow = SimpleMatrix.ones(1, input.getNumCols());
        this.gradientBias = beta.mult(onesRow.transpose()).scale(((double) 1 / input.getNumCols()));
        this.weightedError = (getWeights().mult(beta));

    }

    @Override
    public SimpleMatrix getWeights() {
        return this.weights;
        // throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public SimpleMatrix getBias() {
        return this.bias;
        // throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public void setWeights(SimpleMatrix weights) {
        this.weights = weights;
        // throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public void setBias(SimpleMatrix bias) {
        this.bias = bias;
        // throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public void setGradientAdjustmentParameters(GradientAdjustmentParameters gradientAdjustmentParameters) {
        this.gradientAdjustmentParameters = gradientAdjustmentParameters;
        handler = GradientDescentFactory.createHandler(gradientAdjustmentParameters);
    }

    @Override
    public GradientAdjustmentParameters getGradientAdjustmentParameters() {
        return this.gradientAdjustmentParameters;
    }

}
