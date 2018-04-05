/**
 * Created by zivlevi1 on 05/04/2018.
 */
public class Main {
    public static void main(String[] args)throws Exception{
        api temp = new api();
        temp.load_graph("Karate.csv");
        temp.calculate_clustering_coefficients();
        temp.get_all_clustering_coefficients();
        temp.get_average_clustering_coefficient();
        System.out.println(temp.get_clustering_coefficient(5));
    }
}
