import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class NeuralNetwork {
    private int numLayers;
    private int[] nodesInEachLayer;
    private Map<String, Double> weights;

    public NeuralNetwork(int numLayers, int[] nodesInEachLayer) {
        this.numLayers = numLayers;
        this.nodesInEachLayer = nodesInEachLayer;
        this.weights = initializeWeights();
    }

    private Map<String, Double> initializeWeights() {
        Map<String, Double> initializedWeights = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < numLayers - 1; i++) {
            for (int node1 = 0; node1 < nodesInEachLayer[i]; node1++) {
                for (int node2 = 0; node2 < nodesInEachLayer[i + 1]; node2++) {
                    System.out.print("Enter weight for edge (" + i + "-" + node1 + ", " + (i + 1) + "-" + node2 + "): ");
                    double weight = scanner.nextDouble();
                    initializedWeights.put(i + "-" + node1 + "-" + (i + 1) + "-" + node2, weight);
                }
            }
        }

        return initializedWeights;
    }

    public double getWeight(int layer1, int node1, int layer2, int node2) {
        String key = layer1 + "-" + node1 + "-" + layer2 + "-" + node2;
        return weights.getOrDefault(key, -1.0); // Return -1 if weight not found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of layers
        System.out.print("Enter the number of layers: ");
        int numLayers = scanner.nextInt();

        // Input the number of nodes in each layer
        int[] nodesInEachLayer = new int[numLayers];
        for (int i = 0; i < numLayers; i++) {
            System.out.print("Enter the number of nodes in layer " + (i + 1) + ": ");
            nodesInEachLayer[i] = scanner.nextInt();
        }

        // Create a NeuralNetwork object
        NeuralNetwork neuralNetwork = new NeuralNetwork(numLayers, nodesInEachLayer);

        // Query the weight of an edge
        System.out.print("Enter the layer of node 1: ");
        int layer1 = scanner.nextInt();
        System.out.print("Enter the node index of node 1: ");
        int node1 = scanner.nextInt();
        System.out.print("Enter the layer of node 2: ");
        int layer2 = scanner.nextInt();
        System.out.print("Enter the node index of node 2: ");
        int node2 = scanner.nextInt();

        double weight = neuralNetwork.getWeight(layer1, node1, layer2, node2);

        if (weight != -1.0) {
            System.out.println("Weight of edge (" + layer1 + "-" + node1 + ", " + layer2 + "-" + node2 + "): " + weight);
        } else {
            System.out.println("No weight found for edge (" + layer1 + "-" + node1 + ", " + layer2 + "-" + node2 + ")");
        }
    }
}
