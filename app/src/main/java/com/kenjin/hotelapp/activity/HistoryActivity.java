package com.kenjin.hotelapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.kenjin.hotelapp.R;
import com.kenjin.hotelapp.adapter.PesananAdapter;
import com.kenjin.hotelapp.database.HotelDb;
import com.kenjin.hotelapp.model.Pesanan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.kenjin.hotelapp.activity.DetailHistori.EXTRA_KEY;

public class HistoryActivity extends AppCompatActivity {

    private List<Pesanan>pesanan=new ArrayList();

    private ListView lv;
    private HotelDb db;
    private PesananAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = HotelDb.getInstance(this);

        lv = (ListView) findViewById(R.id.listview_history);

        lv.setEmptyView(findViewById(R.id.empty));
        adapter = new PesananAdapter(this, pesanan);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent i=new Intent(getBaseContext(),DetailHistori.class);
               i.putExtra(EXTRA_KEY, pesanan.get(position));
               startActivity(i);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            Log.e("Home pressed", "home presed");
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pesanan.clear();
        pesanan.addAll(db.getAllPesanan());
        adapter.notifyDataSetChanged();
    }
}
