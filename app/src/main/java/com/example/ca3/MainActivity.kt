package com.example.ca3

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.PhoneAccount.builder
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AlertDialog
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.shape.ShapeAppearanceModel.builder
import com.google.android.material.snackbar.Snackbar
import java.net.URI
import java.util.stream.DoubleStream.builder

lateinit var image1:ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        image1=findViewById<ImageView>(R.id.image1)
        val fButton=findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val relative=findViewById<RelativeLayout>(R.id.relative)
        val radio=findViewById<RadioGroup>(R.id.radio)
        val options=findViewById<Spinner>(R.id.spinner)
        val arr= arrayOf(" ","India","USA","France","Italy","Russia","Pakistan","Hungary","Spain","Australia","England")
        options.adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr)
        options.onItemSelectedListener= object:OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }


        findViewById<Button>(R.id.btnsubmit).setOnClickListener {
            val builder=AlertDialog.Builder(this)
            builder.setMessage("ARE YOU SURE ?")
            builder.setPositiveButton("Yes",{ dialogInterface: DialogInterface, i: Int ->
                val id = getResources().getIdentifier("x2.png", null, null)
                image1.setImageResource(id)
                findViewById<EditText>(R.id.name).setText(" ")
                if(findViewById<CheckBox>(R.id.cb1).isChecked())
                {
                    findViewById<CheckBox>(R.id.cb1).setChecked(false)
                }
                if(findViewById<CheckBox>(R.id.cb2).isChecked())
                {
                    findViewById<CheckBox>(R.id.cb2).setChecked(false)
                }
                if(findViewById<CheckBox>(R.id.cb3).isChecked())
                {
                    findViewById<CheckBox>(R.id.cb3).setChecked(false)
                }
                radio.clearCheck()
                Snackbar.make(
                    relative,
                    "Thank You",
                    Snackbar.LENGTH_LONG
                ).show()
            })
            builder.setNegativeButton("NO",{dialogInterface: DialogInterface, i: Int ->})
            builder.show()
        }
        radio.setOnCheckedChangeListener { radioGroup, i ->
            var rb:RadioButton? =findViewById(i)
            if(rb!=null)
            {
                Snackbar.make(
                    relative,
                    "You selected "+rb.text,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        fButton.setOnClickListener{
            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent )
        var uri:Uri? = intent?.data
        image1.setImageURI(uri)
    }
}