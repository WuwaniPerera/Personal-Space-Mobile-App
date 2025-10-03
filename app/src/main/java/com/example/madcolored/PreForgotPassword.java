package com.example.madcolored;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PreForgotPassword extends AppCompatActivity {

    private EditText edtResetPass;
    private Button btnReset;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_forgot_password);

        edtResetPass = findViewById(R.id.edtResetPass);
        btnReset = findViewById(R.id.btnReset);

        mAuth = FirebaseAuth.getInstance();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = edtResetPass.getText().toString().trim();
                if(mail.isEmpty()){
                    Toast.makeText(PreForgotPassword.this, "Enter your email!", Toast.LENGTH_SHORT).show();
                } else {
                    //send mail
                    
                    mAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(PreForgotPassword.this, "Email sent to recover your password.", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(PreForgotPassword.this,LoginActivity.class));
                            } else {
                                Toast.makeText(PreForgotPassword.this, "Email is wrong or account does not exists!.", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });



    }
}