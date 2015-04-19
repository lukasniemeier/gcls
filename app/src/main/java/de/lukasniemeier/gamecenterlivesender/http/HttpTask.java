package de.lukasniemeier.gamecenterlivesender.http;

import android.content.Context;

import org.droidparts.concurrent.task.AsyncTaskResultListener;
import org.droidparts.concurrent.task.SimpleAsyncTask;
import org.droidparts.net.http.HTTPResponse;
import org.droidparts.net.http.RESTClient2;

import de.lukasniemeier.gamecenterlivesender.utils.Functional;

public abstract class HttpTask extends SimpleAsyncTask<HTTPResponse> implements Task {

    protected final RESTClient2 restClient;

    protected final String url;

    private final Functional.Procedure preFunction;

    public HttpTask(RESTClient2 restClient,
                    String url,
                    Context ctx,
                    Functional.Consumer<HTTPResponse> successFunction,
                    Functional.Consumer<Exception> failureFunction) {
        this(restClient, url, ctx, null, successFunction, failureFunction);
    }

    public HttpTask(RESTClient2 restClient,
                    String url,
                    Context ctx,
                    Functional.Procedure preFunction,
                    Functional.Consumer<HTTPResponse> successFunction,
                    Functional.Consumer<Exception> failureFunction) {
        super(ctx, createListener(successFunction, failureFunction));
        this.restClient = restClient;
        this.url = url;
        this.preFunction = preFunction;
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    protected void onPreExecute() {
        if (preFunction != null) {
            preFunction.execute();
        }
    }

    private static AsyncTaskResultListener<HTTPResponse> createListener(
            Functional.Consumer<HTTPResponse> successFunction,
            Functional.Consumer<Exception> failureFunction) {
        return new AsyncTaskResultListener<HTTPResponse>() {
            @Override
            public void onAsyncTaskSuccess(HTTPResponse response) {
                successFunction.accept(response);
            }

            @Override
            public void onAsyncTaskFailure(Exception e) {
                failureFunction.accept(e);
            }
        };
    }
}
