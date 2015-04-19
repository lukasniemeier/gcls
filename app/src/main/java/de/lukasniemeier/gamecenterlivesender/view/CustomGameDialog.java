package de.lukasniemeier.gamecenterlivesender.view;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

import de.lukasniemeier.gamecenterlivesender.BuildConfig;
import de.lukasniemeier.gamecenterlivesender.R;
import de.lukasniemeier.gamecenterlivesender.model.GameHelper;
import de.lukasniemeier.gamecenterlivesender.model.games.Game;
import de.lukasniemeier.gamecenterlivesender.utils.Functional;

public class CustomGameDialog {

    private final Context context;
    private final Functional.BiConsumer<Game, String> castCallback;

    private EditText streamUrlInput = null;
    private CheckBox streamLive = null;
    private CheckBox streamProxy = null;

    public CustomGameDialog(Context context, Functional.BiConsumer<Game, String> castCallback) {
        this.context = context;
        this.castCallback = castCallback;
    }

    public void show() {
        MaterialDialog dialog = new MaterialDialog.Builder(context)
                .title(R.string.custom_game)
                .customView(R.layout.custom_feed, true)
                .positiveText(R.string.stream)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        assert streamUrlInput != null;
                        assert streamLive != null;
                        assert streamProxy != null;
                        boolean isLive = streamLive.isChecked();
                        boolean shouldBeProxied = streamProxy.isChecked();

                        Game customGame = GameHelper.fakeGame(isLive);
                        String url = streamUrlInput.getText().toString();
                        if (shouldBeProxied) {
                            url = BuildConfig.CORS_PROXY_URL + url;
                        }

                        castCallback.accept(customGame, url);
                    }
                })
                .build();

        View view = dialog.getCustomView();
        assert view != null;
        streamUrlInput = (EditText) view.findViewById(R.id.feed_url);
        streamLive = (CheckBox) view.findViewById(R.id.feed_live);
        streamProxy = (CheckBox) view.findViewById(R.id.feed_proxy);
        dialog.show();
    }

}
