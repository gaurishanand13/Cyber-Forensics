package com.example.mobrevenu_launcher

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.app.WallpaperManager
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream



class MainActivity : AppCompatActivity() {

    var postition = -1
    var adapter : recylerViewAdapter? =null

    val list = ArrayList<appObject>()

    private fun getInstalled_AllList(){
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val untreatedAppList = applicationContext.packageManager.queryIntentActivities(intent, 0)

        for (untreatedApp in untreatedAppList) {
            val appName = untreatedApp.activityInfo.packageName
            val appPackageName = untreatedApp.activityInfo.loadLabel(packageManager).toString()
            val appImage = untreatedApp.activityInfo.loadIcon(packageManager)
            var system_app_or_not = false
            if(untreatedApp.activityInfo.flags==0)
            {
                system_app_or_not = true
            }
            val app = appObject(appPackageName, appImage,appName,system_app_or_not)
            if (!list.contains(app))
            {
                list.add(app)
            }
        }
        drawerGrid.layoutManager = LinearLayoutManager(this)
        adapter =  recylerViewAdapter(this,list,object : clickInterface{
            override fun longClick(position: Int) {
                val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
                val inflater: LayoutInflater = getLayoutInflater()
                var dialogView: View = inflater.inflate(R.layout.alert_dialog_layout, null)
                dialogBuilder.setView(dialogView)

                var textView = dialogView.findViewById<TextView>(R.id.app_name_alert) as TextView
                textView.setText("Do you want to check if ${list.get(position).name} is secure?")
                var alertDialog: AlertDialog = dialogBuilder.create()
                alertDialog.show()
                dialogView.findViewById<Button>(R.id.yes_button).setOnClickListener{
                    val dialog = ProgressDialog(this@MainActivity)
                    dialog.setMessage("Checking ...")
                    dialog.show()

                    Handler().postDelayed(object : Runnable{
                        override fun run() {
                            dialog.dismiss()
                            alertDialog.dismiss()


                            if(list.get(position).name.equals("loginTester") || list.get(position).name.equals("White Wizard")){
                                dialogView = inflater.inflate(R.layout.unsafe, null)
                                dialogBuilder.setView(dialogView)
                                alertDialog = dialogBuilder.create()
                                alertDialog.show()
                            }else{
                                dialogView = inflater.inflate(R.layout.safe, null)
                                dialogBuilder.setView(dialogView)

                                alertDialog = dialogBuilder.create()
                                alertDialog.show()
                            }

                        }
                    },7000)
                }

                dialogView.findViewById<Button>(R.id.no_button).setOnClickListener{
                    alertDialog.dismiss()
                }
            }
        })
        drawerGrid.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getInstalled_AllList()
    }


}
