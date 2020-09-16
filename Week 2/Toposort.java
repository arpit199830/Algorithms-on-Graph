import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Toposort {
    private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
        int used[] = new int[adj.length];
        ArrayList<Integer> order = new ArrayList<Integer>();
        //write your code here
	for (int i = 0; i < used.length; i++) used[i] = 0;
        for (int i = 0; i < used.length; i++) {
            if (used[i] != 0) continue;
            dfs(adj, used, order, i);
        }

        Collections.reverse(order);
        return order;
    }

    private static void dfs(ArrayList<Integer>[] adj, int[] used, ArrayList<Integer> order, int s) {
      //write your code here
       Stack<Integer> stack = new Stack<>();
        stack.push(s);

        // using delayed pop and list removal
        // note: this should also allow post order processing without 2nd stack
        used[s] = 1;
        while (!stack.isEmpty()) {
            int top = stack.peek();
            List<Integer> list = adj[top];
	    if (!list.isEmpty()) {
                int take = list.remove(list.size() - 1); //remove last is O(1)
		if (used[take] != 0) continue;
		used[take] = 1;
                stack.push(take);
            } else order.add(stack.pop()); //post order
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        ArrayList<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}

