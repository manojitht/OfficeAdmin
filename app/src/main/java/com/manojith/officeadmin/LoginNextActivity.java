package com.manojith.officeadmin;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginNextActivity extends AppCompatActivity {

    private EditText edtLoginEmail, edtLoginPassword;
    private Button btnLoginActivity, btnSignUpActivity;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_next);

        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        btnLoginActivity = findViewById(R.id.btnLoginActivity);
        btnSignUpActivity = findViewById(R.id.btnSignUpActivity);
        mAuth = FirebaseAuth.getInstance();


        btnLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


        btnSignUpActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginNextActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }

    private void signIn(){
        mAuth.signInWithEmailAndPassword(edtLoginEmail.getText().toString(), edtLoginPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Toast.makeText(LoginNextActivity.this,"login is successful !", Toast.LENGTH_LONG).show();

                    edtLoginEmail.setText("");
                    edtLoginPassword.setText("");

                    transitionToOfficeActivity();

                }else {
                    Toast.makeText(LoginNextActivity.this, "Invalid email & password !", Toast.LENGTH_LONG).show();

                    edtLoginEmail.setText("");
                    edtLoginPassword.setText("");

                }
            }
        });
    }

    private void transitionToOfficeActivity(){
        Intent intent = new Intent(this, OfficeActivity.class);
        startActivity(intent);
    }
}
