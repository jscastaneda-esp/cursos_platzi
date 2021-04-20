package com.platzi.platzigram.login.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.platzi.platzigram.R;

public class CreateAccountActivity extends AppCompatActivity {

    private static final String TAG = "CreateAccountActivity";
    private TextInputEditText email, pass, confirmPass;
    private Button btnJoinUs;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Crashlytics.log("Initialize class " + TAG);

        setContentView(R.layout.activity_create_account);
        showToolbar(getResources().getString(R.string.toolbar_title_createaccount), true);

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    Crashlytics.log(Log.WARN, TAG, "Usuario Logeado " + firebaseUser.getEmail());
                } else {
                    Crashlytics.log(Log.WARN, TAG, "Usuario No Logeado");
                }
            }
        };

        email = (TextInputEditText) findViewById(R.id.email);
        pass = (TextInputEditText) findViewById(R.id.pass);
        confirmPass = (TextInputEditText) findViewById(R.id.confirmPass);
        btnJoinUs = (Button) findViewById(R.id.joinUs);
        btnJoinUs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                createAccount();
            }
        });
    }

    private void createAccount() {
        firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(CreateAccountActivity.this, "Cuenta Creada Exitosamente", Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        Toast.makeText(CreateAccountActivity.this, "Ocurrió un error al crear la cuenta " + task.getException().getMessage(), Toast.LENGTH_SHORT)
                                .show();
                        Log.e(TAG, "Error in createAccount > " + task.getException().getMessage(), task.getException());
                        Crashlytics.logException(task.getException());
                    }
                }
            });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

    public void showToolbar(String title, boolean upButton) {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
