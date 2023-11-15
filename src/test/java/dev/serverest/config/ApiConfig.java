package dev.serverest.config;

import dev.serverest.utils.LogUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ApiConfig {

    protected Response response;
    protected Object body;
    protected Map<String, String> cookies = new HashMap<>();;
    protected String cookie = "";
    protected String url;
    protected ContentType contentType;
    protected Map<String, String> headers = new HashMap<>();
    protected Map<String, String> pathParams = new HashMap<>();
    protected Map<String, String> queryParams = new HashMap<>();
    protected Map<String, String> formParams = new HashMap<>();
    protected String token;

    public void log(String httpMethod) {
        LogUtils.logInfo("*********************** Sent Data **********************");
        LogUtils.logInfo(httpMethod + " " + url);
        LogUtils.logInfo("Body: \n" + body);
        LogUtils.logInfo("Headers: " + headers);
        LogUtils.logInfo("Path Params: " + pathParams);
        LogUtils.logInfo("Query Params: " + queryParams);

        LogUtils.logInfo("\n********************* Received Data ********************");
        LogUtils.logInfo("Status code: " + response.getStatusCode());
        LogUtils.logInfo("Received payload: \n" + response.asPrettyString());
        LogUtils.logInfo("Response time: " + response.timeIn(TimeUnit.MILLISECONDS));
        LogUtils.logInfo("********************************************************\n");
    }
}
