package com.example.chequebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewRecordDataActivity extends AppCompatActivity {

    public double amount = 0;
    public boolean dotClicked = false;
    public boolean noDecimalPlace = true;
    public boolean firstDecimalPlace = false;
    public boolean lastDecimalPlace = false;
    public boolean income = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record_data);
    }

    public void onClickEvent(View v) {
        Button b = (Button)v;
        if (b.getId() == R.id.btnZero || b.getId() == R.id.btnOne  || b.getId() == R.id.btnTwo || b.getId() == R.id.btnThree || b.getId() == R.id.btnFour ||
                b.getId() == R.id.btnFive || b.getId() == R.id.btnSix || b.getId() == R.id.btnSeven || b.getId() == R.id.btnEight || b.getId() == R.id.btnNine) {
            if (!dotClicked) {
                editInteger(v);
            }
            else {
                editDecimal(v);
            }
        }

        if (b.getId() == R.id.btnExpense || b.getId() == R.id.btnIncome) {
            setIncomeOrExpense(v);
        }

        if (b.getId() == R.id.btnDot) {
            onDotClick(v);
        }

        if (b.getId() == R.id.btnBack) {
            backspace(v);
        }

        if (b.getId() == R.id.btnClear) {
            clear(v);
        }
    }

    public void onDotClick(View v) {
        dotClicked = true;
    }

    public void editInteger (View v) {
        Button b = (Button)v;

        String bVal = String.valueOf(b.getText());
        String temp = String.valueOf(amount);

        if (temp.substring(0, temp.indexOf('.')).length() <= 7) {
            temp = temp.substring(0, temp.indexOf('.')) + bVal + temp.substring(temp.indexOf('.'));
            writeAmount(temp);
        }
    }

    public void editDecimal (View v) {
        Button b = (Button)v;

        String bVal = String.valueOf(b.getText());
        String temp = String.valueOf(amount);

        if (noDecimalPlace && !firstDecimalPlace && !lastDecimalPlace) {
            temp = temp.substring(0, temp.indexOf('.') + 1) + bVal;
        }
        if (firstDecimalPlace && !noDecimalPlace && !lastDecimalPlace) {
            temp = temp.substring(0, temp.indexOf('.') + 2) + bVal;
        }
        if (lastDecimalPlace && !noDecimalPlace && ! firstDecimalPlace) {
            return;
        }
        writeAmount(temp);
    }

    public void writeAmount(String temp) {
        TextView tvAmount = (TextView) findViewById(R.id.tvAmount);

        amount = Double.valueOf(temp);
        temp = String.format("%.2f", amount);
        tvAmount.setText(temp);
    }

    public void setIncomeOrExpense(View v) {
        Button b = (Button)v;
        TextView tvPlusOrMinus = (TextView)findViewById(R.id.tvPlusOrMinus);

        if (b.getId() == R.id.btnIncome) {
            tvPlusOrMinus.setText("+");
            income = true;
        }
        else if (b.getId() == R.id.btnExpense) {
            tvPlusOrMinus.setText("-");
            income = false;
        }
    }

    public void clear(View v) {
        amount = 0;
        dotClicked = false;
        writeAmount("0");
    }

    public void backspace(View v) {
        String temp = String.valueOf(amount);

        if (dotClicked) {
            if (temp.substring(temp.indexOf('.') + 1).length() > 1) {
                temp = temp.substring(0, temp.length() - 2);
            }
            else if (temp.substring(temp.indexOf('.')).length() == 1){
                temp = temp.substring(0, temp.length() - 3);
            }
            else {
                temp = temp.substring(0, temp.indexOf('.'));
                dotClicked = false;
            }
        }

        else {
            if (!temp.startsWith("0")) {
                temp = temp.substring(0, temp.indexOf('.') - 1);
            }
        }

        writeAmount(temp);
    }
}
