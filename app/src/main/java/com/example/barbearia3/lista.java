package com.example.barbearia3;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

public class lista extends AppCompatActivity {

    FirebaseDatabase objetoRef;
    DatabaseReference ref;

    private static final String TAG = "lista";
    DatabaseHelper databaseHelper;
    ListView list;
    Button apaga;
    private Object view;
    String nome;
    private int id;
    String a;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listitem);


        list = (ListView) findViewById(R.id.listView);
        databaseHelper = new DatabaseHelper(this);


        objetoRef = FirebaseDatabase.getInstance();
        ref = objetoRef.getReference();

                populateListView();
            }

            private void populateListView() {
                Log.d(TAG, "populateListView: Displaying data in listView");

                Cursor data = databaseHelper.getData();
                ArrayList<String> listData = new ArrayList<>();
                while (data.moveToNext()) {
                    listData.add(data.getString(1));
                }
                ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listData){
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        // Como o simple_list_item_1 retorna um TextView, esse cast pode ser feito sem problemas
                        ((TextView) view).setTextColor(GREEN);
                        ((TextView) view).setBackgroundResource(R.drawable.borda);

                        return view;
                    }
               };

                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(final AdapterView<?> adapterView, final View view, int i, long l) {

                   final String name = adapterView.getItemAtPosition(i).toString();

                       Cursor data = databaseHelper.getItemID(name);
                        int itemID = -1;
                        while (data.moveToNext()) {
                            itemID = data.getInt(0);
                        }if (itemID > -1) {

                            id = itemID;
                            nome = name;
                        }

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(lista.this);
                        alertDialog.setTitle("Confirm Delete...");
                        alertDialog.setMessage("Deseja cancelar o agendamento?");
                            alertDialog.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Date da = new Date();
                                SimpleDateFormat dtaForm = new  SimpleDateFormat("EEEEEEE dd/MM/yy");
                                String dtaFormatada = dtaForm.format(da);
                                String ab = dtaFormatada;

                                SimpleDateFormat sdf = new  SimpleDateFormat("EEEEEEE dd/MM/yy");
                                Calendar data1 = Calendar.getInstance();
                                Calendar data2 = Calendar.getInstance();
                                try {
                                    data1.setTime(sdf.parse(ab));
                                    data2.setTime(sdf.parse(name.toString()));
                                } catch (java.text.ParseException e) {
                                }

                                // int dias = data2.get(Calendar.DAY_OF_YEAR) - data1.get(Calendar.DAY_OF_YEAR);

                                if ( data1.get(Calendar.DAY_OF_YEAR) <= data2.get(Calendar.DAY_OF_YEAR))
                                {


                                ref = objetoRef.getReference();
                                String estado = "disponivel";
                                if(name.toString().contains("08:") && name.contains("SEGUNDA")){ref.child("segunda/8").setValue(estado);  }
                                if(name.toString().contains("09:") && name.contains("SEGUNDA")){ref.child("segunda/9").setValue(estado);  }
                                if(name.toString().contains("10:") && name.contains("SEGUNDA")){ref.child("segunda/10").setValue(estado);  }
                                if(name.toString().contains("11:") && name.contains("SEGUNDA")){ref.child("segunda/11").setValue(estado);  }
                                if(name.toString().contains("12:") && name.contains("SEGUNDA")){ref.child("segunda/12").setValue(estado);  }
                                if(name.toString().contains("13:") && name.contains("SEGUNDA")){ref.child("segunda/13").setValue(estado);  }
                                if(name.toString().contains("14:") && name.contains("SEGUNDA")){ref.child("segunda/14").setValue(estado);  }
                                if(name.toString().contains("15:") && name.contains("SEGUNDA")){ref.child("segunda/15").setValue(estado);  }
                                if(name.toString().contains("16:") && name.contains("SEGUNDA")){ref.child("segunda/16").setValue(estado);  }
                                if(name.toString().contains("17:") && name.contains("SEGUNDA")){ref.child("segunda/17").setValue(estado);  }
                                if(name.toString().contains("18:") && name.contains("SEGUNDA")){ref.child("segunda/18").setValue(estado);  }
                                if(name.toString().contains("08:") && name.contains("TERCA")){ref.child("terca/8").setValue(estado);  }
                                if(name.toString().contains("09:") && name.contains("TERCA")){ref.child("terca/9").setValue(estado);  }
                                if(name.toString().contains("10:") && name.contains("TERCA")){ref.child("terca/10").setValue(estado);  }
                                if(name.toString().contains("11:") && name.contains("TERCA")){ref.child("terca/11").setValue(estado);  }
                                if(name.toString().contains("12:") && name.contains("TERCA")){ref.child("terca/12").setValue(estado);  }
                                if(name.toString().contains("13:") && name.contains("TERCA")){ref.child("terca/13").setValue(estado);  }
                                if(name.toString().contains("14:") && name.contains("TERCA")){ref.child("terca/14").setValue(estado);  }
                                if(name.toString().contains("15:") && name.contains("TERCA")){ref.child("terca/15").setValue(estado);  }
                                if(name.toString().contains("16:") && name.contains("TERCA")){ref.child("terca/16").setValue(estado);  }
                                if(name.toString().contains("17:") && name.contains("TERCA")){ref.child("terca/17").setValue(estado);  }
                                if(name.toString().contains("18:") && name.contains("TERCA")){ref.child("terca/18").setValue(estado);  }
                                if(name.toString().contains("08:") && name.contains("QUARTA")){ref.child("quarta/8").setValue(estado);  }
                                if(name.toString().contains("09:") && name.contains("QUARTA")){ref.child("quarta/9").setValue(estado);  }
                                if(name.toString().contains("10:") && name.contains("QUARTA")){ref.child("quarta/10").setValue(estado);  }
                                if(name.toString().contains("11:") && name.contains("QUARTA")){ref.child("quarta/11").setValue(estado);  }
                                if(name.toString().contains("12:") && name.contains("QUARTA")){ref.child("quarta/12").setValue(estado);  }
                                if(name.toString().contains("13:") && name.contains("QUARTA")){ref.child("quarta/13").setValue(estado);  }
                                if(name.toString().contains("14:") && name.contains("QUARTA")){ref.child("quarta/14").setValue(estado);  }
                                if(name.toString().contains("15:") && name.contains("QUARTA")){ref.child("quarta/15").setValue(estado);  }
                                if(name.toString().contains("16:") && name.contains("QUARTA")){ref.child("quarta/16").setValue(estado);  }
                                if(name.toString().contains("17:") && name.contains("QUARTA")){ref.child("quarta/17").setValue(estado);  }
                                if(name.toString().contains("18:") && name.contains("QUARTA")){ref.child("quarta/18").setValue(estado);  }
                                if(name.toString().contains("08:") && name.contains("QUINTA")){ref.child("quinta/8").setValue(estado);  }
                                if(name.toString().contains("09:") && name.contains("QUINTA")){ref.child("quinta/9").setValue(estado);  }
                                if(name.toString().contains("10:") && name.contains("QUINTA")){ref.child("quinta/10").setValue(estado);  }
                                if(name.toString().contains("11:") && name.contains("QUINTA")){ref.child("quinta/11").setValue(estado);  }
                                if(name.toString().contains("12:") && name.contains("QUINTA")){ref.child("quinta/12").setValue(estado);  }
                                if(name.toString().contains("13:") && name.contains("QUINTA")){ref.child("quinta/13").setValue(estado);  }
                                if(name.toString().contains("14:") && name.contains("QUINTA")){ref.child("quinta/14").setValue(estado);  }
                                if(name.toString().contains("15:") && name.contains("QUINTA")){ref.child("quinta/15").setValue(estado);  }
                                if(name.toString().contains("16:") && name.contains("QUINTA")){ref.child("quinta/16").setValue(estado);  }
                                if(name.toString().contains("17:") && name.contains("QUINTA")){ref.child("quinta/17").setValue(estado);  }
                                if(name.toString().contains("18:") && name.contains("QUINTA")){ref.child("quinta/18").setValue(estado);  }
                                if(name.toString().contains("08:") && name.contains("SEXTA")){ref.child("sexta/8").setValue(estado);  }
                                if(name.toString().contains("09:") && name.contains("SEXTA")){ref.child("sexta/9").setValue(estado);  }
                                if(name.toString().contains("10:") && name.contains("SEXTA")){ref.child("sexta/10").setValue(estado);  }
                                if(name.toString().contains("11:") && name.contains("SEXTA")){ref.child("sexta/11").setValue(estado);  }
                                if(name.toString().contains("12:") && name.contains("SEXTA")){ref.child("sexta/12").setValue(estado);  }
                                if(name.toString().contains("13:") && name.contains("SEXTA")){ref.child("sexta/13").setValue(estado);  }
                                if(name.toString().contains("14:") && name.contains("SEXTA")){ref.child("sexta/14").setValue(estado);  }
                                if(name.toString().contains("15:") && name.contains("SEXTA")){ref.child("sexta/15").setValue(estado);  }
                                if(name.toString().contains("16:") && name.contains("SEXTA")){ref.child("sexta/16").setValue(estado);  }
                                if(name.toString().contains("17:") && name.contains("SEXTA")){ref.child("sexta/17").setValue(estado);  }
                                if(name.toString().contains("18:") && name.contains("SEXTA")){ref.child("sexta/18").setValue(estado);  }
                                if(name.toString().contains("08:") && name.contains("SABADO")){ref.child("sabado/8").setValue(estado);  }
                                if(name.toString().contains("09:") && name.contains("SABADO")){ref.child("sabado/9").setValue(estado);  }
                                if(name.toString().contains("10:") && name.contains("SABADO")){ref.child("sabado/10").setValue(estado);  }
                                if(name.toString().contains("11:") && name.contains("SABADO")){ref.child("sabado/11").setValue(estado);  }
                                if(name.toString().contains("12:") && name.contains("SABADO")){ref.child("sabado/12").setValue(estado);  }
                                if(name.toString().contains("13:") && name.contains("SABADO")){ref.child("sabado/13").setValue(estado);  }
                                if(name.toString().contains("14:") && name.contains("SABADO")){ref.child("sabado/14").setValue(estado);  }
                                if(name.toString().contains("15:") && name.contains("SABADO")){ref.child("sabado/15").setValue(estado);  }
                                if(name.toString().contains("16:") && name.contains("SABADO")){ref.child("sabado/16").setValue(estado);  }
                                if(name.toString().contains("17:") && name.contains("SABADO")){ref.child("sabado/17").setValue(estado);  }
                                if(name.toString().contains("18:") && name.contains("SABADO")){ref.child("sabado/18").setValue(estado);  }

                                ((TextView) view).setBackgroundColor(RED);
                                Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_SHORT).show();
                                databaseHelper.remove(name);
                            }
                            else{
                                    Toast.makeText(getApplicationContext(), "Não foi possível cancelar!(Data atual superior a data agendada.)", Toast.LENGTH_LONG).show();
                                }

                            }

                            }).setNegativeButton("NAO", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {


                                    Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                }
                            });
                        alertDialog.show();

                    }
                });
            }
}