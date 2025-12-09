package com.example.demo.bridge;

public interface Exporter {
    byte[] export(String content) throws Exception;
}
