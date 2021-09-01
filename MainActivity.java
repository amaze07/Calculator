package com.vm.newcalc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private EditText result ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        result.setShowSoftInputOnFocus(false);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getString(R.string.display).equals(result.getText().toString())){
                    result.setText("");
                }
            }
        });
    }

    private void updateText(String strToAdd){
        String oldStr = result.getText().toString();
        int cursorPosition = result.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPosition);
        String rightStr = oldStr.substring(cursorPosition);
        if (getString(R.string.display).equals(result.getText().toString())){
            result.setText(strToAdd);
            result.setSelection(cursorPosition+1);
        }
        else{
            result.setText(String.format("%s%s%s",leftStr,strToAdd,rightStr));
            result.setSelection(cursorPosition+1);
        }

    }

    public void zeroBTN(View v){
        updateText("0");
    }

    public void oneBTN(View v){
        updateText("1");
    }

    public void twoBTN(View v){
        updateText("2");
    }

    public void threeBTN(View v){
        updateText("3");
    }

    public void fourBTN(View v){
        updateText("4");
    }

    public void fiveBTN(View v){
        updateText("5");
    }

    public void sixBTN(View v){
        updateText("6");
    }

    public void sevenBTN(View v){
        updateText("7");
    }

    public void eightBTN(View v){
        updateText("8");
    }

    public void nineBTN(View v){
        updateText("9");
    }

    public void addBTN(View v){
        updateText("+");
    }

    public void subBTN(View v){
        updateText("-");
    }

    public void mulBTN(View v){
        updateText("×");
    }

    public void divBTN(View v){
        updateText("÷");
    }

    public void pmBTN(View v){
        updateText("-");
    }

    public void dotBTN(View v){
        updateText(".");
    }

    public void bracketsBTN(View v){
        int cursorPos = result.getSelectionStart();
        int openBra = 0;
        int closeBra = 0;
        int textLen = result.getText().length();

        for(int i = 0; i < cursorPos; i++){
            if(result.getText().toString().substring(i , i+1).equals("(")){
                openBra += 1;
            }
            if(result.getText().toString().substring(i , i+1).equals(")")){
                closeBra += 1;
            }
        }

        if(openBra == closeBra || result.getText().toString().substring(textLen - 1, textLen).equals('(')){
            updateText("(");
            result.setSelection(cursorPos + 1);
        }
        else if(closeBra < openBra && !result.getText().toString().substring(textLen - 1, textLen).equals('(')){
            updateText(")");
        }
        result.setSelection(cursorPos + 1);
    }

    public void eqlBTN(View v){
        String equation = result.getText().toString();

        equation = equation.replaceAll("÷","/");
        equation = equation.replaceAll("×", "*");

        Expression exp  = new Expression(equation);

        String display = String.valueOf(exp.calculate());
        result.setText(display);
        result.setSelection(display.length());
    }

    public void clearBTN(View v){
        result.setText(" ");
    }

    public void backspaceBTN(View v){
        int cursorPos = result.getSelectionStart();
        int textLen = result.getText().length();
        if(cursorPos !=0 && textLen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) result.getText();
            selection.replace(cursorPos -1 , cursorPos, "");
            result.setText(selection);
            result.setSelection(cursorPos - 1);
        }
    }

}