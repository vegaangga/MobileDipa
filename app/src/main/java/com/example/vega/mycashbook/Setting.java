package com.example.vega.mycashbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


public class Setting extends AppCompatActivity {
    final int sdk = android.os.Build.VERSION.SDK_INT;
    private EditText oldPwdEdit;
    private EditText newPwdEdit;

    private Button submitBtn;

    private String oldPwd;
    private String newPwd;
    private String password_now;
    String id;

    SessionManagement sessionManagement;
    DatabaseHelper dbcenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        sessionManagement = new SessionManagement(Setting.this);
        final HashMap<String, String> user = sessionManagement.getUserInformation();
        dbcenter = new DatabaseHelper(this); //membuat objek dari class datahelper

        oldPwdEdit = (EditText)findViewById(R.id.oldEdt);
        newPwdEdit = (EditText)findViewById(R.id.newEdt);

        submitBtn = (Button)findViewById(R.id.cnfrmPwdBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                oldPwd = oldPwdEdit.getText().toString().trim();
                newPwd = newPwdEdit.getText().toString().trim();
                password_now = user.get(sessionManagement.KEY_PASSWOrD);


                if(oldPwd==null||"".equalsIgnoreCase(oldPwd)){
                    String header = "OLD PASSWORD REQUIRE";
                    Toast.makeText(getApplicationContext(),header,300).show();
                }
                else if(newPwd==null ||"".equalsIgnoreCase(newPwd)){
                    String header = "NEW PASSWORD IS REQUIRE";
                    Toast.makeText(getApplicationContext(), header,300).show();
                }
                else if(oldPwd.matches(newPwd)){
                    String header = "NEW PASSWORD CANNOT BE SAME AS OLD PASSWORD";
                    Toast.makeText(getApplicationContext(), header,300).show();
                }
                else if(!oldPwd.matches(password_now)){
                    String header = "OLD PASSWORD DOES NOT MATCH";
                    Toast.makeText(getApplicationContext(), header,300).show();
                }
                else{
                    dbcenter = new DatabaseHelper(getApplicationContext());
                    dbcenter.updatePassword(Integer.parseInt(user.get(sessionManagement.KEY_ID_USER)), newPwd);
                    String header = "PASSWORD HAS BEEN CHANGED";
                    Toast.makeText(getApplicationContext(), header,100).show();
                    Intent intent = new Intent(Setting.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}
