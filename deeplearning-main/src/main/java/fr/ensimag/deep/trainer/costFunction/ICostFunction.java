package fr.ensimag.deep.trainer.costFunction;

/** 
 * Cost function measuring the difference between a result and 
 * the expected value
 */

public interface ICostFunction {
    /**
     * Evaluation (cost) of the difference between a value 
     * and the corresponding expected value
     * @param y the value
     * @param expected_y the expected value
     * @return the cost
     */
    public double apply(double y, double expected_y);

    /**
     * Derivative value of the error between the value
     * and the corresponding expected value
     * @param y the value
     * @param expected_y the expected value
     * @return
     */
    public double derivApply(double y, double expected_y);
}
