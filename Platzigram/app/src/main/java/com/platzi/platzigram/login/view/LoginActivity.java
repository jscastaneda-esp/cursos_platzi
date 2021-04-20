package com.platzi.platzigram.login.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.platzi.platzigram.R;
import com.platzi.platzigram.login.presenter.LoginPresenter;
import com.platzi.platzigram.login.presenter.LoginPresenterImpl;
import com.platzi.platzigram.view.ContainerActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private static final String TAG = "LoginActivity";

    private TextInputEditText username, password;
    private Button login;
    private LoginButton loginFacebook;
    private ProgressBar progressBarLogin;

    private LoginPresenter presenter;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Crashlytics.log("Initialize class " + TAG);

        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    Crashlytics.log(Log.WARN, TAG, "Usuario Logeado " + firebaseUser.getEmail());
                    goHome();
                } else {
                    Crashlytics.log(Log.WARN, TAG, "Usuario No Logeado");
                }
            }
        };

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        username = (TextInputEditText) findViewById(R.id.username);
        password = (TextInputEditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if ("".equals(username.getText().toString()) || "".equals(password.getText().toString())) {
                    Snackbar.make(view, getString(R.string.messageInputInvalid), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    signIn(username.getText().toString(), password.getText().toString());
                }
            }
        });
        loginFacebook = (LoginButton) findViewById(R.id.login_facebook);
        loginFacebook.setReadPermissions("email");
        loginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Crashlytics.log(Log.WARN, TAG, "Facebook Login Success Token: " + loginResult.getAccessToken().getToken());
                signInFacebook(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Crashlytics.log(Log.WARN, TAG, "Facebook Login Cancelado");
            }

            @Override
            public void onError(FacebookException error) {
                loginError(error.getMessage());
                Log.e(TAG, "Facebook Login Error: " + error.getMessage(), error);
                Crashlytics.logException(error);
            }
        });
        progressBarLogin = (ProgressBar) findViewById(R.id.progressBarLogin);
        hideProgressBar();

        presenter = new LoginPresenterImpl(this);
    }

    private void signIn(String username, String password) {
        presenter.signIn(username, password, this, firebaseAuth);
    }

    private void signInFacebook(AccessToken accessToken) {
        presenter.signIn(FacebookAuthProvider.getCredential(accessToken.getToken()), this, firebaseAuth);
    }

    @Override
    public void enableInputs() {
        username.setEnabled(true);
        password.setEnabled(true);
        login.setEnabled(true);
    }

    @Override
    public void disabledInputs() {
        username.setEnabled(false);
        password.setEnabled(false);
        login.setEnabled(false);
    }

    @Override
    public void showProgressBar() {
        progressBarLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBarLogin.setVisibility(View.GONE);
    }

    @Override
    public void loginError(String error) {
        password.setText("");
        Toast.makeText(this, String.format("%s %s", getString(R.string.messageGenericError), error), Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void goPlatziWeb() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://platzi.com"));
        startActivity(intent);
    }

    public void goPlatziWeb(View view) {
        goPlatziWeb();
    }

    @Override
    public void goCreateAccount() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void goCreateAccount(View view) {
        goCreateAccount();
    }

    @Override
    public void goHome() {
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
