package com.example.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class BasicInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        mip = txt();
        final EditText editTextId = findViewById(R.id.id_number_edit);
        editTextId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String idNumber = editTextId.getText().toString();
                if (s.length()==18 && idNumber.equals("")==false) {
                    if (isIDRight(idNumber.toString()) == false) {
                        Toast.makeText(getApplication(), "身份证输入错误，请重新输入！", Toast.LENGTH_SHORT).show();
                    }
                    Student.getStudent().id = idNumber;
                    Integer idPalace = Integer.parseInt(idNumber.substring(0, 6));
                    EditText editTextPalace = findViewById(R.id.native_place_edit);
                    editTextPalace.setText(mip.get(idPalace));
                    EditText editYear = findViewById(R.id.birthday_year_edit);
                    EditText editMonth = findViewById(R.id.birthday_month_edit);
                    EditText editDay = findViewById(R.id.birthday_day_edit);
                    editYear.setText(idNumber.substring(6,10));
                    editMonth.setText(idNumber.substring(10,12));
                    editDay.setText(idNumber.substring(12,14));
                }
            }
            private boolean isIDRight(String id) {
                String[] Jiaoyan = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                String[] Wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};
                int sum = 0;
                String Ai = id.substring(0,17);

                for (int i = 0; i < 17; i++) {
                    sum = sum + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
                }
                int modValue = sum % 11;
                String str = Jiaoyan[modValue];
                Ai = Ai + str;

                if (Ai.equals(id) == false) {

                    return false;
                }
                return true;
            }
        });
        Button button = findViewById(R.id.next_button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup sex = (RadioGroup) findViewById(R.id.sex);
                int id = sex.getCheckedRadioButtonId();
                RadioButton sexM = (RadioButton)findViewById(id);
                Student.getStudent().sex = sexM.getText().toString();
                final EditText editTextStuId = findViewById(R.id.stu_id_edit);
                Student.getStudent().stu_id =editTextStuId.getText().toString();
                EditText editName =findViewById(R.id.name_edit);
                Student.getStudent().name = editName.getText().toString();
                Student.getStudent().id = editTextId.getText().toString();
                EditText editnative = findViewById(R.id.native_place_edit);
                Student.getStudent().nativePlace=editnative.getText().toString();
                EditText editYear = findViewById(R.id.birthday_year_edit);
                Student.getStudent().year =Integer.parseInt(editYear.getText().toString());
                EditText editmonth = findViewById(R.id.birthday_month_edit);
                Student.getStudent().month =Integer.parseInt(editYear.getText().toString());
                EditText editDay = findViewById(R.id.birthday_day_edit);
                Student.getStudent().day =Integer.parseInt(editYear.getText().toString());
                Intent intent = new Intent(BasicInfoActivity.this,SchoolInfoActivity.class);
                startActivity(intent);
            }
        });
    }
    private static Map<Integer,String> mip = new HashMap<>();
    private Map<Integer,String> txt(){
        InputStream inputStream = null;
        inputStream = this.getResources().openRawResource(R.raw.raw_file);
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String line="";
        try {
            while (line.equals("null")==false){
                line = reader.readLine();
                if(line.equals("null")!=true) {
                    String str = line.substring(0,6);
                    Integer i = Integer.parseInt(str);
                    mip.put(i, line.substring(7));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mip;
    }
}
