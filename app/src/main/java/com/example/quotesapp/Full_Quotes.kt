package com.example.quotesapp

import android.content.ClipData
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Environment
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.quotesapp.databinding.ActivityFullQuotesBinding
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class Full_Quotes : AppCompatActivity(),TextToSpeech.OnInitListener {

    lateinit var savedFile: File
    lateinit var binding: ActivityFullQuotesBinding
    lateinit var id: String
    lateinit var Cname: String
    lateinit var Aid: String
    lateinit var Aname: String
    lateinit var Body: String
    lateinit var tts :TextToSpeech
    var i = 0
    var status = 0
    var BgImg = intArrayOf(
        R.drawable.alone_bg_1,
        R.drawable.alone_bg_2,
        R.drawable.alone_bg_3,
        R.drawable.alone_bg_4,
        R.drawable.alone_bg_5,
        R.drawable.alone_bg_6,
        R.drawable.friendship_bg_1,
        R.drawable.friendship_bg_2,
        R.drawable.friendship_bg_3,
        R.drawable.friendship_bg_4,
        R.drawable.friendship_bg_5,
        R.drawable.friendship_bg_6,
        R.drawable.motivational_bg_1,
        R.drawable.motivational_bg_2,
        R.drawable.motivational_bg_3,
        R.drawable.motivational_bg_4,
        R.drawable.motivational_bg_5,
        R.drawable.motivational_bg_6,
        R.drawable.strength_bg_1,
        R.drawable.strength_bg_2,
        R.drawable.strength_bg_3,
        R.drawable.strength_bg_4,
        R.drawable.strength_bg_5,
        R.drawable.strength_bg_6,
        R.drawable.attitude_bg_1,
        R.drawable.attitude_bg_2,
        R.drawable.attitude_bg_3,
        R.drawable.attitude_bg_4,
        R.drawable.attitude_bg_5,
        R.drawable.attitude_bg_6,
        R.drawable.trust_bg_1,
        R.drawable.trust_bg_2,
        R.drawable.trust_bg_3,
        R.drawable.trust_bg_4,
        R.drawable.trust_bg_5,
        R.drawable.trust_bg_6,
        R.drawable.happiness_bg_1,
        R.drawable.happiness_bg_2,
        R.drawable.happiness_bg_3,
        R.drawable.happiness_bg_4,
        R.drawable.happiness_bg_5,
        R.drawable.happiness_bg_6,
        R.drawable.life_bg_1,
        R.drawable.life_bg_2,
        R.drawable.life_bg_3,
        R.drawable.life_bg_4,
        R.drawable.life_bg_5,
        R.drawable.life_bg_6,
        R.drawable.nature_bg_1,
        R.drawable.nature_bg_2,
        R.drawable.nature_bg_3,
        R.drawable.nature_bg_4,
        R.drawable.nature_bg_5,
        R.drawable.nature_bg_6,
        R.drawable.relationship_bg_1,
        R.drawable.relationship_bg_2,
        R.drawable.relationship_bg_3,
        R.drawable.relationship_bg_4,
        R.drawable.relationship_bg_5,
        R.drawable.relationship_bg_6,
        R.drawable.success_bg_1,
        R.drawable.success_bg_2,
        R.drawable.success_bg_3,
        R.drawable.success_bg_4,
        R.drawable.success_bg_5,
        R.drawable.success_bg_6,
        R.drawable.bestlife_bg_1,
        R.drawable.bestlife_bg_2,
        R.drawable.bestlife_bg_3,
        R.drawable.bestlife_bg_4,
        R.drawable.bestlife_bg_5,
        R.drawable.bestlife_bg_6,
        R.drawable.love_bg_1,
        R.drawable.love_bg_2,
        R.drawable.love_bg_3,
        R.drawable.love_bg_4,
        R.drawable.love_bg_5,
        R.drawable.love_bg_6,
        R.drawable.positive_bg_1,
        R.drawable.positive_bg_2,
        R.drawable.positive_bg_3,
        R.drawable.positive_bg_4,
        R.drawable.positive_bg_5,
        R.drawable.positive_bg_6,
        R.drawable.smile_bg_1,
        R.drawable.smile_bg_2,
        R.drawable.smile_bg_3,
        R.drawable.smile_bg_4,
        R.drawable.smile_bg_5,
        R.drawable.smile_bg_6,
        R.drawable.time_bg_1,
        R.drawable.time_bg_2,
        R.drawable.time_bg_3,
        R.drawable.time_bg_4,
        R.drawable.time_bg_5,
        R.drawable.time_bg_6
    )


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityFullQuotesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        id = intent.getStringExtra("id").toString()
        Cname = intent.getStringExtra("Cname").toString()
        Aname = intent.getStringExtra("Aname").toString()
        Body = intent.getStringExtra("Body").toString()
        Aid = intent.getStringExtra("Aid").toString()

        binding.Slogans.setText(Body).toString()
        binding.txtAName.setText(Aname).toString()
        binding.txtCName.setText(Cname).toString()

        initClick()

        tts = TextToSpeech(this, this)
    }

    fun initClick() {
        status = 0

        binding.imgLike.setOnClickListener {

            if (status==0)
            {
                binding.imgLike.setImageResource(R.drawable.ic_baseline_favorite_24)
                status = 1
            }
            else
            {
                binding.imgLike.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                status=0
            }
        }

        binding.imgShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, Body)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        binding.imgSpeak.setOnClickListener {
            val text = binding.Slogans.text.toString()
            tts.language = Locale.US
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
        }

        binding.imgCopy.setOnClickListener{
            Toast.makeText(this, "copy", Toast.LENGTH_SHORT).show()
        }

        binding.quotesImg.setOnClickListener {
            if (i < BgImg.size) {
                binding.quotesImg.setImageResource(BgImg[i++])
            } else if (i == BgImg.size) {
                i = 0
                binding.quotesImg.setImageResource(BgImg[i++])
            }
        }

        binding.imgDownload.setOnClickListener {
            saveImage(binding.quotesImg.drawable)
        }
    }

    fun saveImage(drawable: Drawable) {
        val file = getDisc()

        if (!file.exists() && !file.mkdirs()) {
            file.mkdir()
        }

        val simpleDateFormat = SimpleDateFormat("yyyymmsshhmmss")
        val date = simpleDateFormat.format(Date())
        val name = "IMG" + date + ".jpg"
        val fileName = file.absolutePath + "/" + name
        val newFile = File(fileName)

        try {
            val draw = drawable as BitmapDrawable
            val bitmap = draw.bitmap
            val fileOutPutStream = FileOutputStream(newFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutPutStream)
            Toast.makeText(this, "File saved succesfully", Toast.LENGTH_SHORT)
                .show()
            savedFile = newFile
            fileOutPutStream.flush()
            fileOutPutStream.close()

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    private fun getDisc(): File {
        val file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        return File(file, "Quotes Image")
    }

    override fun onInit(p0: Int) {

    }
}