package dev.serverest.tests;

import dev.serverest.api.applicationAPI.LoginAPI;
import dev.serverest.pojo.Login;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginTest {

    @Test
    public void Login(){
       Login requestLogin = loginBuilder("raffe@qa.com.br","raff");

        Response response = LoginAPI.post(requestLogin);
        Assert.assertEquals(response.statusCode(),200);

        Login responseLogin = response.as(Login.class);
        Assert.assertEquals(responseLogin.getMessage(),"Login realizado com sucesso");
    }

    public Login loginBuilder(String email,String password){
        return Login.builder().
                email(email).
                password(password).
                build();
    }
}
