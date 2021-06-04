package com.vng.ewallet.user.verticle;

import com.vng.ewallet.user.User;
import com.vng.ewallet.user.UserService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class UserVerticle extends AbstractVerticle {

    private final UserService userService;

    @Autowired
    public UserVerticle(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void start(Future<Void> startFuture) throws Exception {
        Router router = Router.router(vertx);

        router.get("/api/v1/users").handler(this::findAllUsers);

        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(config().getInteger("http.port", 7070),
                        result -> {
                            if (result.succeeded()) {
                                log.info("UserVerticle api start success");
                                startFuture.complete();
                            } else {
                                log.error("UserVerticle api start failed. {}", result.cause().getMessage());
                                startFuture.fail(result.cause());
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

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        super.stop(stopFuture);
    }

}
