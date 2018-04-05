import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class api {

    public int[][] graph;


    public void load_graph (String path) throws Exception{
        final BufferedReader br = new BufferedReader(new InputStreamReader(api.class.getClassLoader().getResourceAsStream(path)));
        ArrayList<Pair<Integer,Integer>> nodeList = new ArrayList<>();
        int maxVertices = 0;
        while(br.ready()){
            String pair = br.readLine();
            String[] vertices = pair.split(",");
            int ver1 = Integer.parseInt(vertices[0]);
            int ver2 = Integer.parseInt(vertices[1]);
            Pair<Integer,Integer> node = new Pair<>(ver1,ver2);
            maxVertices  =  Math.max(Math.max(ver1, ver2),maxVertices);
            nodeList.add(node);
        }
        initialize_graph(maxVertices+1 , nodeList);
        System.out.println(graph);
    }

    public void calculate_clustering_coefficients(){

    }

    public double get_average_clustering_coefficient(){
        return -1;
    }

    public double get_clustering_coefficient(int node_id){
        return -1;
    }

    /*
    public List<Pair<Integer,Double>> get_all_clustering_coefficients(){

    }
    */

    private void initialize_graph(int maxVertices ,  ArrayList<Pair<Integer,Integer>> nodeList){
        graph = new int[maxVertices][maxVertices];
        for (int i=0; i< maxVertices; i++){
            for (int j=0; j< maxVertices; j++){
                graph[i][j] = 0;
            }
        }
        for (int i=0; i< nodeList.size(); i++){
            graph[nodeList.get(i).getKey()][nodeList.get(i).getValue()] = 1;
            graph[nodeList.get(i).getValue()][nodeList.get(i).getKey()] = 1;
        }

    }
}
