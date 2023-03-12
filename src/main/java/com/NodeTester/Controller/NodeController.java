package com.NodeTester.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.NodeTester.Service.NodeService;

@RestController
public class NodeController {

	@Autowired
    private final NodeService nodeService;

    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @PostMapping("/nodes")
    public ResponseEntity<String> joinNodes(@RequestParam("node1") int node1, @RequestParam("node2") int node2) {
        if (node1 == node2) {
            return ResponseEntity.badRequest().body("Cannot connect a node to itself");
        }
        nodeService.addNode(node1, node2);
        return ResponseEntity.ok().body("Connected Successful..Nodes("+node1+","+node2+")");
    }

    @GetMapping("/nodes")
    public ResponseEntity<Boolean> checkIfConnected(@RequestParam("node1") int node1, @RequestParam("node2") int node2) {
        boolean isConnected = nodeService.checkIfConnected(node1, node2);
        return ResponseEntity.ok(isConnected);
    }
}

