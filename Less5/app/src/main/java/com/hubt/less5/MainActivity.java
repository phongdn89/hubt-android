package com.hubt.less5;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView ivAlbum;
    private TextView tvSongTitle;
    private Spinner spSongs;
    private ProgressBar pbLoading;
    private SeekBar seekBar;
    private ImageButton btnBack, btnPlay, btnPause, btnNext;
    private Switch swRepeat;

    private MediaPlayer mediaPlayer;
    private int currentIndex = 0;
    private Handler handler = new Handler();

    // Dữ liệu bài hát (Tên, File nhạc, Ảnh)
    // Lưu ý: Bạn cần thêm các file nhạc tương ứng vào res/raw
    private String[] songTitles = {"Góc tối - Nguyễn Hải Phong", "Love Story (Instrumental)", "Waka Waka (World Cup 2010)"};
    private int[] songFiles = {R.raw.song1, R.raw.song2, R.raw.song3}; 
    private int[] songImages = {R.drawable.album1, R.drawable.album2, R.drawable.album3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupSpinner();
        setupEventListeners();

        // Khởi tạo bài hát đầu tiên (ở chế độ chờ)
        prepareSong(currentIndex);
    }

    private void initViews() {
        ivAlbum = findViewById(R.id.ivAlbum);
        tvSongTitle = findViewById(R.id.tvSongTitle);
        spSongs = findViewById(R.id.spSongs);
        pbLoading = findViewById(R.id.pbLoading);
        seekBar = findViewById(R.id.seekBar);
        btnBack = findViewById(R.id.btnBack);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnNext = findViewById(R.id.btnNext);
        swRepeat = findViewById(R.id.swRepeat);
    }

    private void setupSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, songTitles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSongs.setAdapter(adapter);

        spSongs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != currentIndex) {
                    currentIndex = position;
                    prepareSong(currentIndex);
                    playMusic();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void setupEventListeners() {
        btnPlay.setOnClickListener(v -> playMusic());
        btnPause.setOnClickListener(v -> pauseMusic());
        btnNext.setOnClickListener(v -> nextSong());
        btnBack.setOnClickListener(v -> backSong());

        // Xử lý SeekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && mediaPlayer != null) {
                    mediaPlayer.seekTo(progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Xử lý Switch Repeat
        swRepeat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (mediaPlayer != null) {
                mediaPlayer.setLooping(isChecked);
            }
        });
    }

    private void prepareSong(int index) {
        try {
            // Giải lập trạng thái đang tải (ProgressBar)
            pbLoading.setVisibility(View.VISIBLE);
            
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }

            // Kiểm tra tài nguyên tồn tại trước khi tạo (để tránh crash nếu bạn chưa copy file)
            mediaPlayer = MediaPlayer.create(this, songFiles[index]);
            
            if (mediaPlayer == null) {
                Toast.makeText(this, "Không tìm thấy file nhạc: " + songTitles[index], Toast.LENGTH_SHORT).show();
                pbLoading.setVisibility(View.GONE);
                return;
            }

            // Cập nhật giao diện
            tvSongTitle.setText(songTitles[index]);
            ivAlbum.setImageResource(songImages[index]);
            spSongs.setSelection(index);
            
            seekBar.setMax(mediaPlayer.getDuration());
            mediaPlayer.setLooping(swRepeat.isChecked());

            // Xử lý khi bài hát kết thúc (tự động chuyển bài nếu không lặp)
            mediaPlayer.setOnCompletionListener(mp -> {
                if (!swRepeat.isChecked()) {
                    nextSong();
                }
            });

            pbLoading.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
            pbLoading.setVisibility(View.GONE);
        }
    }

    private void playMusic() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            updateSeekBar();
        }
    }

    private void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    private void nextSong() {
        currentIndex = (currentIndex + 1) % songTitles.length;
        prepareSong(currentIndex);
        playMusic();
    }

    private void backSong() {
        currentIndex = (currentIndex - 1 + songTitles.length) % songTitles.length;
        prepareSong(currentIndex);
        playMusic();
    }

    private void updateSeekBar() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
            handler.postDelayed(this::updateSeekBar, 1000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        handler.removeCallbacksAndMessages(null);
    }
}