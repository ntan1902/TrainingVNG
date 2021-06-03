package com.vng.ewallet.user.grpc;

import com.vng.ewallet.grpc.model.AllUsersResponse;
import com.vng.ewallet.grpc.model.EmptyRequest;
import com.vng.ewallet.grpc.model.UserServiceGrpc;
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
    public void findAllUsers(EmptyRequest request, StreamObserver<AllUsersResponse> responseObserver) {
        responseObserver.onNext(userService.findAllUsers(request));
        responseObserver.onCompleted();
    }
}
