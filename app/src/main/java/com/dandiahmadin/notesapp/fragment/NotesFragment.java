// NIM   : 10119033
// Nama  : Dandi Ahmadin
// Kelas : IF-1

package com.dandiahmadin.notesapp.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dandiahmadin.notesapp.MainActivity;
import com.dandiahmadin.notesapp.R;
import com.dandiahmadin.notesapp.activity.note.AddNoteActivity;
import com.dandiahmadin.notesapp.activity.note.DetailNoteActivity;
import com.dandiahmadin.notesapp.activity.note.EditNoteActivity;
import com.dandiahmadin.notesapp.adapter.NoteAdapter;
import com.dandiahmadin.notesapp.database.Database;
import com.dandiahmadin.notesapp.model.NoteModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends Fragment {

    private ListView listView;
    private List<NoteModel> listNote = new ArrayList<>();
    private Cursor cursor;
    private Database database;
    private FloatingActionButton floatingActionButton;
    private NoteAdapter noteAdapter;

    public NotesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        floatingActionButton = view.findViewById(R.id.btn_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddNoteActivity.class));
            }
        });

        noteAdapter = new NoteAdapter(getContext(), listNote);
        listView = view.findViewById(R.id.container_list_view);
        database = new Database(getContext());
        getAllNote();
        refreshList(view);
        noteAdapter.notifyDataSetChanged();
        return view;
    }

    private void getAllNote() {
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery(database.selectAll(), null);
        listNote.clear();
        if (cursor.moveToFirst()){
            do {
                NoteModel note = new NoteModel();
                note.setId(cursor.getInt(0));
                note.setTitle(cursor.getString(1));
                note.setCategory(cursor.getString(2));
                note.setNote(cursor.getString(3));
                listNote.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        noteAdapter.notifyDataSetChanged();
    }

    private void refreshList(View view) {
//        SQLiteDatabase db = database.getReadableDatabase();
//        cursor = db.rawQuery(database.selectAll(), null);
//        listNote.clear();
//        if (cursor.moveToFirst()){
//            do {
//                NoteModel note = new NoteModel();
//                note.setId(cursor.getInt(0));
//                note.setTitle(cursor.getString(1));
//                note.setCategory(cursor.getString(2));
//                note.setNote(cursor.getString(3));
//                listNote.add(note);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();

        listView.setAdapter(noteAdapter);
        listView.setSelected(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int id = listNote.get(i).getId();
                final CharSequence[] dialogItem = {
                        "Detail Notes",
                        "Edit Notes",
                        "Delete Notes"
                };
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Intent intent_detail = new Intent(requireActivity(), DetailNoteActivity.class);
                                intent_detail.putExtra("id", id);
                                startActivity(intent_detail);
                                break;
                            case 1:
                                Intent intent_edit = new Intent(getActivity(), EditNoteActivity.class);
                                intent_edit.putExtra("id", id);
                                startActivity(intent_edit);
                                break;
                            case 2:
                                SQLiteDatabase db = database.getWritableDatabase();
                                database.deleteById(db, id);
                                Toast.makeText(getContext(), "Berhasil menghapus catatan.", Toast.LENGTH_SHORT).show();
//                                refreshList(getView());
                                getAllNote();
                                break;
                            default:
                                i = 0;
                        }
                    }
                }).show();
            }
        });
    }

}