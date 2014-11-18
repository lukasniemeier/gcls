package de.lukasniemeier.gamecenterlivesender.view;

import android.content.Context;
import android.support.v7.media.MediaRouter;
import android.widget.Toast;

import com.google.sample.castcompanionlibrary.cast.callbacks.VideoCastConsumerImpl;

public class SimpleVideoCastConsumer extends VideoCastConsumerImpl {

    private Context context;

    public SimpleVideoCastConsumer(Context context) {
        this.context = context;
    }

    @Override
    public void onCastDeviceDetected(MediaRouter.RouteInfo info) {
        Toast.makeText(context, "Chromecast detected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed(int resourceId, int statusCode) {
        Toast.makeText(context, "onFailed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onApplicationConnectionFailed(int errorCode) {
        Toast.makeText(context, "onApplicationConnectionFailed", Toast.LENGTH_SHORT).show();
        return super.onApplicationConnectionFailed(errorCode);
    }

}
