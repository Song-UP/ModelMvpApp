package com.example.songup.modelmvpapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_main);
        ButterKnife.bind(this);

        tv.setText("哈哈哈哈");
        Util.start();
        initView();

//        R.string.new_house_module1_str
    }

    public void initView(){
        int myname =0;

    }

    @OnClick(R.id.tv)
    public void onViewClicked() {
        Toast.makeText(this,"好哈哈",Toast.LENGTH_SHORT).show();
    }
}
