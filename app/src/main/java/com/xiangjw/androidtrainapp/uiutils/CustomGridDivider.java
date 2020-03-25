package com.xiangjw.androidtrainapp.uiutils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomGridDivider extends RecyclerView.ItemDecoration {
    private int dividerHeight;
    private int dividerWidth;

    public CustomGridDivider() {
        super();
        this.dividerHeight = 2;
        this.dividerWidth = 2;
    }

    public CustomGridDivider(int dividerHeight, int dividerWidth) {
        super();
        this.dividerHeight = dividerHeight;
        this.dividerWidth = dividerWidth;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);

        outRect.top = dividerHeight;
        if (position % 2 == 0){
            outRect.left = dividerWidth;
            outRect.right = dividerWidth / 2;
        }else{
            outRect.left = dividerWidth - dividerWidth / 2;
            outRect.right = dividerWidth;
        }
    }
}
