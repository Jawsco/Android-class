package com.example.practice

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

data class Album_Info(val cover: String, val name: String, val artist: String, val sale_Year: String)

class MainActivity : AppCompatActivity()
{
    var album_List = arrayListOf<Album_Info>(
            Album_Info("infinite", "Infinite", "Eminem", "1996"),
            Album_Info("the_slim_shady_lp", "The Slim Shady LP", "Eminem", "1999"),
            Album_Info("the_marshall_mathers_lp", "The Marshall Mathers LP", "Eminem", "2000"),
            Album_Info("the_eminem_show", "The Eminem Show", "Eminem", "2002"),
            Album_Info("encore", "Encore", "Eminem", "2004"),
            Album_Info("relapse", "Relapse", "Eminem", "2009"),
            Album_Info("recovery", "Recovery", "Eminem", "2011"),
            Album_Info("the_marshall_mathers_lp_2", "The Marshall Mathers LP 2", "Eminem", "2013"),
            Album_Info("revival", "Revival", "Eminem", "2018"),
            Album_Info("kamikaze", "Kamikaze", "Eminem", "2019")
    )
    var mp3_Player : MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val album_Info_Adapater = Album_List_Adapter(this, album_List)
        album_ListView.adapter = album_Info_Adapater


        album_ListView.setOnItemClickListener { parent, view, position, id ->
            when(position)
            {
                0 ->
                {
                    val url : String = "https://namu.wiki/w/Infinite(에미넴)"
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                    mp3_Player = MediaPlayer.create(this, R.raw.eminem_infinite)
                    mp3_Player?.start()
                }
                1 ->
                {
                    val url : String = "https://namu.wiki/w/The%20Slim%20Shady%20LP"
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                }
                2 ->
                {
                    val url : String = "https://namu.wiki/w/The%20Marshall%20Mathers%20LP"
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                }
                3 ->
                {
                    val url : String = "https://namu.wiki/w/The%20Eminem%20Show"
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                }
                4 ->
                {
                    val url : String = "https://namu.wiki/w/Encore"
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                }
                5 ->
                {
                    val url : String = "https://namu.wiki/w/Relapse"
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                }
                6 ->
                {
                    val url : String = "https://namu.wiki/w/Recovery"
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                }
                7 ->
                {
                    val url : String = "https://namu.wiki/w/The%20Marshall%20Mathers%20LP%202"
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                }
                8 ->
                {
                    val url : String = "https://namu.wiki/w/Revival(음반)"
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                }
                9 ->
                {
                    val url : String = "https://namu.wiki/w/Kamikaze(에미넴)"
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                }
                else ->
                {
                    Toast.makeText(this, "오류입니다", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy()
    {
        super.onDestroy()
        mp3_Player?.release()
    }
}

class Album_List_Adapter(val context: Context, var list: ArrayList<Album_Info>) : BaseAdapter()
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View?
    {
        val view: View? = LayoutInflater.from(context).inflate(R.layout.album_list, null)

        val cover = view?.findViewById<ImageView>(R.id.album_Cover)
        val name = view?.findViewById<TextView>(R.id.album_Name)
        val artist = view?.findViewById<TextView>(R.id.album_Artist_Name)
        val sale_Year = view?.findViewById<TextView>(R.id.album_Sale_Year)


        val info = list[position]
        val resourceId = context.resources.getIdentifier(info.cover, "drawable", context.packageName)

        cover?.setImageResource(resourceId)
        name?.text = info.name
        artist?.text = info.artist
        sale_Year?.text = info.sale_Year

        return view
    }

    override fun getItem(position: Int): Any
    {
        return list[position]
    }

    override fun getItemId(position: Int): Long
    {
        return 0
    }

    override fun getCount(): Int
    {
        return list.size
    }
}
