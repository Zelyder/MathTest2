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
    final float scale = getResources().getDisplayMetrics().density;
    final float textSize = 9*scale;

    public Keyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.keyboard_view, this);
    }

    public void setUpKeyboard(final KeyboardOutput keyboardOutput, String[] titleBtn) {
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
            btn.setText(titleBtn[i]);
            btn.setLayoutParams(buttonParams);
            btn.setTextSize(textSize);
            btn.setAllCaps(false);
            btn.setBackground(this.getResources().getDrawable(R.drawable.button_test));
            btn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                   keyboardOutput.insertChar(btn.getText().toString());
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
                btn.setText(titleBtn[(i*countColumn)+j+(countColumn - 2)]);
                btn.setLayoutParams(buttonParams);
                btn.setTextSize(textSize);
                btn.setAllCaps(false);
                btn.setBackground(this.getResources().getDrawable(R.drawable.button_test));
                btn.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       keyboardOutput.insertChar(btn.getText().toString());
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
                btn.setText(titleBtn[titleBtn.length - residual + i]);
                btn.setLayoutParams(buttonLastLineParams);
                btn.setBackground(this.getResources().getDrawable(R.drawable.button_test));
                btn.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       keyboardOutput.insertChar(btn.getText().toString());
                    }
                });
                lastRow.addView(btn);
            }
            linearLayout.addView(lastRow);
        }

    }
}
