package com.example.animatedtextviewapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, MainMvp {

    TextView textView;
    FrameLayout layout;
    Presenter presenter;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new Presenter();
        presenter.onAttach(this);
        textView = findViewById(R.id.tv_hello);
        layout = findViewById(R.id.fl);
        layout.setOnTouchListener(this);



        textView.setOnClickListener(v -> {
            presenter.shouldRepeatAnimations = false;
            textView.clearAnimation();
            presenter.timer.cancel();
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        presenter.timer.cancel();
        textView.clearAnimation();
        presenter.setLocation(findViewById(R.id.fl).getMeasuredHeight());
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            textView.setX(event.getX());
            textView.setY(event.getY());
            textView.setTextColor(getColor(R.color.main_text_color));
            Toast.makeText(this, "click", Toast.LENGTH_LONG).show();
        }
        presenter.timer.start();
        return true;
    }

    @Override
    public void setAnimation(TranslateAnimation animation) {
        textView.startAnimation(animation);
    }
}