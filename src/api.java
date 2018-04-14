import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class api {

    private HashMap <Integer,ArrayList<Integer>> graph = new HashMap<>();
    private ArrayList<Pair<Integer,Double>> clustering_coefficients = new ArrayList<>();
    private double average_coefficients;

    public HashMap<Integer, ArrayList<Integer>> getGraph() {
        return graph;
    }

    public ArrayList<Pair<Integer, Double>> getClustering_coefficients() {
        return clustering_coefficients;
    }

    public double getAverage_coefficients() {
        return average_coefficients;
    }

    public void load_graph (String path) throws Exception{
        final BufferedReader br = new BufferedReader(new InputStreamReader(api.class.getClassLoader().getResourceAsStream(path)));
        ArrayList<Pair<Integer,Integer>> nodeList = new ArrayList<>();
        while(br.ready()){
            String pair = br.readLine();
            String[] vertices = pair.split(",");
            int ver1 = Integer.parseInt(vertices[0]);
            int ver2 = Integer.parseInt(vertices[1]);
            Pair<Integer,Integer> node = new Pair<>(ver1,ver2);
            nodeList.add(node);
        }
        initialize_graph(nodeList);
    }

    public void calculate_clustering_coefficients(){
        double countNodes = 0;
        double sumCoefficients =0;
        for (Map.Entry<Integer,ArrayList<Integer>> entry : graph.entrySet()) {
            countNodes++;
            int node = entry.getKey();
            ArrayList<Integer> neighbors = entry.getValue();
            double k = neighbors.size();
            double e = 0;
            double coefficients = 0;
            if (k>1) {
                for (int i = 0; i < neighbors.size(); i++) {
                    ArrayList<Integer> neighbors_of_neighbor = graph.get(neighbors.get(i));
                    if (neighbors_of_neighbor!=null) {
                        for (int j = 0; j < neighbors.size(); j++) {
                            if (neighbors_of_neighbor.contains(neighbors.get(j))) {
                                e++;
                            }
                        }
                    }
                }
                coefficients = e/(k*(k-1));
                sumCoefficients += coefficients;
            }
            clustering_coefficients.add(new Pair<>(node,coefficients));
        }
        average_coefficients = sumCoefficients/countNodes;
    }

    public double get_average_clustering_coefficient(){
        return average_coefficients;
    }

    public double get_clustering_coefficient(int node_id){
        for (int i=0; i<clustering_coefficients.size(); i++){
            Pair<Integer,Double> nodePair = clustering_coefficients.get(i);
            if (nodePair.getKey()==node_id){
                return nodePair.getValue();
            }
        }
        return -1;
    }


    public List<Pair<Integer,Double>> get_all_clustering_coefficients(){
        Collections.sort(clustering_coefficients, (a, b) -> a.getValue() < b.getValue() ? 1 : a.getValue().equals(b.getValue()) ? 0 : -1);
        return clustering_coefficients;
    }


    private void initialize_graph(ArrayList<Pair<Integer,Integer>> nodeList){
        for (int i=0; i< nodeList.size(); i++){
            if (!graph.containsKey(nodeList.get(i).getKey())){
                graph.put(nodeList.get(i).getKey(),new ArrayList<>());
            }
            if (!graph.containsKey(nodeList.get(i).getValue())){
                graph.put(nodeList.get(i).getValue(),new ArrayList<>());
            }
            ArrayList neighbors1 =  graph.get(nodeList.get(i).getKey());
            ArrayList neighbors2 =  graph.get(nodeList.get(i).getValue());
            neighbors1.add(nodeList.get(i).getValue());
            graph.put(nodeList.get(i).getKey(),neighbors1);
            neighbors2.add(nodeList.get(i).getKey());
            graph.put(nodeList.get(i).getValue(),neighbors2);
        }
    }

    public ArrayList<Pair<Integer,Double>> get_top_nodes(int k){
        ArrayList<Pair<Integer,Double>> k_top_nodes = new ArrayList<Pair<Integer,Double>>();
        get_all_clustering_coefficients();
        for (int i = k; i > 0; i--) {
            k_top_nodes.add(clustering_coefficients.get(i));
        }
        return k_top_nodes;
    }
}
