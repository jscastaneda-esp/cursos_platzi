package com.platzi.platzigram.login.interactor;

import android.app.Activity;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.platzi.platzigram.login.presenter.LoginPresenter;
import com.platzi.platzigram.login.repository.LoginRepository;
import com.platzi.platzigram.login.repository.LoginRepositoryImpl;

public class LoginInteraptorImpl implements LoginInteraptor {

    private LoginPresenter presenter;
    private LoginRepository repository;

    public LoginInteraptorImpl(LoginPresenter presenter) {
        this.presenter = presenter;
        repository = new LoginRepositoryImpl(presenter);
    }

    @Override
    public void signIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth) {
        repository.signIn(username, password, activity, firebaseAuth);
    }

    @Override
    public void signIn(AuthCredential authCredential, Activity activity, FirebaseAuth firebaseAuth) {
        repository.signIn(authCredential, activity, firebaseAuth);
    }
}
