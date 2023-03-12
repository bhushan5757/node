package com.NodeTester.Dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.springframework.stereotype.Repository;

@Repository
public class NodeDAO {

    private final Map<Integer, Set<Integer>> graph = new HashMap<>();

    public void addNode(int node1, int node2) {
        graph.computeIfAbsent(node1, k -> new HashSet<>()).add(node2);
        graph.computeIfAbsent(node2, k -> new HashSet<>()).add(node1);
    }

    public boolean checkIfConnected(int node1, int node2) {
        if (!graph.containsKey(node1) || !graph.containsKey(node2)) {
            return false;
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node1);
        while (!queue.isEmpty()) {
            int currNode = queue.poll();
            visited.add(currNode);
            if (currNode == node2) {
                return true;
            }
            for (int neighbor : graph.getOrDefault(currNode, Collections.emptySet())) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                }
            }
        }
        return false;
    }
}

