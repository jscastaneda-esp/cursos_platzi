package com.platzi.platzigram.login.repository;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.platzi.platzigram.login.presenter.LoginPresenter;

public class LoginRepositoryImpl implements LoginRepository {

    private static final String TAG = "LoginRepositoryImpl";

    private LoginPresenter presenter;

    public LoginRepositoryImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void signIn(String username, String password, final Activity activity, FirebaseAuth firebaseAuth) {
        firebaseAuth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    proccessResult(task, activity);
                }
            });
    }

    @Override
    public void signIn(AuthCredential authCredential, final Activity activity, FirebaseAuth firebaseAuth) {
        firebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        proccessResult(task, activity);
                    }
                });
    }

    private void proccessResult(Task<AuthResult> task, Activity activity) {
        if (task.isSuccessful()) {
            SharedPreferences preferences = activity.getSharedPreferences("com.platzi.platzigram_user", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();

            FirebaseUser user = task.getResult().getUser();
            editor.putString("email", user.getEmail());
            editor.commit();

            Crashlytics.log(Log.WARN, TAG, "Usuario logeado " + user.getEmail());

            presenter.loginSuccess();
        } else {
            presenter.loginError(task.getException().getMessage());
            Log.e(TAG, "Error in proccessResult signIn > " + task.getException().getMessage(), task.getException());
            Crashlytics.logException(task.getException());
        }
    }
}
