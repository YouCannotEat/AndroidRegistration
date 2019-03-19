package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SchoolInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);
        final Spinner spinner = findViewById(R.id.school_spin);
        List<String> list = new ArrayList<>();
        list.add("光电工程学院");
        list.add("机电工程学院");
        list.add("材料与化学工程学院");
        list.add("电子信息工程学院");
        list.add("经济管理学院");
        list.add("计算机科学与工程学院");
        list.add("建筑工程学院");
        list.add("外国语学院");
        list.add("人文学院");
        list.add("理学院");
        list.add("体育学院");
        list.add("艺术与传媒学院");
        list.add("中国书法学院");
        ArrayAdapter<String>adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Button button = findViewById(R.id.next_button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student.getStudent().school = spinner.getSelectedItem().toString();
                EditText editMajor = findViewById(R.id.major_edit);
                Student.getStudent().major = editMajor.getText().toString();
                EditText editClass = findViewById(R.id.stu_class_edit);
                Student.getStudent().stuClass = editClass.getText().toString();
                Intent intent = new Intent(SchoolInfoActivity.this,OtherInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
