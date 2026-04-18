package com.hubt.less12;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etTieuDe, etNoiDung;
    private DatePicker datePicker;
    private Button btnThem, btnSua;
    private ListView lvGhiChu;

    private DatabaseHelper dbHelper;
    private List<Note> noteList;
    private ArrayAdapter<Note> adapter;
    private Note selectedNote = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        dbHelper = new DatabaseHelper(this);
        loadNotes();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNote();
            }
        });

        lvGhiChu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedNote = noteList.get(position);
                etTieuDe.setText(selectedNote.getTieuDe());
                etNoiDung.setText(selectedNote.getNoiDung());
                
                String[] dateParts = selectedNote.getNgayTao().split("/");
                if (dateParts.length == 3) {
                    int day = Integer.parseInt(dateParts[0]);
                    int month = Integer.parseInt(dateParts[1]) - 1;
                    int year = Integer.parseInt(dateParts[2]);
                    datePicker.updateDate(year, month, day);
                }
            }
        });
    }

    private void initViews() {
        etTieuDe = findViewById(R.id.etTieuDe);
        etNoiDung = findViewById(R.id.etNoiDung);
        datePicker = findViewById(R.id.datePicker);
        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        lvGhiChu = findViewById(R.id.lvGhiChu);
    }

    private void loadNotes() {
        noteList = dbHelper.getAllNotes();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, noteList);
        lvGhiChu.setAdapter(adapter);
    }

    private String getSelectedDate() {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();
        return day + "/" + month + "/" + year;
    }

    private void addNote() {
        String tieuDe = etTieuDe.getText().toString().trim();
        String noiDung = etNoiDung.getText().toString().trim();
        String ngayTao = getSelectedDate();

        if (tieuDe.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tiêu đề", Toast.LENGTH_SHORT).show();
            return;
        }

        Note note = new Note(tieuDe, noiDung, ngayTao);
        dbHelper.addNote(note);
        clearFields();
        loadNotes();
        Toast.makeText(this, "Đã thêm ghi chú", Toast.LENGTH_SHORT).show();
    }

    private void updateNote() {
        if (selectedNote == null) {
            Toast.makeText(this, "Vui lòng chọn ghi chú để sửa", Toast.LENGTH_SHORT).show();
            return;
        }

        String tieuDe = etTieuDe.getText().toString().trim();
        String noiDung = etNoiDung.getText().toString().trim();
        String ngayTao = getSelectedDate();

        if (tieuDe.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tiêu đề", Toast.LENGTH_SHORT).show();
            return;
        }

        selectedNote.setTieuDe(tieuDe);
        selectedNote.setNoiDung(noiDung);
        selectedNote.setNgayTao(ngayTao);

        dbHelper.updateNote(selectedNote);
        clearFields();
        loadNotes();
        selectedNote = null;
        Toast.makeText(this, "Đã cập nhật ghi chú", Toast.LENGTH_SHORT).show();
    }

    private void clearFields() {
        etTieuDe.setText("");
        etNoiDung.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_intro) {
            Intent intent = new Intent(this, IntroActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
