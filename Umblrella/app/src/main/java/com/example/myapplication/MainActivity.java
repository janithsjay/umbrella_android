package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    String city="Colombo";
    TextView v1,v2,v3;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

//    public void doInBack() {
//        URL url = null;
//        try {
//            url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=colombo&APPID=9afd76322b0263b66ccefadb523d549d");
//            HttpURLConnection http = (HttpURLConnection) url.openConnection();
//            InputStream is = http.getInputStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            String ln = "";
//            while (ln != null) {
//                ln = br.readLine();
//            }
//            System.out.println(ln);
//            System.out.println("**************************************************");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }

    public void test() {
        NotificationCompat.Builder nb = new
                NotificationCompat.Builder(this, "nTest")
                .setSmallIcon(R.drawable.ic_stat_message)
                .setContentTitle("Umbrella !!!")
                .setContentText("It's seems likes to be rainy today, better to keep the umbrella with you")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat nm = NotificationManagerCompat.from(this);
        int id1 = 1;
        nm.notify(id1, nb.build());


    }
    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            String data = ( (new WeatherHTTPClient()).getWeatherData(city));
            System.out.println("++++++++++++++++"+data+"+++++++++++++++++");
            System.out.println(data);
            try {
                weather = JSONWeatherParser.getWeather(data);

                // Let's retrieve the icon
//                weather.iconData = ( (new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));

            } catch (Exception e) {
                e.printStackTrace();
            }

            return weather;

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        v1=findViewById(R.id.View);
        v2=findViewById(R.id.View2);
        v3=findViewById(R.id.View3);

        findViewById(R.id.navigation_dashboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v1.setText(JSONWeatherParser.condition);
                v2.setText("");
                v3.setText("");
            }
        });

        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(city);




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel chanel = new NotificationChannel("nTest", "nTest", NotificationManager.IMPORTANCE_DEFAULT);
            chanel.setDescription("Rainy");
            NotificationManager m = getSystemService(NotificationManager.class);
            m.createNotificationChannel(chanel);
        }

        findViewById(R.id.navigation_notifications).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (JSONWeatherParser.notify){
                    test();
                }
            }
        });




    }
}

