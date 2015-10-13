package com.isil.am2lesson3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.isil.am2lesson3.storage.PreferencesHelper;
import com.isil.am2lesson3.view.NavigationDrawerActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String VALUE_ADMIN="admin";
    private static final String VALUE_PASSWORD="123456";

    private EditText editTxtUsername;
    private EditText editTxtPassword;
    private Button btnLogin;

    private String username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(session())
        {
            gotoHome();
            return;

        }else
        {
            init();
        }
    }

    private boolean session() {
        String tmp =PreferencesHelper.getUserSession(this);
        if(tmp==null) return false;

        return true;
    }

    private void init() {
        editTxtUsername= (EditText)findViewById(R.id.editTxtUsername);
        editTxtPassword= (EditText)findViewById(R.id.editTxtPassword);
        btnLogin= (Button)findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm())
                {
                    if(username.equals(VALUE_ADMIN) && password.equals(VALUE_PASSWORD))
                    {
                        saveSession();
                        gotoHome();
                    }else
                    {
                        //TODO mostrar mensaje de error
                    }
                }
            }
        });
    }

    private void saveSession() {
        PreferencesHelper.saveSession(this, username,password);
    }

    private void gotoHome() {
        Intent intent= new Intent(this, NavigationDrawerActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean validateForm() {
        username = editTxtUsername.getText().toString().trim();
        password = editTxtPassword.getText().toString().trim();

        if (username.isEmpty()) {
            editTxtUsername.setError("Username ,campo inválido");
            return false;
        }
        if (password.isEmpty())
        {
            editTxtUsername.setError("Passsword ,campo inválido");
            return false;
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
}
