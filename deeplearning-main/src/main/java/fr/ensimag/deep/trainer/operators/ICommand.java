package fr.ensimag.deep.trainer.operators;

import org.ejml.simple.SimpleMatrix;

public interface ICommand {

    /**
     * 
     * @param expectedOutput
     * @param output
     * @param cost
     */
    public void compute(SimpleMatrix expectedOutput, SimpleMatrix output, SimpleMatrix cost);
}
