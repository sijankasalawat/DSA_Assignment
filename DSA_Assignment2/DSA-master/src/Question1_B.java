import java.util.ArrayList;
import java.util.List;

public class Question1_B {
    private static final int N = 8;

    private static boolean[] visited;
    private static List<Integer> impactedDevices;
    private static List<List<Integer>> adj;

    // Function to create adjacency list
    private static void createAdjList(int[][] edges) {
        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            adj.get(x).add(y);
            adj.get(y).add(x);
        }
    }

    // Function to get all impacted devices
    private static void getImpactedDevices(int targetDevice) {
        visited = new boolean[N];
        impactedDevices = new ArrayList<>();
        dfs(targetDevice);
    }

    // Function to perform DFS
    private static void dfs(int node) {
        visited[node] = true;
        for (int next : adj.get(node)) {
            if (!visited[next]) {
                impactedDevices.add(next);
                dfs(next);
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 6}, {2, 4}, {4, 6}, {4, 5}, {5, 7}};
        int targetDevice = 4;

        createAdjList(edges);
        getImpactedDevices(targetDevice);

        System.out.println("Impacted Devices: " + impactedDevices);
    }
}