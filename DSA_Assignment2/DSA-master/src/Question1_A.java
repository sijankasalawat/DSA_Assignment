import java.util.*;

public class Question1_A {

    // Edge class to represent a route
    static class Edge {
        int src, dest, cost;
        Edge(int src, int dest, int cost) {
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }
    }

    public static int findCheapestRoute(int[][] edges, int[] charges, int sourceNode, int destNode, int timeLimit) {
        // Construct graph using adjacency list
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];
            int cost = edge[2];
            graph.computeIfAbsent(src, k -> new ArrayList<>()).add(new Edge(src, dest, cost));
        }

        // Initialize priority queue and visited set
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // [node, time]
        Set<Integer> visited = new HashSet<>();

        // Add source node to priority queue
        pq.offer(new int[]{sourceNode, 0, charges[sourceNode]}); // [node, time, cost]

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currNode = curr[0];
            int currTime = curr[1];
            int currCost = curr[2];

            // If destination reached, return cost
            if (currNode == destNode) {
                return currCost;
            }

            // Mark node as visited
            visited.add(currNode);

            // Explore neighboring nodes
            if (graph.containsKey(currNode)) {
                for (Edge edge : graph.get(currNode)) {
                    int nextNode = edge.dest;
                    int nextTime = currTime + edge.cost;
                    int nextCost = currCost + charges[nextNode];

                    // Check if next node can be visited within time limit and has not been visited before
                    if (nextTime <= timeLimit && !visited.contains(nextNode)) {
                        pq.offer(new int[]{nextNode, nextTime, nextCost});
                    }
                }
            }
        }

        // If destination not reachable, return -1
        return -1;
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1,5}, {0,3,2}, {1,2,5}, {3,4,5}, {4,5,6}, {2,5,5}};
        int[] charges = {10,2,3,25,25,4};
        int sourceNode = 0;
        int destNode = 5;
        int timeLimit = 14;
        int cheapestCost = findCheapestRoute(edges, charges, sourceNode, destNode, timeLimit);
        System.out.println("Cheapest route cost: " + cheapestCost);
    }
}
