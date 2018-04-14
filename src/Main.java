import javafx.util.Pair;
import java.util.*;

/**
 * Created by zivlevi1 on 05/04/2018.
 */
public class Main {
    public static void main(String[] args)throws Exception{
        api temp = new api();
        temp.load_graph("LesMisreblas_proccesed.csv");
        temp.calculate_clustering_coefficients();
        temp.get_all_clustering_coefficients();
        System.out.println(temp.get_average_clustering_coefficient());
        System.out.println(temp.get_clustering_coefficient(5));
        HashMap<Integer, ArrayList<Integer>> graph = temp.getGraph();
        int edges =0 ;
        for (Map.Entry<Integer,ArrayList<Integer>> entry : graph.entrySet()) {
            edges += entry.getValue().size();
        }
        System.out.println(edges/2);
        ArrayList<Pair<Integer,Double>> top_nodes = temp.get_top_nodes(10);
        top_nodes.forEach(x -> System.out.println("Key: " + x.getKey() +" Val: " + x.getValue()));
    }
}
