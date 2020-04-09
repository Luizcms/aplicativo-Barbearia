package com.example.barbearia3

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main3.*
import java.text.SimpleDateFormat
import java.util.*


class Main3Activity : AppCompatActivity() {


    internal lateinit var objetoRef: FirebaseDatabase;
    internal lateinit var ref: DatabaseReference;


    internal lateinit var nome: EditText
    internal lateinit var telef: EditText
    internal lateinit var dia: TextView
    internal lateinit var hora: TextView
    internal lateinit var msg: EditText
    internal lateinit var endereco: Button
    internal lateinit var contato: Button
    internal lateinit var meuTexto: TextView;
    internal lateinit var enviar: Button
    internal lateinit var servico: TextView


    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)


        var data = findViewById(R.id.data) as TextView;
        nome = findViewById(R.id.nome) as EditText
        telef = findViewById(R.id.telefone) as EditText
        servico = findViewById(R.id.servico) as TextView
        dia = findViewById(R.id.dia) as TextView
        hora = findViewById(R.id.hora) as TextView
        msg = findViewById(R.id.msg) as EditText
        enviar = findViewById<View>(R.id.enviar) as Button

        var segunda = findViewById<View>(R.id.segunda) as TextView
        var terca = findViewById<View>(R.id.terca) as TextView
        var quarta = findViewById<View>(R.id.quarta) as TextView
        var quinta = findViewById<View>(R.id.quinta) as TextView
        var sexta = findViewById<View>(R.id.sexta) as TextView
        var sabado = findViewById<View>(R.id.sabado) as TextView

//transforma radiogroup todos
        val group = RadioGroupHelper(
            this,
            R.id.txt0,
            R.id.txt01,
            R.id.txt02,
            R.id.txt03,
            R.id.txt04,
            R.id.txt05,
            R.id.txt06,
            R.id.txt1,
            R.id.txt2,
            R.id.txt3,
            R.id.txt4,
            R.id.txt5,
            R.id.txt6,
            R.id.txt7,
            R.id.txt8,
            R.id.txt9,
            R.id.txt10,
            R.id.txt11,
            R.id.txt12,
            R.id.txt13,
            R.id.txt14,
            R.id.txt15,
            R.id.txt16,
            R.id.txt17,
            R.id.txt18,
            R.id.txt19,
            R.id.txt20,
            R.id.txt21,
            R.id.txt22,
            R.id.txt23,
            R.id.txt24,
            R.id.txt25,
            R.id.txt26,
            R.id.txt27,
            R.id.txt28,
            R.id.txt29,
            R.id.txt30,
            R.id.txt31,
            R.id.txt32,
            R.id.txt33,
            R.id.txt34,
            R.id.txt35,
            R.id.txt36,
            R.id.txt37,
            R.id.txt38,
            R.id.txt39,
            R.id.txt40,
            R.id.txt41,
            R.id.txt42,
            R.id.txt43,
            R.id.txt44,
            R.id.txt45,
            R.id.txt46,
            R.id.txt47,
            R.id.txt48,
            R.id.txt49,
            R.id.txt50,
            R.id.txt51,
            R.id.txt52,
            R.id.txt53,
            R.id.txt54,
            R.id.txt55,
            R.id.txt56,
            R.id.txt57,
            R.id.txt58,
            R.id.txt59,
            R.id.txt60
        )
        group.radioButtons[0].performClick()

