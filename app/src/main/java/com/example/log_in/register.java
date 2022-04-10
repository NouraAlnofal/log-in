package com.example.log_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {

    private FirebaseAuth uAuth;
    private EditText email , pass;
//    private Button register;
    private TextView signup,login,c1,c2,c3,c4;
    private ImageView e1,e2,e3,e4;
    private boolean is8=false, hasupper=false,haslower=false,hasnum=false;
    public String user, pas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        uAuth= FirebaseAuth.getInstance();
        email=findViewById(R.id.regemail);
        pass=findViewById(R.id.regpass);
//        register=findViewById(R.id.register);
        signup=findViewById(R.id.signup);
        login=findViewById(R.id.Txtlogin);

//        c1=(CardView)findViewById(R.id.card1);
//        c2=(CardView)findViewById(R.id.card2);
//        c3=(CardView)findViewById(R.id.card3);
//        c4=(CardView)findViewById(R.id.card4);
        c1=(TextView)findViewById(R.id.con1);
        c2=(TextView)findViewById(R.id.con2);
        c3=(TextView)findViewById(R.id.con3);
        c4=(TextView)findViewById(R.id.con4);

        e1=(ImageView) findViewById(R.id.e1);
        e2=(ImageView) findViewById(R.id.e2);
        e3=(ImageView) findViewById(R.id.e3);
        e4=(ImageView) findViewById(R.id.e4);

        e1.setVisibility(View.INVISIBLE);
        e2.setVisibility(View.INVISIBLE);
        e3.setVisibility(View.INVISIBLE);
        e4.setVisibility(View.INVISIBLE);


        inputChanged();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(register.this,login.class));
            }
        });
    }


    private void inputChanged(){
        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passValidation();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @SuppressLint("ResourceType")
    private void passValidation() {
         user=email.getText().toString().trim();
         pas=pass.getText().toString().trim();
        //check length
        if(pas.length()>=8){
            is8=true;
            c1.setTextColor(Color.parseColor(getString(R.color.purple)));
            e1.setVisibility(View.VISIBLE);
        }else{
            is8=false;
            c1.setTextColor(Color.parseColor(getString(R.color.black)));
            e1.setVisibility(View.INVISIBLE);
        }
        //check num
        if(pas.matches(".*[0-9].*")){
           hasnum=true;
            c2.setTextColor(Color.parseColor(getString(R.color.purple)));
            e2.setVisibility(View.VISIBLE);
    }else{
            hasnum=false;
            c2.setTextColor(Color.parseColor(getString(R.color.black)));
            e2.setVisibility(View.INVISIBLE);
        }

        //check upper
        if(pas.matches(".*[A-Z].*")){
            hasupper=true;
            c3.setTextColor(Color.parseColor(getString(R.color.purple)));
            e3.setVisibility(View.VISIBLE);
        }else{
            hasupper=false;
            c3.setTextColor(Color.parseColor(getString(R.color.black)));
            e3.setVisibility(View.INVISIBLE);

        }

        //check lowe
        if(pas.matches(".*[a-z].*")){
            haslower=true;
            c4.setTextColor(Color.parseColor(getString(R.color.purple)));
            e4.setVisibility(View.VISIBLE);
        }else {
            haslower = false;
            c4.setTextColor(Color.parseColor(getString(R.color.black)));
            e4.setVisibility(View.INVISIBLE);

        }

    }

    private void register() {
        user=email.getText().toString().trim();
        pas=pass.getText().toString().trim();
        if (user.isEmpty()) {
            email.setError("Email cannot be empty");
        }
        if (pas.isEmpty()) {
            pass.setError("Password cannot be empty");
        }

        if (is8 && hasupper && haslower && hasnum) {
        uAuth.createUserWithEmailAndPassword(user, pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(register.this, "register success ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(register.this, MainActivity.class));
                } else {
                    Toast.makeText(register.this, "register failed " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
        }else{
            pass.setError("Password not formatted correctly");
        }
     }

    }
