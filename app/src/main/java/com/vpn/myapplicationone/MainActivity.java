package com.vpn.myapplicationone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.BinderThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button button;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn_ok);
        videoView = findViewById(R.id.videoView);

        Uri videoUri = getIntent().getData();

        if (videoUri == null) {

            Toast.makeText(this, "No video found", Toast.LENGTH_SHORT).show();

        }

        playVideo(videoUri);


//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
////                    Intent intent = new Intent("OPEN_SPLASH");
////                    startActivity(intent);
//
//                   /* Intent intent = new Intent("PAYAL");
//                    startActivity(intent);*/
//
//                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                    intent.setType("video/*");
//                    startActivityForResult(intent, 102);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            if (requestCode == 101) {
                //    imageView.setImageURI(uri);
            } else if (requestCode == 102) {
                playVideo(uri);
            } else if (requestCode == 103) {
                //     playAudio(uri);
            }
        }
    }

    private void playVideo(Uri uri) {
        VideoView videoView = findViewById(R.id.videoView);
        videoView.setVideoURI(uri);
        videoView.start();
    }
}