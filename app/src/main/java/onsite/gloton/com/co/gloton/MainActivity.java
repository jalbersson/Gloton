package onsite.gloton.com.co.gloton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.TimerTask;

import onsite.gloton.com.co.gloton.activity.GalleryActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }
}
