package com.example.uassi6tj1922500197.datadosen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.uassi6tj1922500197.MainActivity;
import com.example.uassi6tj1922500197.R;
import com.example.uassi6tj1922500197.RequestHandler;

import java.util.HashMap;

public class datadosen extends AppCompatActivity {
    private EditText txtetNIDN;
    private EditText txtetNmDosen;
    private EditText txtetJabatan;
    private EditText txtetGolongan;
    private EditText txtetKeahlian;
    private EditText txtetStudi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_dosen);

        txtetNIDN = (EditText) findViewById(R.id.et_NIDN);
        txtetNmDosen = (EditText) findViewById(R.id.et_Nm_Dosen);
        txtetJabatan = (EditText) findViewById(R.id.et_Jabatan);
        txtetGolongan = (EditText) findViewById(R.id.et_Golongan);
        txtetKeahlian = (EditText) findViewById(R.id.et_Keahlian);
        txtetStudi = (EditText) findViewById(R.id.et_Studi);
    }

    public void save(View view){
        final String nidn             = txtetNIDN.getText().toString().trim();
        final String nama_dosen    = txtetNmDosen.getText().toString().trim();
        final String jabatan           = txtetJabatan.getText().toString().trim();
        final String gol_pang      = txtetGolongan.getText().toString().trim();
        final String keahlian    = txtetKeahlian.getText().toString().trim();
        final String program_studi    = txtetStudi.getText().toString().trim();

        class save2 extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(datadosen.this, "Add...", "Wait...", false, false);
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put("nidn", nidn);
                params.put("nama_dosen", nama_dosen);
                params.put("jabatan", jabatan);
                params.put("gol_pang", gol_pang);
                params.put("keahlian", keahlian);
                params.put("program_studi", program_studi);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest("http://192.168.61.220/mit/tambah_dosen.php", params);
                return res;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();

                //Pindah ke menu utama
                if (s.equals("berhasil")){
                    Intent i = new Intent(datadosen.this, MainActivity.class);
                    startActivity(i);
                }else {
                    Toast.makeText(datadosen.this, "Data harus lengkap", Toast.LENGTH_SHORT).show();
                }
            }
        }
        save2 ae = new save2();
        ae.execute();
    }

    public void back(View view){
        Intent i = new Intent(datadosen.this, MainActivity.class);
        startActivity(i);
    }

}