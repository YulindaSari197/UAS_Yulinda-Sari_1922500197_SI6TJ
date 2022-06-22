package com.example.uassi6tj1922500197;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.uassi6tj1922500197.R;
import com.example.uassi6tj1922500197.datadosen.DataDosenJsonPlaceHolderAPI;
import com.example.uassi6tj1922500197.datadosen.PostDosen;
import com.example.uassi6tj1922500197.datadosen.datadosen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private DataDosenJsonPlaceHolderAPI jsonPlaceHolderAPI;

    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.flat);
        //Ketika di klik
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, datadosen.class);
                startActivity(i);
            }
        });

        //Penampil data
        textView = findViewById(R.id.txt_dosen_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.61.220/mit/") //jika data tidak tampil ubah ip
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderAPI = retrofit.create(DataDosenJsonPlaceHolderAPI.class);
        getPosts();
    }

    private void getPosts() {
        Map<String, String > parameters = new HashMap<>();
        Call<List<PostDosen>> call = jsonPlaceHolderAPI.getPosts();
        call.enqueue(new Callback<List<PostDosen>>() {
            @Override
            public void onResponse(Call<List<PostDosen>> call, Response<List<PostDosen>> response) {
                if (!response.isSuccessful()){
                    textView.setText("Code: " + response.code());
                    return;
                }
                List<PostDosen> posts = response.body();
                for (PostDosen post : posts) {
                    String content = "";
                    content += "NIDN: " + post.getNidn() + "\n";
                    content += "Nama Dosen : " + post.getNamaDosen() + "\n";
                    content += "Jabatan : " + post.getJabatan() + "\n";
                    content += "Golongan Pangkat : " + post.getGolPang() + "\n";
                    content += "Keahlian : " + post.getKeahlian() + "\n";
                    content += "Program Studi : " + post.getProgramStudi() + "\n\n";
                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<PostDosen>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}