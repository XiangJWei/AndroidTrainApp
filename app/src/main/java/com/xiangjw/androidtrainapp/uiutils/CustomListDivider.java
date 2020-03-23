package com.xiangjw.androidtrainapp.uiutils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomListDivider extends RecyclerView.ItemDecoration {
    private int dividerHeight;
    private int leftMargin;
    private int rightMargin;
    private int dividerColor;
    private boolean lastNeed;
    private Paint paint;

    public CustomListDivider() {
        super();
        this.dividerHeight = 1;
        this.leftMargin = 0;
        this.rightMargin = 0;
        this.dividerColor = Color.GRAY;
        this.lastNeed = true;
        initPaint();
    }

    public CustomListDivider(int dividerHeight, int leftMargin, int rightMargin, int dividerColor , boolean lastNeed) {
        super();
        this.dividerHeight = dividerHeight;
        this.leftMargin = leftMargin;
        this.rightMargin = rightMargin;
        this.dividerColor = dividerColor;
        this.lastNeed = lastNeed;
        initPaint();
    }

    private void initPaint(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(dividerColor);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);//针对整个RecyclerView绘画，所以需要循环
        int count = parent.getChildCount();
        for (int i = 0 ; i < count ; i ++){
            View itemView = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(itemView);
            if(lastNeed){
                c.drawRect(itemView.getLeft() + leftMargin
                        , itemView.getBottom()
                        , itemView.getRight() - rightMargin
                        , itemView.getBottom() + dividerHeight , paint);
            }else if (index > 0){
                c.drawRect(itemView.getLeft() + leftMargin
                        , itemView.getTop() - dividerHeight
                        , itemView.getRight() - rightMargin
                        , itemView.getTop() , paint);
            }
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if(lastNeed){
            //设定每一个列表项内容不填充的区域。这里即为每个列表项底部留出一段空白区域
            //再在这段空白区域onDraw画分割线
            outRect.bottom = dividerHeight;
        }else if(parent.getChildAdapterPosition(view) > 0){
            outRect.top = dividerHeight;
        }
    }
}
