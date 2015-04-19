package de.lukasniemeier.gamecenterlivesender.http;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import org.droidparts.contract.HTTP;
import org.droidparts.net.http.HTTPException;
import org.droidparts.net.http.RESTClient2;

import java.util.HashMap;
import java.util.Map;

import de.lukasniemeier.gamecenterlivesender.R;
import de.lukasniemeier.gamecenterlivesender.model.publishpoint.PublishPointResult;
import de.lukasniemeier.gamecenterlivesender.utils.Functional;

public class TaskFactory {

    private static final String TAG = TaskFactory.class.getSimpleName();

    private final Context context;

    private final RESTClient2 restClient;

    private final TextView statusView;

    private final Animation fadeOutAnimation;

    public TaskFactory(Context context, RESTClient2 restClient, TextView statusView) {
        this.context = context;
        this.restClient = restClient;
        this.statusView = statusView;
        this.fadeOutAnimation = new AlphaAnimation(1.0f, 0.0f);
        fadeOutAnimation.setDuration(2000);
        fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                statusView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
    }

    public HttpTask createPublishTask(String url, Functional.Consumer<String> castConsumer) {
        return createPublish(url, castConsumer,
                exception -> {
                    String message = context.getString(R.string.error_cast_generic);
                    if (exception instanceof HTTPException) {
                        HTTPException httpException = (HTTPException) exception;
                        if (httpException.getResponseCode() == HTTP.ResponseCode.SC_BAD_REQUEST) {
                            message = context.getString(R.string.error_missing_feed);
                        }
                    }
                    showErrorToast(message, exception);
                });
    }

    public HttpTask createPublishTaskWithFallback(final HttpTask fallbackTask, String url, Functional.Consumer<String> castConsumer) {
        return createPublish(url, castConsumer, exception -> {
            Log.w(TAG, "Publish task failed, falling back", exception);
            fallbackTask.execute();
        });
    }

    private HttpTask createPublish(String url, Functional.Consumer<String> castConsumer, Functional.Consumer<Exception> failureFunction) {
        return new GetHttpTask(restClient, url, context,
                createStatusFunc("Obtaining video feed..."),
                response -> {
                    statusView.setText("Starting to cast...");
                    try {
                        PublishPointResult result = PublishPointResult.parse(response.body, context);
                        castConsumer.accept(result.path);
                        statusView.startAnimation(fadeOutAnimation);
                    } catch (Exception e) {
                        showErrorToast(context.getString(R.string.error_parse_pp), e);
                    }
                },
                failureFunction);
    }

    public HttpTask createLoginTask(String user, String password, final HttpTask publishTask) {
        final Map<String, String> formData = new HashMap<String, String>();
        formData.put("username", user);
        formData.put("password", password);
        formData.put("deviceid", "unknown");
        formData.put("devicetype", "8");
        formData.put("nosublink", "true");
        return new PostHttpTask(formData, restClient, context.getString(R.string.login_url), context,
                createStatusFunc("Logging in..."),
                response -> {
                    response.headers.get("Set-Cookie");
                    publishTask.execute();
                },
                exception -> showErrorToast(context.getString(R.string.error_unable_login), exception));
    }

    public void showErrorToast(String message, Exception e) {
        Toast.makeText(context, String.format(context.getString(R.string.error_error), message), Toast.LENGTH_LONG).show();
        Log.e(TAG, String.format("Error: %s", message), e);
        statusView.startAnimation(fadeOutAnimation);
    }

    private Functional.Procedure createStatusFunc(String message) {
        return () -> {
            statusView.setVisibility(View.VISIBLE);
            statusView.setText(message);
        };
    }

}
