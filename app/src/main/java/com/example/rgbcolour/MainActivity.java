package com.example.rgbcolour;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.SwitchCompat;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    SeekBar sbRed, sbGreen, sbBlue;
    TextView setColor, lblRed, lblGreen, lblBlue;
    EditText setHex;
    Button btnEnter, btnClear;
    SwitchCompat hex_rgb;

    int cRed, cGreen, cBlue, Red, Green, Blue;
    String hex, hex_input;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbRed = findViewById(R.id.sbRed);
        sbGreen = findViewById(R.id.sbGreen);
        sbBlue = findViewById(R.id.sbBlue);

        setHex = findViewById(R.id.setHex);
        setColor = findViewById(R.id.setColor);

        btnEnter = findViewById(R.id.btnEnter);
        btnClear = findViewById(R.id.btnClear);

        lblRed = findViewById(R.id.txtRED);
        lblGreen = findViewById(R.id.txtGREEN);
        lblBlue = findViewById(R.id.txtBLUE);

        hex_rgb = findViewById(R.id.sw_color);

        sbRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.e("values", "RED: " + progress);
                cRed = progress;
                lblRed.setText("RED: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seeColor(cRed, cGreen, cBlue);
            }
        });

        sbGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.e("values", "GREEN: " + progress);
                cGreen = progress;
                lblGreen.setText("GREEN: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seeColor(cRed, cGreen, cBlue);
            }
        });

        sbBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.e("values", "BLUE: " + progress);
                cBlue = progress;
                lblBlue.setText("BLUE: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seeColor(cRed, cGreen, cBlue);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                clear();
            }
        });

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hex_input = setHex.getText().toString();

                try {
                    getRGB(hex_input);

                    sbRed.setProgress(Red);
                    sbGreen.setProgress(Green);
                    sbBlue.setProgress(Blue);

                    seeColor(Red, Green, Blue);
                    Toast.makeText(MainActivity.this, "RGB: " + Red + ", " + Green + ", " + Blue, Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(MainActivity.this, "INVALID HEX CODE", Toast.LENGTH_SHORT).show();
                    setHex.getText().clear();
                    setHex.setHint("HEX CODE");
                }
                Log.e("values", "RGB: " + Red + ", " + Green + ", " + Blue);
            }
        });

        hex_rgb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sbRed.setEnabled(false);
                    sbGreen.setEnabled(false);
                    sbBlue.setEnabled(false);
                    setHex.setEnabled(true);
                    setHex.getText().clear();
                    setHex.setHint("HEX CODE");
                    Toast.makeText(MainActivity.this, "DRAG FOR COLOURS", Toast.LENGTH_SHORT).show();

                } else {
                    sbRed.setEnabled(true);
                    sbGreen.setEnabled(true);
                    sbBlue.setEnabled(true);
                    setHex.setEnabled(false);
                    setHex.setText("#000000");
                    Toast.makeText(MainActivity.this, "TYPE HEX CODE FOR COLOURS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void clear() {
        if(hex_rgb.isChecked())
        {
            sbRed.setProgress(0);
            sbGreen.setProgress(0);
            sbBlue.setProgress(0);
            setHex.setHint("HEX CODE");
            setColor.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
            setColor.getBackground().setColorFilter(Color.parseColor("#808080"), PorterDuff.Mode.OVERLAY);
        }

        else
        {
            sbRed.setProgress(0);
            sbGreen.setProgress(0);
            sbBlue.setProgress(0);
            setHex.setText("#000000");
            setColor.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
            setColor.getBackground().setColorFilter(Color.parseColor("#808080"), PorterDuff.Mode.OVERLAY);
        }
    }

    public void seeColor(int cRed, int cGreen, int cBlue) {
        hex = String.format("#%02X%02X%02X", cRed, cGreen, cBlue);
        Log.e("status", "VALUE: " + hex);

        EditText setHex = findViewById(R.id.setHex);
        TextView setColor = findViewById(R.id.setColor);
        setColor.getBackground().setColorFilter(Color.parseColor(hex), PorterDuff.Mode.SRC_ATOP);

        setHex.setText(hex);
    }

    public void getRGB(String hex_input)
    {
        String upper_case_input = hex_input.toUpperCase();

            Red = Integer.parseInt(upper_case_input.substring(1, 3), 16);
            Green = Integer.parseInt(upper_case_input.substring(3, 5), 16);
            Blue = Integer.parseInt(upper_case_input.substring(5, 7), 16);
    }
}
