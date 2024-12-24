import java.util.*;

public class HierholzerAlgorithm {
    public static List<Integer> findEulerCycle(int[][] graph) {
        int n = graph.length;
        for (int i = 0; i < n; i++) {
            int degree = 0;
            for (int j = 0; j < n; j++) {
                degree += graph[i][j];
            }
            if (degree % 2 != 0) {
                throw new IllegalArgumentException("Граф не является Эйлеровым (есть вершина с нечётной степенью).");
            }
        }
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                if (graph[i][j] > 0) {
                    adjList.get(i).add(j);
                }
            }
        }
        Stack<Integer> stack = new Stack<>();
        List<Integer> cycle = new ArrayList<>();
        stack.push(0);
        while (!stack.isEmpty()) {
            int current = stack.peek();
            if (!adjList.get(current).isEmpty()) {
                int next = adjList.get(current).remove(0);
                adjList.get(next).remove((Integer) current);
                stack.push(next);
            } else {
                cycle.add(stack.pop());
            }
        }

        return cycle;
    }
}