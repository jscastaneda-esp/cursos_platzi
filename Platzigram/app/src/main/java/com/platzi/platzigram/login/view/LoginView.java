package com.platzi.platzigram.login.view;

import android.view.View;

public interface LoginView {

    void enableInputs();
    void disabledInputs();
    void showProgressBar();
    void hideProgressBar();
    void loginError(String error);
    void goPlatziWeb();
    void goCreateAccount();
    void goHome();
}
