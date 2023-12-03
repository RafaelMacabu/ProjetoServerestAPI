package dev.serverest.api.applicationAPI;

import dev.serverest.api.RestResource;
import dev.serverest.pojo.Login;
import io.restassured.response.Response;

import static dev.serverest.api.Route.LOGIN;
import static dev.serverest.utils.LogUtils.logInfo;

public class LoginAPI {

    public static Response post(Login requestLogin) {
        return RestResource.post(LOGIN, requestLogin);
    }

    public static Login loginBuilder(String email, String password) {
        return Login.builder().
                email(email).
                password(password).
                build();
    }

    public static void logResponse(Login responseAsClass) {
        logInfo("========== RESPONSE BODY ==========");
        logInfo("Mensagem: " + responseAsClass.getMessage());
        if (responseAsClass.getAuthorization() != null) {
            logInfo("Bearer: " + responseAsClass.getAuthorization());
        }
        logInfo("===================================");
    }

}
