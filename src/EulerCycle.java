import java.util.*;
public class EulerCycle {
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
        int[] degrees = new int[n];
        for (int i = 0; i < n; i++) {
            degrees[i] = adjList.get(i).size();
        }
        int startVertex = 0;
        for (int i = 1; i < n; i++) {
            if (degrees[i] < degrees[startVertex]) {
                startVertex = i;
            }
        }
        Stack<Integer> stack = new Stack<>();
        List<Integer> path = new ArrayList<>();
        stack.push(startVertex);
        while (!stack.isEmpty()) {
            int current = stack.peek();

            if (adjList.get(current).isEmpty()) {
                path.add(stack.pop());
            } else {
                adjList.get(current).sort((a, b) -> {
                    if (degrees[a] != degrees[b]) {
                        return Integer.compare(degrees[a], degrees[b]);
                    }
                    return Integer.compare(a, b);
                });
                int next = adjList.get(current).remove(0);
                adjList.get(next).remove((Integer) current);
                stack.push(next);
            }
        }

        return path;

    }

}







