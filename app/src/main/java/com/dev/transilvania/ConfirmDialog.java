package com.dev.transilvania;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ConfirmDialog {

    AlertDialog dialog;
    AlertDialog.Builder builder;
    boolean debounce;


    public void dialogAnwser(Context ct, String option) {

        builder = new AlertDialog.Builder(ct);

        builder.setTitle("Confirmação de " + option);
        builder.setMessage("Favor confirmar se deseja solicitar o " + option);
        builder.setCancelable(false);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                debounce = true;
            }
        });

        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                debounce = false;
            }
        });

        builder.create();
        builder.show();

    }
}
