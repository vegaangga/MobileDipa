package com.example.vega.mycashbook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class CashFlow extends AppCompatActivity {
    TextView tvIncome, tvExpanses, tvBalance, tvJns, tvEmail, tvUser;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    Cursor cursor;
    private SessionManagement sessionManagement;
    RecyclerView rv;
    private ArrayList<String> dataId,dataTanggal, dataKeterangan, dataJumlah, dataJenis;
    DatabaseHelper dbcenter;
    String id="";
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_flow);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        dbcenter = new DatabaseHelper(this);
//        setSupportActionBar(toolbar);
        sessionManagement = new SessionManagement(CashFlow.this);
        tvIncome = findViewById(R.id.income);
        tvExpanses = findViewById(R.id.expenses);
        tvBalance=findViewById(R.id.balance);

        dataId = new ArrayList<>();
        dataTanggal = new ArrayList<>();
        dataKeterangan=new ArrayList<>();
        dataJumlah = new ArrayList<>();
        dataJenis = new ArrayList<>();

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });


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



        initDataset();
        rv = (RecyclerView) findViewById(R.id.rv_main);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        adapter = new MyAdapter(dataId,dataTanggal,dataKeterangan,dataJumlah, dataJenis);
        rv.setAdapter(adapter);
    }
    private void initDataset() {

        SQLiteDatabase db = dbcenter.getReadableDatabase();
        //mengambil data dari database untuk isi dari recycler view
        cursor = db.rawQuery("SELECT * FROM transaksi where id_user = "+ id,null);
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            //masukkan data kedalam array
            dataId.add(cursor.getString(0).toString());
            dataTanggal.add(cursor.getString(1).toString());
            dataKeterangan.add(cursor.getString(6).toString());
            dataJumlah.add(cursor.getString(5).toString());
            dataJenis.add(cursor.getString(2).toString());
        }

    }

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
        Intent moveIntent = new Intent(CashFlow.this, MainActivity.class);
        startActivity(moveIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

}
