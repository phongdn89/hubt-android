package com.hubt.less12;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QLGhiChu.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "tblGhiChu";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TIEU_DE = "tieuDe";
    private static final String COLUMN_NOI_DUNG = "noiDung";
    private static final String COLUMN_NGAY_TAO = "ngayTao";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TIEU_DE + " TEXT, " +
                COLUMN_NOI_DUNG + " TEXT, " +
                COLUMN_NGAY_TAO + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Thêm ghi chú
    public long addNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TIEU_DE, note.getTieuDe());
        values.put(COLUMN_NOI_DUNG, note.getNoiDung());
        values.put(COLUMN_NGAY_TAO, note.getNgayTao());

        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    // Cập nhật ghi chú
    public int updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TIEU_DE, note.getTieuDe());
        values.put(COLUMN_NOI_DUNG, note.getNoiDung());
        values.put(COLUMN_NGAY_TAO, note.getNgayTao());

        int result = db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
        return result;
    }

    // Lấy danh sách ghi chú
    public List<Note> getAllNotes() {
        List<Note> noteList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                note.setTieuDe(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIEU_DE)));
                note.setNoiDung(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOI_DUNG)));
                note.setNgayTao(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NGAY_TAO)));
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return noteList;
    }
}
