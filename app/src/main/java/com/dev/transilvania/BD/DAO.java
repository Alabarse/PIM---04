package com.dev.transilvania.BD;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.dev.transilvania.MenuActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    private static Connection con;
    ConnectionHelper connectionHelper = new ConnectionHelper();


    public void resetingSeed() {
        try {
            con = connectionHelper.connectionClass();
            PreparedStatement pst = con.prepareStatement("DBCC checkident ('produtos', RESEED, 0)");
            pst.executeQuery();
        }catch (Exception e) {
            Log.e("dbError", e.getMessage());
        }
    }

    public void deleteInformation(String table, int id) {

        try {
            con = connectionHelper.connectionClass();
            PreparedStatement pst = con.prepareStatement("DELETE FROM "+ table + " WHERE id = ?");
            pst.setInt(1, id);
            pst.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }


    public List<String> selectInformation(String information, String table, String quarto) {
        List<String> dados = new ArrayList<>();

        try {
            con = connectionHelper.connectionClass();
            PreparedStatement pst = con.prepareStatement("Select " + information + " from " + table + " where quarto = " + quarto);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dados.add(rs.getString(information));
            }
            rs.close();
            pst.close();
        } catch (Exception e) {

            Log.e("Select error", e.getMessage());
        }
        return dados;
    }

    public void insertProducts(String idQuarto, String typeProduct, String product, String value) {
        try {
            con = connectionHelper.connectionClass();
            PreparedStatement pst = con.prepareStatement("Insert into produtos (quarto, tipoProduto, produto, valor) values (?,?,?,?)");
            pst.setString(1, idQuarto);
            pst.setString(2, typeProduct);
            pst.setString(3, product);
            pst.setString(4, value);
            pst.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void InsertService(String idQuarto, String service, String data) {
        try {
            con = connectionHelper.connectionClass();
            PreparedStatement pst = con.prepareStatement("Insert into servicos (quarto, servico, horario) values (?,?,?)");
            pst.setString(1, idQuarto);
            pst.setString(2, service);
            pst.setString(3, data);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public static void searchLogin(Context ct, String login, String password) {
        try {
            boolean debounce = false;
            ConnectionHelper connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();
            if (con != null) {
                String query = "Select * from login";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);

                while (rs.next()) {

                    if (rs.getString(2).equalsIgnoreCase(login) && rs.getString(3).equalsIgnoreCase(password)) {
                        debounce = true;
                        Intent it = new Intent(ct, MenuActivity.class);
                        ct.startActivity(it);
                    }
                }
                if (debounce == false) {
                    Toast.makeText(ct, "ID ou senha inválidos!", Toast.LENGTH_LONG).show();
                }
            }

        } catch (Exception e) {
            Log.e("BD error", "Erro ao conectar ao banco de dados");
        }
    }

}