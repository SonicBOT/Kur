import java.util.*;

public class FleuryAlgorithm {

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
        List<Integer> cycle = new ArrayList<>();
        dfsFleury(0, adjList, cycle);
        return cycle;
    }
    private static void dfsFleury(int current, List<List<Integer>> adjList, List<Integer> cycle) {
        List<Integer> neighbors = new ArrayList<>(adjList.get(current));

        for (int next : neighbors) {
            if (isBridge(current, next, adjList)) continue;
            adjList.get(current).remove((Integer) next);
            adjList.get(next).remove((Integer) current);

            dfsFleury(next, adjList, cycle);
        }
        cycle.add(current);
    }
    private static boolean isBridge(int u, int v, List<List<Integer>> adjList) {
        adjList.get(u).remove((Integer) v);
        adjList.get(v).remove((Integer) u);
        boolean[] visited = new boolean[adjList.size()];
        dfsConnectivity(u, adjList, visited);
        adjList.get(u).add(v);
        adjList.get(v).add(u);
        for (boolean visit : visited) {
            if (!visit) return true;
        }
        return false;
    }
    private static void dfsConnectivity(int current, List<List<Integer>> adjList, boolean[] visited) {
        visited[current] = true;
        for (int neighbor : adjList.get(current)) {
            if (!visited[neighbor]) dfsConnectivity(neighbor, adjList, visited);
        }
    }
}