package com.example.listviewku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BantuDatabase extends SQLiteOpenHelper {
    private static final String DB_GAME = "db_game";
    private static final String KODE = "kode";
    private static final String NAMA_GAME = "nm_game";
    private static final String TABEL_GAME = "tbl_game";

    public BantuDatabase(@Nullable Context context) {
        super(context, DB_GAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String nama_tabel = "create table " + TABEL_GAME + "(" + KODE + " integer primary key autoincrement," + NAMA_GAME + " text)";
        db.execSQL(nama_tabel);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean tambahData(String nama) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAMA_GAME,nama);

        long hasil = db.insert(TABEL_GAME,null,contentValues);
        return hasil != -1;
    }

    public Cursor tampilGame() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from "+TABEL_GAME;
        Cursor cursor = db.rawQuery(sql,null);

        return cursor;
    }

    public boolean hapusRecord(int kode) {
        String kd = Integer.toString(Integer.parseInt(String.valueOf(kode)));
        int kdbrg = Integer.parseInt(kd);
        MainActivity.editText.setText(Integer.toString(kdbrg));
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABEL_GAME,KODE+"="+kdbrg,null)>0;
    }

    public boolean updateData(String name,String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAMA_GAME, name);
        db.update(TABEL_GAME,contentValues,"kode = ?", new String[]{id});
        return true;
    }
}
