package com.example.barbearia3

import android.content.DialogInterface
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat

import android.graphics.Color.GREEN
import android.graphics.Color.RED
import kotlinx.android.synthetic.main.listitem.*
import java.util.*

class lista : AppCompatActivity() {

    internal lateinit var objetoRef: FirebaseDatabase
    internal lateinit var ref: DatabaseReference
    internal lateinit var databaseHelper: DatabaseHelper
    internal lateinit var list: ListView
    internal var apaga: Button? = null
    private val view: Any? = null
    internal lateinit var nome: String
    private var id: Int = 0
    internal var a: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listitem)


        list = findViewById<View>(R.id.listView) as ListView
        databaseHelper = DatabaseHelper(this)


        objetoRef = FirebaseDatabase.getInstance()
        ref = objetoRef.reference

        populateListView()
    }

    private fun populateListView() {
        Log.d(TAG, "populateListView: Displaying data in listView")

        val data = databaseHelper.data
        val listData = ArrayList<String>()
        while (data.moveToNext()) {
            listData.add(data.getString(1))
        }
        val adapter =
            object : ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listData) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = super.getView(position, convertView, parent)
                    // Como o simple_list_item_1 retorna um TextView, esse cast pode ser feito sem problemas
                    (view as TextView).setTextColor(GREEN)
                    view.setBackgroundResource(R.drawable.borda)

                    return view
                }
            }

        list.adapter = adapter
        list.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val name = adapterView.getItemAtPosition(i).toString()

            val dat = databaseHelper.getItemID(name)
            var itemID = -1
            while (dat.moveToNext()) {
                itemID = dat.getInt(0)
            }
            if (itemID > -1) {

                id = itemID
                nome = name
            }


            val alertDialog = AlertDialog.Builder(this@lista)
            alertDialog.setTitle("Confirm Delete...")
            alertDialog.setMessage("Deseja cancelar o agendamento?")
            alertDialog.setPositiveButton("SIM") { dialog, which ->

                val da = Date()
                val dtaForm = SimpleDateFormat("EEEEEEE dd/MM/yy HH:mm",Locale("pt","BR")).format(da)
                val dtaFormatada = dtaForm.format(da)
                val dta = dtaFormatada


               /* val sd = SimpleDateFormat("dd/MM/yy")
                val d = sd.parse(name)
                val gc = GregorianCalendar()
                gc.time = d
                val n = sd.format(gc.time)*/


                val sdf = SimpleDateFormat("EEEEEEE dd/MM/yy HH:mm",Locale("pt","BR"))
                val data1 = Calendar.getInstance()
                val data2 = Calendar.getInstance()
                try {
                    data1.time = sdf.parse(name)
                    data2.time = sdf.parse(dta)
                } catch (e:ParseException) {
                }
                val d1 = data1.getTime()
                val d2 = data2.getTime()
               // val dat = data1.get(Calendar.DATE) - data2.get(Calendar.DATE)
                val diferencahr = (d1.time - d2.time) /3600000


                if (diferencahr >= 2) {

                    ref = objetoRef.reference
                    val estado = "disponivel"
                    if (name.contains("08:") && name.contains("SEGUNDA")) {
                        ref.child("segunda/8").setValue(estado)
                    }
                    if (name.contains("09:") && name.contains("SEGUNDA")) {
                        ref.child("segunda/9").setValue(estado)
                    }
                    if (name.contains("10:") && name.contains("SEGUNDA")) {
                        ref.child("segunda/10").setValue(estado)
                    }
                    if (name.contains("11:") && name.contains("SEGUNDA")) {
                        ref.child("segunda/11").setValue(estado)
                    }
                    if (name.contains("12:") && name.contains("SEGUNDA")) {
                        ref.child("segunda/12").setValue(estado)
                    }
                    if (name.contains("13:") && name.contains("SEGUNDA")) {
                        ref.child("segunda/13").setValue(estado)
                    }
                    if (name.contains("14:") && name.contains("SEGUNDA")) {
                        ref.child("segunda/14").setValue(estado)
                    }
                    if (name.contains("15:") && name.contains("SEGUNDA")) {
                        ref.child("segunda/15").setValue(estado)
                    }
                    if (name.contains("16:") && name.contains("SEGUNDA")) {
                        ref.child("segunda/16").setValue(estado)
                    }
                    if (name.contains("17:") && name.contains("SEGUNDA")) {
                        ref.child("segunda/17").setValue(estado)
                    }
                    if (name.contains("18:") && name.contains("SEGUNDA")) {
                        ref.child("segunda/18").setValue(estado)
                    }
                    if (name.contains("08:") && name.contains("TERCA")) {
                        ref.child("terca/8").setValue(estado)
                    }
                    if (name.contains("09:") && name.contains("TERCA")) {
                        ref.child("terca/9").setValue(estado)
                    }
                    if (name.contains("10:") && name.contains("TERCA")) {
                        ref.child("terca/10").setValue(estado)
                    }
                    if (name.contains("11:") && name.contains("TERCA")) {
                        ref.child("terca/11").setValue(estado)
                    }
                    if (name.contains("12:") && name.contains("TERCA")) {
                        ref.child("terca/12").setValue(estado)
                    }
                    if (name.contains("13:") && name.contains("TERCA")) {
                        ref.child("terca/13").setValue(estado)
                    }
                    if (name.contains("14:") && name.contains("TERCA")) {
                        ref.child("terca/14").setValue(estado)
                    }
                    if (name.contains("15:") && name.contains("TERCA")) {
                        ref.child("terca/15").setValue(estado)
                    }
                    if (name.contains("16:") && name.contains("TERCA")) {
                        ref.child("terca/16").setValue(estado)
                    }
                    if (name.contains("17:") && name.contains("TERCA")) {
                        ref.child("terca/17").setValue(estado)
                    }
                    if (name.contains("18:") && name.contains("TERCA")) {
                        ref.child("terca/18").setValue(estado)
                    }
                    if (name.contains("08:") && name.contains("QUARTA")) {
                        ref.child("quarta/8").setValue(estado)
                    }
                    if (name.contains("09:") && name.contains("QUARTA")) {
                        ref.child("quarta/9").setValue(estado)
                    }
                    if (name.contains("10:") && name.contains("QUARTA")) {
                        ref.child("quarta/10").setValue(estado)
                    }
                    if (name.contains("11:") && name.contains("QUARTA")) {
                        ref.child("quarta/11").setValue(estado)
                    }
                    if (name.contains("12:") && name.contains("QUARTA")) {
                        ref.child("quarta/12").setValue(estado)
                    }
                    if (name.contains("13:") && name.contains("QUARTA")) {
                        ref.child("quarta/13").setValue(estado)
                    }
                    if (name.contains("14:") && name.contains("QUARTA")) {
                        ref.child("quarta/14").setValue(estado)
                    }
                    if (name.contains("15:") && name.contains("QUARTA")) {
                        ref.child("quarta/15").setValue(estado)
                    }
                    if (name.contains("16:") && name.contains("QUARTA")) {
                        ref.child("quarta/16").setValue(estado)
                    }
                    if (name.contains("17:") && name.contains("QUARTA")) {
                        ref.child("quarta/17").setValue(estado)
                    }
                    if (name.contains("18:") && name.contains("QUARTA")) {
                        ref.child("quarta/18").setValue(estado)
                    }
                    if (name.contains("08:") && name.contains("QUINTA")) {
                        ref.child("quinta/8").setValue(estado)
                    }
                    if (name.contains("09:") && name.contains("QUINTA")) {
                        ref.child("quinta/9").setValue(estado)
                    }
                    if (name.contains("10:") && name.contains("QUINTA")) {
                        ref.child("quinta/10").setValue(estado)
                    }
                    if (name.contains("11:") && name.contains("QUINTA")) {
                        ref.child("quinta/11").setValue(estado)
                    }
                    if (name.contains("12:") && name.contains("QUINTA")) {
                        ref.child("quinta/12").setValue(estado)
                    }
                    if (name.contains("13:") && name.contains("QUINTA")) {
                        ref.child("quinta/13").setValue(estado)
                    }
                    if (name.contains("14:") && name.contains("QUINTA")) {
                        ref.child("quinta/14").setValue(estado)
                    }
                    if (name.contains("15:") && name.contains("QUINTA")) {
                        ref.child("quinta/15").setValue(estado)
                    }
                    if (name.contains("16:") && name.contains("QUINTA")) {
                        ref.child("quinta/16").setValue(estado)
                    }
                    if (name.contains("17:") && name.contains("QUINTA")) {
                        ref.child("quinta/17").setValue(estado)
                    }
                    if (name.contains("18:") && name.contains("QUINTA")) {
                        ref.child("quinta/18").setValue(estado)
                    }
                    if (name.contains("08:") && name.contains("SEXTA")) {
                        ref.child("sexta/8").setValue(estado)
                    }
                    if (name.contains("09:") && name.contains("SEXTA")) {
                        ref.child("sexta/9").setValue(estado)
                    }
                    if (name.contains("10:") && name.contains("SEXTA")) {
                        ref.child("sexta/10").setValue(estado)
                    }
                    if (name.contains("11:") && name.contains("SEXTA")) {
                        ref.child("sexta/11").setValue(estado)
                    }
                    if (name.contains("12:") && name.contains("SEXTA")) {
                        ref.child("sexta/12").setValue(estado)
                    }
                    if (name.contains("13:") && name.contains("SEXTA")) {
                        ref.child("sexta/13").setValue(estado)
                    }
                    if (name.contains("14:") && name.contains("SEXTA")) {
                        ref.child("sexta/14").setValue(estado)
                    }
                    if (name.contains("15:") && name.contains("SEXTA")) {
                        ref.child("sexta/15").setValue(estado)
                    }
                    if (name.contains("16:") && name.contains("SEXTA")) {
                        ref.child("sexta/16").setValue(estado)
                    }
                    if (name.contains("17:") && name.contains("SEXTA")) {
                        ref.child("sexta/17").setValue(estado)
                    }
                    if (name.contains("18:") && name.contains("SEXTA")) {
                        ref.child("sexta/18").setValue(estado)
                    }
                    if (name.contains("08:") && name.contains("SABADO")) {
                        ref.child("sabado/8").setValue(estado)
                    }
                    if (name.contains("09:") && name.contains("SABADO")) {
                        ref.child("sabado/9").setValue(estado)
                    }
                    if (name.contains("10:") && name.contains("SABADO")) {
                        ref.child("sabado/10").setValue(estado)
                    }
                    if (name.contains("11:") && name.contains("SABADO")) {
                        ref.child("sabado/11").setValue(estado)
                    }
                    if (name.contains("12:") && name.contains("SABADO")) {
                        ref.child("sabado/12").setValue(estado)
                    }
                    if (name.contains("13:") && name.contains("SABADO")) {
                        ref.child("sabado/13").setValue(estado)
                    }
                    if (name.contains("14:") && name.contains("SABADO")) {
                        ref.child("sabado/14").setValue(estado)
                    }
                    if (name.contains("15:") && name.contains("SABADO")) {
                        ref.child("sabado/15").setValue(estado)
                    }
                    if (name.contains("16:") && name.contains("SABADO")) {
                        ref.child("sabado/16").setValue(estado)
                    }
                    if (name.contains("17:") && name.contains("SABADO")) {
                        ref.child("sabado/17").setValue(estado)
                    }
                    if (name.contains("18:") && name.contains("SABADO")) {
                        ref.child("sabado/18").setValue(estado)
                    }
                    (view as TextView).setBackgroundColor(RED)
                    Toast.makeText(applicationContext, "Cancelado!", Toast.LENGTH_SHORT).show()
                    databaseHelper.remove(name)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Não foi possível cancelar!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }.setNegativeButton("NAO") { dialog, which ->
                Toast.makeText(applicationContext, "Ok", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }
            alertDialog.show()
        }
    }

    companion object {

        private val TAG = "lista"
    }
}