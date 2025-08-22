package com.example.demo.reactive.grpc;

import com.example.demo.reactive.reactive.service.identity.IdentityServiceReactive;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * gRPC service implementation for User operations.
 * Uses the existing reactive service layer.
 */
@GrpcService
@RequiredArgsConstructor
@Slf4j
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

  private final IdentityServiceReactive identityServiceReactive;

  @Override
  public void getUsers(GetUsersRequest request, StreamObserver<UserResponse> responseObserver) {
    log.info("gRPC GetUsers request received");
    
    identityServiceReactive.findIdentities()
        .map(UserMapper::toUserResponse)
        .doOnNext(userResponse -> {
          log.debug("Sending user: {}", userResponse.getUsername());
          responseObserver.onNext(userResponse);
        })
        .doOnComplete(() -> {
          log.info("Completed streaming all users");
          responseObserver.onCompleted();
        })
        .doOnError(throwable -> {
          log.error("Error in GetUsers", throwable);
          responseObserver.onError(throwable);
        })
        .subscribe();
  }

  @Override
  public void getUserCount(GetUserCountRequest request, StreamObserver<UserCountResponse> responseObserver) {
    log.info("gRPC GetUserCount request received");
    
    identityServiceReactive.findIdentitiesCount()
        .map(count -> UserCountResponse.newBuilder().setCount(count).build())
        .doOnSuccess(countResponse -> {
          log.info("Sending user count: {}", countResponse.getCount());
          responseObserver.onNext(countResponse);
          responseObserver.onCompleted();
        })
        .doOnError(throwable -> {
          log.error("Error in GetUserCount", throwable);
          responseObserver.onError(throwable);
        })
        .subscribe();
  }

  @Override
  public void getUsersByPage(GetUsersByPageRequest request, StreamObserver<UserResponse> responseObserver) {
    log.info("gRPC GetUsersByPage request received for page: {}, totalPages: {}", 
        request.getPage(), request.getTotalPages());
    
    identityServiceReactive.findIdentitiesPerPage(request.getPage(), request.getTotalPages())
        .map(UserMapper::toUserResponse)
        .doOnNext(userResponse -> {
          log.debug("Sending user from page {}: {}", request.getPage(), userResponse.getUsername());
          responseObserver.onNext(userResponse);
        })
        .doOnComplete(() -> {
          log.info("Completed streaming users for page: {}", request.getPage());
          responseObserver.onCompleted();
        })
        .doOnError(throwable -> {
          log.error("Error in GetUsersByPage for page: {}", request.getPage(), throwable);
          responseObserver.onError(throwable);
        })
        .subscribe();
  }
}