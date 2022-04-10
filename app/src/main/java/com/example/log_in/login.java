package com.example.log_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    private FirebaseAuth uAuth;
    private EditText email , pass;
    private TextView register,login,forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uAuth=FirebaseAuth.getInstance();
        email=findViewById(R.id.logemail);
        pass=findViewById(R.id.logpass);
        login=findViewById(R.id.login);
        register=findViewById(R.id.TxtRegister);
        forgot=findViewById(R.id.Txtforgot);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,register.class));
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(login.this,forgot.class));
                }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        String user=email.getText().toString().trim();
        String pas=pass.getText().toString().trim();

        if(user.isEmpty()){
            email.setError("Email cannot be empty");
        }
        if(pas.isEmpty()){
            pass.setError("Password cannot be empty");
        }

        else{
            uAuth.signInWithEmailAndPassword(user,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {

                    Toast.makeText(login.this, "Login success ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(login.this, home.class));
//                    startActivity(intent);
                   }else{
                    Toast.makeText(login.this, "Login failed "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                     }
                }
            });
        }
    }
}