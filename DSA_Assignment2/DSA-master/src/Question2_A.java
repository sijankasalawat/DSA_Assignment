import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Question2_A {
    class Solution {
        // Method to calculate the greatest common divisor of two numbers
        public int findGcd(int num1, int num2) {
            if (num2 == 0) {
                return num1;
            }
            return findGcd(num2, num1 % num2);
        }

        // Depth-first search method to traverse the tree and find the closest ancestor for each node
        public void dfs(int[] nums, LinkedList<Integer>[] tree, int depth, int node, boolean[] visited, int[] ans, Map<Integer,int[]> map, boolean[][] poss) {
            if(visited[node]) return;
            visited[node] = true;
            int ancestor = -1;
            int d = Integer.MAX_VALUE;
            for(int i = 1; i < 51; i++) {
                if(poss[nums[node]][i] && map.containsKey(i)) {
                    if(depth - map.get(i)[0] <= d) {
                        d = depth - map.get(i)[0];
                        ancestor = map.get(i)[1];
                    }
                }
            }
            ans[node] = ancestor;
            int[] exist = (map.containsKey(nums[node])) ? map.get(nums[node]) :  new int[]{-1,-1};
            map.put(nums[node],new int[]{depth,node});
            for(int child : tree[node]) {
                if(visited[child]) continue;
                dfs(nums, tree, depth+1, child, visited, ans, map, poss);
            }
            if(exist[0] != -1) map.put(nums[node], exist);
            else map.remove(nums[node]);

            return;
        }

        public int[] closestAncestor(int[] nums, int[][] edges) {
            // Initialize a boolean matrix to check if the GCD of two numbers is 1 or not
            boolean[][] gcdPossibility = new boolean[51][51];
            for(int i = 1; i < 51; i++) {
                for(int j = 1; j < 51; j++) {
                    if(findGcd(i,j) == 1) {
                        gcdPossibility[i][j] = true;
                        gcdPossibility[j][i] = true;
                    }
                }
            }
            int n = nums.length;
            LinkedList<Integer>[] tree = new LinkedList[n];

            for(int i =0 ; i < tree.length; i++) tree[i] = new LinkedList<>();
            for(int edge[] : edges) {
                tree[edge[0]].add(edge[1]);
                tree[edge[1]].add(edge[0]);
            }

            int[] ans = new int[n];
            Arrays.fill(ans, -1);
            ans[0] = -1;
            Map<Integer,int[]> map = new HashMap<>();

            boolean[] visited = new boolean[n];
            dfs(nums, tree, 0, 0, visited, ans, map, gcdPossibility);
            return ans;
        }
    }

}

