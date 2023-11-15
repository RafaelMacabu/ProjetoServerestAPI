package dev.serverest.services;

import io.restassured.response.Response;
import org.testng.Assert;

public class Assertions {

    protected void assertEquals(Object actual,Object expected){
        Assert.assertEquals(actual,expected);
    }
}
