import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NegativeCycle {
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        // write your code here
	Map<Integer, Integer> distance = new HashMap<>();

        // using a virtual source node with 0 distance to all nodes
        // i.e. null values in map are 0, not inf

        for (int j = 0; j < adj.length - 1; j++) { //v - 1 iteration
            for (int u = 0; u < adj.length; u++) {
                List<Integer> adjList = adj[u];
                List<Integer> costList = cost[u];
                for (int k = 0; k < adjList.size(); k++) {
                    int v = adjList.get(k);
                    int w = costList.get(k);
                    int dist = distance.getOrDefault(v, 0);
                    int newDist = distance.getOrDefault(u, 0) + w;

                    if (dist > newDist) distance.put(v, newDist); //relax
                }
            }
        }

        // negative cycle iteration, refactor?
        for (int u = 0; u < adj.length; u++) {
            List<Integer> adjList = adj[u];
            List<Integer> costList = cost[u];
            for (int k = 0; k < adjList.size(); k++) {
                int v = adjList.get(k);
                int w = costList.get(k);
                int dist = distance.getOrDefault(v, 0);
                int newDist = distance.getOrDefault(u, 0) + w;

                if (dist > newDist) return 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}

