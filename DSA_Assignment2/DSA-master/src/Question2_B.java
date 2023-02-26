import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question2_B {
    private int[] parents;
    private int[] depths;
    private List<Integer>[] children;
    private int[] subtreeSizes;
    private int[] serviceCenters;
    private int numNodes;
    // Define the method to calculate minimum number of service centers
    public int calculateMinServiceCenters(int numNodes, List<Integer>[] children) {
        // Initialize class variables
        this.numNodes = numNodes;
        this.children = children;
        parents = new int[numNodes];
        depths = new int[numNodes];
        subtreeSizes = new int[numNodes];
        serviceCenters = new int[numNodes];
        Arrays.fill(parents, -1);
        Arrays.fill(serviceCenters, -1);

        // Traverse the tree to calculate subtree sizes and depths
        dfs1(0, -1);

        // Traverse the tree again to calculate minimum number of service centers
        return dfs2(0, -1);
    }

    // Depth-first search to calculate subtree sizes and depths
    private void dfs1(int node, int parent) {
        parents[node] = parent;
        depths[node] = parent == -1 ? 0 : depths[parent] + 1;
        subtreeSizes[node] = 1;
        for (int child : children[node]) {
            dfs1(child, node);
            subtreeSizes[node] += subtreeSizes[child];
        }
    }

    // Depth-first search to calculate minimum number of service centers
    private int dfs2(int node, int parent) {
        // Check if minimum number of service centers is already calculated for this node
        if (serviceCenters[node] != -1) {
            return serviceCenters[node];
        }
        int count = 0;
        for (int child : children[node]) {
            count += dfs2(child, node);
        }
        int option1 = count;
        int option2 = numNodes - subtreeSizes[node];
        serviceCenters[node] = Math.min(option1, option2) + 1;
        return serviceCenters[node];
    }

    public static void main(String[] args) {
        // Create a sample tree
        List<Integer>[] children = new List[5];
        for (int i = 0; i < 5; i++) {
            children[i] = new ArrayList<>();
        }
        children[0].add(1);
        children[0].add(2);
        children[0].add(3);

        // Calculate minimum number of service centers and print the result
        Question2_B sc = new Question2_B();
        System.out.println(sc.calculateMinServiceCenters(5, children));
    }

}