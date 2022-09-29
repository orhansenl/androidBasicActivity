package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int database_VERSION = 1;
    private static final String database_NAME = "ajanlarDB";
    private static final String table_AJANLAR = "ajanlar";
    private static final String ajan_ID = "id";
    private static final String ajan_AD = "ad";
    private static final String ajan_ACIKLAMA = "aciklama";
    private static final String[] COLUMNS = {ajan_ID, ajan_AD, ajan_ACIKLAMA};
    private static final String CREATE_AJAN_TABLE = "CREATE TABLE " + table_AJANLAR + " ("
            + ajan_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ajan_AD + "	TEXT,"
            + ajan_ACIKLAMA + " TEXT)";


    public SQLiteHelper(@Nullable Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_AJAN_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + table_AJANLAR);
        this.onCreate(db);
    }

    public void AjanEkle(Ajan ajan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues degerler = new ContentValues();
        degerler.put(ajan_AD, ajan.getAd());
        degerler.put(ajan_ACIKLAMA, ajan.getAciklama());
        db.insert(table_AJANLAR, null, degerler);
        db.close();
    }

    public List<Ajan> AjanlariListele() {
        List<Ajan> ajanlar = new ArrayList<>();
        String query = "SELECT * FROM " + table_AJANLAR;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Ajan ajan = null;
        if (cursor.moveToFirst()) {
            do {
                ajan = new Ajan();
                ajan.setId(Integer.parseInt(cursor.getString(0)));
                ajan.setAd(cursor.getString(1));
                ajan.setAciklama(cursor.getString(2));
                ajanlar.add(ajan);
            } while (cursor.moveToNext());
        }
        return ajanlar;
    }

    public Ajan ajanOku(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(table_AJANLAR, COLUMNS, " id = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Ajan ajan = new Ajan();
        ajan.setId(Integer.parseInt(cursor.getString(0)));
        ajan.setAd(cursor.getString(1));
        ajan.setAciklama(cursor.getString(2));
        return ajan;

    }
}
