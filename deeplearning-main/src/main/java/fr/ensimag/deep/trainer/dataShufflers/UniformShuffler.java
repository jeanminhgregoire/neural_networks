package fr.ensimag.deep.trainer.dataShufflers;

import java.util.Vector;
import java.util.Collections;

import org.ejml.simple.SimpleMatrix;

import fr.ensimag.deep.utils.DataMatrix;

public class UniformShuffler implements IDataShuffler{

    @Override
    public DataMatrix shuffle(DataMatrix data) {
        Vector<Integer> permut = new Vector<>();
        SimpleMatrix inputs = data.getInputs();
        SimpleMatrix outputs = data.getOutputs();       

        for(int i=0; i<inputs.getNumCols(); i++)
        /* inputs and outputs have the same number of columns : we choose inputs */
            permut.add(i);
        Collections.shuffle(permut);

        SimpleMatrix permutInputs = new SimpleMatrix(inputs.getNumRows(), inputs.getNumCols());
        SimpleMatrix permutOutputs = new SimpleMatrix(outputs.getNumRows(), outputs.getNumCols());        

        for(int i=0; i<inputs.getNumCols(); i++)
        {
            permutInputs.setColumn(i, inputs.getColumn(permut.get(i)));
            permutOutputs.setColumn(i, outputs.getColumn(permut.get(i)));
        }


        DataMatrix result = new DataMatrix(permutInputs, permutOutputs);

        return result;
    }

}
