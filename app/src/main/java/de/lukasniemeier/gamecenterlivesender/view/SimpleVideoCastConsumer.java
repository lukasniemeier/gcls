package de.lukasniemeier.gamecenterlivesender.view;

import android.content.Context;
import android.widget.Toast;

import com.google.android.libraries.cast.companionlibrary.cast.callbacks.VideoCastConsumerImpl;

public class SimpleVideoCastConsumer extends VideoCastConsumerImpl {

    private Context context;

    public SimpleVideoCastConsumer(Context context) {
        this.context = context;
    }

    @Override
    public void onFailed(int resourceId, int statusCode) {
        Toast.makeText(context, "Connection failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onApplicationConnectionFailed(int errorCode) {
        Toast.makeText(context, "Connection failed", Toast.LENGTH_SHORT).show();
        super.onApplicationConnectionFailed(errorCode);
    }

}
