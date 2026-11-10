package fr.ensimag.deep.serialization.json;

import java.util.List;
import java.util.Random;
import org.ejml.simple.SimpleMatrix;
import fr.ensimag.deep.activators.IActivator;
import fr.ensimag.deep.layers.AbstractLayer;
import fr.ensimag.deep.layers.StandardLayer;

public class LayerFactory {

    static private Random random = new Random(1);

    public static AbstractLayer create(SerializedLayer serializedLayer, int previousLayerSize)
    {
        AbstractLayer layer = null;

        int layerSize = serializedLayer.getSize();
        SimpleMatrix weights = null;

        if (serializedLayer.getWeights() == null ||
            serializedLayer.getWeights().size()==0)
            weights = randomMatrix(previousLayerSize, layerSize, -1.0f, 1.0f);
        else
        {
            double[][] weight_values = new double[previousLayerSize][layerSize];
            List<List<Double>> ser_weights = serializedLayer.getWeights();
            for (int i=0; i<ser_weights.size(); i++)
                for (int j=0; j<ser_weights.get(i).size(); j++)
                    weight_values[i][j] = ser_weights.get(i).get(j);

            weights = new SimpleMatrix(weight_values);
        }

        SimpleMatrix bias = null;
        if (serializedLayer.getBias() == null ||
            serializedLayer.getBias().size() == 0)
            // no bias specified in the json file
            bias = randomMatrix(layerSize, 1, -1.0f, 1.0f);
        else {
            double[] bias_values = new double[layerSize];
            int i=0;
            for (double b : serializedLayer.getBias())
                bias_values[i++] = b;
            bias = new SimpleMatrix(bias_values);
            }

        IActivator activator = ActivatorFactory.create(serializedLayer.getActivatorType());

        if ("Standard".equals(serializedLayer.getType()))
        {
            layer = new StandardLayer(weights, bias, activator);
        }

        return layer;
    }

    public static SimpleMatrix randomMatrix(int rows, int cols, double min, double max)
    {
        SimpleMatrix result = new SimpleMatrix(rows, cols);

        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                result.set(i,j, LayerFactory.random.nextDouble()*(max-min)+min);
        return result ;
    }
}
