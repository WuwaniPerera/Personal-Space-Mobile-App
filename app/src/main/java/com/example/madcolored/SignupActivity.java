package com.example.madcolored;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupActivity extends AppCompatActivity {

    EditText edtNameSignup,edtEmailSignup, edtPhone, edtPasswordSignup;
    Button BtnSignup;
    CheckBox checkBoxAgree;
    TextView txtLogin;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        txtLogin = findViewById(R.id.txtLogin);
        edtEmailSignup = findViewById(R.id.edtEmailSignup);
        edtNameSignup = findViewById(R.id.edtNameSignup);
        edtPhone = findViewById(R.id.edtPhone);
        edtPasswordSignup = findViewById(R.id.edtPasswordSignup);
        BtnSignup = findViewById(R.id.BtnSignup);
        checkBoxAgree = findViewById(R.id.checkBoxAgree);

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        //////

        BtnSignup.setOnClickListener(view -> {
           createUser();
        });
    }

    private void createUser() {
        String email = edtEmailSignup.getText().toString();
        String name = edtNameSignup.getText().toString();
        String phone = edtPhone.getText().toString();
        String password = edtPasswordSignup.getText().toString();

        if (TextUtils.isEmpty(email)) {
            edtEmailSignup.setError("Email cannot be empty");
            edtEmailSignup.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            edtPasswordSignup.setError("Password cannot be empty");
            edtEmailSignup.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignupActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                    } else {
                        Toast.makeText(SignupActivity.this, "Registration error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}