package com.vng.ewallet.verticle.user;

import com.vng.ewallet.entity.User;
import com.vng.ewallet.service.user.UserService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class UserVerticle extends AbstractVerticle {

    private final UserService userService;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        Router router = Router.router(vertx);

        router.get("/api/v1/users").handler(this::findAllUsers);

        vertx
                .createHttpServer()
                .requestHandler(router)
                .listen(config().getInteger("http.port", 7070),
                        result -> {
                            if (result.succeeded()) {
                                log.info("UserVerticle api start success");
                                startPromise.complete();
                            } else {
                                log.error("UserVerticle api start failed. {}", result.cause().getMessage());
                                startPromise.fail(result.cause());
                            }
                        }
                );
    }

    public void findAllUsers(RoutingContext routingContext) {

        HttpServerResponse response = routingContext.response();

        response.putHeader("content-type", "application/json; charset=utf-8");
        try {
            List<User> users = this.userService.findAllUsers();

            response.end(Json.encodePrettily(users));
        } catch (Exception e) {
            response.end(e.getMessage());
        }

    }

}
