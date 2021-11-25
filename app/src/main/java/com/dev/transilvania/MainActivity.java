package com.dev.transilvania;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dev.transilvania.BD.DAO;

import org.w3c.dom.Text;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    Login objLogin = new Login();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.mViewHolder.btnLogin = findViewById(R.id.btn_login);
        this.mViewHolder.valueID = findViewById(R.id.value_id);
        this.mViewHolder.valuePassword = findViewById(R.id.value_password);

        this.mViewHolder.btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {

            objLogin.setID(this.mViewHolder.valueID.getText().toString());
            DAO.searchLogin(this, this.mViewHolder.valueID.getText().toString(), this.mViewHolder.valuePassword.getText().toString());
        }
    }

    private static class ViewHolder {
        EditText valueID;
        EditText valuePassword;
        Button btnLogin;
    }

    public TextView getValueID() {
        return this.mViewHolder.valueID;
    }
}
