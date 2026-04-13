package com.hubt.less7;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvCurrentSong;
    private ImageView ivDisk;
    private ImageButton btnPlay, btnPause, btnBack, btnNext;
    private ListView lvSongs;

    private ArrayList<Song> songList;
    private ArrayList<String> songTitles;
    private int currentIndex = 0;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initData();
        setupListView();
        setupListeners();
    }

    private void initViews() {
        tvCurrentSong = findViewById(R.id.tvCurrentSong);
        ivDisk = findViewById(R.id.ivDisk);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
        lvSongs = findViewById(R.id.lvSongs);
    }

    private void initData() {
        songList = new ArrayList<>();
        
        songList.add(new Song("Hà Nội niềm tin và hy vọng - Phan Nhân", R.raw.song1));
        songList.add(new Song("Nhớ mùa thu Hà Nội - Trịnh Công Sơn", R.raw.song2));
        songList.add(new Song("Hà Nội mùa vắng những cơn mưa - Trương Quý Hải", R.raw.song3));
        songList.add(new Song("Người Hà Nội - Nguyễn Đình Thi", R.raw.song4));
        songList.add(new Song("Hà Nội 12 mùa hoa - Giáng Son", R.raw.song5));
        songList.add(new Song("Em ơi Hà Nội phố - Phú Quang", R.raw.song6));
        songList.add(new Song("Nồng nàn Hà Nội - Nguyễn Đức Cường", R.raw.song2));

        songTitles = new ArrayList<>();
        for (Song song : songList) {
            songTitles.add(song.getTitle());
        }
    }

    private void setupListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, songTitles);
        lvSongs.setAdapter(adapter);
    }

    private void setupListeners() {
        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentIndex = position;
                playSong();
            }
        });

        btnPlay.setOnClickListener(v -> {
            if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            } else if (mediaPlayer == null) {
                playSong();
            }
        });

        btnPause.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        });

        btnNext.setOnClickListener(v -> {
            currentIndex++;
            if (currentIndex >= songList.size()) {
                currentIndex = 0;
            }
            playSong();
        });

        btnBack.setOnClickListener(v -> {
            currentIndex--;
            if (currentIndex < 0) {
                currentIndex = songList.size() - 1;
            }
            playSong();
        });
    }

    private void playSong() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        Song currentSong = songList.get(currentIndex);
        tvCurrentSong.setText(currentSong.getTitle());
        
        // Chỉ phát nếu có file resource hợp lệ (khác 0)
        if (currentSong.getFile() != 0) {
            mediaPlayer = MediaPlayer.create(MainActivity.this, currentSong.getFile());
            mediaPlayer.start();
        } else {
            Toast.makeText(this, "Chưa có file nhạc cho bài này!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
