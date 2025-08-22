package com.example.demo.reactive.grpc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * Integration test for the gRPC UserService.
 */
@SpringBootTest
@TestPropertySource(properties = {
    "grpc.server.port=9091" // Use different port for tests to avoid conflicts
})
class UserGrpcServiceIntegrationTest {

  @Test
  void contextLoads() {
    // This test verifies that the Spring context loads successfully with gRPC service
    assertTrue(true, "Spring context should load with gRPC service");
  }
}