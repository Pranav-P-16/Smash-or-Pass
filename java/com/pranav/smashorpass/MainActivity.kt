package com.pranav.smashorpass


import android.content.ContentValues.TAG
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.util.Random
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ison=isOnline(this)
        if (ison==false) {
            Toast.makeText(applicationContext, "No Internet Lawde!", Toast.LENGTH_SHORT).show()
            moveTaskToBack(true);
            exitProcess(-1)
        }

        val random = Random()
        fun rand(from: Int, to: Int) : Int {
            return random.nextInt(to - from) + from
        }
        var a=rand(0,3)
        var score=0

        val musicButton = findViewById<ImageView>(R.id.imageView)


        fun img(){
            Picasso.with(this).load("https://xsgames.co/randomusers/avatar.php?g=female")
                .into(musicButton);
            a=rand(0,3)
        }
        img()
        //var count_smash=0
        val smashButton=findViewById<MaterialButton>(R.id.button1)
        val passButton=findViewById<MaterialButton>(R.id.button2)
        val textvi = findViewById<TextView>(R.id.textView)
        smashButton.setOnClickListener {
            if (a == 1 || a == 2) {
                score += 1
                textvi.setText(score.toString())
                Toast.makeText(applicationContext, "She's Hot! ", Toast.LENGTH_SHORT).show()
            } else {
                score -= 1
                textvi.setText(score.toString())
                Toast.makeText(applicationContext, "Are you serious? ", Toast.LENGTH_SHORT).show()
            }
            smashButton.setEnabled(false)
            passButton.setEnabled(false)
            Handler().postDelayed(Runnable { // This method will be executed once the timer is over
                smashButton.setEnabled(true)
                passButton.setEnabled(true)
                Log.d(TAG, "resend1")
            }, 2000)
            Picasso.with(this).invalidate("https://xsgames.co/randomusers/avatar.php?g=female");
            Picasso.with(this).load("https://xsgames.co/randomusers/avatar.php?g=female")
                .networkPolicy(
                    NetworkPolicy.NO_CACHE
                ).memoryPolicy(MemoryPolicy.NO_CACHE);
            img()
        }

        passButton.setOnClickListener {
            if (a==0) {
                score+=1
                textvi.setText(score.toString())
                Toast.makeText(applicationContext, "Phew!!! ", Toast.LENGTH_SHORT).show()
            } else {
                score-=1
                textvi.setText(score.toString())
                Toast.makeText(applicationContext, "She was hot Mannn ", Toast.LENGTH_SHORT).show()
            }
            smashButton.setEnabled(false)
            passButton.setEnabled(false)
            Handler().postDelayed(Runnable { // This method will be executed once the timer is over
                smashButton.setEnabled(true)
                passButton.setEnabled(true)
                Log.d(TAG, "resend1")
            }, 2000)
            Picasso.with(this).invalidate("https://xsgames.co/randomusers/avatar.php?g=female");
            Picasso.with(this).load("https://xsgames.co/randomusers/avatar.php?g=female").networkPolicy(
                NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE);
            img()
        }

    }
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}