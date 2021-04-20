package com.platzi.platzigram.login.presenter;

import android.app.Activity;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.platzi.platzigram.login.interactor.LoginInteraptor;
import com.platzi.platzigram.login.interactor.LoginInteraptorImpl;
import com.platzi.platzigram.login.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView view;
    private LoginInteraptor interaptor;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        interaptor = new LoginInteraptorImpl(this);
    }

    @Override
    public void signIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth) {
        view.disabledInputs();
        view.showProgressBar();
        interaptor.signIn(username, password, activity, firebaseAuth);
    }

    @Override
    public void signIn(AuthCredential authCredential, Activity activity, FirebaseAuth firebaseAuth) {
        view.disabledInputs();
        view.showProgressBar();
        interaptor.signIn(authCredential, activity, firebaseAuth);
    }

    @Override
    public void loginSuccess() {
        view.goHome();
        view.hideProgressBar();
        view.enableInputs();
    }

    @Override
    public void loginError(String error) {
        view.hideProgressBar();
        view.enableInputs();
        view.loginError(error);
    }
}
