package ru.kartyshova.androidtv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.kartyshova.androidtv.di.dependency

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin{
            androidContext(this@MainActivity.applicationContext)
            modules(dependency)
        }
        setContentView(R.layout.activity_main)
    }


}