package com.example.chequebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openNewItemForm2(View view) {
        Intent intent = new Intent(MainActivity.this, NewRecordDataActivity.class);
        startActivity(intent);
    }

    public void openNewItemForm(View view) {
        Intent intent = new Intent(MainActivity.this, NewRecordFormActivity.class);
        startActivity(intent);
    }


}
