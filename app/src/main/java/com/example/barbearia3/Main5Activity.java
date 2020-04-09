package com.example.barbearia3;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.TimeZone;

import static android.app.AlarmManager.INTERVAL_HALF_HOUR;
import static android.widget.Toast.LENGTH_SHORT;

public class Main5Activity extends AppCompatActivity {

    DatabaseReference mDatabase;
    DatabaseReference mtel;// ...

    private static final String TAG = "Main5Activity";

    DatabaseHelper databaseHelper;

    TextView txt,txthora;
    Button agend,end,contato,insta,face;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        insta=(Button)findViewById(R.id.instagram);
        face=(Button)findViewById(R.id.faceb);
        end=(Button)findViewById(R.id.endereco);
        contato=(Button)findViewById(R.id.contato);
        agend=(Button)findViewById(R.id.agendar);
        txt=(TextView) findViewById(R.id.txt);
        txthora=(TextView) findViewById(R.id.txthora);
       Button sair=(Button)findViewById(R.id.sair);


        Bundle c = new Bundle();
        c = getIntent().getExtras();
        final String nome = c.getString("name");
        final String nome1 = c.getString("name1");
        final String nome2 = c.getString("name2");
        final String nome3 = c.getString("name3");

//lembrete
        agend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int ndias=Integer.parseInt(nome3);
                if(ndias>2){
                    ndias = ndias - 1;
                }

                AlarmManager alarmMgr = (AlarmManager) Main5Activity.this.getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(Main5Activity.this, onReceiver.class);

                Calendar calendar = Calendar.getInstance();

                PendingIntent alarmIntent = PendingIntent.getBroadcast(Main5Activity.this, 0, intent, 0);
                long t = INTERVAL_HALF_HOUR;
                alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis() + ndias * 60 * 60 * 1000  ,t, alarmIntent);
                Toast.makeText(getApplicationContext(), "Lembrete adicionado", Toast.LENGTH_LONG).show();


    }
});
        sair.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });


        String dado =("Agendado para: "+ nome + " " + nome2 +"\nhorario -> "+ nome1+ " horas." );
        txt.setText( nome + " " + nome2 );
        txthora.setText(  nome1+ " horas." );

        databaseHelper= new DatabaseHelper(this);


                String newEntry = dado.toString();
                if (dado.length() != 0) {
                    AddData(newEntry);

                } else {
                   // Toast.makeText(Main5Activity.this, " adicionado", LENGTH_SHORT).show();
                }

    }

    public void AddData(String newEntry) {
        boolean insertData = databaseHelper.addData(newEntry);
        if (insertData == true){
          //  Toast.makeText(Main5Activity.this, "adicionado", LENGTH_SHORT).show();
        }else{

        //Toast.makeText(Main5Activity.this, "não adicionado", LENGTH_SHORT).show();
    }

//contatos
        mDatabase = FirebaseDatabase.getInstance().getReference().child("endereco");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                final String ender = dataSnapshot.getValue(String.class);

                end.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String uri = "http://maps.google.com/maps?saddr=" + "&daddr=" + ender;
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));

                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
        mtel = FirebaseDatabase.getInstance().getReference().child("telefone");
        mtel.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                final String tel = dataSnapshot.getValue(String.class);

                contato.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setPackage("com.whatsapp");
                        intent.setData(Uri.parse(String.format("https://api.whatsapp.com/send?phone=%s", tel)));
                        if (getPackageManager().resolveActivity(intent, 0) != null) {
                            startActivity(intent);
                        } else {
                            Toast.makeText(Main5Activity.this, "Instale o WhatsApp", LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference().child("facebook");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                final String faceb = dataSnapshot.getValue(String.class);


                face.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String uri = faceb;
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);

                        }else{
                            Toast.makeText(Main5Activity.this, "Sem link Facebook", LENGTH_SHORT).show();

                        }     }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference().child("instagram");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                final String inst = dataSnapshot.getValue(String.class);

                insta.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String uri = inst;
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }else{
                            Toast.makeText(Main5Activity.this, "Sem link Instagram", LENGTH_SHORT).show();
                        }  }
                });

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });

    }
}

