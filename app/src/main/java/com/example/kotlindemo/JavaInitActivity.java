package com.example.kotlindemo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.kotlindemo.Tutorial.A18_Companion;
import com.example.kotlindemo.Tutorial.MainKotlinActivity;

public class JavaInitActivity extends AppCompatActivity {

    String TAG = MainKotlinActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_java_init);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        A18_Companion.MyClass.MyObject.f();
        A18_Companion.MyClass.f();      // If JvmStatic annotation is there then its working as static method

    }
}