package com.dev.transilvania;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dev.transilvania.BD.DAO;

public class ProductsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    DAO bd = new DAO();
    Login objLogin = new Login();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        this.mViewHolder.btnBack = findViewById(R.id.btn_back);
        this.mViewHolder.autoCompleteTextType = findViewById(R.id.auto_complete_textType);
        this.mViewHolder.autoCompleteTextProducts = findViewById(R.id.auto_complete_textView_products);
        this.mViewHolder.btnSolProducts = findViewById(R.id.btn_solProdutos);
        this.mViewHolder.productsValue = findViewById(R.id.products_value);

        String[] productsType = getResources().getStringArray(R.array.products_types);
        ArrayAdapter arrayAdapterType = new ArrayAdapter(this, R.layout.dropdown_products_type, productsType);
        this.mViewHolder.autoCompleteTextType.setAdapter(arrayAdapterType);

        String[] products = getResources().getStringArray(R.array.products_drinks);
        ArrayAdapter arrayAdapterProducts = new ArrayAdapter(this, R.layout.dropdown_products, products);
        this.mViewHolder.autoCompleteTextProducts.setAdapter(arrayAdapterProducts);

        this.mViewHolder.btnBack.setOnClickListener(this);
        this.mViewHolder.autoCompleteTextType.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectTypeOfProducts();
            }
        });

        this.mViewHolder.autoCompleteTextProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                defineValueOfProducts();
            }
        });

        this.mViewHolder.btnSolProducts.setOnClickListener(this);
    }

    public static class ViewHolder {
        Button btnSolProducts;
        AutoCompleteTextView productsValue;
        AutoCompleteTextView autoCompleteTextProducts;
        AutoCompleteTextView autoCompleteTextType;
        ImageView btnBack;
    }

    public void selectTypeOfProducts() {
        this.mViewHolder.autoCompleteTextProducts.setText("");

        if (this.mViewHolder.autoCompleteTextType.getText().toString().equalsIgnoreCase("BEBIDAS")) {
            String[] products = getResources().getStringArray(R.array.products_drinks);
            ArrayAdapter arrayAdapterProducts = new ArrayAdapter(this, R.layout.dropdown_products, products);
            this.mViewHolder.autoCompleteTextProducts.setAdapter(arrayAdapterProducts);
        } else if (this.mViewHolder.autoCompleteTextType.getText().toString().equalsIgnoreCase("DOCES")) {
            String[] products = getResources().getStringArray(R.array.products_foods);
            ArrayAdapter arrayAdapterProducts = new ArrayAdapter(this, R.layout.dropdown_products, products);
            this.mViewHolder.autoCompleteTextProducts.setAdapter(arrayAdapterProducts);
        } else if (this.mViewHolder.autoCompleteTextType.getText().toString().equalsIgnoreCase("CANAIS")) {
            String[] products = getResources().getStringArray(R.array.products_tv);
            ArrayAdapter arrayAdapterProducts = new ArrayAdapter(this, R.layout.dropdown_products, products);
            this.mViewHolder.autoCompleteTextProducts.setAdapter(arrayAdapterProducts);
        }

    }

    public void defineValueOfProducts() {
        switch (this.mViewHolder.autoCompleteTextProducts.getText().toString()) {
            case "COCA-COLA":
                String value = getResources().getString(R.string.coca_value);
                this.mViewHolder.productsValue.setText(value);
                break;
            case "VINHO":
                value = getResources().getString(R.string.vinho_value);
                this.mViewHolder.productsValue.setText(value);
                break;
            case "PEPSI":
                value = getResources().getString(R.string.pepsi_value);
                this.mViewHolder.productsValue.setText(value);
                break;
            case "SPRITE":
                value = getResources().getString(R.string.sprite_value);
                this.mViewHolder.productsValue.setText(value);
                break;
            case "FINI":
                value = getResources().getString(R.string.fini_value);
                this.mViewHolder.productsValue.setText(value);
                break;
            case "SNICKERS":
                value = getResources().getString(R.string.snickers_value);
                this.mViewHolder.productsValue.setText(value);
                break;
            case "PIPOCA":
                value = getResources().getString(R.string.pipoca_value);
                this.mViewHolder.productsValue.setText(value);
                break;
            case "HBO":
                value = getResources().getString(R.string.hbo_value);
                this.mViewHolder.productsValue.setText(value);
                break;
            case "TELECINE":
                value = getResources().getString(R.string.telecine_value);
                this.mViewHolder.productsValue.setText(value);
                break;
            case "ESPN":
                value = getResources().getString(R.string.espn_value);
                this.mViewHolder.productsValue.setText(value);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back) {
            Intent it = new Intent(this, MenuActivity.class);
            startActivity(it);
        } else if (v.getId() == R.id.btn_solProdutos) {

            if (bd.selectInformation("id", "produtos", objLogin.getID()).isEmpty()) {
                bd.resetingSeed();
            }

            if (this.mViewHolder.autoCompleteTextProducts.getText().toString().isEmpty()) {
                Toast.makeText(ProductsActivity.this, "Favor informar o produto", Toast.LENGTH_LONG).show();
            } else {
                bd.insertProducts(objLogin.getID(), this.mViewHolder.autoCompleteTextType.getText().toString(),
                        this.mViewHolder.autoCompleteTextProducts.getText().toString(), this.mViewHolder.productsValue.getText().toString());
            }

        }
    }
}
