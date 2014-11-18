package de.lukasniemeier.gamecenterlivesender.http;

import android.content.Context;

import org.droidparts.net.http.HTTPResponse;
import org.droidparts.net.http.RESTClient2;

import java.util.Map;

import de.lukasniemeier.gamecenterlivesender.utils.Functional;

public class PostHttpTask extends HttpTask {

    private final Map<String, String> formData;

    public PostHttpTask(Map<String, String> formData,
                        RESTClient2 restClient,
                        String url,
                        Context ctx,
                        Functional.Consumer<HTTPResponse> successFunction,
                        Functional.Consumer<Exception> failureFunction) {
        super(restClient, url, ctx, successFunction, failureFunction);
        this.formData = formData;
    }

    public PostHttpTask(Map<String, String> formData,
                        RESTClient2 restClient,
                        String url,
                        Context ctx,
                        Functional.Procedure preFunction,
                        Functional.Consumer<HTTPResponse> successFunction,
                        Functional.Consumer<Exception> failureFunction) {
        super(restClient, url, ctx, preFunction, successFunction, failureFunction);
        this.formData = formData;
    }

    @Override
    protected HTTPResponse onExecute() throws Exception {
        return restClient.post(url, formData);
    }
}
