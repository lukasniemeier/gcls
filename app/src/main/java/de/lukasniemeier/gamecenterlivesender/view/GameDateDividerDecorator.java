package de.lukasniemeier.gamecenterlivesender.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

public class GameDateDividerDecorator extends RecyclerView.ItemDecoration {

    private static final int DIVIDER_HEIGHT = 50;
    private static final int TOP_PADDING = DIVIDER_HEIGHT + 40;


    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    private Drawable mDivider;

    private Map<Integer, String> dividers;

    public GameDateDividerDecorator(Context context) {
        final TypedArray attributes = context.obtainStyledAttributes(ATTRS);
        mDivider = attributes.getDrawable(0);
        attributes.recycle();

        dividers = new HashMap<Integer, String>();
    }

    public void clearDividers() {
        dividers.clear();
    }

    public void addDivider(int index, String groupName) {
        dividers.put(index, groupName);
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            renderChild(canvas, parent, left, right, child);
        }
    }

    private void renderChild(Canvas canvas, RecyclerView parent, int left, int right, View child) {
        int childIndex = parent.getChildPosition(child);
        if (!dividers.keySet().contains(childIndex)) {
            return;
        }

        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        final int top = child.getTop() - mDivider.getIntrinsicHeight() - DIVIDER_HEIGHT;
        final int bottom = child.getTop();
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(canvas);

        String text = dividers.get(childIndex);
        renderText(canvas, left, right, top, bottom, text);
    }

    private void renderText(Canvas canvas, int left, int right, int top, int bottom, String text) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setTextSize(24);

        Rect textBounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), textBounds);

        int width = right - left;
        int textFullXMargin = width - textBounds.width();
        int textXMargin = (int) ((double) textFullXMargin * 0.5);

        int height = bottom - top;
        int textFullYMargin = height - textBounds.height();
        int textYMargin = (int) ((double) textFullYMargin * 0.5);

        canvas.drawText(text, left + textXMargin, top - textBounds.top + textYMargin, paint);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int index = parent.getChildPosition(view);
        if (dividers.keySet().contains(index)) {
            outRect.set(0, mDivider.getIntrinsicHeight() + TOP_PADDING, 0, 0);
        } else {
            outRect.set(0, 0, 0, 0);
        }
    }
}