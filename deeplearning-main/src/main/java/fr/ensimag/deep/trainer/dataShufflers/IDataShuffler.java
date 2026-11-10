package fr.ensimag.deep.trainer.dataShufflers;

import fr.ensimag.deep.utils.DataMatrix;

public interface IDataShuffler {
    /**
     * Return a new matrix as a column permutation of the input matrix. 
     * It changes the order of the samples (one sample per column)
     * @param data the initial matrix
     * @return the permetuted result
     */
    public DataMatrix shuffle(DataMatrix data);
}
