package com.NodeTester.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.NodeTester.Dao.NodeDAO;

@Service
public class NodeService {

	@Autowired
    private final NodeDAO nodeDAO;

    public NodeService(NodeDAO nodeDAO) {
        this.nodeDAO = nodeDAO;
    }

    public void addNode(int node1, int node2) {
        if (node1 == node2) {
            throw new IllegalArgumentException("Cannot connect a node to itself");
        }
        nodeDAO.addNode(node1, node2);
    }

    public boolean checkIfConnected(int node1, int node2) {
        return nodeDAO.checkIfConnected(node1, node2);
    }
}

