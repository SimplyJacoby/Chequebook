package com.example.chequebook;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class NewRecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);
    }

    public void onClickEvent(View v) {
        Button b = (Button)v;
        buttonEffect(v);
        if (String.valueOf(b.getText()).equals("+") || String.valueOf(b.getText()).equals("-")) {
            changeSign(v);
        }
        else if (String.valueOf(b.getText()).equals("C")) {
            clearText(v);
        }
        else {
            addNumber(v);
        }
    }

    public void onImageButtonClickEvent(View v) {
        ImageButton b = (ImageButton)v;
        buttonEffect(v);
        if (b.getId() == R.id.btnBack) {
            backspace(v);
        }
    }

    public void buttonEffect(View button){
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(getResources().getColor(R.color.clickColor), PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }

    public void addNumber(View v) {
        TextView tvAmount = (TextView) findViewById(R.id.tvAmount);
        Button b = (Button)v;
        double temp = 0;
        if (String.valueOf(tvAmount.getText()).equals("-") || String.valueOf(tvAmount.getText()).equals("")) {
            temp = 0;
        }
        else {
            temp = Double.valueOf(String.valueOf(tvAmount.getText()));
        }

        int decPlace = String.valueOf(tvAmount.getText()).indexOf(".");
        if (decPlace > 0 && String.valueOf(tvAmount.getText()).length() - decPlace == 3 && !String.valueOf(tvAmount.getText()).equals("-0.00") && !String.valueOf(tvAmount.getText()).equals("0.00")) {
            return;
        }

        if (String.valueOf(tvAmount.getText()).contains(".") && String.valueOf(b.getText()).equals(".") && !String.valueOf(tvAmount.getText()).equals("-0.00") && !String.valueOf(tvAmount.getText()).equals("0.00")) {
            return;
        }
        else if ((String.valueOf(tvAmount.getText()).equals("-0.00") || String.valueOf(tvAmount.getText()).equals("0.00")) && String.valueOf(b.getText()).equals(".")) {
            if (String.valueOf(tvAmount.getText()).equals("-0.00")) {
                tvAmount.setText("-0.");
            }
            else {
                tvAmount.setText("0.");
            }
            return;
        }

        if (String.valueOf(tvAmount.getText()).equals("-0.00")) {
            tvAmount.setText("-" + b.getText());
        }
        else if (String.valueOf(tvAmount.getText()).equals("0.00")) {
            tvAmount.setText(b.getText());
        }
        else if ((String.valueOf(tvAmount.getText()).equals("-") || String.valueOf(tvAmount.getText()).equals("")) && String.valueOf(b.getText()).equals(".")) {
            if (String.valueOf(tvAmount.getText()).equals("-")) {
                tvAmount.setText("-0.");
            }
            else {
                tvAmount.setText("0.");
            }
        }
        else if (String.valueOf(tvAmount.getText()).equals("-.") && String.valueOf(b.getText()).equals("+")) {
            tvAmount.setText("0.");
        }
        else if (temp >= 999999999 || temp <= -999999999) {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, "I doubt you're Bill Gates", duration);
            toast.show();
        }
        else {
            tvAmount.append(b.getText());
        }
    }

    public void changeSign (View v) {
        TextView tvAmount = (TextView) findViewById(R.id.tvAmount);
        Button b = (Button)v;
        double temp = Double.valueOf(String.valueOf(tvAmount.getText()));
        if (temp < 0 && String.valueOf(b.getText()).equals("+")) {
            temp = temp * -1;
            tvAmount.setText(String.valueOf(temp));
        }
        else if (temp > 0 && String.valueOf(b.getText()).equals("-")) {
            temp = temp * -1;
            tvAmount.setText(String.valueOf(temp));
        }
        else if (temp == 0) {
            temp = temp * -1;
            tvAmount.setText(String.valueOf(temp));
        }
    }

    public void clearText(View v) {
        TextView tvAmount = (TextView) findViewById(R.id.tvAmount);
        Button b = (Button)v;
        tvAmount.setText("-0.00");
    }

    public void backspace(View v) {
        TextView tvAmount = (TextView) findViewById(R.id.tvAmount);
        Button b = (Button)v;
        String str = String.valueOf(tvAmount.getText());
        if (str != null && str.length() > 1) {
            str = str.substring(0, str.length() - 1);
        }
        else if (str != null && str.length() <= 1) {
            str = "-0.00";
        }
        tvAmount.setText(str);
    }
}
