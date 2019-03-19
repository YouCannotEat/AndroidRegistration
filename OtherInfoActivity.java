package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class OtherInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        Button button = findViewById(R.id.commit_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editCell = findViewById(R.id.phone_edit);
                Student.getStudent().Cell = editCell.getText().toString();
                EditText editEmail = findViewById(R.id.email_edit);
                Student.getStudent().email = editEmail.getText().toString();
                EditText editWeiXin = findViewById(R.id.weixin_edit);
                Student.getStudent().weixin = editWeiXin.getText().toString();
                int id[] = {R.id.music_check,R.id.arts_check,R.id.handwriting_check,R.id.basketball_check,R.id.football_check,R.id.swimming_check};
                for(int i:id){
                    CheckBox chk = (CheckBox)findViewById(i);
                    if(chk.isChecked()){
                        Student.getStudent().skill.add(chk.getText().toString());
                    }
                }
//                String UNIQUE_STRING ="com.example.myapplication";
                String UNIQUE_STRING ="com.hrbeu.abc";
                Intent intent = new Intent(UNIQUE_STRING);
                Student.getStudent().info = print();
                intent.putExtra("info",print());
//                for(int i=0;i<Student.skill.size();i++){
//                    intent.putExtra("skill"+i,Student.skill.get(i));
//                }
                sendBroadcast(intent);
            }
        });

    }
    private String print(){
        StringBuilder skill = new StringBuilder();
        for(int i=0;i<Student.getStudent().skill.size();i++){
            skill.append(Student.getStudent().skill.get(i)).append('、');
        }
        return "姓名:"+Student.getStudent().name+'\n'+"学号："+Student.getStudent().stu_id+'\n'
                +"身份证号："+Student.getStudent().id+'\n'+"性别："+Student.getStudent().sex+'\n'
                +"籍贯："+Student.getStudent().nativePlace+'\n'
                +"生日："+Student.getStudent().year+"年"+Student.getStudent().month+"月"+Student.getStudent().day+"日"+'\n'
                +"所在学院："+Student.getStudent().school+'\n'
                +"专业："+Student.getStudent().major+'\n'
                +"班级："+Student.getStudent().stuClass+'\n'
                +"电话："+Student.getStudent().Cell+'\n'
                +"电子邮件："+Student.getStudent().email+'\n'
                +"微信："+Student.getStudent().weixin+'\n'
                +"特长:"+skill+'\n';


    }
}
