package fr.ensimag.deep.examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.ejml.simple.SimpleMatrix;
import fr.ensimag.deep.NeuralNetwork;

public class PricingFromFile {
    public static void main(String args[]) throws IOException {
        // Charger le réseau de neurones à partir du fichier de description
        NeuralNetwork n = NeuralNetwork.fromDescription("Examples/TrainingConsole/pricing_network.json");

        SimpleMatrix inputs = loadCSV("Examples/TrainingConsole/pricing-data-inputs.csv", 7);
        
        n.setBatchSize(100);
        n.propagate(inputs);
        System.out.println("Inputs:");
        System.out.println(inputs);
        System.out.println("Outputs:");
        System.out.println(n.getOutput());
        
        // Sauvegarder le réseau après entraînement
        n.save("pricing_res.json");
    }

    public static SimpleMatrix loadCSV(String filePath, int numCols) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        double[][] data = new double[lines.size()][numCols];
        
        for (int i = 0; i < lines.size(); i++) {
            String[] values = lines.get(i).split(",");
            for (int j = 0; j < numCols; j++) {
                data[i][j] = Double.parseDouble(values[j]);
            }
        }
        
        return new SimpleMatrix(data).transpose();
    }
}
