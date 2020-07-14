package com.gxun.mynews;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gxun.mynews.util.Validator;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvCancel;
    EditText etUserName, etPassword, etRePassword, etTel, etEmail;
    Button btnRegister;

    private static boolean isTelRight = false;
    private static boolean isEmailRight = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initWidget(); // 初始化控件
        tvCancel.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        etTel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ValidateTel();
            }
        });

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ValidateEmail();
            }
        });

    }

    protected void initWidget(){
        tvCancel = findViewById(R.id.cancel);
        etUserName = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        etRePassword = findViewById(R.id.rePassword);
        etTel = findViewById(R.id.tel);
        etEmail = findViewById(R.id.email);
        btnRegister = findViewById(R.id.register);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel: goBack(); break;
            case R.id.register: goRegister(); break;
        }
    }

    public void goBack(){
        finish();
    }

    public void goRegister(){
        String username = etUserName.getText().toString();
        String password = etPassword.getText().toString();
        String rePassword = etRePassword.getText().toString();
        String tel = etTel.getText().toString();
        String email = etEmail.getText().toString();
        if (username == null && username.equals("")){
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_LONG).show();
        }else if (password == null && password.equals("")){
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_LONG).show();
        }else if (!password.equals(rePassword)){
            Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_LONG).show();
        }else if(!isTelRight){
            Toast.makeText(this, "手机号错误", Toast.LENGTH_LONG).show();
        }else if (!isEmailRight){
            Toast.makeText(this, "邮箱错误", Toast.LENGTH_LONG).show();
        }else{

        }
    }

    private void ValidateTel(){
        isTelRight = Validator.isTelephone(etTel.getText().toString());
        if(isTelRight){
            //符合手机号码格式则不处理
            etTel.setTextColor(Color.parseColor("#000000"));
        }else{
            etTel.setTextColor(Color.parseColor("#FF3030"));
        }
    }

    private void ValidateEmail(){
        isEmailRight = Validator.isEmail(etEmail.getText().toString());
        if(isEmailRight){
            //是邮件地址则不处理
            etEmail.setTextColor(Color.parseColor("#000000"));
        }else{
            etEmail.setTextColor(Color.parseColor("#FF3030"));
        }
    }

    // 设置点击返回键事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && (event.getRepeatCount() == 0)) {
            finish(); //按下返回键结束当前Activity
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}