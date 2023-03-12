package com.NodeTester.newtest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.NodeTester.Service.NodeService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NodeControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private NodeService nodeService;

    @Test
    public void testJoinNodes_Success() {
        int node1 = 1;
        int node2 = 2;

        ResponseEntity<String> response = restTemplate.postForEntity("/nodes?node1={node1}&node2={node2}", null, String.class, node1, node2);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(nodeService).addNode(node1, node2);
    }

    @Test
    public void testJoinNodes_BadRequest() {
        int node1 = 1;
        int node2 = 1;

        ResponseEntity<String> response = restTemplate.postForEntity("/nodes?node1={node1}&node2={node2}", null, String.class, node1, node2);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Cannot connect a node to itself", response.getBody());
        is(nodeService);
    }

    @Test
    public void testCheckIfConnected_Connected() {
        int node1 = 1;
        int node2 = 2;

        when(nodeService.checkIfConnected(node1, node2)).thenReturn(true);

        ResponseEntity<Boolean> response = restTemplate.getForEntity("/nodes?node1={node1}&node2={node2}", Boolean.class, node1, node2);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
        verify(nodeService).checkIfConnected(node1, node2);
    }

    @Test
    public void testCheckIfConnected_NotConnected() {
        int node1 = 1;
        int node2 = 2;

        when(nodeService.checkIfConnected(node1, node2)).thenReturn(false);

        ResponseEntity<Boolean> response = restTemplate.getForEntity("/nodes?node1={node1}&node2={node2}", Boolean.class, node1, node2);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody());
        verify(nodeService).checkIfConnected(node1, node2);
    }
}

