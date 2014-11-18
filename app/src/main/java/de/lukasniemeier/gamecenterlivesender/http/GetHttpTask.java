package de.lukasniemeier.gamecenterlivesender.http;

import android.content.Context;

import org.droidparts.net.http.HTTPResponse;
import org.droidparts.net.http.RESTClient2;
import org.joda.time.DateTime;

import de.lukasniemeier.gamecenterlivesender.utils.Functional;

public class GetHttpTask extends HttpTask {

    private final DateTime modifiedSince;
    private final boolean asBody;

    public GetHttpTask(RESTClient2 restClient,
                       String url,
                       DateTime modifiedSince,
                       Context ctx,
                       Functional.Consumer<HTTPResponse> successFunction,
                       Functional.Consumer<Exception> failureFunction) {
        super(restClient, url, ctx, successFunction, failureFunction);
        this.modifiedSince = modifiedSince;
        this.asBody = true;
    }

    public GetHttpTask(RESTClient2 restClient,
                       String url,
                       Context ctx,
                       Functional.Consumer<HTTPResponse> successFunction,
                       Functional.Consumer<Exception> failureFunction) {
        this(restClient, url, new DateTime(0), ctx, successFunction, failureFunction);
    }

    public GetHttpTask(RESTClient2 restClient, String url, Context ctx,
                       Functional.Procedure preFunction,
                       Functional.Consumer<HTTPResponse> successFunction,
                       Functional.Consumer<Exception> failureFunction) {
        super(restClient, url, ctx, preFunction, successFunction, failureFunction);
        this.modifiedSince = new DateTime(0);
        this.asBody = true;
    }

    @Override
    protected HTTPResponse onExecute() throws Exception {
        return restClient.get(url, modifiedSince.getMillis(), "", asBody);
    }
}