// tabhost
        val mTabHost = findViewById(R.id.tabHost) as TabHost
        mTabHost.setup()

        var mSpec: TabHost.TabSpec = mTabHost.newTabSpec("t1")
        mSpec.setContent(R.id.group)
        mSpec.setIndicator("seg")
        mTabHost.addTab(mSpec)


        mSpec = mTabHost.newTabSpec("t2")
        mSpec.setContent(R.id.group2)
        mSpec.setIndicator("ter")
        mTabHost.addTab(mSpec)


        mSpec = mTabHost.newTabSpec("t3")
        mSpec.setContent(R.id.group3)
        mSpec.setIndicator("qua")
        mTabHost.addTab(mSpec)


        mSpec = mTabHost.newTabSpec("t4")
        mSpec.setContent(R.id.group4)
        mSpec.setIndicator("qui")
        mTabHost.addTab(mSpec)

        mSpec = mTabHost.newTabSpec("t5")
        mSpec.setContent(R.id.group5)
        mSpec.setIndicator("sex")
        mTabHost.addTab(mSpec)

        mSpec = mTabHost.newTabSpec("t6")
        mSpec.setContent(R.id.group6)
        mSpec.setIndicator("sab")
        mTabHost.addTab(mSpec)

        // tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);

        val tw = mTabHost.findViewById<View>(android.R.id.tabs) as TabWidget
        for (i in 0 until tw.childCount) {
            val tabView = tw.getChildTabViewAt(i)
            val tv = tabView.findViewById<View>(android.R.id.title) as TextView
            tv.textSize = 10f
            tabView.setBackgroundResource(R.drawable.cor_tabhost)
        }


        // valores vindo do activ servicos
        val extras = intent.extras
        val a = extras!!.getString("qString")
        val b = extras!!.getString("profis")
        servico.setText(a + "\n" + b)

        // recebe mensagem do firebase
        objetoRef = FirebaseConfig.getDatabase()

        ref = objetoRef.getReference("segunda/8")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem1 = dataSnapshot.getValue(String::class.java)

                if (txt01 != null) {
                    txt01.setText(mensagem1)
                }
                if (txt01!!.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt01!!.getText().toString().equals("indisponivel")
                ) {
                    txt01.setBackgroundResource(R.drawable.btn)
                    txt01.isEnabled = false

                } else
                    if (txt01.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt01.isEnabled = true
                        txt01.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })

        ref = objetoRef.getReference("segunda/9")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var mensagem = dataSnapshot.getValue(String::class.java)

                if (txt1 != null) {
                    txt1.setText(mensagem)
                }
                if (txt1 != null) {
                    if (txt1.getText().toString().contains(
                            "reservado",
                            ignoreCase = true
                        ) or txt1!!.getText().toString().equals("indisponivel")
                    ) {
                        txt1.setBackgroundResource(R.drawable.btn)
                        txt1.isEnabled = false
                    } else
                        if (txt1.getText().toString().equals("disponivel", ignoreCase = true)) {
                            txt1.isEnabled = true
                            txt1.setBackgroundResource(R.drawable.botao)
                        }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        ref = objetoRef.getReference("segunda/10")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                if (txt7 != null) {
                    txt7.setText(mensagem)
                }
                if (txt7!!.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt7!!.getText().toString().equals("indisponivel")
                ) {
                    txt7.setBackgroundResource(R.drawable.btn)
                    txt7.isEnabled = false
                } else
                    if (txt7.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt7.isEnabled = true
                        txt7.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("segunda/11")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                if (txt13 != null) {
                    txt13.setText(mensagem)
                }
                if (txt13!!.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt13!!.getText().toString().equals("indisponivel")
                ) {
                    txt13.setBackgroundResource(R.drawable.btn)
                    txt13.isEnabled = false
                } else
                    if (txt13.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt13.isEnabled = true
                        txt13.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("segunda/12")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                if (txt19 != null) {
                    txt19.setText(mensagem)
                }
                if (txt19!!.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt19!!.getText().toString().equals("indisponivel")
                ) {
                    txt19.setBackgroundResource(R.drawable.btn)
                    txt19.isEnabled = false
                } else
                    if (txt19.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt19.isEnabled = true
                        txt19.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("segunda/13")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                if (txt25 != null) {
                    txt25.setText(mensagem)
                }
                if (txt25.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt25!!.getText().toString().equals("indisponivel")
                ) {
                    txt25.setBackgroundResource(R.drawable.btn)
                    txt25.isEnabled = false
                } else
                    if (txt25.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt25.isEnabled = true
                        txt25.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("segunda/14")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                if (txt31 != null) {
                    txt31.setText(mensagem)
                }
                if (txt31!!.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt31!!.getText().toString().equals("indisponivel")
                ) {
                    txt31.setBackgroundResource(R.drawable.btn)
                    txt31.isEnabled = false
                } else
                    if (txt31.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt31.isEnabled = true
                        txt31.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("segunda/15")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                if (txt37 != null) {
                    txt37.setText(mensagem)
                }
                if (txt37!!.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt37!!.getText().toString().equals("indisponivel")
                ) {
                    txt37.setBackgroundResource(R.drawable.btn)
                    txt37.isEnabled = false
                } else
                    if (txt37.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt37.isEnabled = true
                        txt37.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("segunda/16")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                if (txt43 != null) {
                    txt43.setText(mensagem)
                }
                if (txt43!!.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt43!!.getText().toString().equals("indisponivel")
                ) {
                    txt43.setBackgroundResource(R.drawable.btn)
                    txt43.isEnabled = false
                } else
                    if (txt43.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt43.isEnabled = true
                        txt43.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("segunda/17")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                if (txt49 != null) {
                    txt49.setText(mensagem)
                }
                if (txt49!!.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt49!!.getText().toString().equals("indisponivel")
                ) {
                    txt49.setBackgroundResource(R.drawable.btn)
                    txt49.isEnabled = false
                } else
                    if (txt49.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt49.isEnabled = true
                        txt49.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("segunda/18")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                if (txt55 != null) {
                    txt55.setText(mensagem)
                }
                if (txt55.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt55!!.getText().toString().equals("indisponivel")
                ) {
                    txt55.setBackgroundResource(R.drawable.btn)
                    txt55.isEnabled = false
                } else
                    if (txt55.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt55.isEnabled = true
                        txt55.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("terca/8")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt02.setText(mensagem)
                if (txt02.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt02!!.getText().toString().equals("indisponivel")
                ) {
                    txt02.setBackgroundResource(R.drawable.btn)
                    txt02.isEnabled = false
                } else
                    if (txt02.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt02.isEnabled = true
                        txt02.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("terca/9")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt2.setText(mensagem)
                if (txt2.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt2!!.getText().toString().equals("indisponivel")
                ) {
                    txt2.setBackgroundResource(R.drawable.btn)
                    txt2.isEnabled = false
                } else
                    if (txt2.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt2.isEnabled = true
                        txt2.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("terca/10")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt8.setText(mensagem)
                if (txt8.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt8!!.getText().toString().equals("indisponivel")
                ) {
                    txt8.setBackgroundResource(R.drawable.btn)
                    txt8.isEnabled = false
                } else
                    if (txt8.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt8.isEnabled = true
                        txt8.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("terca/11")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt14.setText(mensagem)
                if (txt14.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt14!!.getText().toString().equals("indisponivel")
                ) {
                    txt14.setBackgroundResource(R.drawable.btn)
                    txt14.isEnabled = false
                } else
                    if (txt14.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt14.isEnabled = true
                        txt14.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("terca/12")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt20.setText(mensagem)
                if (txt20.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt20!!.getText().toString().equals("indisponivel")
                ) {
                    txt20.setBackgroundResource(R.drawable.btn)
                    txt20.isEnabled = false
                } else
                    if (txt20.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt20.isEnabled = true
                        txt20.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("terca/13")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt26.setText(mensagem)
                if (txt26.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt26!!.getText().toString().equals("indisponivel")
                ) {
                    txt26.setBackgroundResource(R.drawable.btn)
                    txt26.isEnabled = false
                } else
                    if (txt26.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt26.isEnabled = true
                        txt26.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("terca/14")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt32.setText(mensagem)
                if (txt32.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt32!!.getText().toString().equals("indisponivel")
                ) {
                    txt32.setBackgroundResource(R.drawable.btn)
                    txt32.isEnabled = false
                } else
                    if (txt32.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt32.isEnabled = true
                        txt32.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("terca/15")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt38.setText(mensagem)
                if (txt38.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt38!!.getText().toString().equals("indisponivel")
                ) {
                    txt38.setBackgroundResource(R.drawable.btn)
                    txt38.isEnabled = false
                } else
                    if (txt38.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt38.isEnabled = true
                        txt38.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("terca/16")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt44.setText(mensagem)
                if (txt44.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt44!!.getText().toString().equals("indisponivel")
                ) {
                    txt44.setBackgroundResource(R.drawable.btn)
                    txt44.isEnabled = false
                } else
                    if (txt44.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt44.isEnabled = true
                        txt44.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("terca/17")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt50.setText(mensagem)
                if (txt50.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt50!!.getText().toString().equals("indisponivel")
                ) {
                    txt50.setBackgroundResource(R.drawable.btn)
                    txt50.isEnabled = false
                } else
                    if (txt50.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt50.isEnabled = true
                        txt50.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("terca/18")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt56.setText(mensagem)
                if (txt56.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt56!!.getText().toString().equals("indisponivel")
                ) {
                    txt56.setBackgroundResource(R.drawable.btn)
                    txt56.isEnabled = false
                } else
                    if (txt56.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt56.isEnabled = true
                        txt56.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("quarta/8")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt03.setText(mensagem)
                if (txt03.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt03!!.getText().toString().equals("indisponivel")
                ) {
                    txt03.setBackgroundResource(R.drawable.btn)
                    txt03.isEnabled = false
                } else
                    if (txt03.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt03.isEnabled = true
                        txt03.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("quarta/9")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt3.setText(mensagem)
                if (txt3.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt3!!.getText().toString().equals("indisponivel")
                ) {
                    txt3.setBackgroundResource(R.drawable.btn)
                    txt3.isEnabled = false
                } else
                    if (txt3.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt3.isEnabled = true
                        txt3.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("quarta/10")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt9.setText(mensagem)
                if (txt9.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt9!!.getText().toString().equals("indisponivel")
                ) {
                    txt9.setBackgroundResource(R.drawable.btn)
                    txt9.isEnabled = false
                } else
                    if (txt9.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt9.isEnabled = true
                        txt9.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("quarta/11")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt15.setText(mensagem)
                if (txt15.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt15!!.getText().toString().equals("indisponivel")
                ) {
                    txt15.setBackgroundResource(R.drawable.btn)
                    txt15.isEnabled = false
                } else
                    if (txt15.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt15.isEnabled = true
                        txt15.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("quarta/12")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt21.setText(mensagem)
                if (txt21.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt21!!.getText().toString().equals("indisponivel")
                ) {
                    txt21.setBackgroundResource(R.drawable.btn)
                    txt21.isEnabled = false
                } else
                    if (txt21.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt21.isEnabled = true
                        txt21.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("quarta/13")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt27.setText(mensagem)
                if (txt27.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt27!!.getText().toString().equals("indisponivel")
                ) {
                    txt27.setBackgroundResource(R.drawable.btn)
                    txt27.isEnabled = false
                } else
                    if (txt27.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt27.isEnabled = true
                        txt27.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("quarta/14")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt33.setText(mensagem)
                if (txt33.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt33!!.getText().toString().equals("indisponivel")
                ) {
                    txt33.setBackgroundResource(R.drawable.btn)
                    txt33.isEnabled = false
                } else
                    if (txt33.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt33.isEnabled = true
                        txt33.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("quarta/15")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt39.setText(mensagem)
                if (txt39.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt39!!.getText().toString().equals("indisponivel")
                ) {
                    txt39.setBackgroundResource(R.drawable.btn)
                    txt39.isEnabled = false
                } else
                    if (txt39.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt39.isEnabled = true
                        txt39.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("quarta/16")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt45.setText(mensagem)
                if (txt45.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt45!!.getText().toString().equals("indisponivel")
                ) {
                    txt45.setBackgroundResource(R.drawable.btn)
                    txt45.isEnabled = false
                } else
                    if (txt45.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt45.isEnabled = true
                        txt45.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("quarta/17")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt51.setText(mensagem)
                if (txt51.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt51!!.getText().toString().equals("indisponivel")
                ) {
                    txt51.setBackgroundResource(R.drawable.btn)
                    txt51.isEnabled = false
                } else
                    if (txt51.getText().toString().contains("disponivel", ignoreCase = true)) {
                        txt51.isEnabled = true
                        txt51.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("quarta/18")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt57.setText(mensagem)
                if (txt57.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt57!!.getText().toString().equals("indisponivel")
                ) {
                    txt57.setBackgroundResource(R.drawable.btn)
                    txt57.isEnabled = false
                } else
                    if (txt57.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt57.isEnabled = true
                        txt57.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("quinta/8")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt04.setText(mensagem)
                if (txt04.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt04!!.getText().toString().equals("indisponivel")
                ) {
                    txt04.setBackgroundResource(R.drawable.btn)
                    txt04.isEnabled = false
                } else
                    if (txt04.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt04.isEnabled = true
                        txt04.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("quinta/9")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt4.setText(mensagem)
                if (txt4.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt4!!.getText().toString().equals("indisponivel")
                ) {
                    txt4.setBackgroundResource(R.drawable.btn)
                    txt4.isEnabled = false
                } else
                    if (txt4.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt4.isEnabled = true
                        txt4.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("quinta/10")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt10.setText(mensagem)
                if (txt10.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt10!!.getText().toString().equals("indisponivel")
                ) {
                    txt10.setBackgroundResource(R.drawable.btn)
                    txt10.isEnabled = false
                } else
                    if (txt10.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt10.isEnabled = true
                        txt10.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("quinta/11")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt16.setText(mensagem)
                if (txt16.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt16!!.getText().toString().equals("indisponivel")
                ) {
                    txt16.setBackgroundResource(R.drawable.btn)
                    txt16.isEnabled = false
                } else
                    if (txt16.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt16.isEnabled = true
                        txt16.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("quinta/12")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt22.setText(mensagem)
                if (txt22.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt22!!.getText().toString().equals("indisponivel")
                ) {
                    txt22.setBackgroundResource(R.drawable.btn)
                    txt22.isEnabled = false
                } else
                    if (txt22.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt22.isEnabled = true
                        txt22.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("quinta/13")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt28.setText(mensagem)
                if (txt28.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt28!!.getText().toString().equals("indisponivel")
                ) {
                    txt28.setBackgroundResource(R.drawable.btn)
                    txt28.isEnabled = false
                } else
                    if (txt28.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt28.isEnabled = true
                        txt28.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("quinta/14")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt34.setText(mensagem)
                if (txt34.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt34!!.getText().toString().equals("indisponivel")
                ) {
                    txt34.setBackgroundResource(R.drawable.btn)
                    txt34.isEnabled = false
                } else
                    if (txt34.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt34.isEnabled = true
                        txt34.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("quinta/15")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt40.setText(mensagem)
                if (txt40.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt40!!.getText().toString().equals("indisponivel")
                ) {
                    txt40.setBackgroundResource(R.drawable.btn)
                    txt40.isEnabled = false
                } else
                    if (txt40.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt40.isEnabled = true
                        txt40.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("quinta/16")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt46.setText(mensagem)
                if (txt46.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt46!!.getText().toString().equals("indisponivel")
                ) {
                    txt46.setBackgroundResource(R.drawable.btn)
                    txt46.isEnabled = false
                } else
                    if (txt46.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt46.isEnabled = true
                        txt46.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("quinta/17")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt52.setText(mensagem)
                if (txt52.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt52!!.getText().toString().equals("indisponivel")
                ) {
                    txt52.setBackgroundResource(R.drawable.btn)
                    txt52.isEnabled = false
                } else
                    if (txt52.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt52.isEnabled = true
                        txt52.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("quinta/18")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt58.setText(mensagem)
                if (txt58.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt58!!.getText().toString().equals("indisponivel")
                ) {
                    txt58.setBackgroundResource(R.drawable.btn)
                    txt58.isEnabled = false
                } else
                    if (txt58.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt58.isEnabled = true
                        txt58.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("sexta/8")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt05.setText(mensagem)
                if (txt05.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt05!!.getText().toString().equals("indisponivel")
                ) {
                    txt05.setBackgroundResource(R.drawable.btn)
                    txt05.isEnabled = false
                } else
                    if (txt05.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt05.isEnabled = true
                        txt05.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("sexta/9")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt5.setText(mensagem)
                if (txt5.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt5!!.getText().toString().equals("indisponivel")
                ) {
                    txt5.setBackgroundResource(R.drawable.btn)
                    txt5.isEnabled = false
                } else
                    if (txt5.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt5.isEnabled = true
                        txt5.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("sexta/10")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt11.setText(mensagem)
                if (txt11.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt11!!.getText().toString().equals("indisponivel")
                ) {
                    txt11.setBackgroundResource(R.drawable.btn)
                    txt11.isEnabled = false
                } else
                    if (txt11.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt11.isEnabled = true
                        txt11.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("sexta/11")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt17.setText(mensagem)
                if (txt17.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt17!!.getText().toString().equals("indisponivel")
                ) {
                    txt17.setBackgroundResource(R.drawable.btn)
                    txt17.isEnabled = false
                } else
                    if (txt17.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt17.isEnabled = true
                        txt17.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("sexta/12")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt23.setText(mensagem)
                if (txt23.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt23!!.getText().toString().equals("indisponivel")
                ) {
                    txt23.setBackgroundResource(R.drawable.btn)
                    txt23.isEnabled = false
                } else
                    if (txt23.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt23.isEnabled = true
                        txt23.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("sexta/13")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt29.setText(mensagem)
                if (txt29.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt29!!.getText().toString().equals("indisponivel")
                ) {
                    txt29.setBackgroundResource(R.drawable.btn)
                    txt29.isEnabled = false
                } else
                    if (txt29.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt29.isEnabled = true
                        txt29.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("sexta/14")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt35.setText(mensagem)
                if (txt35.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt35!!.getText().toString().equals("indisponivel")
                ) {
                    txt35.setBackgroundResource(R.drawable.btn)
                    txt35.isEnabled = false
                } else
                    if (txt35.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt35.isEnabled = true
                        txt35.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("sexta/15")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt41.setText(mensagem)
                if (txt41.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt41!!.getText().toString().equals("indisponivel")
                ) {
                    txt41.setBackgroundResource(R.drawable.btn)
                    txt41.isEnabled = false
                } else
                    if (txt41.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt41.isEnabled = true
                        txt41.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("sexta/16")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt47.setText(mensagem)
                if (txt47.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt47!!.getText().toString().equals("indisponivel")
                ) {
                    txt47.setBackgroundResource(R.drawable.btn)
                    txt47.isEnabled = false
                } else
                    if (txt47.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt47.isEnabled = true
                        txt47.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("sexta/17")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt53.setText(mensagem)
                if (txt53.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt53!!.getText().toString().equals("indisponivel")
                ) {
                    txt53.setBackgroundResource(R.drawable.btn)
                    txt53.isEnabled = false
                } else
                    if (txt53.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt53.isEnabled = true
                        txt53.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("sexta/18")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt59.setText(mensagem)
                if (txt59.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt59!!.getText().toString().equals("indisponivel")
                ) {
                    txt59.setBackgroundResource(R.drawable.btn)
                    txt59.isEnabled = false
                } else
                    if (txt59.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt59.isEnabled = true
                        txt59.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("sabado/8")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt06.setText(mensagem)
                if (txt06.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt06!!.getText().toString().equals("indisponivel")
                ) {
                    txt06.setBackgroundResource(R.drawable.btn)
                    txt06.isEnabled = false
                } else
                    if (txt06.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt06.isEnabled = true
                        txt06.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("sabado/9")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt6.setText(mensagem)
                if (txt6.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt6!!.getText().toString().equals("indisponivel")
                ) {
                    txt6.setBackgroundResource(R.drawable.btn)
                    txt6.isEnabled = false
                } else
                    if (txt6.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt6.isEnabled = true
                        txt6.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("sabado/10")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt12.setText(mensagem)
                if (txt12.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt12!!.getText().toString().equals("indisponivel")
                ) {
                    txt12.setBackgroundResource(R.drawable.btn)
                    txt12.isEnabled = false
                } else
                    if (txt12.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt12.isEnabled = true
                        txt12.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("sabado/11")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt18.setText(mensagem)
                if (txt18.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt18!!.getText().toString().equals("indisponivel")
                ) {
                    txt18.setBackgroundResource(R.drawable.btn)
                    txt18.isEnabled = false
                } else
                    if (txt18.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt18.isEnabled = true
                        txt18.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("sabado/12")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt24.setText(mensagem)
                if (txt24.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt24!!.getText().toString().equals("indisponivel")
                ) {
                    txt24.setBackgroundResource(R.drawable.btn)
                    txt24.isEnabled = false
                } else
                    if (txt24.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt24.isEnabled = true
                        txt24.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        ref = objetoRef.getReference("sabado/13")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt30.setText(mensagem)
                if (txt30.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt30!!.getText().toString().equals("indisponivel")
                ) {
                    txt30.setBackgroundResource(R.drawable.btn)
                    txt30.isEnabled = false
                } else
                    if (txt30.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt30.isEnabled = true
                        txt30.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("sabado/14")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt36.setText(mensagem)
                if (txt36.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt36!!.getText().toString().equals("indisponivel")
                ) {
                    txt36.setBackgroundResource(R.drawable.btn)
                    txt36.isEnabled = false
                } else
                    if (txt36.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt36.isEnabled = true
                        txt36.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("sabado/15")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt42.setText(mensagem)

                if (txt42.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt42!!.getText().toString().equals("indisponivel")
                ) {
                    txt42.setBackgroundResource(R.drawable.btn)
                    txt42.isEnabled = false
                } else
                    if (txt42.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt42.isEnabled = true
                        txt42.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("sabado/16")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt48.setText(mensagem)
                if (txt48.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt48!!.getText().toString().equals("indisponivel")
                ) {
                    txt48.setBackgroundResource(R.drawable.btn)
                    txt48.isEnabled = false
                } else
                    if (txt48.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt48.isEnabled = true
                        txt48.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("sabado/17")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt54.setText(mensagem)
                if (txt54.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt54!!.getText().toString().equals("indisponivel")
                ) {
                    txt54.setBackgroundResource(R.drawable.btn)
                    txt54.isEnabled = false
                } else
                    if (txt54.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt54.isEnabled = true
                        txt54.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("sabado/18")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                txt60.setText(mensagem)
                if (txt60.getText().toString().equals(
                        "reservado",
                        ignoreCase = true
                    ) or txt60!!.getText().toString().equals("indisponivel")
                ) {
                    txt60.setBackgroundResource(R.drawable.btn)
                    txt60.isEnabled = false
                } else
                    if (txt60.getText().toString().equals("disponivel", ignoreCase = true)) {
                        txt60.isEnabled = true
                        txt60.setBackgroundResource(R.drawable.botao)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        //botoes

        var a01 = "";
        var b01 = "";
        var b02 = "";
        var b03 = "";
        var b04 = "";
        var b05 = ""
        var b1 = "";
        var b2 = "";
        var b3 = "";
        var b4 = "";
        var b5 = "";
        var b6 = ""
        var b7 = "";
        var b8 = "";
        var b9 = "";
        var b10 = "";
        var b11 = "";
        var b12 = ""
        var b13 = "";
        var b14 = "";
        var b15 = "";
        var b16 = "";
        var b17 = "";
        var b18 = ""
        var b19 = "";
        var b20 = "";
        var b21 = "";
        var b22 = "";
        var b23 = "";
        var b24 = ""
        var b25 = "";
        var b26 = "";
        var b27 = "";
        var b28 = "";
        var b29 = "";
        var b30 = ""
        var b31 = "";
        var b32 = "";
        var b33 = "";
        var b34 = "";
        var b35 = "";
        var b36 = ""
        var b37 = "";
        var b38 = "";
        var b39 = "";
        var b40 = "";
        var b41 = "";
        var b42 = ""
        var b43 = "";
        var b44 = "";
        var b45 = "";
        var b46 = "";
        var b47 = "";
        var b48 = ""
        var b49 = "";
        var b50 = "";
        var b51 = "";
        var b52 = "";
        var b53 = "";
        var b54 = ""
        var b55 = "";
        var b56 = "";
        var b57 = "";
        var b58 = "";
        var b59 = "";
        var b60 = ""

        txt01.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt01.setBackgroundResource(R.drawable.radio_b_pers)
                a01 = "reservado"
                var seg = segunda.getText().toString()
                dia.setText(seg)
                data.setText("SEGUNDA")
                hora.setText("08:00")
            } else {
                a01 = "disponivel";txt01.setBackgroundResource(R.drawable.botao)
            }
        })
        txt02.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt02.setBackgroundResource(R.drawable.radio_b_pers)
                b01 = "reservado"
                var ter = terca.getText().toString()
                dia.setText(ter)
                data.setText("TERCA")
                hora.setText("08:00")
            } else {
                b01 = "disponivel";txt02.setBackgroundResource(R.drawable.botao)
            }
        })
        txt03.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt03.setBackgroundResource(R.drawable.radio_b_pers)
                b02 = "reservado"
                var qua = quarta.getText().toString()
                dia.setText(qua)
                data.setText("QUARTA")
                hora.setText("08:00")
            } else {
                b02 = "disponivel";txt03.setBackgroundResource(R.drawable.botao)
            }
        })
        txt04.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt04.setBackgroundResource(R.drawable.radio_b_pers)
                b03 = "reservado"
                var qui = quinta.getText().toString()
                dia.setText(qui)
                data.setText("QUINTA")
                hora.setText("08:00")
            } else {
                b03 = "disponivel";txt04.setBackgroundResource(R.drawable.botao)
            }
        })
        txt05.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt05.setBackgroundResource(R.drawable.radio_b_pers)
                b04 = "reservado"
                var sex = sexta.getText().toString()
                dia.setText(sex)
                data.setText("SEXTA")
                hora.setText("08:00")
            } else {
                b04 = "disponivel";txt05.setBackgroundResource(R.drawable.botao)
            }
        })
        txt06.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt06.setBackgroundResource(R.drawable.radio_b_pers)
                b05 = "reservado"
                var sab = sabado.getText().toString()
                dia.setText(sab)
                data.setText("SABADO")
                hora.setText("08:00")
            } else {
                b05 = "disponivel";txt06.setBackgroundResource(R.drawable.botao)
            }
        })
        txt1.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt1.setBackgroundResource(R.drawable.radio_b_pers)
                b1 = "reservado"
                var seg = segunda.getText().toString()
                dia.setText(seg)
                data.setText("SEGUNDA")
                hora.setText("09:00")
            } else {
                b1 = "disponivel";txt1.setBackgroundResource(R.drawable.botao)
            }
        })
        txt2.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt2.setBackgroundResource(R.drawable.radio_b_pers)
                b2 = "reservado"
                var ter = terca.getText().toString()
                dia.setText(ter)
                data.setText("TERCA")
                hora.setText("09:00")
            } else {
                b2 = "disponivel";txt2.setBackgroundResource(R.drawable.botao)
            }
        })
        txt3.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt3.setBackgroundResource(R.drawable.radio_b_pers)
                b3 = "reservado"
                var qua = quarta.getText().toString()
                dia.setText(qua)
                data.setText("QUARTA")
                hora.setText("09:00")
            } else {
                b3 = "disponivel";txt3.setBackgroundResource(R.drawable.botao)
            }
        })
        txt4.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt4.setBackgroundResource(R.drawable.radio_b_pers)
                b4 = "reservado"
                var qui = quinta.getText().toString()
                dia.setText(qui)
                data.setText("QUINTA")
                hora.setText("09:00")
            } else {
                b4 = "disponivel";txt4.setBackgroundResource(R.drawable.botao)
            }
        })
        txt5.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt5.setBackgroundResource(R.drawable.radio_b_pers)
                b5 = "reservado"
                var sex = sexta.getText().toString()
                dia.setText(sex)
                data.setText("SEXTA")
                hora.setText("09:00")
            } else {
                b5 = "disponivel";txt5.setBackgroundResource(R.drawable.botao)
            }
        })
        txt6.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt6.setBackgroundResource(R.drawable.radio_b_pers)
                b6 = "reservado"
                var sab = sabado.getText().toString()
                dia.setText(sab)
                data.setText("SABADO")
                hora.setText("09:00")
            } else {
                b6 = "disponivel";txt6.setBackgroundResource(R.drawable.botao)
            }
        })
        txt7.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt7.setBackgroundResource(R.drawable.radio_b_pers)
                b7 = "reservado"
                var seg = segunda.getText().toString()
                dia.setText(seg)
                data.setText("SEGUNDA")
                hora.setText("10:00")
            } else {
                b7 = "disponivel";txt7.setBackgroundResource(R.drawable.botao)
            }
        })
        txt8.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt8.setBackgroundResource(R.drawable.radio_b_pers)
                b8 = "reservado"
                var ter = terca.getText().toString()
                dia.setText(ter)
                data.setText("TERCA")
                hora.setText("10:00")
            } else {
                b8 = "disponivel";txt8.setBackgroundResource(R.drawable.botao)
            }
        })
        txt9.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt9.setBackgroundResource(R.drawable.radio_b_pers)
                b9 = "reservado"
                var qua = quarta.getText().toString()
                dia.setText(qua)
                data.setText("QUARTA")
                hora.setText("10:00")
            } else {
                b9 = "disponivel";txt9.setBackgroundResource(R.drawable.botao)
            }
        })
        txt10.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt10.setBackgroundResource(R.drawable.radio_b_pers)
                b10 = "reservado"
                var qui = quinta.getText().toString()
                dia.setText(qui)
                data.setText("QUINTA")
                hora.setText("10:00")
            } else {
                b10 = "disponivel";txt10.setBackgroundResource(R.drawable.botao)
            }
        })
        txt11.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt11.setBackgroundResource(R.drawable.radio_b_pers)
                b11 = "reservado"
                var sex = sexta.getText().toString()
                dia.setText(sex)
                data.setText("SEXTA")
                hora.setText("10:00")
            } else {
                b11 = "disponivel";txt11.setBackgroundResource(R.drawable.botao)
            }
        })
        txt12.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt12.setBackgroundResource(R.drawable.radio_b_pers)
                b12 = "reservado"
                var sab = sabado.getText().toString()
                dia.setText(sab)
                data.setText("SABADO")
                hora.setText("10:00")
            } else {
                b12 = "disponivel";txt12.setBackgroundResource(R.drawable.botao)
            }
        })
        txt13.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt13.setBackgroundResource(R.drawable.radio_b_pers)
                b13 = "reservado"
                var seg = segunda.getText().toString()
                dia.setText(seg)
                data.setText("SEGUNDA")
                hora.setText("11:00")
            } else {
                b13 = "disponivel";txt13.setBackgroundResource(R.drawable.botao)
            }
        })
        txt14.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt14.setBackgroundResource(R.drawable.radio_b_pers)
                b14 = "reservado"
                var ter = terca.getText().toString()
                dia.setText(ter)
                data.setText("TERCA")
                hora.setText("11:00")
            } else {
                b14 = "disponivel";txt14.setBackgroundResource(R.drawable.botao)
            }
        })
        txt15.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt15.setBackgroundResource(R.drawable.radio_b_pers)
                b15 = "reservado"
                var qua = quarta.getText().toString()
                dia.setText(qua)
                data.setText("QUARTA")
                hora.setText("11:00")
            } else {
                b15 = "disponivel";txt15.setBackgroundResource(R.drawable.botao)
            }
        })
        txt16.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt16.setBackgroundResource(R.drawable.radio_b_pers)
                b16 = "reservado"
                var qui = quinta.getText().toString()
                dia.setText(qui)
                data.setText("QUINTA")
                hora.setText("11:00")
            } else {
                b16 = "disponivel";txt16.setBackgroundResource(R.drawable.botao)
            }
        })
        txt17.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt17.setBackgroundResource(R.drawable.radio_b_pers)
                b17 = "reservado"
                var sex = sexta.getText().toString()
                dia.setText(sex)
                data.setText("SEXTA")
                hora.setText("11:00")
            } else {
                b17 = "disponivel";txt17.setBackgroundResource(R.drawable.botao)
            }
        })
        txt18.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt18.setBackgroundResource(R.drawable.radio_b_pers)
                b18 = "reservado"
                var sab = sabado.getText().toString()
                dia.setText(sab)
                data.setText("SABADO")
                hora.setText("11:00")
            } else {
                b18 = "disponivel";txt18.setBackgroundResource(R.drawable.botao)
            }
        })
        txt19.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt19.setBackgroundResource(R.drawable.radio_b_pers)
                b19 = "reservado"
                var seg = segunda.getText().toString()
                dia.setText(seg)
                data.setText("SEGUNDA")
                hora.setText("12:00")
            } else {
                b19 = "disponivel";txt19.setBackgroundResource(R.drawable.botao)
            }
        })
        txt20.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt20.setBackgroundResource(R.drawable.radio_b_pers)
                b20 = "reservado"
                var ter = terca.getText().toString()
                dia.setText(ter)
                data.setText("TERCA")
                hora.setText("12:00")
            } else {
                b20 = "disponivel";txt20.setBackgroundResource(R.drawable.botao)
            }
        })
        txt21.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt21.setBackgroundResource(R.drawable.radio_b_pers)
                b21 = "reservado"
                var qua = quarta.getText().toString()
                dia.setText(qua)
                data.setText("QUARTA")
                hora.setText("12:00")
            } else {
                b21 = "disponivel";txt21.setBackgroundResource(R.drawable.botao)
            }
        })
        txt22.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt22.setBackgroundResource(R.drawable.radio_b_pers)
                b22 = "reservado"
                var qui = quinta.getText().toString()
                dia.setText(qui)
                data.setText("QUINTA")
                hora.setText("12:00")
            } else {
                b22 = "disponivel";txt22.setBackgroundResource(R.drawable.botao)
            }
        })
        txt23.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt23.setBackgroundResource(R.drawable.radio_b_pers)
                b23 = "reservado"
                var sex = sexta.getText().toString()
                dia.setText(sex)
                data.setText("SEXTA")
                hora.setText("12:00")
            } else {
                b23 = "disponivel";txt23.setBackgroundResource(R.drawable.botao)
            }
        })
        txt24.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt24.setBackgroundResource(R.drawable.radio_b_pers)
                b24 = "reservado"
                var sab = sabado.getText().toString()
                dia.setText(sab)
                data.setText("SABADO")
                hora.setText("12:00")
            } else {
                b24 = "disponivel";txt24.setBackgroundResource(R.drawable.botao)
            }
        })
        txt25.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt25.setBackgroundResource(R.drawable.radio_b_pers)
                b25 = "reservado"
                var seg = segunda.getText().toString()
                dia.setText(seg)
                data.setText("SEGUNDA")
                hora.setText("13:00")
            } else {
                b25 = "disponivel";txt25.setBackgroundResource(R.drawable.botao)
            }
        })
        txt26.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt26.setBackgroundResource(R.drawable.radio_b_pers)
                b26 = "reservado"
                var ter = terca.getText().toString()
                dia.setText(ter)
                data.setText("TERCA")
                hora.setText("13:00")
            } else {
                b26 = "disponivel";txt26.setBackgroundResource(R.drawable.botao)
            }
        })
        txt27.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt27.setBackgroundResource(R.drawable.radio_b_pers)
                b27 = "reservado"
                var qua = quarta.getText().toString()
                dia.setText(qua)
                data.setText("QUARTA")
                hora.setText("13:00")
            } else {
                b27 = "disponivel";txt27.setBackgroundResource(R.drawable.botao)
            }
        })
        txt28.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt28.setBackgroundResource(R.drawable.radio_b_pers)
                b28 = "reservado"
                var qui = quinta.getText().toString()
                dia.setText(qui)
                data.setText("QUINTA")
                hora.setText("13:00")
            } else {
                b28 = "disponivel";txt28.setBackgroundResource(R.drawable.botao)
            }
        })
        txt29.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt29.setBackgroundResource(R.drawable.radio_b_pers)
                b29 = "reservado"
                var sex = sexta.getText().toString()
                dia.setText(sex)
                data.setText("SEXTA")
                hora.setText("13:00")
            } else {
                b29 = "disponivel";txt29.setBackgroundResource(R.drawable.botao)
            }
        })
        txt30.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt30.setBackgroundResource(R.drawable.radio_b_pers)
                b30 = "reservado"
                var sab = sabado.getText().toString()
                dia.setText(sab)
                data.setText("SABADO")
                hora.setText("13:00")
            } else {
                b30 = "disponivel";txt30.setBackgroundResource(R.drawable.botao)
            }
        })
        txt31.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt31.setBackgroundResource(R.drawable.radio_b_pers)
                b31 = "reservado"
                var seg = segunda.getText().toString()
                dia.setText(seg)
                data.setText("SEGUNDA")
                hora.setText("14:00")
            } else {
                b31 = "disponivel";txt31.setBackgroundResource(R.drawable.botao)
            }
        })
        txt32.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt32.setBackgroundResource(R.drawable.radio_b_pers)
                b32 = "reservado"
                var ter = terca.getText().toString()
                dia.setText(ter)
                data.setText("TERCA")
                hora.setText("14:00")
            } else {
                b32 = "disponivel";txt32.setBackgroundResource(R.drawable.botao)
            }
        })
        txt33.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt33.setBackgroundResource(R.drawable.radio_b_pers)
                b33 = "reservado"
                var qua = quarta.getText().toString()
                dia.setText(qua)
                data.setText("QUARTA")
                hora.setText("14:00")
            } else {
                b33 = "disponivel";txt33.setBackgroundResource(R.drawable.botao)
            }
        })
        txt34.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt34.setBackgroundResource(R.drawable.radio_b_pers)
                b34 = "reservado"
                var qui = quinta.getText().toString()
                dia.setText(qui)
                data.setText("QUINTA")
                hora.setText("14:00")
            } else {
                b34 = "disponivel";txt34.setBackgroundResource(R.drawable.botao)
            }
        })
        txt35.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt35.setBackgroundResource(R.drawable.radio_b_pers)
                b35 = "reservado"
                var sex = sexta.getText().toString()
                dia.setText(sex)
                data.setText("SEXTA")
                hora.setText("14:00")
            } else {
                b35 = "disponivel";txt35.setBackgroundResource(R.drawable.botao)
            }
        })
        txt36.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt36.setBackgroundResource(R.drawable.radio_b_pers)
                b36 = "reservado"
                var sab = sabado.getText().toString()
                dia.setText(sab)
                data.setText("SABADO")
                hora.setText("14:00")
            } else {
                b36 = "disponivel";txt36.setBackgroundResource(R.drawable.botao)
            }
        })
        txt37.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt37.setBackgroundResource(R.drawable.radio_b_pers)
                b37 = "reservado"
                var seg = segunda.getText().toString()
                dia.setText(seg)
                data.setText("SEGUNDA")
                hora.setText("15:00")
            } else {
                b37 = "disponivel";txt37.setBackgroundResource(R.drawable.botao)
            }
        })
        txt38.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt38.setBackgroundResource(R.drawable.radio_b_pers)
                b38 = "reservado"
                var ter = terca.getText().toString()
                dia.setText(ter)
                data.setText("TERCA")
                hora.setText("15:00")
            } else {
                b38 = "disponivel";txt38.setBackgroundResource(R.drawable.botao)
            }
        })
        txt39.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt39.setBackgroundResource(R.drawable.radio_b_pers)
                b39 = "reservado"
                var qua = quarta.getText().toString()
                dia.setText(qua)
                data.setText("QUARTA")
                hora.setText("15:00")
            } else {
                b39 = "disponivel";txt39.setBackgroundResource(R.drawable.botao)
            }
        })
        txt40.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt40.setBackgroundResource(R.drawable.radio_b_pers)
                b40 = "reservado"
                var qui = quinta.getText().toString()
                dia.setText(qui)
                data.setText("QUINTA")
                hora.setText("15:00")
            } else {
                b40 = "disponivel";txt40.setBackgroundResource(R.drawable.botao)
            }
        })
        txt41.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt41.setBackgroundResource(R.drawable.radio_b_pers)
                b41 = "reservado"
                var sex = sexta.getText().toString()
                dia.setText(sex)
                data.setText("SEXTA")
                hora.setText("15:00")
            } else {
                b41 = "disponivel";txt41.setBackgroundResource(R.drawable.botao)
            }
        })
        txt42.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt42.setBackgroundResource(R.drawable.radio_b_pers)
                b42 = "reservado"
                var sab = sabado.getText().toString()
                dia.setText(sab)
                data.setText("SABADO")
                hora.setText("15:00")
            } else {
                b42 = "disponivel";txt42.setBackgroundResource(R.drawable.botao)
            }
        })
        txt43.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt43.setBackgroundResource(R.drawable.radio_b_pers)
                b43 = "reservado"
                var seg = segunda.getText().toString()
                dia.setText(seg)
                data.setText("SEGUNDA")
                hora.setText("16:00")
            } else {
                b43 = "disponivel";txt43.setBackgroundResource(R.drawable.botao)
            }
        })
        txt44.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt44.setBackgroundResource(R.drawable.radio_b_pers)
                b44 = "reservado"
                var ter = terca.getText().toString()
                dia.setText(ter)
                data.setText("TERCA")
                hora.setText("16:00")
            } else {
                b44 = "disponivel";txt44.setBackgroundResource(R.drawable.botao)
            }
        })
        txt45.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt45.setBackgroundResource(R.drawable.radio_b_pers)
                b45 = "reservado"
                var qua = quarta.getText().toString()
                dia.setText(qua)
                data.setText("QUARTA")
                hora.setText("16:00")
            } else {
                b45 = "disponivel";txt45.setBackgroundResource(R.drawable.botao)
            }
        })
        txt46.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt46.setBackgroundResource(R.drawable.radio_b_pers)
                b46 = "reservado"
                var qui = quinta.getText().toString()
                dia.setText(qui)
                data.setText("QUINTA")
                hora.setText("16:00")
            } else {
                b46 = "disponivel";txt46.setBackgroundResource(R.drawable.botao)
            }
        })
        txt47.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt47.setBackgroundResource(R.drawable.radio_b_pers)
                b47 = "reservado"
                var sex = sexta.getText().toString()
                dia.setText(sex)
                data.setText("SEXTA")
                hora.setText("16:00")
            } else {
                b47 = "disponivel";txt47.setBackgroundResource(R.drawable.botao)
            }
        })
        txt48.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt48.setBackgroundResource(R.drawable.radio_b_pers)
                b48 = "reservado"
                var sab = sabado.getText().toString()
                dia.setText(sab)
                data.setText("SABADO")
                hora.setText("16:00")
            } else {
                b48 = "disponivel";txt48.setBackgroundResource(R.drawable.botao)
            }
        })
        txt49.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt49.setBackgroundResource(R.drawable.radio_b_pers)
                b49 = "reservado"
                var seg = segunda.getText().toString()
                dia.setText(seg)
                data.setText("SEGUNDA")
                hora.setText("17:00")
            } else {
                b49 = "disponivel";txt49.setBackgroundResource(R.drawable.botao)
            }
        })
        txt50.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt50.setBackgroundResource(R.drawable.radio_b_pers)
                b50 = "reservado"
                var ter = terca.getText().toString()
                dia.setText(ter)
                data.setText("TERCA")
                hora.setText("17:00")
            } else {
                b50 = "disponivel";txt50.setBackgroundResource(R.drawable.botao)
            }
        })
        txt51.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt51.setBackgroundResource(R.drawable.radio_b_pers)
                b51 = "reservado"
                var qua = quarta.getText().toString()
                dia.setText(qua)
                data.setText("QUARTA")
                hora.setText("17:00")
            } else {
                b51 = "disponivel";txt51.setBackgroundResource(R.drawable.botao)
            }
        })
        txt52.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt52.setBackgroundResource(R.drawable.radio_b_pers)
                b52 = "reservado"
                var qui = quinta.getText().toString()
                dia.setText(qui)
                data.setText("QUINTA")
                hora.setText("17:00")
            } else {
                b52 = "disponivel";txt52.setBackgroundResource(R.drawable.botao)
            }
        })
        txt53.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt53.setBackgroundResource(R.drawable.radio_b_pers)
                b53 = "reservado"
                var sex = sexta.getText().toString()
                dia.setText(sex)
                data.setText("SEXTA")
                hora.setText("17:00")
            } else {
                b53 = "disponivel";txt53.setBackgroundResource(R.drawable.botao)
            }
        })
        txt54.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt54.setBackgroundResource(R.drawable.radio_b_pers)
                b54 = "reservado"
                var sab = sabado.getText().toString()
                dia.setText(sab)
                data.setText("SABADO")
                hora.setText("17:00")
            } else {
                b54 = "disponivel";txt54.setBackgroundResource(R.drawable.botao)
            }
        })
        txt55.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt55.setBackgroundResource(R.drawable.radio_b_pers)
                b55 = "reservado"
                var seg = segunda.getText().toString()
                dia.setText(seg)
                data.setText("SEGUNDA")
                hora.setText("18:00")
            } else {
                b55 = "disponivel";txt55.setBackgroundResource(R.drawable.botao)
            }
        })
        txt56.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt56.setBackgroundResource(R.drawable.radio_b_pers)
                b56 = "reservado"
                var ter = terca.getText().toString()
                dia.setText(ter)
                data.setText("TERCA")
                hora.setText("18:00")
            } else {
                b56 = "disponivel";txt56.setBackgroundResource(R.drawable.botao)
            }
        })
        txt57.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt57.setBackgroundResource(R.drawable.radio_b_pers)
                b57 = "reservado"
                var qua = quarta.getText().toString()
                dia.setText(qua)
                data.setText("QUARTA")
                hora.setText("18:00")
            } else {
                b57 = "disponivel"; txt57.setBackgroundResource(R.drawable.botao)
            }
        })
        txt58.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt58.setBackgroundResource(R.drawable.radio_b_pers)
                b58 = "reservado"
                var qui = quinta.getText().toString()
                dia.setText(qui)
                data.setText("QUINTA")
                hora.setText("18:00")
            } else {
                b58 = "disponivel"; txt58.setBackgroundResource(R.drawable.botao)
            }
        })
        txt59.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt59.setBackgroundResource(R.drawable.radio_b_pers)
                b59 = "reservado"
                var sex = sexta.getText().toString()
                dia.setText(sex)
                data.setText("SEXTA")
                hora.setText("18:00")
            } else {
                b59 = "disponivel"; txt59.setBackgroundResource(R.drawable.botao)
            }
        })
        txt60.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked) {
                txt60.setBackgroundResource(R.drawable.radio_b_pers)
                b60 = "reservado"
                var sab = sabado.getText().toString()
                dia.setText(sab)
                data.setText("SABADO")
                hora.setText("18:00")
            } else {
                b60 = "disponivel";txt60.setBackgroundResource(R.drawable.botao)
            }
        })


        ref = objetoRef.getReference()

        // habilita botao se estiverem preenchidos

        enviar.isEnabled = false
        val editTexts = listOf(nome, telef, dia, hora)
        for (editText in editTexts) {
            editText.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    var et1 = nome.text.toString().trim()
                    var et2 = telef.text.toString().trim()
                    var et3 = dia.text.toString().trim()
                    var et4 = hora.text.toString().trim()

                    enviar.isEnabled = et1.isNotEmpty()
                            && et2.isNotEmpty()
                            && et3.isNotEmpty()
                            && et4.isNotEmpty()

                }

                override fun beforeTextChanged(
                    s: CharSequence, start: Int, count: Int, after: Int
                ) {
                }

                override fun afterTextChanged(
                    s: Editable
                ) {
                }
            })
        }
        //datas da semana

        val calendar = Calendar.getInstance(TimeZone.getDefault())
        val dt = Date()
        val dtForm = SimpleDateFormat("dd/MM/yy").format(dt)
        val dtFormatada = dtForm.format(dt)
        val dt1 = dtFormatada


        val dateFormat = SimpleDateFormat("dd/MM/yy")
        val c2 = Calendar.getInstance()
        c2.add(Calendar.DATE, 1)
        val dt2 = (dateFormat.format(c2.time))

        val dateFormat3 = SimpleDateFormat("dd/MM/yy")
        val c3 = Calendar.getInstance()
        c3.add(Calendar.DATE, 2)
        val dt3 = (dateFormat3.format(c3.time))

        val dateFormat4 = SimpleDateFormat("dd/MM/yy")
        val c4 = Calendar.getInstance()
        c4.add(Calendar.DATE, 3)
        val dt4 = (dateFormat4.format(c4.time))

        val dateFormat5 = SimpleDateFormat("dd/MM/yy")
        val c5 = Calendar.getInstance()
        c5.add(Calendar.DATE, 4)
        val dt5 = (dateFormat5.format(c5.time))

        val dateFormat6 = SimpleDateFormat("dd/MM/yy")
        val c6 = Calendar.getInstance()
        c6.add(Calendar.DATE, 5)
        val dt6 = (dateFormat6.format(c6.time))

        val dateFormat7 = SimpleDateFormat("dd/MM/yy")
        val c7 = Calendar.getInstance()
        c7.add(Calendar.DATE, 6)
        val dt7 = (dateFormat7.format(c7.time))

        val days = calendar.get(Calendar.DAY_OF_WEEK)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val min = calendar.get(Calendar.MINUTE)

        if (days == 1) {
            segunda.setText(dt2);terca.setText(dt3);quarta.setText(dt4);quinta.setText(dt5);sexta.setText(
                dt6
            );sabado.setText(dt7)
        }
        if (days == 2) {
            segunda.setText(dt1);terca.setText(dt2);quarta.setText(dt3);quinta.setText(dt4);sexta.setText(
                dt5
            );sabado.setText(dt6)
        }
        if (days == 3) {
            segunda.setText(dt7);terca.setText(dt1);quarta.setText(dt2);quinta.setText(dt3);sexta.setText(
                dt4
            );sabado.setText(dt5)
        }
        if (days == 4) {
            segunda.setText(dt6);terca.setText(dt7);quarta.setText(dt1);quinta.setText(dt2);sexta.setText(
                dt3
            );sabado.setText(dt4)
        }
        if (days == 5) {
            segunda.setText(dt5);terca.setText(dt6);quarta.setText(dt7);quinta.setText(dt1);sexta.setText(
                dt2
            );sabado.setText(dt3)
        }
        if (days == 6) {
            segunda.setText(dt4);terca.setText(dt5);quarta.setText(dt6);quinta.setText(dt7);sexta.setText(
                dt1
            );sabado.setText(dt2)
        }
        if (days == 7) {
            segunda.setText(dt3);terca.setText(dt4);quarta.setText(dt5);quinta.setText(dt6);sexta.setText(
                dt7
            );sabado.setText(dt1)
        }


