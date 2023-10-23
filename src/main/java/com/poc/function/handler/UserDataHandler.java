package com.poc.function.handler;


import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.poc.function.functions.BulkDataQueryFunction;
import com.poc.function.model.User;
import com.poc.function.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDataHandler {

    @Autowired
    private BulkDataQueryFunction bulkDataQueryFunction;

    @FunctionName("hello")
    public HttpResponseMessage execute(
            @HttpTrigger(name = "request", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<User>> request,
            ExecutionContext context) {
            User user = new User();
            user.setId("1");
            user.setName("John");
            user.setEmail("hello@gmail.com");
            user.setPhone("1234567890");
            user.setAddress("Bangalore");
        context.getLogger().info("Greeting user name: " + user.getName());
        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(bulkDataQueryFunction.saveUser().apply(user))
                .header("Content-Type", "application/json")
                .build();
    }

    @FunctionName("getUsers")
    public HttpResponseMessage getUsers(
            @HttpTrigger(name = "request", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<User>> request,
            @BindingName("name") String name,
            ExecutionContext context) {
            context.getLogger().info("Greeting user by name: ");
            return request
                    .createResponseBuilder(HttpStatus.OK)
                    .body(bulkDataQueryFunction.getUser().apply(name))
                    .header("Content-Type", "application/json")
                    .build();
    }

}
