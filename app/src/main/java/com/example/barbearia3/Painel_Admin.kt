package com.example.barbearia3

import android.content.Intent
import android.graphics.Color.GREEN
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.painel_admin.*
import java.util.*
import android.widget.ArrayAdapter as ArrayAdapter1


class Painel_Admin : AppCompatActivity() {

    internal lateinit var objetoRef: FirebaseDatabase;
    internal lateinit var ref: DatabaseReference;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.painel_admin)

        var i = findViewById<EditText>(R.id.txtInicio)
        var i1 = findViewById<EditText>(R.id.txtservico1)
        var i2 = findViewById<EditText>(R.id.txtservico2)
        var i3 = findViewById<EditText>(R.id.txtservico3)
        var i4 = findViewById<EditText>(R.id.txtservico4)
        var i5 = findViewById<EditText>(R.id.txtservico5)
        var p1 = findViewById<EditText>(R.id.preco1)
        var p2 = findViewById<EditText>(R.id.preco2)
        var p3 = findViewById<EditText>(R.id.preco3)
        var p4 = findViewById<EditText>(R.id.preco4)
        var p5 = findViewById<EditText>(R.id.preco5)
        var pr1 = findViewById<EditText>(R.id.prof1)
        var pr2 = findViewById<EditText>(R.id.prof2)
        var desc1 = findViewById<EditText>(R.id.descricao1)
        var desc = findViewById<EditText>(R.id.descricao)
        var end = findViewById<EditText>(R.id.ender)
        var telef = findViewById<EditText>(R.id.telef)
        var face = findViewById<EditText>(R.id.face)
        var instag = findViewById<EditText>(R.id.inst)

        var scrol = findViewById<ScrollView>(R.id.scrol)
        btn_alterar.setOnClickListener{
            scrol.visibility=View.VISIBLE
            list_p.visibility=View.INVISIBLE
        }
        reservas.setOnClickListener{
            scrol.visibility=View.INVISIBLE
            list_p.visibility=View.VISIBLE
        }
        objetoRef = FirebaseConfig.getDatabase()

        val array = ArrayList<String>()
        val ar = array.asReversed()
        val adapter = object :
            android.widget.ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                // Como o simple_list_item_1 retorna um TextView, esse cast pode ser feito sem problemas
                (view as TextView).setTextColor(GREEN)
                view.setBackgroundResource(R.drawable.borda)
                return view
            }
        }
        list_p.setAdapter(adapter)

        ref = objetoRef.getReference("Pessoa")
        ref.orderByChild("dia").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (s in dataSnapshot.children) {

                    var tel = s.child("telef").value!!.toString()
                    var nome = s.child("nome").value!!.toString()
                    var serv = s.child("servico").value!!.toString()
                    var dia = s.child("dia").value!!.toString()
                    var hora = s.child("hora").value!!.toString()
                    var msg = s.child("msg").value!!.toString()
                    ar.add("Nome: "+nome+"\nTel: "+tel+"\nServico: "+serv+"\nData: "+dia+"\nHora: "+hora+"\nMsg: "+msg+"\n")

                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
        var ref1 = objetoRef.getReference("inicio")
        var ref2 = objetoRef.getReference("servico 1")
        var ref3 = objetoRef.getReference("servico 2")
        var ref4 = objetoRef.getReference("servico 3")
        var ref5 = objetoRef.getReference("servico 4")
        var ref6 = objetoRef.getReference("servico 5")
        var ref7 = objetoRef.getReference("preco 1")
        var ref8 = objetoRef.getReference("preco 2")
        var ref9 = objetoRef.getReference("preco 3")
        var ref10 = objetoRef.getReference("preco 4")
        var ref11 = objetoRef.getReference("preco 5")
        var ref12 = objetoRef.getReference("profissional 1")
        var ref13 = objetoRef.getReference("profissional 2")
        var refd14 = objetoRef.getReference("descricao1")
        var ref14 = objetoRef.getReference("descricao")
        var ref15 = objetoRef.getReference("endereco")
        var ref16 = objetoRef.getReference("telefone")
        var ref17 = objetoRef.getReference("facebook")
        var ref18 = objetoRef.getReference("instagram")


        btimg.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Admin_fotos::class.java)
            startActivity(intent)
        })
        button.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Texto da tela inicial.")
            alertDialog.setMessage("Deseja mudar a mensagem inicial?")
            alertDialog.setPositiveButton("SIM"
            ) { dialog, which ->
                var a = i.getText().toString()
                ref1.setValue(a)
                Toast.makeText(applicationContext, "Alterado", Toast.LENGTH_SHORT).show()
            }.setNegativeButton("NAO"
            ) { dialog, which ->
                Toast.makeText(applicationContext, "Ok", Toast.LENGTH_SHORT).show()
                dialog.cancel() }
            alertDialog.show()
        }
        servico1.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Servico 1 e preco 1.")
            alertDialog.setMessage("Deseja mudar o servico e valor?")
            alertDialog.setPositiveButton("SIM"
            ) { dialog, which ->
                var a = i1.getText().toString()
                var b = p1.getText().toString()

                ref2.setValue(a)
                ref7.setValue(b)
                Toast.makeText(applicationContext, "Alterado", Toast.LENGTH_SHORT).show()
            }.setNegativeButton("NAO"
            ) { dialog, which ->
                Toast.makeText(applicationContext, "Ok", Toast.LENGTH_SHORT).show()
                dialog.cancel() }
            alertDialog.show()
        }
        servico2.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Servico 2 e preco 2.")
            alertDialog.setMessage("Deseja mudar o servico e valor?")
            alertDialog.setPositiveButton("SIM"
            ) { dialog, which ->
                var a = i2.getText().toString()
                var b = p2.getText().toString()
                ref3.setValue(a)
                ref8.setValue(b)
                Toast.makeText(applicationContext, "Alterado", Toast.LENGTH_SHORT).show()
            }.setNegativeButton("NAO"
            ) { dialog, which ->
                Toast.makeText(applicationContext, "Ok", Toast.LENGTH_SHORT).show()
                dialog.cancel() }
            alertDialog.show()
        }
        servico3.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Servico 3 e preco 3.")
            alertDialog.setMessage("Deseja mudar o servico e valor?")
            alertDialog.setPositiveButton("SIM"
            ) { dialog, which ->
                var a = i3.getText().toString()
                var b = p3.getText().toString()
                ref4.setValue(a)
                ref9.setValue(b)
                Toast.makeText(applicationContext, "Alterado", Toast.LENGTH_SHORT).show()
            }.setNegativeButton("NAO"
            ) { dialog, which ->
                Toast.makeText(applicationContext, "Ok", Toast.LENGTH_SHORT).show()
                dialog.cancel() }
            alertDialog.show()
        }
        servico4.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Servico 4 e preco 4.")
            alertDialog.setMessage("Deseja mudar o servico e valor?")
            alertDialog.setPositiveButton("SIM"
            ) { dialog, which ->
                var a = i4.getText().toString()
                var b = p4.getText().toString()
                ref5.setValue(a)
                ref10.setValue(b)
                Toast.makeText(applicationContext, "Alterado", Toast.LENGTH_SHORT).show()
            }.setNegativeButton("NAO"
            ) { dialog, which ->
                Toast.makeText(applicationContext, "Ok", Toast.LENGTH_SHORT).show()
                dialog.cancel() }
            alertDialog.show()
        }
        servico5.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Servico 5 e preco 5.")
            alertDialog.setMessage("Deseja mudar o servico e valor?")
            alertDialog.setPositiveButton("SIM"
            ) { dialog, which ->
                var a = i5.getText().toString()
                var b = p5.getText().toString()
                ref6.setValue(a)
                ref11.setValue(b)
                Toast.makeText(applicationContext, "Alterado", Toast.LENGTH_SHORT).show()
            }.setNegativeButton("NAO"
            ) { dialog, which ->
                Toast.makeText(applicationContext, "Ok", Toast.LENGTH_SHORT).show()
                dialog.cancel() }
            alertDialog.show()
        }
        btprof1.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Profissional.")
            alertDialog.setMessage("Deseja mudar o nome do profissional?")
            alertDialog.setPositiveButton("SIM"
            ) { dialog, which ->
                var a = pr1.getText().toString()
                ref12.setValue(a)
                Toast.makeText(applicationContext, "Alterado", Toast.LENGTH_SHORT).show()
            }.setNegativeButton("NAO"
            ) { dialog, which ->
                Toast.makeText(applicationContext, "Ok", Toast.LENGTH_SHORT).show()
                dialog.cancel() }
            alertDialog.show()
        }
        btprof2.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Profissional")
            alertDialog.setMessage("Deseja mudar o nome do profissional?")
            alertDialog.setPositiveButton("SIM"
            ) { dialog, which ->
                var a = pr2.getText().toString()
                ref13.setValue(a)
                Toast.makeText(applicationContext, "Alterado", Toast.LENGTH_SHORT).show()
            }.setNegativeButton("NAO"
            ) { dialog, which ->
                Toast.makeText(applicationContext, "Ok", Toast.LENGTH_SHORT).show()
                dialog.cancel() }
            alertDialog.show()
        }
        btdesc.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Descricao,ficara logo abaixo da galeria")
            alertDialog.setMessage("Deseja enviar nova descrição?")
            alertDialog.setPositiveButton("SIM"
            ) { dialog, which ->
                var a = desc.getText().toString()
                ref14.setValue(a)
                Toast.makeText(applicationContext, "Alterado", Toast.LENGTH_SHORT).show()
            }.setNegativeButton("NAO"
            ) { dialog, which ->
                Toast.makeText(applicationContext, "Ok", Toast.LENGTH_SHORT).show()
                dialog.cancel() }
            alertDialog.show()
        }
        btdesc1.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Descricao,ficara logo acima da galeria")
            alertDialog.setMessage("Deseja enviar nova descrição?")
            alertDialog.setPositiveButton("SIM"
            ) { dialog, which ->
                var a = desc1.getText().toString()
                refd14.setValue(a)
                Toast.makeText(applicationContext, "Alterado", Toast.LENGTH_SHORT).show()
            }.setNegativeButton("NAO"
            ) { dialog, which ->
                Toast.makeText(applicationContext, "Ok", Toast.LENGTH_SHORT).show()
                dialog.cancel() }
            alertDialog.show()
        }
        btend.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Endereco comercial!")
            alertDialog.setMessage("Deseja inserir endereço?")
            alertDialog.setPositiveButton("SIM"
            ) { dialog, which ->
                var a = end.getText().toString()
                ref15.setValue(a)
                Toast.makeText(applicationContext, "Alterado", Toast.LENGTH_SHORT).show()
            }.setNegativeButton("NAO"
            ) { dialog, which ->
                Toast.makeText(applicationContext, "Ok", Toast.LENGTH_SHORT).show()
                dialog.cancel() }
            alertDialog.show()
        }
        bttelef.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Contato para whatsapp")
            alertDialog.setMessage("Deseja inserir contato?")
            alertDialog.setPositiveButton("SIM"
            ) { dialog, which ->
                var a = telef.getText().toString()
                ref16.setValue(a)
                Toast.makeText(applicationContext, "Alterado", Toast.LENGTH_SHORT).show()
            }.setNegativeButton("NAO"
            ) { dialog, which ->
                Toast.makeText(applicationContext, "Ok", Toast.LENGTH_SHORT).show()
                dialog.cancel() }
            alertDialog.show()
        }
        btface.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Link facebook")
            alertDialog.setMessage("Deseja inserir link do facebook?")
            alertDialog.setPositiveButton("SIM"
            ) { dialog, which ->
                var a = face.getText().toString()
                ref17.setValue(a)
                Toast.makeText(applicationContext, "Alterado", Toast.LENGTH_SHORT).show()
            }.setNegativeButton("NAO"
            ) { dialog, which ->
                Toast.makeText(applicationContext, "Ok", Toast.LENGTH_SHORT).show()
                dialog.cancel() }
            alertDialog.show()
        }
        btinst.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("link do Instagram")
            alertDialog.setMessage("Deseja inserir link do Instagram?")
            alertDialog.setPositiveButton("SIM"
            ) { dialog, which ->
                var a = instag.getText().toString()
                ref18.setValue(a)
                Toast.makeText(applicationContext, "Alterado", Toast.LENGTH_SHORT).show()
            }.setNegativeButton("NAO"
            ) { dialog, which ->
                Toast.makeText(applicationContext, "Ok", Toast.LENGTH_SHORT).show()
                dialog.cancel() }
            alertDialog.show()
        }
    }
}
