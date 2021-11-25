package com.dev.transilvania;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dev.transilvania.BD.DAO;

import java.util.ArrayList;
import java.util.List;

public class ServicesRequest extends AppCompatActivity {

    DAO bd = new DAO();
    Login objLogin = new Login();
    private ViewHolder mViewHolder = new ViewHolder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_request);


        this.mViewHolder.list = findViewById(R.id.request_list);
        this.mViewHolder.listProducts = findViewById(R.id.request_listProducts);


        List<String> service = bd.selectInformation("servico", "servicos", objLogin.getID());
        List<String> dates = bd.selectInformation("horario", "servicos", objLogin.getID());
        List<String> idServices = bd.selectInformation("id", "servicos", objLogin.getID());
        List<Integer> idServicesConverted = new ArrayList<>();

        List<String> products = bd.selectInformation("produto", "produtos", objLogin.getID());
        List<String> values = bd.selectInformation("valor", "produtos", objLogin.getID());
        List<String> idProduct = bd.selectInformation("id", "produtos", objLogin.getID());
        List<Integer> idProductConverted = new ArrayList<Integer>();


        ArrayList<String> array = new ArrayList<>();
        ArrayList<String> arrayProducts = new ArrayList<>();


        for (int i = 0; i < service.size(); i++) {
            array.add("ID: " + idServices.get(i) + " | " + service.get(i) + "  |  " + dates.get(i));
        }

        for (int i = 0; i < products.size(); i++) {
            arrayProducts.add("ID: "+ idProduct.get(i) + " | " + products.get(i) + "  |  " + values.get(i));
        }

        for (int i = 0; i < idProduct.size(); i++) {
            idProductConverted.add(i, Integer.parseInt(idProduct.get(i)));
        }

        for (int i = 0; i < idServices.size(); i ++) {
            idServicesConverted.add(i, Integer.parseInt(idServices.get(i)));
        }


        ArrayAdapter<String> productsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, arrayProducts);
        ArrayAdapter<String> servicesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, array);
        //bd.selectInformation("servico, horario", "servicos", objLogin.getID())

        this.mViewHolder.list.setAdapter(servicesAdapter);
        System.out.println(arrayProducts);
        this.mViewHolder.listProducts.setAdapter(productsAdapter);

        this.mViewHolder.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder alert = new AlertDialog.Builder(ServicesRequest.this);
                alert.setTitle("Cancelamento de serviços");
                alert.setMessage("Favor confirmar se deseja cancelar o serviço");
                alert.setCancelable(false);
                alert.setPositiveButton("SIM", (dialog, which) -> {
                    bd.deleteInformation("servicos", idServicesConverted.get(position));

                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                });

                alert.setNegativeButton("NÃo", (dialog, which) -> {

                });

                alert.create().show();


            }
        });

        this.mViewHolder.listProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder alert = new AlertDialog.Builder(ServicesRequest.this);
                alert.setTitle("Cancelamento de produto");
                alert.setMessage("Favor confirmar se deseja cancelar o produto solicitado");
                alert.setCancelable(false);
                alert.setPositiveButton("SIM", (dialog, which) -> {

                    bd.deleteInformation("produtos", idProductConverted.get(position));

                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);


                    //Toast.makeText(ServicesRequest.this, String.valueOf(position), Toast.LENGTH_LONG).show();

                });

                alert.setNegativeButton("NÃO", (dialog, which) -> {

                });

                alert.setCancelable(false);

                alert.create();
                alert.show();

            }
        });


    }

    public static class ViewHolder {
        ListView list;
        ListView listProducts;
    }
}
