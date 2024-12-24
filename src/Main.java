import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[][] graph1 = {
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 0}
        };
        int[][] graph2 = {
                {0, 1, 1, 1, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 0, 1, 1},
                {1, 1, 1, 0, 1},
                {1, 1, 1, 1, 0}
        };
        int[][] graph3 = {
                {0, 1, 1, 1, 1, 1, 1},
                {1, 0, 1, 1, 1, 1, 1},
                {1, 1, 0, 1, 1, 1, 1},
                {1, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 1, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 0}
        };
        int[][] graph4 = {
                {0, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 0, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 0}
        };
        int[][] graph5 = {
                {0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0},
                {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0}
        };
        int [][] graph6 = generateRandomEulerianAdjacencyMatrix(10);
        int [][] graph7 = generateRandomEulerianAdjacencyMatrix(15);
        int [][] graph8 = generateRandomEulerianAdjacencyMatrix(20);
        choseGraph(graph1);
        choseGraph(graph2);
        choseGraph(graph3);
        choseGraph(graph4);
        choseGraph(graph5);
        choseGraph(graph6);
        choseGraph(graph7);
        choseGraph(graph8);

    }


    public static int[][] generateRandomEulerianAdjacencyMatrix(int n) {
        Random rand = new Random();
        int[][] matrix;

        do {
            matrix = new int[n][n];
            // Создаем случайные рёбра
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (rand.nextBoolean()) {
                        matrix[i][j] = 1;
                        matrix[j][i] = 1;
                    }
                }
            }
        } while (!isEulerian(matrix));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        return matrix;
    }

    private static boolean isEulerian(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            if (getDegree(matrix, i) % 2 != 0) {
                return false;
            }
        }
        return isConnected(matrix);
    }

    private static int getDegree(int[][] matrix, int vertex) {
        int degree = 0;
        for (int j = 0; j < matrix.length; j++) {
            degree += matrix[vertex][j];
        }
        return degree;
    }

    private static boolean isConnected(int[][] matrix) {
        boolean[] visited = new boolean[matrix.length];
        int startVertex = 0;
        while (startVertex < matrix.length && getDegree(matrix, startVertex) == 0) {
            startVertex++;
        }

        if (startVertex == matrix.length) {
            return true;
        }
        dfs(matrix, visited, startVertex);
        for (boolean v : visited) {
            if (!v && getDegree(matrix, visited.length - 1) > 0) {
                return false;
            }
        }

        return true;
    }

    private static void dfs(int[][] matrix, boolean[] visited, int vertex) {
        visited[vertex] = true;

        for (int j = 0; j < matrix.length; j++) {
            if (matrix[vertex][j] == 1 && !visited[j]) {
                dfs(matrix, visited, j);
            }
        }
    }

    private static void printAdjacencyMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
    public static void choseGraph(int [][] graph){
        System.out.println("\n\n");
        printAdjacencyMatrix(graph);
        EulerCycle eulerCycle = new EulerCycle();
        FleuryAlgorithm fleuryAlgorithm = new FleuryAlgorithm();
        HierholzerAlgorithm hierholzerAlgorithm = new HierholzerAlgorithm();
        long start2 = System.nanoTime();
        try {
            List<Integer> cycle = hierholzerAlgorithm.findEulerCycle(graph);
            System.out.println("Эйлеров цикл (Хирхольцер): " + cycle);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        long duration2 = System.nanoTime() - start2;
        System.out.println("Время выполнения: " + duration2 + " нс");
        long start1 = System.nanoTime();
        try {
            List<Integer> cycle = eulerCycle.findEulerCycle(graph);
            System.out.println("Эйлеров цикл: " + cycle);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());


        }
        long duration1 = System.nanoTime() - start1;
        System.out.println("Время выполнения: " + duration1 + " нс");



        long start3 = System.nanoTime();
        try {
            List<Integer> cycle = fleuryAlgorithm.findEulerCycle(graph);
            System.out.println("Эйлеров цикл (Флёри): " + cycle);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        long duration3 = System.nanoTime() - start3;
        System.out.println("Время выполнения: " + duration3 + " нс");
    }
}



