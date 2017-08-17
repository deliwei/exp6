package com.example.deli.exp6;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button register, login;
    private EditText name, password;
    private CheckBox rememberPass;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String Name, Password;
    private boolean isRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = (Button) findViewById(R.id.register_button);
        login = (Button) findViewById(R.id.login_button);
        name = (EditText) findViewById(R.id.name_editText);
        password = (EditText) findViewById(R.id.password_editText);
        rememberPass = (CheckBox) findViewById(R.id.checkBox);

        rememberPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 是否记住密码按钮也作为一个数据记录在user_data文件中
                pref = getSharedPreferences("user_data", MODE_PRIVATE);
                pref.edit().putBoolean("isRemember", isChecked).commit();

                Name = name.getText().toString();
                if (isChecked && Name.equals(pref.getString("name", "")))
                    password.setText(pref.getString("password", ""));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Name = name.getText().toString();
                Password = password.getText().toString();

                if (Name.equals("") | Password.equals(""))
                    Toast.makeText(
                            getApplicationContext(), "Register Error", Toast.LENGTH_SHORT).show();
                else {
                    // 将数据以键值对的形式存入user_data文件中
                    editor = getSharedPreferences("user_data", MODE_PRIVATE).edit();
                    editor.putString("name", Name);
                    editor.putString("password", Password);
                    editor.commit();
                    Toast.makeText(
                            getApplicationContext(), "Register Success", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = name.getText().toString();
                Password = password.getText().toString();

                pref = getSharedPreferences("user_data", MODE_PRIVATE); // 从user_data文件中读取数据
                String tempName = pref.getString("name", "");
                String tempPassword = pref.getString("password", "");
                // 比较读取文件和新输入的帐号密码是否匹配
                if (Name.equals(tempName) & Password.equals(tempPassword)) {
                    Toast.makeText(
                            getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, EditFileActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(
                            getApplicationContext(), "Login Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume()
    {
        pref = getSharedPreferences("user_data", MODE_PRIVATE);
        isRemember = pref.getBoolean("isRemember", false);
        // 通过判断user_data中的isRemember是否为true进行操作
        if (isRemember) {
            // 将账号和密码都设置到文本框中
            Name = pref.getString("name", "");
            Password = pref.getString("password", "");
            name.setText(Name);
            password.setText(Password);
        }
        else
            password.setText("");

        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
