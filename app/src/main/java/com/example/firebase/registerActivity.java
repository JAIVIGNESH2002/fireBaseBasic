package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registerActivity extends AppCompatActivity {
    private EditText text_email;
    private EditText text_pass;
    private FirebaseAuth fs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button registerButton = findViewById(R.id.registerButton);
        text_email = findViewById(R.id.email_field);
        text_pass = findViewById(R.id.password_field);
        fs = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = text_email.getText().toString();
                String pass = text_pass.getText().toString();
                if(TextUtils.isEmpty(email)||TextUtils.isEmpty(pass)){
                    Toast.makeText(getApplicationContext(),"Invalid credentials!",Toast.LENGTH_SHORT).show();
                }
                else if(pass.length()<6){
                    Toast.makeText(getApplicationContext(),"Password is too short!",Toast.LENGTH_SHORT).show();
                }
                else{
                    registerUser(email,pass);
                }
            }
        });


    }

    private void registerUser(String email, String pass) {
        fs.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(registerActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(registerActivity.this,"Registered successfully!",Toast.LENGTH_SHORT).show();
                    Log.i("Registration:","Successful");
                }
                else{
                    Toast.makeText(registerActivity.this,"Registered failed!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}