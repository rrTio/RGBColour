package com.example.rgbcolour;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;

public class MainActivity extends AppCompatActivity
{

    SeekBar sbRed, sbGreen, sbBlue;
    TextView setColor, setHex, lblRed, lblGreen, lblBlue;

    int cRed, cGreen, cBlue;
    String hex;

    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbRed = findViewById(R.id.sbRed);
        sbGreen = findViewById(R.id.sbGreen);
        sbBlue = findViewById(R.id.sbBlue);

        setHex = findViewById(R.id.setHex);
        setColor = findViewById(R.id.setColor);

        btnClear = findViewById(R.id.btnClear);

        lblRed = findViewById(R.id.txtRED);
        lblGreen = findViewById(R.id.txtGREEN);
        lblBlue = findViewById(R.id.txtBLUE);


        sbRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                Log.e("values", "RED: " + progress);
                cRed = progress;
                lblRed.setText("RED: " + progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {seeColor(cRed, cGreen, cBlue);}
        });

        sbGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                Log.e("values", "GREEN: " + progress);
                cGreen = progress;
                lblGreen.setText("GREEN: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {seeColor(cRed, cGreen, cBlue);}
        });

        sbBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                Log.e("values", "BLUE: " + progress);
                cBlue = progress;
                lblBlue.setText("BLUE: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {seeColor(cRed, cGreen, cBlue);}
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
    }

    public void clear()
    {
        sbRed.setProgress(0);
        sbGreen.setProgress(0);
        sbBlue.setProgress(0);
        setHex.setText("#000000");
        setColor.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
        setColor.getBackground().setColorFilter(Color.parseColor("#808080"), PorterDuff.Mode.OVERLAY);
    }

    public void seeColor(int cRed, int cGreen, int cBlue)
    {
        hex = String.format("#%02X%02X%02X", cRed, cGreen, cBlue);
        Log.e("status", "VALUE: " + hex);

        TextView setHex = findViewById(R.id.setHex);
        TextView setColor = findViewById(R.id.setColor);
        setColor.getBackground().setColorFilter(Color.parseColor(hex), PorterDuff.Mode.SRC_ATOP);

        setHex.setText(hex);
    }
}