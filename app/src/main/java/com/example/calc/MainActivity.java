package com.example.calc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.parser.ParseException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar t;

    // сдивг экрана

    // смена после минуса
    private EditText eTInput;
    private EditText eTResult;

    private String historyStr;
    private static final String HISTORY_KEY = "MainActivity.historyKey";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        t = findViewById(R.id.t);
        setSupportActionBar(t);
        eTInput = findViewById(R.id.inputField);
        eTResult = findViewById(R.id.resultField);
        eTInput.setClickable(false);
        eTResult.setClickable(false);
        eTResult.setFocusable(false);
        eTInput.setFocusable(false);
        if(savedInstanceState!=null) {
           historyStr = savedInstanceState.getString(HISTORY_KEY);
        }else {
            historyStr = "";
        }
    }
    public void clickNumber(View view){
        String number = eTInput.getText().toString();
        if (view.getId() == R.id.one) {
            number = number + "1";
        }
        if (view.getId() == R.id.two) {
            number = number + "2";
        }
        if (view.getId() == R.id.three) {
            number = number + "3";
        }
        if (view.getId() == R.id.four) {
            number = number + "4";
        }
        if (view.getId() == R.id.five) {
            number = number + "5";
        }
        if (view.getId() == R.id.six) {
            number = number + "6";
        }
        if (view.getId() == R.id.seven) {
            number = number + "7";
        }
        if (view.getId() == R.id.eight) {
            number = number + "8";
        }
        if (view.getId() == R.id.nine) {
            number = number + "9";
        }
        if (view.getId() == R.id.zero) {
            number = number + "0";
        }
        if (view.getId() == R.id.add) {
            if(!number.equals("")){
                if(number.charAt(number.length()-1)!='*' &&
                        number.charAt(number.length()-1)!='+' &&
                        number.charAt(number.length()-1)!='-' &&
                        number.charAt(number.length()-1)!= '/'){
                    number = number + "+";
                }else{

                    number=number.substring(0,number.length()-1)+number.substring(number.length()-1).replace(number.charAt(number.length()-1),'+');
                }
            }
        }
        if (view.getId() == R.id.subtract) {
            if(!number.equals("")){
                if(number.charAt(number.length()-1)!='*' &&
                        number.charAt(number.length()-1)!='+' &&
                        number.charAt(number.length()-1)!='-' &&
                        number.charAt(number.length()-1)!= '/'){
                    number = number + "-";
                }else{
                    number=number.substring(0,number.length()-1)+number.substring(number.length()-1).replace(number.charAt(number.length()-1),'-');
                }
            }else{
                number = number + "-";
            }
        }
        if (view.getId() == R.id.division) {
            if(!number.equals("")){
                if(number.charAt(number.length()-1)!='*' &&
                        number.charAt(number.length()-1)!='+' &&
                        number.charAt(number.length()-1)!='-' &&
                        number.charAt(number.length()-1)!= '/'){
                    number = number + "/";
                }else{
                    number=number.substring(0,number.length()-1)+number.substring(number.length()-1)
                            .replace(number.charAt(number.length()-1),'/');
                }
            }
        }
        if (view.getId() == R.id.multiplication) {
            if(!number.equals("") ){
                if(number.charAt(number.length()-1)!='*' &&
                        number.charAt(number.length()-1)!='+' &&
                        number.charAt(number.length()-1)!='-' &&
                        number.charAt(number.length()-1)!= '/'){
                    number = number + "*";
                }else{
                    number=number.substring(0,number.length()-1)+number.substring(number.length()-1)
                            .replace(number.charAt(number.length()-1),'*');
//                    number=number.replace(number.charAt(number.length()-1),'*');
                }
            }

        }
        if (view.getId() == R.id.point) {
            if(!number.equals("")){
                if(number.charAt(number.length()-1)!='.'){
                    number = number + ".";
                }
            }
        }
        if(view.getId() == R.id.bracketLeft){
            number = number + "(";
        }
        if(view.getId() == R.id.bracketRight){
            number = number + ")";
        }
        if(view.getId() == R.id.degree){
             number = number + "^";
        }
        if(view.getId() == R.id.radical){
              number = number + "√(";
        }
        if(view.getId() == R.id.sin){
            number = number + "sin(";
        }
        if(view.getId() == R.id.cos){
            number = number + "cos(";
        }
        if(view.getId() == R.id.fact){
            // проверка на отрицат
            number = number + "fact(";
        }
        if(view.getId() == R.id.ln){
            number = number + "ln(";
        }
        if(view.getId() == R.id.lg){
            number = number + "lg(";
        }
        if (view.getId() == R.id.percent) {
            if(!number.equals("")){
                // игнор после знаков

                number = number+"%";
//                Expression expression = new Expression(number);
//                EvaluationValue result = null;
//                try {
//                    result = expression.evaluate();
//                    number = String.valueOf(result.getNumberValue().divide(BigDecimal.valueOf(100)));
//                    eTResult.setText("="+number);
//                }  catch (EvaluationException e) {
//                    eTResult.setText("=Делить на 0 нельзя!");
//
//                }catch (RuntimeException | ParseException e){
//                    eTResult.setText("=Неверный ввод!");
//                }
            }
        }
        if (view.getId() == R.id.clearAll) {
            number="";
            eTResult.setText("=");
        }
        if (view.getId() == R.id.clearOne) {
            if(!number.equals("")){
                number = number.substring(0,number.length()-1);
            }
            eTResult.setText("");
        }
        if (view.getId() == R.id.result) {

            String resNumber;
            resNumber = number.replace("sin","SIN")
                    .replace("√","SQRT")
                    .replace("cos","COS")
                    .replace("ln","LOG")
                    .replace("lg","LOG10")
                    .replace("fact","FACT")
                    .replace("%","/100");
            resNumber = "ROUND("+resNumber+", 4)";
            Expression expression = new Expression(resNumber);
            EvaluationValue result = null;
            try {
                result = expression.evaluate();
                eTResult.setText("="+result.getNumberValue().toString());
                if(historyStr!=null){
                    historyStr+=number+"="+result.getNumberValue().toString()+",\n";
                }
            } catch (EvaluationException e) {
                // lg ln
                eTResult.setText("=Делить на 0 нельзя!");

            }catch (RuntimeException | ParseException e){
                eTResult.setText("=Неверный ввод!");
            }
        }
        eTInput.setText(number);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("history",historyStr);
        startActivity(intent);
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(HISTORY_KEY,historyStr);
        super.onSaveInstanceState(outState);

    }
}
