package fr.ensimag.deep.trainer.costFunction;

/**
 * Mean Square Error cost function
 */
public class QuadraticCostFunction implements ICostFunction {

    @Override
    public double apply(double y, double expected_y) {
        return 0.5*Math.pow((y-expected_y),2);
    }

    @Override
    public double derivApply(double y, double expected_y) {
        return y-expected_y;
    }

}
