package com.zelyder.mathtest.ui.fragments;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.zelyder.mathtest.R;
import com.zelyder.mathtest.interfaces.KeyboardOutput;

public class Keyboard extends LinearLayout {
    float scale = getResources().getDisplayMetrics().density;
    float textSize = 9*scale;

    public Keyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.keyboard_view, this);
    }

    public void setUpKeyboard(final KeyboardOutput keyboardOutput, String[] titleBtn) {

        if(titleBtn.length >= 19){
            scale *= 0.7;
            textSize *= 0.7;
        }

        LayoutParams viewParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        int widthBtn = (int) (88 * scale);
        int heightBtn = (int) (65 * scale);

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        int countColumn = width / widthBtn;

        LayoutParams buttonParams = new LayoutParams(widthBtn, heightBtn);
        LayoutParams buttonLastLineParams = new LayoutParams(LayoutParams.MATCH_PARENT, heightBtn, 1f);
        LayoutParams lastLinearParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);

        LinearLayout linearLayout = findViewById(R.id.keyboard_view);

        LinearLayout firstRow = new LinearLayout(getContext());
        firstRow.setOrientation(HORIZONTAL);
        firstRow.setLayoutParams(viewParams);

        ImageButton delBtn = new ImageButton(getContext());
        delBtn.setBackground(this.getResources().getDrawable(R.drawable.button_test));
        delBtn.setImageResource(R.drawable.ic_delete_arrow);
        delBtn.setLayoutParams(buttonParams);
        delBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                keyboardOutput.deleteChar();
            }
        });
        firstRow.addView(delBtn);

        for(int i = 0;i < countColumn - 2;i++){
            final Button btn = new Button(getContext());
            final String title = titleBtn[i];
            btn.setText(subsSpecialSymbols(title));
            btn.setLayoutParams(buttonParams);
            btn.setTextSize(textSize);
            btn.setAllCaps(false);
            btn.setBackground(this.getResources().getDrawable(R.drawable.button_test));
            btn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                   keyboardOutput.insertChar(title);
                }
            });
            firstRow.addView(btn);
        }

        ImageButton okBtn = new ImageButton(getContext());
        okBtn.setBackground(this.getResources().getDrawable(R.drawable.button_test_ok));
        okBtn.setImageResource(R.drawable.ic_check);
        okBtn.setLayoutParams(buttonParams);
        okBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                keyboardOutput.checkFormula();
            }
        });

        firstRow.addView(okBtn);

        linearLayout.addView(firstRow);



        for (int i = 0; (titleBtn.length - (countColumn - 2)) / countColumn > i; i++) {
            LinearLayout row = new LinearLayout(getContext());
            row.setOrientation(HORIZONTAL);
            row.setLayoutParams(viewParams);
            for (int j = 0; countColumn > j; j++) {
                final Button btn = new Button(getContext());
                final String title = titleBtn[(i*countColumn)+j+(countColumn - 2)];
                btn.setText(subsSpecialSymbols(title));
                btn.setLayoutParams(buttonParams);
                btn.setTextSize(textSize);
                btn.setAllCaps(false);
                btn.setBackground(this.getResources().getDrawable(R.drawable.button_test));
                btn.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       keyboardOutput.insertChar(title);
                    }
                });
                row.addView(btn);
            }
            linearLayout.addView(row);
        }

        int residual = (titleBtn.length - (countColumn - 2)) % countColumn;
        if(residual != 0){
            LinearLayout lastRow = new LinearLayout(getContext());
            lastRow.setOrientation(HORIZONTAL);
            lastRow.setLayoutParams(lastLinearParams);
            for(int i = 0; residual > i; i++){
                final Button btn = new Button(getContext());
                final String title = titleBtn[titleBtn.length - residual + i];
                btn.setText(subsSpecialSymbols(title));
                btn.setLayoutParams(buttonLastLineParams);
                btn.setBackground(this.getResources().getDrawable(R.drawable.button_test));
                btn.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       keyboardOutput.insertChar(title);
                    }
                });
                lastRow.addView(btn);
            }
            linearLayout.addView(lastRow);
        }

    }

    private String subsSpecialSymbols(String title){
        String output = title;
        switch (title){
            case "\\\\alpha":
                output = "α";
            break;
            case "\\\\beta":
                output = "β";
                break;
            case "\\\\gamma":
                output = "γ";
                break;
            case "\\\\delta":
                output = "δ";
                break;
            case "\\\\ell":
                output = "ℓ";
                break;
            case "\\\\pi":
                output = "π";
                break;
            case "\\\\sin":
                output = "sin";
                break;
            case "\\\\cos":
                output = "cos";
                break;
            case "\\\\tg":
                output = "tg";
                break;
            case "\\\\tan":
                output = "tan";
                break;
            case "\\\\ctg":
                output = "ctg";
                break;
            case "\\\\cot":
                output = "cot";
                break;
            case "\\\\sinh":
                output = "sinh";
                break;
            case "\\\\cosh":
                output = "cosh";
                break;
            case "\\\\tanh":
                output = "tanh";
                break;
            case "\\\\coth":
                output = "coth";
                break;
            case "\\\\pm":
                output = "±";
                break;
            case "\\\\mp":
                output = "∓";
                break;
            case "\\\\phi":
                output = "φ";
                break;
            case "\\\\infty":
                output = "∞";
                break;
        }
        return output;
    }

}
