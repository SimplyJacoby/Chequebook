package com.example.chequebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewRecordFormActivity extends AppCompatActivity {

    public double amount = 0;
    public int integer = 0;
    public int decimal = 0;
    public boolean dotClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record_form);
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

    public void editInteger(View v) {
        Button b = (Button)v;

        String bVal = String.valueOf(b.getText());
        String temp = String.valueOf(integer);

        if (temp.length() <= 7) {
            temp += bVal;
            integer = Integer.valueOf(temp);
            writeAmount(v);
        }
    }

    public void editDecimal(View v) {
        Button b = (Button)v;

        String bVal = String.valueOf(b.getText());
        String temp = String.valueOf(decimal);

        if (temp.length() < 1) {
            temp += bVal;
            decimal = Integer.valueOf(temp);
            writeAmount(v);
        }
    }

    public void writeAmount(View v) {
        TextView tvAmount = (TextView) findViewById(R.id.tvAmount);
        String temp = String.valueOf(decimal);

        String display = "";

        if (temp.length() > 1) {
            display = integer + "." + decimal;
        }
        else {
            display = integer + "." + decimal + "0";
        }

        tvAmount.setText(display);
    }

    public void setIncomeOrExpense(View v) {
        Button b = (Button)v;
        TextView tvPlusOrMinus = (TextView)findViewById(R.id.tvPlusOrMinus);

        if (b.getId() == R.id.btnIncome && amount < 0) {
            amount = amount * -1;
        }
        else if (b.getId() == R.id.btnExpense && amount > 0) {
            amount = amount * -1;
        }

        if (b.getId() == R.id.btnIncome) {
            tvPlusOrMinus.setText("+");
        }
        else if (b.getId() == R.id.btnExpense) {
            tvPlusOrMinus.setText("-");
        }
    }

    public void backspace(View v) {
        if (dotClicked) {
            String temp = String.valueOf(decimal);
            TextView tvAmount = (TextView) findViewById(R.id.tvAmount);
            String display = "";

            if (temp.length() > 1) {
                temp = temp.substring(0, 1);
                decimal = Integer.valueOf(temp);
                display = integer + "." + decimal;
            }
            else if (temp.length() == 1) {
                temp = "0";
                decimal = Integer.valueOf(temp);
                display = integer + ".";
            }
            else {
                temp = "0";
                decimal = Integer.valueOf(temp);
                display = integer + ".00";
                dotClicked = false;
            }

            tvAmount.setText(display);
        }

        if (!dotClicked) {
            String temp = String.valueOf(integer);

            if (temp.length() > 1) {
                temp = temp.substring(0, temp.length() - 1);
            }
            else {
                temp = "0";
            }

            integer = Integer.valueOf(temp);
            writeAmount(v);
        }
    }

    public void clear(View v) {
        integer = 0;
        decimal = 0;
        dotClicked = false;
        writeAmount(v);
    }
}
