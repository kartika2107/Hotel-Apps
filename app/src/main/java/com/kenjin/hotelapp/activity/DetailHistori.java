package com.kenjin.hotelapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kenjin.hotelapp.R;
import com.kenjin.hotelapp.database.HotelDb;
import com.kenjin.hotelapp.model.Pesanan;

public class DetailHistori extends AppCompatActivity {

    public static final String EXTRA_KEY="EXTRA_KEY";

    private EditText namapesanan;
    private EditText lamapemesanan;
    private EditText email;
    private EditText telefon;
    private Button edit;
    private Button hapus;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        namapesanan= (EditText) findViewById(R.id.editText);
        lamapemesanan= (EditText) findViewById(R.id.editText2);
        email= (EditText) findViewById(R.id.editText4);
        telefon= (EditText) findViewById(R.id.editText5);
        edit= (Button) findViewById(R.id.button);
        hapus= (Button) findViewById(R.id.button2);

        final HotelDb db = HotelDb.getInstance(this);
        final Pesanan pesanan = (Pesanan) getIntent().getSerializableExtra(EXTRA_KEY);

        namapesanan.setText(pesanan.getNama());
        lamapemesanan.setText(pesanan.getLamainap());
        email.setText(pesanan.getEmail());
        telefon.setText(pesanan.getNumber());


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pesanan.setNama(namapesanan.getText().toString());
                pesanan.setLamainap(lamapemesanan.getText().toString());
                pesanan.setEmail(email.getText().toString());
                pesanan.setNumber(telefon.getText().toString());

                db.editPesanan(pesanan);
                finish();
            }
        });
        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deletePesanan(pesanan.getId());
                finish();

            }
        });
    }
}
