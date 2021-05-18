package com.example.mobrevenu_launcher

import android.app.AlertDialog
import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.each_app_item.view.*


class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
public interface clickInterface{
    fun longClick(position: Int)
}

class recylerViewAdapter(val context: Context, val list : ArrayList<appObject>,var myInterface : clickInterface):RecyclerView.Adapter<CourseViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CourseViewHolder {
        val li = p0.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = li.inflate(R.layout.each_app_item, p0, false)
        var courseViewHolder = CourseViewHolder(itemView)
        return courseViewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val linearLayout = holder.itemView?.findViewById<LinearLayout>(R.id.particular_app_item_layout)
        val imageView = holder.itemView?.findViewById<ImageView>(R.id.image)
        val LabelView = holder.itemView?.findViewById<TextView>(R.id.label)

        holder.itemView.label.label?.text = list.get(position).name
        if(list.get(position).name.contains("WhatsApp")){
            val bitmap = BitmapFactory.decodeStream(context.resources.openRawResource(R.raw.whatsapp_icon))
            imageView?.setImageBitmap(bitmap)
        }
        else if(list.get(position).name.contains("YouTube")){
            val bitmap = BitmapFactory.decodeStream(context.resources.openRawResource(R.raw.youtube_icon))
            imageView?.setImageBitmap(bitmap)
        }
        else if(list.get(position).name.contains("Snapchat")){
            val bitmap = BitmapFactory.decodeStream(context.resources.openRawResource(R.raw.snapchat_icon))
            imageView?.setImageBitmap(bitmap)
        }
        else if(list.get(position).name.contains("Twitter")){
            val bitmap = BitmapFactory.decodeStream(context.resources.openRawResource(R.raw.twitter_icon))
            imageView?.setImageBitmap(bitmap)
        }
        else if(list.get(position).name.contains("LinkedIn")){
            val bitmap = BitmapFactory.decodeStream(context.resources.openRawResource(R.raw.linkedin_icon))
            imageView?.setImageBitmap(bitmap)
        }
        else if(list.get(position).name.contains("Gmail")){
            val bitmap = BitmapFactory.decodeStream(context.resources.openRawResource(R.raw.gmail_icon))
            imageView?.setImageBitmap(bitmap)
        }
        else if(list.get(position).name.contains("Instagram")){
            val bitmap = BitmapFactory.decodeStream(context.resources.openRawResource(R.raw.insta_icon))
            imageView?.setImageBitmap(bitmap)
        }
        else if(list.get(position).name.contains("Cricbuzz")){
            val bitmap = BitmapFactory.decodeStream(context.resources.openRawResource(R.raw.cricbuzz_icon))
            imageView?.setImageBitmap(bitmap)
        }
        else if(list.get(position).name.contains("Facebook")){
            val bitmap = BitmapFactory.decodeStream(context.resources.openRawResource(R.raw.fb_icon))
            imageView?.setImageBitmap(bitmap)
        }
        else
        {
            imageView?.setImageDrawable(list.get(position).image)
        }

        linearLayout.setOnLongClickListener(object : View.OnLongClickListener{
            override fun onLongClick(v: View?): Boolean {
                myInterface?.longClick(position)
                return true
            }

        })

    }
}