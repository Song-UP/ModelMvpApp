package com.example.commonbusiness.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.commonbusiness.util.widget.RegisterEditTextView;
import com.example.songup.modelmvpapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity {
    @BindView(R.id.retv_account)
    RegisterEditTextView retvAccount;
    @BindView(R.id.retv_phone_no)
    RegisterEditTextView retvPhoneNo;
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_business_activity_main);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.button)
    public void onViewClicked(View view) {
        retvAccount.setTvRequiredVisiblity(true);
        retvPhoneNo.setTvRequiredVisiblity(true);

        retvPhoneNo.setTvErrorVisiblity(true);


    }
}
