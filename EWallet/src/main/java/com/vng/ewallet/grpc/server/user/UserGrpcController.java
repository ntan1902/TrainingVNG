package com.vng.ewallet.grpc.server.user;

import com.google.protobuf.Empty;
import com.vng.ewallet.grpc.service.user.UserGrpcService;
import grpc.user.*;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
@RequiredArgsConstructor
public class UserGrpcController extends UserServiceGrpc.UserServiceImplBase {
    private final UserGrpcService userGrpcService;

    @Override
    public void findAllUserItems(Empty request, StreamObserver<UserItemsResponse> responseObserver) {
        responseObserver.onNext(userGrpcService.findAllUserItems(request));
        responseObserver.onCompleted();
    }

    @Override
    public void insertUserItem(UserItem request, StreamObserver<UserItem> responseObserver) {
        responseObserver.onNext(userGrpcService.insertUserItemById(request));
        responseObserver.onCompleted();
    }

    @Override
    public void findUserItemById(UserIdRequest request, StreamObserver<UserItem> responseObserver) {
        responseObserver.onNext(userGrpcService.findUserItemById(request));
        responseObserver.onCompleted();
    }

    @Override
    public void updateUserItemById(UserItem request, StreamObserver<UserItem> responseObserver) {
        responseObserver.onNext(userGrpcService.updateUserItem(request));
        responseObserver.onCompleted();
    }

    @Override
    public void deleteUserItemById(UserIdRequest request, StreamObserver<APIResponse> responseObserver) {
        responseObserver.onNext(userGrpcService.deleteUserItemById(request));
        responseObserver.onCompleted();
    }
}
