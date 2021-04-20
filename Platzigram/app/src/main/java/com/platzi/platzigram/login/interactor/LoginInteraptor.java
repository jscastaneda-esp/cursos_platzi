package com.platzi.platzigram.login.interactor;

import android.app.Activity;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;

public interface LoginInteraptor {

    void signIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth);
    void signIn(AuthCredential authCredential, Activity activity, FirebaseAuth firebaseAuth);
}
