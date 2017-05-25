package com.justappp.nekitpc.writereaddeletefile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.CharBuffer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String FILE_NAME = "TestFileName";

    private EditText edtWrite;
    private TextView txtRead;
    private Button btnWrite, btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtWrite = (EditText) findViewById(R.id.edtWrite);
        txtRead = (TextView) findViewById(R.id.txtRead);

        btnWrite = (Button) findViewById(R.id.btnWrite);
        btnRead = (Button) findViewById(R.id.btnRead);

        btnWrite.setOnClickListener(this);
        btnRead.setOnClickListener(this);
    }

    private void writeData(String fileName, String data) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(openFileOutput(fileName, MODE_PRIVATE)));
            writer.write(data);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String readData(String fileName){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(openFileInput(fileName)));

            String line;

            while ((line = reader.readLine()) != null){
                return line;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnWrite:
                writeData(FILE_NAME, edtWrite.getText().toString());
                Toast.makeText(MainActivity.this, R.string.fileWritten, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnRead:
                txtRead.setText(readData(FILE_NAME));
                Toast.makeText(MainActivity.this, R.string.fileReadOut, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
