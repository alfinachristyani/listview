package com.example.listviewku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity {
    EditText idne,edGame,nama_game,hasilkode;
    private String nama,noid;
    Button saveEd, viewlis;
    BantuDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        db = new BantuDatabase(this);
        idne = (EditText) findViewById(R.id.noGame);
        edGame = (EditText) findViewById(R.id.editGame);
        nama_game = findViewById(R.id.hasileditgame);
        hasilkode = findViewById(R.id.kodehasil);
        Bundle extras = getIntent().getExtras();
        idne.setText(extras.getString("data1"));
        edGame.setText(extras.getString("data2"));

        saveEd = (Button) findViewById(R.id.btnEdit);
        viewlis = (Button) findViewById(R.id.btnListview);
        viewlis.setOnClickListener((view) -> {
            Intent intent = new Intent(UpdateData.this,MainActivity.class);
            startActivity(intent);
        });

        saveEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hasilkode.setText(idne.getText().toString());
                nama_game.setText(edGame.getText().toString());

                boolean hasile = db.updateData(edGame.getText().toString(),idne.getText().toString());
                if (hasile==true) {
                    Toast.makeText(UpdateData.this, "UPDATE BERHASIL", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateData.this, "UPDATE GAGAL", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}