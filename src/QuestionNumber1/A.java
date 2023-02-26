package QuestionNumber1;

import java.util.*;

public class A {
    static class Edge {
        int src, dest, cost;
        Edge(int src, int dest, int cost) {
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }
    }

    public static int findCheapestRoute(int[][] edges, int[] charges, int source, int destination, int timeConstraint) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];
            int cost = edge[2];
            graph.computeIfAbsent(src, k -> new ArrayList<>()).add(new Edge(src, dest, cost));
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // [node, time]
        Set<Integer> visited = new HashSet<>();

        pq.offer(new int[]{source, 0, charges[source]}); // [node, time, cost]

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int time = curr[1];
            int cost = curr[2];

            if (node == destination) {
                return cost;
            }

            visited.add(node);

            if (graph.containsKey(node)) {
                for (Edge edge : graph.get(node)) {
                    int nextNode = edge.dest;
                    int nextTime = time + edge.cost;
                    int nextCost = cost + charges[nextNode];


                    if (nextTime <= timeConstraint && !visited.contains(nextNode)) {
                        pq.offer(new int[]{nextNode, nextTime, nextCost});
                    }
                }
            }
        }


        return -1;
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1,5}, {0,3,2}, {1,2,5}, {3,4,5}, {4,5,6}, {2,5,5}};
        int[] charges = {10,2,3,25,25,4};
        int source = 0;
        int destination = 5;
        int timeConstraint = 14;
        int cheapestCost = findCheapestRoute(edges, charges, source, destination, timeConstraint);
        System.out.println("Cheapest route cost: " + cheapestCost);
    }

}
