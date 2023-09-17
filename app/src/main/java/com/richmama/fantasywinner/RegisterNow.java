package com.richmama.fantasywinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.leandroborgesferreira.loadingbutton.customViews.CircularProgressButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterNow extends AppCompatActivity {
    String TAG = "Registernow";
    TextView haveAccount;
    EditText emailReg;
    EditText passReg;
    CircularProgressButton buttonRegister;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_now);

        haveAccount = findViewById(R.id.textview_haveaccout);
        buttonRegister = findViewById(R.id.cirLoginReg);
        emailReg = findViewById(R.id.editTextEmail_reg);
        passReg = findViewById(R.id.editTextPassword_reg);
        mAuth = FirebaseAuth.getInstance();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNow();
            }
        });

        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterNow.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(RegisterNow.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void registerNow(){
        String email = String.valueOf(emailReg.getText());
        String pass = String.valueOf(passReg.getText() );

        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"Please Enter a Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"Please Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(RegisterNow.this, "Registration Success .", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterNow.this,MainActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterNow.this, "Registration failed.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });




    }


}