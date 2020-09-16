import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class ConnectedComponents {
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        int result = 0;
        //write your code here
        Stack<Integer> stack = new Stack<>();
        Set<Integer> explored = new HashSet<>();

        for (int i = 0; i < adj.length; i++) {
            if (explored.contains(i)) continue;

            stack.push(i);

            // core
            while (!stack.isEmpty()) {
                int vertex = stack.pop();

                if (explored.contains(vertex)) continue;

                explored.add(vertex);
                adj[vertex].forEach(stack::push);
            }

            // alt
//            explored.add(i);
//            while (!stack.isEmpty()) {
//                int top = stack.peek();
//                List<Integer> list = adj[top];
//
//                if (!list.isEmpty()) {
//                    int take = list.remove(list.size() - 1); //remove last is O(1)
//                    if (explored.contains(take)) continue;
//
//                    explored.add(take);
//                    stack.push(take);
//                } else stack.pop();
//            }

            result++;
        }

        return result;
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
            adj[y - 1].add(x - 1);
        }
        System.out.println(numberOfComponents(adj));
    }
}

