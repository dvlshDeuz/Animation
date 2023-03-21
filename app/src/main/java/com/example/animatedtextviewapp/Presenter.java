package com.example.animatedtextviewapp;

import android.os.CountDownTimer;
import android.view.animation.TranslateAnimation;
import androidx.appcompat.app.AppCompatActivity;

public class Presenter extends AppCompatActivity {


    TranslateAnimation animationDown;
    TranslateAnimation animationUp;
    boolean shouldRepeatAnimations = true;
    int layoutHeight;
    MainMvp view;
    CountDownTimer timer;

    void onAttach(MainMvp view) {
       this.view = view;
        timer = new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                shouldRepeatAnimations = false;
            }

            public void onFinish() {
                TranslateAnimation anim = new TranslateAnimation(0f, 0f, 1f, layoutHeight);
                anim.setAnimationListener(down);
                anim.setDuration(4000);
                anim.setFillAfter(true);
                shouldRepeatAnimations = true;
                view.setAnimation(anim);
            }
        };
    }

    public void setLocation(int height) {
        layoutHeight = height;
    }

    android.view.animation.Animation.AnimationListener up = new android.view.animation.Animation.AnimationListener() {
        @Override
        public void onAnimationStart(android.view.animation.Animation animation) {}

        @Override
        public void onAnimationEnd(android.view.animation.Animation animation) {
            if (shouldRepeatAnimations) {
                animationDown = new TranslateAnimation(0f, 0f, -layoutHeight, layoutHeight);
                animationDown.setAnimationListener(down);
                animationDown.setDuration(2000);
                view.setAnimation(animationDown);

            }
        }

        @Override
        public void onAnimationRepeat(android.view.animation.Animation animation) {}
    };

    android.view.animation.Animation.AnimationListener down = new android.view.animation.Animation.AnimationListener() {
        @Override
        public void onAnimationStart(android.view.animation.Animation animation) {}

        @Override
        public void onAnimationEnd(android.view.animation.Animation animation) {
            if (shouldRepeatAnimations) {
                animationUp = new TranslateAnimation(0f, 0f, layoutHeight, -layoutHeight);
                animationUp.setAnimationListener(up);
                animationUp.setDuration(2000);
                view.setAnimation(animationUp);
            }
        }

        @Override
        public void onAnimationRepeat(android.view.animation.Animation animation) {}
    };
}
