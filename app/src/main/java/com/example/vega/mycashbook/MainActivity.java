package com.example.vega.mycashbook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    TextView tvIncome, tvExpanses, tvBalance, tvEmail, tvUser;
    Cursor cursor;
    private SessionManagement sessionManagement;
    DatabaseHelper dbcenter;
    String id="";
    Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        dbcenter = new DatabaseHelper(this);
        sessionManagement = new SessionManagement(MainActivity.this);
        tvIncome = findViewById(R.id.income);
        tvExpanses = findViewById(R.id.expenses);
        tvBalance=findViewById(R.id.balance);
//        tvEmail = findViewById(R.id.txtemail);
//        tvUser = findViewById(R.id.txtusername);
        buttonLogout = findViewById(R.id.button_logout);

        if(sessionManagement.isLoggedIn()) {
            final HashMap<String, String> user = sessionManagement.getUserInformation();
            SQLiteDatabase db = dbcenter.getReadableDatabase();
            int i = 0;
            int e = 0;
            //mengambil jumlah income user dari sqlite
            cursor = db.rawQuery("SELECT SUM(jumlah) FROM transaksi WHERE id_user = '" + user.get(sessionManagement.KEY_ID_USER) + "' and jenis = 'Income'", null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) //jika hasil query tidak kosong
            {
                cursor.moveToPosition(0);
                if (cursor.isNull(0)) {
                    tvIncome.setText("0"); //jika jumlah nya null isi text view dengan angka 0
                    i = 0;
                } else { //jika tidak null isi sesuai databasw
                    tvIncome.setText(cursor.getString(0).toString());
                    i = Integer.parseInt(cursor.getString(0).toString());
                }

            }
            //mengambil jumlah expenses user dari sqlite
            cursor = db.rawQuery("SELECT SUM(jumlah) FROM transaksi WHERE id_user = '" + user.get(sessionManagement.KEY_ID_USER) + "' and jenis = 'Expenses'", null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) //jika hasil query tidak kosong
            {
                cursor.moveToPosition(0);
                if (cursor.isNull(0)) { //jika jumlah nya null isi text view dengan angka 0
                    tvExpanses.setText("0");
                    e = 0;
                } else{
                    tvExpanses.setText(cursor.getString(0).toString());
                    e = Integer.parseInt(cursor.getString(0).toString());
                }
            }
            int total = i-e; //total diperoleh dari income - expenses
            tvBalance.setText(Integer.toString(total));
            id=user.get(sessionManagement.KEY_ID_USER);

        }
    }

    public void menu1(View view){
        Intent moveIntent = new Intent(MainActivity.this, TambahPemasukan.class);
        startActivity(moveIntent);
    }

    public void menu2(View view){
        Intent moveIntent = new Intent(MainActivity.this, AddPengeluaran.class);
        startActivity(moveIntent);
    }

    public void menu3(View view){
        Intent moveIntent = new Intent(MainActivity.this, CashFlow.class);
        startActivity(moveIntent);
    }

    public void menu4(View view){
        Intent moveIntent = new Intent(MainActivity.this, Setting.class);
        startActivity(moveIntent);
    }

    public void buttonLogout(View view){
        sessionManagement.logoutUser();

    }
}
