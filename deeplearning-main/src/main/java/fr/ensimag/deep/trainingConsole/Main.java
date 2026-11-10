package fr.ensimag.deep.trainingConsole;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.ejml.simple.SimpleMatrix;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import fr.ensimag.deep.NeuralNetwork;
import fr.ensimag.deep.layers.AbstractLayer;
import fr.ensimag.deep.trainer.NetworkTrainer;
import fr.ensimag.deep.trainer.costFunction.ICostFunction;
import fr.ensimag.deep.trainer.dataShufflers.UniformShuffler;
import fr.ensimag.deep.trainingConsole.params.CostFunctionFactory;
import fr.ensimag.deep.trainingConsole.params.ExpeParams;
import fr.ensimag.deep.utils.DataMatrix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.io.File;


public class Main
{
    public static void main(String[] args) throws Exception
    {
        // create the command line parser
        CommandLineParser parser = new DefaultParser();

        // creates the options
        Options options = new Options();
        options.addOption(Option.builder("x").
            longOpt("expe").
            type(File.class).
            desc("Json description of the experiment parameters").
            hasArg().
            required(true).
            build());
        
        try {
        // parse the command line arguments
            CommandLine line = parser.parse(options, args);
            String expe_file = null;

            // validate that block-size has been set
            if (line.hasOption("expe")) {
                // print the value of block-size
                expe_file = line.getOptionValue("expe");
            } 

            byte[] jsonData;
            jsonData = Files.readAllBytes(Paths.get(expe_file));
            ObjectMapper objectMapper = new ObjectMapper().configure(JsonParser.Feature.ALLOW_COMMENTS, true);
		
            //convert json string to object
            ExpeParams params = objectMapper.readValue(jsonData, ExpeParams.class);        

            Main main = new Main();
            main.compute(params);
    
        }
        catch (ParseException exp) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "Neural Network training console", options);
        }     

    }

    private void compute(ExpeParams params) throws Exception {

        // Shall we randomly intialize the network
        boolean init = false ;
        if ((params.getInitialize() != null) && params.getInitialize())
            init = true;

        // Display the final error curve using gnuplot
        boolean gnuplot = false; 
        if ((params.getGnuplot() != null) && params.getGnuplot())
            gnuplot = true;

        // dump activation of all neurons during training
        String activationLog = null;
        if ((params.getActivationFile() != null) && !"".equals(params.getActivationFile()))
           activationLog = params.getActivationFile();

        // dump the error and validation values during learning steps
        String learningLog = null;
        if ((params.getLearningLogFile() != null) && !"".equals(params.getLearningLogFile()))
            learningLog = params.getLearningLogFile();

        // the final input - output on the validation data is dumped 
        String finalValidation = null;
        if ((params.getFinalValidation() != null) && !"".equals(params.getFinalValidation()))
            finalValidation = params.getFinalValidation();

        try {
            // creates the neural network from its json description
            NeuralNetwork network = NeuralNetwork.fromDescription(params.getNetworkDescription());
            int inputSize = network.getInputSize();
            int outputSize = network.getOutputSize();

            if (init)
                network.initialize();

            // loads the dataset
            DataMatrix trainingData = null;
            DataMatrix validationData = null;            
            ICostFunction costFunction = CostFunctionFactory.create(params.getCostFunction());
            NetworkTrainer trainer = new NetworkTrainer(network, costFunction, new UniformShuffler());
            trainingData = readAllData(params.getTrainingData(), inputSize, outputSize);
            validationData = readAllData(params.getValidationData(), inputSize, outputSize);

            // prepares the error csv file if necessary
            CSVWriter writer = null;
            if (learningLog != null)
            {
                FileWriter outputFile = new FileWriter(learningLog); 
                // create CSVWriter object filewriter object as parameter 
                writer = new CSVWriter(outputFile, ' ', CSVWriter.NO_QUOTE_CHARACTER, '\\', "\n"); 
                // adding header to csv 
                // String[] header = { "Validation error"}; 
                // writer.writeNext(header);                 
            }

            CSVWriter activationWriter = null;
            if (activationLog != null)
            {
                FileWriter activationFilewriter = new FileWriter(activationLog);
                activationWriter = new CSVWriter(activationFilewriter, ' ', CSVWriter.NO_QUOTE_CHARACTER, '\\', "\n"); 
                ArrayList<String> headers = new ArrayList<>();
                
                int layer_num=0;
                for (AbstractLayer l : network.getLayers())
                {
                    for (int i=0; i<l.getLayerSize(); i++)
                        headers.add(String.format("Neuron_%d_of_Layer_%d", i, layer_num));
                    layer_num++;
                }
                activationWriter.writeNext(headers.stream().toArray(String[]::new));
            }

            // trains the networks
            int steps=0;  // to dump logs every validationStep
            int validationSteps = 1; // default value
            if (params.getValidationSteps() != null)
                validationSteps = params.getValidationSteps();

            for (int i=0; i<params.getEpochs(); i++)
            {
                trainer.train(trainingData);
                steps++ ;

                if (steps % validationSteps == 0)
                // validates and dumps the logs
                {
                    double cost = trainer.validate(trainingData);
                    double validate_error = trainer.validate(validationData);
                    if (learningLog != null)
                    {
                        String[] val = {String.valueOf(cost), String.valueOf(validate_error)};                
                        writer.writeNext(val);
                    }
                    else
                        System.out.println(String.format("%f\t%f", cost, validate_error));

                    // dumps the activation of all neurons if requestedx
                    if (activationLog != null)
                    {
                        ArrayList<String> activationVals = new ArrayList<>();
                        for (AbstractLayer l : network.getLayers())
                        {
                            for (int j=0; j<l.getLayerSize(); j++)
                                activationVals.add(String.format("%f", l.getActivation().get(j,0)));
                        }
                        activationWriter.writeNext(activationVals.stream().toArray(String[]::new));
                    }
                }
            }

            if (params.getTrainedNetwork() != null)
                network.save(params.getTrainedNetwork());

            // closes the error csv file
            if (writer != null)
                writer.close();

            // close the activation log file
            if (activationWriter != null)
                activationWriter.close();

            // dumps on a csv file the network input/output value on the validation dataset if requested
            if (finalValidation != null)
            {
                // print the output of the network on all the input data
                network.setBatchSize(validationData.getInputs().getNumCols());
                network.propagate(validationData.getInputs());

                FileWriter outputfile = new FileWriter(finalValidation); 
                // create CSVWriter object filewriter object as parameter 
                writer = new CSVWriter(outputfile, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, '\\', "\n");                 

                SimpleMatrix inputs = validationData.getInputs();
                SimpleMatrix outputs = network.getOutput();

                String[] line = new String[inputs.getNumRows()+outputs.getNumRows()];
                for (int i=0; i<validationData.getInputs().getNumCols(); i++)
                {
                    double[][] col_input = inputs.getColumn(i).toArray2();
                    for (int j=0; j<col_input.length; j++)
                        line[j] = String.valueOf(col_input[j][0]);
                    double[][] col_output = outputs.getColumn(i).toArray2();
                    for (int j=0; j<col_output.length; j++)
                        line[j+col_input.length] = String.valueOf(col_output[j][0]);
                    writer.writeNext(line);
                }
                writer.close();
            }

            boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
            if (gnuplot && learningLog != null) {
                String gnuplotScript;
                if (isWindows) {
                    gnuplotScript = "C:\\Users\\Public\\gnuplot_script.plt"; // Change to a valid path
                } else {
                    gnuplotScript = "/tmp/gnuplot.script";
                }

                // Create the script file
                try (FileWriter script = new FileWriter(gnuplotScript)) {
                    script.write("set title 'Training and Validation Error'\n");
                    script.write("set xlabel 'Epochs'\n");
                    script.write("set ylabel 'Error'\n");
                    script.write("plot '" + learningLog + "' using 0:1 with lines title 'Training Error', '" 
                                + learningLog + "' using 0:2 with lines title 'Validation Error'\n");
                    script.write("pause -1\n");
                }

                // Execute Gnuplot
                try {
                    ProcessBuilder pb;
                    if (isWindows) {
                        pb = new ProcessBuilder("C:\\Program Files\\gnuplot\\bin\\gnuplot.exe", gnuplotScript); // Windows assumes gnuplot.exe is in PATH
                    } else {
                        pb = new ProcessBuilder("gnuplot", gnuplotScript);
                    }
                    Process p = pb.start();
                    p.waitFor();
                } catch (Exception e) {
                    System.err.println("Gnuplot execution failed: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.err.println("Unknown File : " + e.getMessage());
            System.exit(-1);
        }

    }

    private DataMatrix readAllData(String filename, int inputSize, int outputSize) throws Exception {
        List<String[]> content;
        try (Reader reader = new FileReader(filename)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                content = csvReader.readAll();
            }
        }
    
        double[][] input = new double[content.size()][inputSize]; 
        double[][] output = new double[content.size()][outputSize];
    
        for (int ligne = 0; ligne < content.size(); ligne++) {
            String[] currentLine = content.get(ligne);
    
            if (currentLine.length != inputSize + outputSize) {
                throw new IllegalArgumentException("La ligne " + ligne + " ne contient pas le bon nombre de colonnes.");
            }
        
            // Remplissage des entrées
            for (int i = 0; i < inputSize; i++) {
                input[ligne][i] = Double.parseDouble(currentLine[i]);
            }
        
            // Remplissage des sorties
            for (int i = 0; i < outputSize; i++) {
                output[ligne][i] = Double.parseDouble(currentLine[i + inputSize]);
            }
        }
        
        // Normalisation des données d'entrée (et ici aussi des sorties, si nécessaire)
        normalizeData(input);
        normalizeData(output); // À enlever si vous ne souhaitez normaliser que les inputs
    
        return new DataMatrix(new SimpleMatrix(input).transpose(), new SimpleMatrix(output).transpose());
    }
    
    /**
     * Normalise chaque colonne de data de façon à obtenir une moyenne nulle et un écart-type égal à 1.
     */
    private void normalizeData(double[][] data) {
        int numRows = data.length;
        int numCols = data[0].length;
        for (int j = 0; j < numCols; j++) {
            double sum = 0;
            for (int i = 0; i < numRows; i++) {
                sum += data[i][j];
            }
            double mean = sum / numRows;
            double sumSq = 0;
            for (int i = 0; i < numRows; i++) {
                double diff = data[i][j] - mean;
                sumSq += diff * diff;
            }
            double std = Math.sqrt(sumSq / numRows);
            if (std == 0) {
                std = 1; // Pour éviter la division par zéro
            }
            for (int i = 0; i < numRows; i++) {
                data[i][j] = (data[i][j] - mean) / std;
            }
        }
    }
    
}
