package com.dev.transilvania;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.mViewHolder.btnRequestedServices = findViewById(R.id.btn_services_requested);
        this.mViewHolder.btnServices = findViewById(R.id.btn_solServicos);
        this.mViewHolder.btnProducts = findViewById(R.id.btn_solProdutos);
        this.mViewHolder.imgBack = findViewById(R.id.btn_back);


        this.mViewHolder.btnRequestedServices.setOnClickListener(this);
        this.mViewHolder.btnServices.setOnClickListener(this);
        this.mViewHolder.btnProducts.setOnClickListener(this);
        this.mViewHolder.imgBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_services_requested) {
            Intent it = new Intent(this, ServicesRequest.class);
            startActivity(it);
        } else if (v.getId() == R.id.btn_solServicos) {
            Intent it = new Intent(this, ServicesActivity.class);
            startActivity(it);
        } else if (v.getId() == R.id.btn_solProdutos) {
            Intent it = new Intent(this, ProductsActivity.class);
            startActivity(it);
        } else if (v.getId() == R.id.btn_back) {
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
        }
    }

    public static class ViewHolder {
        ImageView imgBack;
        Button btnRequestedServices;
        Button btnServices;
        Button btnProducts;
    }
}
