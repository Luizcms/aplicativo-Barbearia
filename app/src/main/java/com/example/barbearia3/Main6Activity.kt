package com.example.barbearia3

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main6.*
import java.io.File
import java.io.IOException

class Main6Activity : AppCompatActivity(),View.OnClickListener {

    internal lateinit var objetoRef: FirebaseDatabase;
    internal lateinit var ref: DatabaseReference;
    internal lateinit var storage: FirebaseStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        var prof1 = findViewById(R.id.prof1) as RadioButton
        var prof2 = findViewById(R.id.prof2) as RadioButton
        var serv1 = findViewById(R.id.servico1) as RadioButton
        var serv2 = findViewById(R.id.servico2) as RadioButton
        var serv3 = findViewById(R.id.servico3) as RadioButton
        var serv4 = findViewById(R.id.servico4) as RadioButton
        var serv5 = findViewById(R.id.servico5) as RadioButton
        var avancar = findViewById(R.id.avancar) as Button
        var preco1 = findViewById(R.id.preco1) as TextView;
        var preco2 = findViewById(R.id.preco2) as TextView;
        var preco3 = findViewById(R.id.preco3) as TextView;
        var preco4 = findViewById(R.id.preco4) as TextView;
        var preco5 = findViewById(R.id.preco5) as TextView;



        avancar.isEnabled=false
        profissional.isEnabled=false
        serv4.isEnabled=false
        serv5.isEnabled=false
        prof1.isEnabled=false
        prof2.isEnabled=false

        objetoRef = FirebaseConfig.getDatabase()


        ref = objetoRef.getReference("preco 1")
        ref.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: com.google.firebase.database.DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                preco1.setText(mensagem)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("preco 2")
        ref.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: com.google.firebase.database.DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                preco2.setText(mensagem)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("preco 3")
        ref.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: com.google.firebase.database.DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                preco3.setText(mensagem)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("preco 4")
        ref.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: com.google.firebase.database.DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                preco4.setText(mensagem)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("preco 5")
        ref.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: com.google.firebase.database.DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                preco5.setText(mensagem)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("servico 1")
        ref.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: com.google.firebase.database.DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                servico1.setText(mensagem)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("servico 2")
        ref.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: com.google.firebase.database.DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                servico2.setText(mensagem)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("servico 3")
        ref.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: com.google.firebase.database.DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                servico3.setText(mensagem)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("servico 4")
        ref.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: com.google.firebase.database.DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                servico4.setText(mensagem)
                if (servico4.getText().toString() != ""){
                    servico4.isEnabled=true
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("servico 5")
        ref.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: com.google.firebase.database.DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                servico5.setText(mensagem)
                if (servico5.getText().toString() != "") {
                    servico5.isEnabled = true
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("profissional 1")
        ref.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: com.google.firebase.database.DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                prof1.setText(mensagem)
                if (prof1.getText().toString() != ""){
                    prof1.isEnabled=true
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        ref = objetoRef.getReference("profissional 2")
        ref.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: com.google.firebase.database.DataSnapshot) {
                val mensagem = dataSnapshot.getValue(String::class.java)
                prof2.setText(mensagem)
                if (prof2.getText().toString() != ""){
                    prof2.isEnabled=true
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        storage = FirebaseStorage.getInstance()
        var img_prof1 = storage.getReference("prof1.jpg")
        try {
            val localFile = File.createTempFile("images", "jpg")
            img_prof1.getFile(localFile)
                .addOnSuccessListener(OnSuccessListener<FileDownloadTask.TaskSnapshot> {
                    val bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath())
                    var img = findViewById<ImageView>(R.id.img_prof1)
                    val rounded = RoundedBitmapDrawableFactory.create(resources, bitmap)
                    rounded.isCircular = true
                    img.setImageDrawable(rounded)


                }).addOnFailureListener(OnFailureListener { })
        } catch (e: IOException) {
        }
        var img_prof2 = storage.getReference("prof2.jpg")
        try {
            val localFile = File.createTempFile("images", "jpg")
            img_prof2.getFile(localFile)
                .addOnSuccessListener(OnSuccessListener<FileDownloadTask.TaskSnapshot> {
                    val bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath())
                    var img = findViewById<ImageView>(R.id.img_prof2)
                    val rounded = RoundedBitmapDrawableFactory.create(resources, bitmap)
                    rounded.isCircular = true
                    img.setImageDrawable(rounded)

                }).addOnFailureListener(OnFailureListener { })
        } catch (e: IOException) {
        }


        serv1.setOnClickListener { onClick(serv1) }
        serv2.setOnClickListener { onClick(serv2) }
        serv3.setOnClickListener { onClick(serv3) }
        serv4.setOnClickListener { onClick(serv4) }
        serv5.setOnClickListener { onClick(serv5) }
        prof1.setOnClickListener { onClick(prof1) }
        prof2.setOnClickListener { onClick(prof2) }


        avancar.setOnClickListener {
            val intent = Intent(this@Main6Activity, Main3Activity::class.java)

            val a = avancar.getText().toString()
            intent.putExtra("qString", a)
            val b = profissional.getText().toString()
            intent.putExtra("profis", b)

            startActivity(intent)
        }
    }

        override fun onClick(v: View) {
            when (v.id) {
                R.id.servico1 -> {
                    var a = servico1.getText().toString()
                    var b = preco1.getText().toString()
                    avancar.setText(a + " " + b)
                    avancar.isEnabled = true
                    avancar.setBackgroundResource(R.drawable.botaotab)

                }
                R.id.servico2 -> {
                    var a = servico2.getText().toString()
                    var b = preco2.getText().toString()
                    avancar.setText(a + " " + b)
                    avancar.isEnabled = true
                    avancar.setBackgroundResource(R.drawable.botaotab)

                }
                R.id.servico3 -> {
                    var a = servico3.getText().toString()
                    var b = preco3.getText().toString()
                    avancar.setText(a + " " + b)
                    avancar.isEnabled = true
                    avancar.setBackgroundResource(R.drawable.botaotab)
                }
                R.id.servico4 -> {
                    var a = servico4.getText().toString()
                    var b = preco4.getText().toString()
                    avancar.setText(a + " " + b)
                    avancar.isEnabled = true
                    avancar.setBackgroundResource(R.drawable.botaotab)
                }
                R.id.servico5 -> {
                    var a = servico5.getText().toString()
                    var b = preco5.getText().toString()
                    avancar.setText(a + " " + b)
                    avancar.isEnabled = true
                    avancar.setBackgroundResource(R.drawable.botaotab)
                }
                R.id.prof1 -> {
                    var a = prof1.getText().toString()
                    profissional.setText("Profissional: "+a)
                    profissional.isEnabled = true
                    profissional.setBackgroundResource(R.drawable.botaotab)
                }
                R.id.prof2 -> {
                    var a = prof2.getText().toString()
                    profissional.setText("Profissional: "+a)
                    profissional.isEnabled = true
                    profissional.setBackgroundResource(R.drawable.botaotab)
                }
                else -> {
                }
            }
        }

    }
