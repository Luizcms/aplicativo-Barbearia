package com.example.barbearia3

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.Toast.LENGTH_SHORT
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.painel_admin.*
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import java.io.File
import java.io.IOException
import java.util.ArrayList

class Main2Activity : AppCompatActivity() {

    internal lateinit var reserva: Button;
    internal lateinit var endereco:Button;
    internal lateinit var contato:Button;
    internal lateinit var storageRef: StorageReference
    internal lateinit var storage: FirebaseStorage

    internal lateinit var recyclerView: RecyclerView
    internal lateinit var adapterAnuncio: AdapterFotos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        recyclerView = findViewById(R.id.myrecycler) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)


        var descricao = findViewById<TextView>(R.id.descricao)
        var descricao1 = findViewById<TextView>(R.id.textView5)
        var faceb = findViewById(R.id.faceb) as Button;
        var insta = findViewById(R.id.instagram) as Button;
        var endereco = findViewById(R.id.endereco) as Button;
        var contato = findViewById(R.id.contato) as Button;


        var objetoRef = FirebaseDatabase.getInstance()

        objetoRef = FirebaseConfig.getDatabase()

        var foto = objetoRef.getReference("Users").child("imagens")
        foto.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: com.google.firebase.database.DataSnapshot) {

                val list = ArrayList<ClasseFotos>()
                for (s1 in dataSnapshot.children) {
                     var p = s1.getValue(ClasseFotos::class.java)
                    list.add(p!!)
                }
                adapterAnuncio = AdapterFotos(this@Main2Activity, list)
                recyclerView.layoutManager = GridLayoutManager(this@Main2Activity, 2)
                recyclerView.adapter = adapterAnuncio
                recyclerView.isNestedScrollingEnabled = false
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        var ref = objetoRef.getReference("descricao")
        ref.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: com.google.firebase.database.DataSnapshot) {
                var mensagem = dataSnapshot.getValue(String::class.java)
                descricao.setText(mensagem)

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        var desc1 = objetoRef.getReference("descricao1")
        desc1.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: com.google.firebase.database.DataSnapshot) {
                var mensagem = dataSnapshot.getValue(String::class.java)
                descricao1.setText(mensagem)

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        var end = objetoRef.getReference("endereco")
        end.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: com.google.firebase.database.DataSnapshot) {
                var end = dataSnapshot.getValue(String::class.java)

                endereco.setOnClickListener(View.OnClickListener {
                    val uri =
                        "http://maps.google.com/maps?saddr=" + "&daddr=" + end
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                    startActivity(intent)
                })
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        var tel = objetoRef.getReference("telefone")
        tel.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: com.google.firebase.database.DataSnapshot) {
                var tel = dataSnapshot.getValue(String::class.java)

                contato.setOnClickListener(View.OnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW);
                    intent.setPackage("com.whatsapp");
                    intent.setData(Uri.parse(String.format("https://api.whatsapp.com/send?phone=%s", tel)));
                    if (packageManager.resolveActivity(intent, 0) != null) {
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@Main2Activity, "Instale o WhatsApp", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        var face = objetoRef.getReference("facebook")
        face.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: com.google.firebase.database.DataSnapshot) {
                var face = dataSnapshot.getValue(String::class.java)

                faceb.setOnClickListener(View.OnClickListener {
                    val uri = face
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                    if (packageManager.resolveActivity(intent, 0) != null) {
                        startActivity(intent)

                    }else{
                        Toast.makeText(this@Main2Activity, "Sem link facebook", Toast.LENGTH_SHORT)
                            .show()
                    }

                })
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        var inst = objetoRef.getReference("instagram")
        inst.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(dataSnapshot: com.google.firebase.database.DataSnapshot) {
                var inst = dataSnapshot.getValue(String::class.java)

                insta.setOnClickListener(View.OnClickListener {
                    val uri = inst
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                    if (packageManager.resolveActivity(intent, 0) != null) {
                        startActivity(intent)
                    }else{
                        Toast.makeText(this@Main2Activity, "sem link instagram", Toast.LENGTH_SHORT)
                            .show()
                    }

                })

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })


        //conexao imagens do firebase para galeria

        storage = FirebaseStorage.getInstance()

        var amb2 = storage.getReference("capa2.jpg")
        try {
            val localFile = File.createTempFile("images", "jpg")
            amb2.getFile(localFile)
                .addOnSuccessListener(OnSuccessListener<FileDownloadTask.TaskSnapshot> {
                    val bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath())
                    var img = findViewById<ImageView>(R.id.imageView)
                    img.setImageBitmap(bitmap)


                }).addOnFailureListener(OnFailureListener { })
        } catch (e: IOException) {
        }




        // Picasso.with(this).load("gs://hrbarbearia-26ab4.appspot.com/fotos/barber1.jpg").into(img)
        agendar.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Main2Activity, Main6Activity::class.java)
            startActivity(intent)
        })

    }
}
