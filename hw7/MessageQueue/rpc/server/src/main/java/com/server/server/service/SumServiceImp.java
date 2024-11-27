package com.server.server.service;

import org.springframework.stereotype.Service;

@Service
public class SumServiceImp implements SumService{
    public Integer sum(Integer a, Integer b) {
        return a + b;
    }
}
