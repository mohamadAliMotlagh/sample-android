package com.dmond.android.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dmond.android.R;
import com.dmond.android.customViews.CEditText;
import com.dmond.android.customViews.CTextInputLayout;
import com.dmond.android.customViews.cTextView;
import com.dmond.android.mapStuff.MapActivity;

/**
 * Created by hosein on 1/21/17.
 */

public class RegisterActivity extends AppCompatActivity implements RegisterView, View.OnClickListener {
    private RegisterPresenter presenter;
    private CTextInputLayout phoneNumberLay;
    private CEditText phoneNumberEdt;
    private cTextView go;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        phoneNumberLay = (CTextInputLayout) findViewById(R.id.phone_number_lay);
        phoneNumberEdt = (CEditText) findViewById(R.id.phone_number);
        presenter = new RegisterPresenterImpl(this);
        go= (cTextView) findViewById(R.id.go_next);
        go.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onClick(View v) {
        presenter.validatePhoneNumber(RegisterActivity.this,phoneNumberEdt.getText().toString());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setPhoneNumberError() {
        phoneNumberLay.setErrorCustom("خطا در وارد کردن شماره تلفن");
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }
}
