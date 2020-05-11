package com.example.authenticatorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText memail ,mpassword;
    Button mbutton2;
    TextView mtextView7;
    ProgressBar progressBar;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        memail=findViewById(R.id.email);
        mpassword=findViewById(R.id.password);
        mbutton2=findViewById(R.id.button2);
        mtextView7=findViewById(R.id.textView7);
        progressBar=findViewById(R.id.progressBar);
        fAuth= FirebaseAuth.getInstance();


        mbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email= memail.getText().toString().trim();
                String password =mpassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    memail.setError("Email is required!");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mpassword.setError("Password is required!");
                    return;
                }

                if(password.length() <6 ) {
                    mpassword.setError("Password must be at least 6 characters");
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);

             //authenticate the user

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                       if(task.isSuccessful()){
                           Toast.makeText(Login.this, "Logged in!", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(getApplicationContext(),MainActivity.class));
                       }
                       else{
                           Toast.makeText(Login.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                       }


                    }
                });

            }
        });



    mtextView7.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),Register.class));
            progressBar.setVisibility(View.INVISIBLE);
        }
    });




    }
}
