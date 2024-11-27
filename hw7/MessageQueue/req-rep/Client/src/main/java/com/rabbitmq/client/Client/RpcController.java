package com.rabbitmq.client.Client;

import com.rabbitmq.client.Client.RpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RpcController {

    @Autowired
    private RpcClient rpcClient;

    @GetMapping("/call/{message}")
    public String callServer(@PathVariable String message) {
        return rpcClient.send(message);
    }
}
