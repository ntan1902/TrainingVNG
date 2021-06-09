package com.vng.ewallet.grpc.service.user;

import com.google.protobuf.Empty;
import grpc.user.APIResponse;
import grpc.user.UserIdRequest;
import grpc.user.UserItem;
import grpc.user.UserItemsResponse;


public interface UserGrpcService {
    UserItemsResponse findAllUserItems(Empty request);

    UserItem insertUserItemById(UserItem request);

    UserItem findUserItemById(UserIdRequest request);

    UserItem updateUserItem(UserItem request);

    APIResponse deleteUserItemById(UserIdRequest request);

}
//