package com.dev.transilvania;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dev.transilvania.BD.DAO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ServicesActivity extends AppCompatActivity implements View.OnClickListener {


    private ViewHolder mViewHolder = new ViewHolder();
    ConfirmDialog dialog = new ConfirmDialog();
    LocalDate localDate;
    DAO bd = new DAO();

    Login objLogin = new Login();

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        this.mViewHolder.autoCompleteTextView = findViewById(R.id.auto_complete_textView);
        this.mViewHolder.autoCompleteTextViewDates = findViewById(R.id.auto_complete_textViewDate);
        this.mViewHolder.btnAgendar = findViewById(R.id.btn_agendar);
        this.mViewHolder.btnBack = findViewById(R.id.btn_back);

        localDate = LocalDate.now().plusDays(1);
        String strDateFormat = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.mViewHolder.autoCompleteTextViewDates.setText(strDateFormat + " 15:30 ");

        String[] services = getResources().getStringArray(R.array.services_room);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, R.layout.dropdown_item, services);
        this.mViewHolder.autoCompleteTextView.setAdapter(arrayAdapter);

        ArrayList<String> dates = generatingDates();
        ArrayAdapter<String> arrayAdapterDates = new ArrayAdapter<>(this, R.layout.dropdown_dates, dates);
        this.mViewHolder.autoCompleteTextViewDates.setAdapter(arrayAdapterDates);

        this.mViewHolder.btnBack.setOnClickListener(this);
        this.mViewHolder.btnAgendar.setOnClickListener(this);


    }

    public ArrayList<String> generatingDates() {
        int count = 1;
        ArrayList<String> dates = new ArrayList<>();
        for (int i = Calendar.getInstance().get(Calendar.DAY_OF_MONTH); i < Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH); i++) {

            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH) - 1, i);
            localDate = LocalDate.now().plusDays(count);
            if (!(localDate.getDayOfWeek().toString().equalsIgnoreCase("SUNDAY"))) {
                String strLocalDateForm = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                dates.add(strLocalDateForm + " 15:30");
            }

            count++;
        }
        return dates;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back) {
            Intent it = new Intent(this, MenuActivity.class);
            startActivity(it);
        } else if (v.getId() == R.id.btn_agendar) {

            boolean debounce = false;
            List<String> base = bd.selectInformation("servico", "servicos", objLogin.getID());
            List<String> date = bd.selectInformation("horario", "servicos", objLogin.getID());


            if (base.isEmpty()) {
                bd.resetingSeed();
                bd.InsertService(objLogin.getID(), mViewHolder.autoCompleteTextView.getText().toString(), mViewHolder.autoCompleteTextViewDates.getText().toString());
                Toast.makeText(getApplicationContext(), "Solicitação concluida!", Toast.LENGTH_LONG).show();
            } else {
                for (int i = 0; i < base.size(); i++) {
                    if (base.get(i).equalsIgnoreCase(mViewHolder.autoCompleteTextView.getText().toString()) && date.get(i).equalsIgnoreCase(mViewHolder.autoCompleteTextViewDates.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Serviço já solicitado", Toast.LENGTH_SHORT).show();
                        debounce = true;
                    }
                }
                if (debounce == false) {
                    bd.InsertService(objLogin.getID(), mViewHolder.autoCompleteTextView.getText().toString(), mViewHolder.autoCompleteTextViewDates.getText().toString());
                    Toast.makeText(getApplicationContext(), "Solicitação concluida!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    private static class ViewHolder {
        Button btnAgendar;
        ImageView btnBack;
        AutoCompleteTextView autoCompleteTextView;
        AutoCompleteTextView autoCompleteTextViewDates;
    }
}





