package fr.ensimag.deep;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import fr.ensimag.deep.serialization.json.InitializerFactory;
import fr.ensimag.deep.serialization.json.LayerFactory;
import fr.ensimag.deep.serialization.json.SerializedLayer;
import org.ejml.simple.SimpleMatrix;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.ensimag.deep.layers.AbstractLayer;
import fr.ensimag.deep.serialization.json.NetworkDescription;
import fr.ensimag.deep.trainer.initializer.INewtorkInitializer;
import lombok.Getter;
import lombok.Setter;

public class NeuralNetwork {
    /** Ordered list of layers of this neural network */
    @Getter private List<AbstractLayer> layers;
    @Getter private int inputSize;
    @Getter private int batchSize;
    @Getter @Setter private INewtorkInitializer initializer = null;

    /** 
     * Creates an empty neural networks
     */
    public NeuralNetwork(int batchSize)
    {
        this.layers = new ArrayList<AbstractLayer>();
        this.batchSize = batchSize;
    }

    /**
     * Returns the output size of the network (the dimension of the output space 
     * of the function implemented by the network)
     * @return the output size
     */
    public int getOutputSize()
    {
        return this.layers.get(this.layers.size() - 1).getLayerSize();
        //throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * Adds a new layer at the end of all the already added layers
     * @param l
     * @return the current Neural Network. It allows to easily chain the addLayer calls
     */
    public NeuralNetwork addLayer(AbstractLayer l)
    {
        this.layers.add(this.layers.size(), l);
        NeuralNetwork res = new NeuralNetwork(this.batchSize);
        res.layers = this.layers;
        return res;
        //throw new UnsupportedOperationException("not yet implemented");
    }

    /** 
     * Randomly initialize the neural network using the associated initializer (private attribute)
     */
    public void initialize()
    {
        if (this.initializer == null)
            throw new RuntimeException("No intitializer has been defined");
        else
            initializer.init(this);
    }

    /** returns the output of the neural network. By definition, the output is
     * the activation of the last layer
     * @return the output
     */
    public SimpleMatrix getOutput()
    {
        AbstractLayer last =  this.layers.get(this.layers.size() - 1);
        return last.getActivation();
        //throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * Calculate the output(s) of the neural network corresponding to the associated input(s)
     * The input matrix is of size mxn where m is the input size of the network (the input space
     * dimension of the corresponding function) and n is the number of input vectors
     * @param input the input vectors
     * @return the output vectors. It is a m'xn matrix, where m' is the output dimension of the 
     *         correspondig function, and n the number of input vectors
     */
    public SimpleMatrix propagate(SimpleMatrix input)
    {
        SimpleMatrix current = input;
        for (int i = 0; i< this.layers.size(); i++){
            AbstractLayer currentLayer = this.layers.get(i);
            currentLayer.propagate(current);
            current = currentLayer.getActivation();

        }
        return current;
        //throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * Specify the batch size (the number of input vectors in a row to compute the output or in a learning step)
     * This batch size must be propagated to every layers
     * @param batchSize the batch size
     */
    public void setBatchSize(int batchSize)
    {
        this.batchSize = batchSize;
        //throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * Backpropagate through the layers (from the last to the first) the gradient computation 
     * and update the weights accordingly
     * @param lossFunctionGradient a matrix corresponding to the derivative of the loss between 
     *                             the expected output and the computed output value
     */
    public void learn(SimpleMatrix lossFunctionGradient)
    {
        this.backpropagateAndUpdate(this.getOutputLayer(), lossFunctionGradient);
        for (int i=getNbLayers()-2; i>=0; i--)
            this.backpropagateAndUpdate(layers.get(i), layers.get(i+1).getWeightedError());
    }

    /**
     * Backpropagate the error to compute the weights and bias gradients on a layer and updates its parameters based on the gradients
     * @param layer the layer
     * @param layerError the output error of this layer
     */
    private void backpropagateAndUpdate(AbstractLayer layer, SimpleMatrix layerError) {
        layer.backpropagate(layerError);
        layer.updateParameters();
    }

    /**
     * Returns the output layer of the Neural Netork (the last layer that has been inserted)
     * @return the Output Layer
     */
    private AbstractLayer getOutputLayer()
    {
        return this.layers.get(this.layers.size()-1);
    }

    /**
     * Number of layers in the current neural network
     * @return the number of layers
     */
    public int getNbLayers()
    {
        return this.layers.size();
    }

    private List<Double> matrixToList(SimpleMatrix matrix) {
        if (matrix.getNumCols() != 1) {
            throw new IllegalArgumentException("Expected a column vector, but got a matrix with " + matrix.getNumCols() + " columns.");
        }
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < matrix.getNumRows(); i++) {
            result.add(matrix.get(i, 0));
        }
        return result;
    }

    // Convertit une matrice 2D en une liste de listes
    private List<List<Double>> matrixToNestedList(SimpleMatrix matrix) {
        List<List<Double>> result = new ArrayList<>();
        for (int i = 0; i < matrix.getNumRows(); i++) {
            List<Double> row = new ArrayList<>();
            for (int j = 0; j < matrix.getNumCols(); j++) {
                row.add(matrix.get(i, j));
            }
            result.add(row);
        }
        return result;
    }

    /**
     * 
     * Saves the current neural network as a JSON file. The JSON format is specified 
     * by the NetworkDescription java object (a Java class corresponding to the json
     * format chosen to represent a neural network)
     * @param filename the file name of the saved network
     * @return true is the network has been saved, false otherwise (no exception is thrown)
     */
   public boolean save(String filename) 
   {
        NetworkDescription dto = new NetworkDescription();

        dto.setBatchSize(this.batchSize);
        dto.setInputSize(this.inputSize);
        dto.setInitializer(this.initializer.getClass().getSimpleName());
        List<SerializedLayer> sLayers = new ArrayList<>();
        for (AbstractLayer layer : this.layers){
            SerializedLayer sLayer = new SerializedLayer();
            sLayer.setSize(layer.getLayerSize());
            sLayer.setActivatorType(layer.getActivator().getClass().getSimpleName());
            sLayer.setBias(matrixToList(layer.getBias()));
            sLayer.setType(layer.getClass().getSimpleName());
            sLayer.setWeights(matrixToNestedList(layer.getWeights()));
            if (layer.getGradientAdjustmentParameters() != null){
                sLayer.setGradientAdjustmentParameters(layer.getGradientAdjustmentParameters());
            }

            sLayers.add(sLayer);

        }
        dto.setSerializedLayers(sLayers);

        // to be completed : initialize the dto object with the neural network caracteristics 

        // serialize the NetworkDescription object (dto) to its json representation
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writerWithDefaultPrettyPrinter().
                                writeValueAsString(dto);
            BufferedWriter w = new BufferedWriter(new FileWriter(filename));

            w.write(json);
            w.close();
        } catch (JsonProcessingException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    /**
     * Creates a Neural Network from its json description in a file
     * @param filename the filename containing the json description
     * @return the Neural Network
     * @throws IOException
     */
    static public NeuralNetwork fromDescription(String filename) throws IOException
    {
        // Created the NetworkDescription object corresponding to the json representation in the file
        NeuralNetwork net = null;


        byte[] jsonData = Files.readAllBytes(Paths.get(filename));


		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper().configure(JsonParser.Feature.ALLOW_COMMENTS, true);;
		
		//convert json string to object
		NetworkDescription neuralDTO = objectMapper.readValue(jsonData, NetworkDescription.class);        

        // to do : Initialize the NeuralNetwork net object with the elements provided by the neuralDTO 
        // loaded from the json file

        net = new NeuralNetwork(neuralDTO.getBatchSize());
        net.inputSize = neuralDTO.getInputSize();
        net.initializer = InitializerFactory.create(neuralDTO.getInitializer());



        int previousLayerSize = net.inputSize;
        for (SerializedLayer sLayer : neuralDTO.getSerializedLayers()) {
            AbstractLayer layer = LayerFactory.create(sLayer, previousLayerSize);;
            layer.setGradientAdjustmentParameters(sLayer.getGradientAdjustmentParameters());
            net.addLayer(layer);
            previousLayerSize = layer.getLayerSize();
        }

        return net;
    }
}
