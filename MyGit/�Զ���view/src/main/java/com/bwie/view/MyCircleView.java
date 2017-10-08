package com.bwie.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

/**
 * Created by 张丹阳 on 2017/10/8.
 */

public class MyCircleView extends View{

    //当前画笔画圆的颜色
    private int CurrenCircleBoundColor;
    private Paint paint;

    private int circleBundColor;
    private float circleBoundWidth;
    private int pivotX;
    private int pivotY;
    private float radius=130;
    private float currentDegree=0;
    private int currentSpeed=1;
    private boolean isPause=false;

    public MyCircleView(Context context) {
        super(context);
        initView(context);
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCustomView);
        for (int i=0;i<typedArray.getIndexCount();i++){
            int attr = typedArray.getIndex(i);
            switch (attr){
                case R.styleable.MyCustomView_circlr_bound_color:
                    circleBundColor = typedArray.getColor(attr, Color.RED);
                    CurrenCircleBoundColor=circleBundColor;
                    break;
                case R.styleable.MyCustomView_circlr_bound_width:
                    circleBoundWidth = typedArray.getDimension(attr, 3);
                    break;
            }
        }
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        paint = new Paint();
    }

    public void setColor(int color){
        if (CurrenCircleBoundColor!=color){
            CurrenCircleBoundColor=color;
        }else {
            CurrenCircleBoundColor=circleBundColor;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);//抗锯齿
        paint.setColor(CurrenCircleBoundColor);//画笔颜色
        paint.setStrokeWidth(circleBoundWidth);//宽度
        paint.setStyle(paint.getStyle().STROKE);
        pivotX = getWidth() / 2;
        pivotY = getHeight() / 2;
        canvas.drawCircle(pivotX,pivotY,radius,paint);
        canvas.save();
        //旋转画布 , 如果旋转的的度数大的话,视觉上看着是旋转快的
        canvas.rotate(currentDegree,pivotX,pivotY);
        //提供了一些API可以用来画路径(画线)
        Path path=new Path();
        //圆的开始位置，从A开始
        path.moveTo(pivotX+radius,pivotY);
        //从A点画一条直线到D点
        path.lineTo(pivotX+radius-20,pivotY-20);
        //从D点画一个直线到B点
        path.lineTo(pivotX+radius,pivotY+20);
        //从B点画一个直线到C点
        path.lineTo(pivotX+radius+20,pivotY-20);
        //闭合  --  从C点画一个直线到A点
        path.close();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawPath(path,paint);
        canvas.restore();
        //旋转的度数一个一个度数增加,  如果乘以一个速度的话,按一个速度速度增加
        currentDegree+=1*currentSpeed;
        if (!isPause){
            invalidate();
        }
    }
    public void speed(){
        ++currentSpeed;
        if (currentSpeed>=10){
            currentSpeed=10;
            Toast.makeText(getContext(),"我比闪电还快",Toast.LENGTH_SHORT).show();
        }
    }
    public void slowDown(){
        --currentSpeed;
        if (currentSpeed<=1){
            currentSpeed=1;
        }
    }
}
