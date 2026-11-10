package fr.ensimag.deep.utils;

import org.ejml.simple.SimpleMatrix;
import lombok.Getter;

public class DataMatrix {

    /** Matrix containing a list of input data. Column i is the ith input data stored in the corresponding rows */
    @Getter private SimpleMatrix inputs;
    /** Matrix containing a list of output data. Column i is the ith output data stored in the corresponding rows */    
    @Getter private SimpleMatrix outputs;

    public DataMatrix(SimpleMatrix inputs, SimpleMatrix outputs)
    {
        this.inputs = inputs;
        this.outputs = outputs;    
    }   
}
