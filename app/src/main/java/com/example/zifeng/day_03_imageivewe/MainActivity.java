package com.example.zifeng.day_03_imageivewe;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by ZiFeng on 2018/3/25.
 */
public class MainActivity extends Activity {
    // 定义一个图片资源数组
    int[] images = new int[]{
            R.drawable.image_1,R.drawable.image_2,
            R.drawable.image_2,R.drawable.image_3,
            R.drawable.image_5
    };
    // 定义默认显示的图片
    int currentImg = 2;
    // 定义图片初始的透明度
    private int alpha = 255;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 实例化控件
        final Button plus = findViewById(R.id.plus);
        final Button minus = findViewById(R.id.minus);
        final Button next = findViewById(R.id.next);
        final ImageView image1 = findViewById(R.id.image1);
        final ImageView inage2 = findViewById(R.id.image2);
        // 控件绑定一个监听器
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 设置显示的图片资源，每次点击递增1
                image1.setImageResource(images[++ currentImg % images.length]);
            }
        });
        // 创建一个监听器
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // 满足条件即执行大括号内的内容
                if (v == plus){
                    alpha += 20;
                }
                if (v == minus){
                    alpha -= 20;
                }
                if (alpha >= 255){
                    alpha = 255;
                }
                if (alpha <=0){
                    alpha = 0;
                }
                // 给image1控件设置透明度
                image1.setImageAlpha(alpha);
            }
        };
        // 绑定监听器
        plus.setOnClickListener(listener);
        minus.setOnClickListener(listener);
        // 设置一个监听器
        image1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //
                BitmapDrawable bitmapDrawable = (BitmapDrawable)image1.getDrawable();
                // 获取第一个图片显示框中的位图
                Bitmap bitmap = bitmapDrawable.getBitmap();
                // bitmap图片实际大小与第一个ImageView的缩放比例
                double scale = 1.0 * bitmap.getHeight() / image1.getHeight();
                // 获取需要显示的图片开始点
                int x = (int) (event.getX() * scale);
                int y = (int) (event.getY() * scale);
                if (x + 120 > bitmap.getWidth()){
                    x = bitmap.getWidth() -120 ;
                }
                if (y + 120 > bitmap.getHeight()){
                    y = bitmap.getHeight() - 120;
                }
                // 显示图片的指定区域
                inage2.setImageBitmap(Bitmap.createBitmap(bitmap,x,y,120,120));
                // 设置透明度
                inage2.setImageAlpha(alpha);
                return true;
            }
        });
    }
}
