package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
    private Button loginButton;
    private EditText loginEmail;
    private EditText loginPass;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        loginButton = findViewById(R.id.loginButton);
        loginEmail = findViewById(R.id.loginEmail_field);
        loginPass = findViewById(R.id.loginPassword_field);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lMail = loginEmail.getText().toString();
                String lPass = loginPass.getText().toString();
                loginUser(lMail,lPass);

            }
        });
    }
    private void loginUser(String lMail, String lPass) {
        auth.signInWithEmailAndPassword(lMail,lPass).addOnSuccessListener(loginActivity.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(loginActivity.this,"Logged in",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(loginActivity.this,MainActivity.class));
                finish();
            }
        }).addOnFailureListener(loginActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(loginActivity.this,"Login failed!",Toast.LENGTH_LONG).show();
            }
        });
    }
}