package com.iffy.mianshi.pattern.facade.glide;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.iffy.mianshi.R;

import java.net.URL;

/**
 * author : iffy
 * time   : 2020/03/26
 */
public class TestMenMianActivity extends AppCompatActivity {
    private MyPicture myPicture;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menmian);
        myPicture = MyPicture.getInstance(this);
        myPicture.showPicture("https://cdn2.jianshu.io/assets/default_avatar/2-9636b13945b9ccf345bc98d0d81074eb.jpg",findViewById(R.id.menmian));
    }


}