// Apos o click mudara para reservado e enviara os dados para firebase,registrara agendamento,
        enviar.setOnClickListener {
            val da = Date()
            val dtaForm = SimpleDateFormat("dd/MM/yy HH:mm").format(da)
            val dtaFormatada = dtaForm.format(da)
            val dta = dtaFormatada

            val hra = hora.getText().toString()
            val texto = dia.getText().toString()

            val sd = SimpleDateFormat("dd/MM/yy")
            val d = sd.parse(texto)
            val gc = GregorianCalendar()
            gc.time = d
            val n = sd.format(gc.time)


            val tx = n + " " + hra

            val sdf = SimpleDateFormat("dd/MM/yy HH:mm")
            val data1 = Calendar.getInstance()
            val data2 = Calendar.getInstance()
            try {
                data1.time = sdf.parse(tx)
                data2.time = sdf.parse(dta)
            } catch (e: java.text.ParseException) {
            }
            val d1 = data1.getTime()
            val d2 = data2.getTime()
            //  val dat = data1.get(Calendar.DATE) - data2.get(Calendar.DATE)
            val diferencahr = (d1.time - d2.time) / 3600000

            if (diferencahr < 2) {
                Toast.makeText(this, "Agende com maior antecedência!", Toast.LENGTH_SHORT).show()
            } else {

                //verifica conexao com internet
                var cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                var netInfo = cm.activeNetworkInfo
                if (netInfo != null && netInfo.isConnectedOrConnecting) {

                    val alertDialog = AlertDialog.Builder(this)
                    alertDialog.setTitle("AGENDAR")
                    alertDialog.setMessage("Deseja confirmar o agendamento?")
                    alertDialog.setPositiveButton(
                        "SIM"
                    ) { dialog, which ->


                        val da = Date()
                        val dtaForm = SimpleDateFormat("dd/MM/yy HH:mm").format(da)
                        val dtaFormatada = dtaForm.format(da)
                        val dta = dtaFormatada

                        val hra = hora.getText().toString()
                        val texto = dia.getText().toString()

                        val sd = SimpleDateFormat("dd/MM/yy")
                        val d = sd.parse(texto)
                        val gc = GregorianCalendar()
                        gc.time = d
                        val n = sd.format(gc.time)


                        val tx = n + " " + hra

                        val sdf = SimpleDateFormat("dd/MM/yy HH:mm")
                        val data1 = Calendar.getInstance()
                        val data2 = Calendar.getInstance()
                        try {
                            data1.time = sdf.parse(tx)
                            data2.time = sdf.parse(dta)
                        } catch (e: java.text.ParseException) {
                        }
                        val d1 = data1.getTime()
                        val d2 = data2.getTime()
                        //  val dat = data1.get(Calendar.DATE) - data2.get(Calendar.DATE)
                        val dias = (d1.time - d2.time) / 3600000


                        // val diferenca = d1.time - d2.time
                        // val diferencaEmHoras = diferenca / 1000 / 60 / 60
                        //   val minutosRestantes = diferenca / 1000 / 60 % 60
                        //  val horasRestantes = minutosRestantes / 60.0
                        // val dias = diferencaEmHoras


// envia dados activ 5 pra finalizar
                        val intent = Intent(this@Main3Activity, Main5Activity::class.java)
                        val name = data.getText().toString()
                        intent.putExtra("name", name)
                        val name1 = hora.getText().toString()
                        intent.putExtra("name1", name1)
                        val name2 = dia.getText().toString()
                        intent.putExtra("name2", name2)
                        val name3 = dias.toString()
                        intent.putExtra("name3", name3)


                        startActivity(intent)
// valor pro firebase disponivel ou reservado

                        a01.toString();if (a01 != "") ref.child("segunda/8").setValue(a01)
                        b01.toString();if (b01 != "") ref.child("terca/8").setValue(b01)
                        b02.toString();if (b02 != "") ref.child("quarta/8").setValue(b02)
                        b03.toString();if (b03 != "") ref.child("quinta/8").setValue(b03)
                        b04.toString();if (b04 != "") ref.child("sexta/8").setValue(b04)
                        b05.toString();if (b05 != "") ref.child("sabado/8").setValue(b05)
                        b1.toString();if (b1 != "") ref.child("segunda/9").setValue(b1)
                        b2.toString();if (b2 != "") ref.child("terca/9").setValue(b2)
                        b3.toString();if (b3 != "") ref.child("quarta/9").setValue(b3)
                        b4.toString();if (b4 != "") ref.child("quinta/9").setValue(b4)
                        b5.toString();if (b5 != "") ref.child("sexta/9").setValue(b5)
                        b6.toString();if (b6 != "") ref.child("sabado/9").setValue(b6)
                        b7.toString();if (b7 != "") ref.child("segunda/10").setValue(b7)
                        b8.toString();if (b8 != "") ref.child("terca/10").setValue(b8)
                        b9.toString();if (b9 != "") ref.child("quarta/10").setValue(b9)
                        b10.toString(); if (b10 != "") ref.child("quinta/10").setValue(b10)
                        b11.toString(); if (b11 != "") ref.child("sexta/10").setValue(b11)
                        b12.toString(); if (b12 != "") ref.child("sabado/10").setValue(b12)
                        b13.toString();if (b13 != "") ref.child("segunda/11").setValue(b13)
                        b14.toString();if (b14 != "") ref.child("terca/11").setValue(b14)
                        b15.toString();if (b15 != "") ref.child("quarta/11").setValue(b15)
                        b16.toString();if (b16 != "") ref.child("quinta/11").setValue(b16)
                        b17.toString();if (b17 != "") ref.child("sexta/11").setValue(b17)
                        b18.toString();if (b18 != "") ref.child("sabado/11").setValue(b18)
                        b19.toString();if (b19 != "") ref.child("segunda/12").setValue(b19)
                        b20.toString();if (b20 != "") ref.child("terca/12").setValue(b20)
                        b21.toString();if (b21 != "") ref.child("quarta/12").setValue(b21)
                        b22.toString();if (b22 != "") ref.child("quinta/12").setValue(b22)
                        b23.toString();if (b23 != "") ref.child("sexta/12").setValue(b23)
                        b24.toString();if (b24 != "") ref.child("sabado/12").setValue(b24)
                        b25.toString();if (b25 != "") ref.child("segunda/13").setValue(b25)
                        b26.toString();if (b26 != "") ref.child("terca/13").setValue(b26)
                        b27.toString();if (b27 != "") ref.child("quarta/13").setValue(b27)
                        b28.toString();if (b28 != "") ref.child("quinta/13").setValue(b28)
                        b29.toString();if (b29 != "") ref.child("sexta/13").setValue(b29)
                        b30.toString();if (b30 != "") ref.child("sabado/13").setValue(b30)
                        b31.toString();if (b31 != "") ref.child("segunda/14").setValue(b31)
                        b32.toString();if (b32 != "") ref.child("terca/14").setValue(b32)
                        b33.toString();if (b33 != "") ref.child("quarta/14").setValue(b33)
                        b34.toString();if (b34 != "") ref.child("quinta/14").setValue(b34)
                        b35.toString();if (b35 != "") ref.child("sexta/14").setValue(b35)
                        b36.toString();if (b36 != "") ref.child("sabado/14").setValue(b36)
                        b37.toString();if (b37 != "") ref.child("segunda/15").setValue(b37)
                        b38.toString();if (b38 != "") ref.child("terca/15").setValue(b38)
                        b39.toString();if (b39 != "") ref.child("quarta/15").setValue(b39)
                        b40.toString();if (b40 != "") ref.child("quinta/15").setValue(b40)
                        b41.toString();if (b41 != "") ref.child("sexta/15").setValue(b41)
                        b42.toString();if (b42 != "") ref.child("sabado/15").setValue(b42)
                        b43.toString();if (b43 != "") ref.child("segunda/16").setValue(b43)
                        b44.toString();if (b44 != "") ref.child("terca/16").setValue(b44)
                        b45.toString();if (b45 != "") ref.child("quarta/16").setValue(b45)
                        b46.toString();if (b46 != "") ref.child("quinta/16").setValue(b46)
                        b47.toString();if (b47 != "") ref.child("sexta/16").setValue(b47)
                        b48.toString();if (b48 != "") ref.child("sabado/16").setValue(b48)
                        b49.toString();if (b49 != "") ref.child("segunda/17").setValue(b49)
                        b50.toString();if (b50 != "") ref.child("terca/17").setValue(b50)
                        b51.toString();if (b51 != "") ref.child("quarta/17").setValue(b51)
                        b52.toString();if (b52 != "") ref.child("quinta/17").setValue(b52)
                        b53.toString();if (b53 != "") ref.child("sexta/17").setValue(b53)
                        b54.toString();if (b54 != "") ref.child("sabado/17").setValue(b54)
                        b55.toString();if (b55 != "") ref.child("segunda/18").setValue(b55)
                        b56.toString();if (b56 != "") ref.child("terca/18").setValue(b56)
                        b57.toString();if (b57 != "") ref.child("quarta/18").setValue(b57)
                        b58.toString();if (b58 != "") ref.child("quinta/18").setValue(b58)
                        b59.toString();if (b59 != "") ref.child("sexta/18").setValue(b59)
                        b60.toString();if (b60 != "") ref.child("sabado/18").setValue(b60)

                        // Firebase
                        val p = Pessoa()
                        p.id = UUID.randomUUID().toString()
                        p.nome = nome.getText().toString()
                        p.telef = telef.getText().toString()
                        p.dia = dia.getText().toString() + " " + data.getText().toString()
                        p.servico = servico.getText().toString()
                        p.hora = hora.getText().toString()
                        p.msg = msg.getText().toString()
                        ref.child("Pessoa").child(p.id).setValue(p)
                        limparCampos()

                        val toast =
                            Toast.makeText(this, "Dados enviados com sucesso!", Toast.LENGTH_LONG)
                        toast.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL, 0, 0)
                        toast.show()
                    }.setNegativeButton(
                        "NAO"
                    ) { dialog, which ->
                        Toast.makeText(applicationContext, "Não enviado", Toast.LENGTH_SHORT).show()
                        dialog.cancel()
                    }
                    alertDialog.show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Dispositivo sem conexão",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    private fun limparCampos() {
        dia.setText("")
        hora.setText("")
        nome.setText("")
        telef.setText("")
        msg.setText("")
        servico.setText("")



    }

}