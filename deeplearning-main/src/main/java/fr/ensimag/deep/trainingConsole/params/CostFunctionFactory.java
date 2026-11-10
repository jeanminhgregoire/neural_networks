package fr.ensimag.deep.trainingConsole.params;

import fr.ensimag.deep.trainer.costFunction.ICostFunction;
import fr.ensimag.deep.trainer.costFunction.QuadraticCostFunction;

public class CostFunctionFactory {
    public static ICostFunction create(String costFunctionName)
    {
        ICostFunction result = null;

        if ("Quadratic".equals(costFunctionName))
            result = new QuadraticCostFunction();

        return result;
    }
}
