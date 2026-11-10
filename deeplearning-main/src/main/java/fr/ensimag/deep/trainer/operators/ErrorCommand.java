package fr.ensimag.deep.trainer.operators;

import org.ejml.simple.SimpleMatrix;

import fr.ensimag.deep.trainer.costFunction.ICostFunction;
import lombok.Getter;

public class ErrorCommand implements ICommand {

    private ICostFunction costFunction;
    @Getter private double error;

    public ErrorCommand(ICostFunction costFunction)
    {
        this.costFunction = costFunction;
        error = 0.0;
    }

    @Override
    public void compute(SimpleMatrix expectedOutput, SimpleMatrix output, SimpleMatrix cost) {
        for (int i=0; i<expectedOutput.getNumRows(); i++)
            for (int j=0; j<expectedOutput.getNumCols(); j++)
                cost.set(i,j,costFunction.apply(output.get(i,j), expectedOutput.get(i,j)));        
        error += cost.elementSum();
    }

}
