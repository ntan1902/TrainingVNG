package com.vng.ewallet.user.grpc;

import com.google.protobuf.Empty;
import com.vng.ewallet.grpc.UserItemsResponse;
import com.vng.ewallet.grpc.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class UserGrpcController extends UserServiceGrpc.UserServiceImplBase {
    private final UserGrpcService userService;

    @Autowired
    public UserGrpcController(UserGrpcService userService) {
        this.userService = userService;
    }

    @Override
    public void findAllUsers(Empty request, StreamObserver<UserItemsResponse> responseObserver) {
        responseObserver.onNext(userService.findAllUsers(request));
        responseObserver.onCompleted();
    }
}
