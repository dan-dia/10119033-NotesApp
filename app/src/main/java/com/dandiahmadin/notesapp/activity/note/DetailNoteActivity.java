// NIM   : 10119033
// Nama  : Dandi Ahmadin
// Kelas : IF-1

package com.dandiahmadin.notesapp.activity.note;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.dandiahmadin.notesapp.R;
import com.dandiahmadin.notesapp.database.Database;
import com.dandiahmadin.notesapp.model.NoteModel;

public class DetailNoteActivity extends AppCompatActivity {

    private TextView title, category, note;
    private Database database;
    private NoteModel noteModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_note);

        title = findViewById(R.id.title);
        category = findViewById(R.id.category);
        note = findViewById(R.id.note);

        int id = getIntent().getIntExtra("id", 0);

        database = new Database(this);
        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = db.rawQuery(database.selectById(id), null);

        cursor.moveToFirst();

        noteModel = new NoteModel();
        noteModel.setId(id);
        noteModel.setTitle(cursor.getString(1).toString());
        noteModel.setCategory(cursor.getString(2).toString());
        noteModel.setNote(cursor.getString(3).toString());

        cursor.close();

        title.setText(noteModel.getTitle());
        category.setText(noteModel.getCategory());
        note.setText(noteModel.getNote());


    }
}