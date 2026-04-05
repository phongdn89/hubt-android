package com.hubt.less3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {

    private EditText edtHoTen, edtCMND, edtBoSung;
    private RadioGroup rgBangCap;
    private CheckBox cbDocBao, cbDocSach, cbDocCode;
    private Button btnGui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        edtHoTen = findViewById(R.id.edtHoTen);
        edtCMND = findViewById(R.id.edtCMND);
        edtBoSung = findViewById(R.id.edtBoSung);
        rgBangCap = findViewById(R.id.rgBangCap);
        cbDocBao = findViewById(R.id.cbDocBao);
        cbDocSach = findViewById(R.id.cbDocSach);
        cbDocCode = findViewById(R.id.cbDocCode);
        btnGui = findViewById(R.id.btnGui);

        btnGui.setOnClickListener(v -> {
            String hoTen = edtHoTen.getText().toString().trim();
            String cmnd = edtCMND.getText().toString().trim();

            // Validation
            if (hoTen.length() < 3) {
                edtHoTen.setError("Họ tên phải ít nhất 3 ký tự");
                edtHoTen.requestFocus();
                return;
            }

            if (cmnd.length() == 0) {
                edtCMND.setError("CMND không được để trống");
                edtCMND.requestFocus();
                return;
            }

            if (!cbDocBao.isChecked() && !cbDocSach.isChecked() && !cbDocCode.isChecked()) {
                Toast.makeText(this, "Phải chọn ít nhất 1 sở thích", Toast.LENGTH_SHORT).show();
                return;
            }

            // Get degree
            int idChecked = rgBangCap.getCheckedRadioButtonId();
            RadioButton rbSelected = findViewById(idChecked);
            String bangCap = rbSelected.getText().toString();

            // Get hobbies
            String soThich = "";
            if (cbDocBao.isChecked()) soThich += cbDocBao.getText().toString() + ", ";
            if (cbDocSach.isChecked()) soThich += cbDocSach.getText().toString() + ", ";
            if (cbDocCode.isChecked()) soThich += cbDocCode.getText().toString() + ", ";
            if (soThich.endsWith(", ")) soThich = soThich.substring(0, soThich.length() - 2);

            String boSung = edtBoSung.getText().toString();

            // Display info
            String msg = "Họ tên: " + hoTen + "\n" +
                         "CMND: " + cmnd + "\n" +
                         "Bằng cấp: " + bangCap + "\n" +
                         "Sở thích: " + soThich + "\n" +
                         "----------------------------\n" +
                         "Thông tin bổ sung:\n" + boSung;

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Thông tin cá nhân");
            builder.setMessage(msg);
            builder.setPositiveButton("Đóng", null);
            builder.create().show();
        });
    }
}