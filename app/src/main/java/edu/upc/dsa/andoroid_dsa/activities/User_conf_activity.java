package edu.upc.dsa.andoroid_dsa.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import java.util.List;
import java.util.Locale;

import edu.upc.dsa.andoroid_dsa.Api;
import edu.upc.dsa.andoroid_dsa.R;
import edu.upc.dsa.andoroid_dsa.RetrofitClient;
import edu.upc.dsa.andoroid_dsa.models.Gadget;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_conf_activity extends AppCompatActivity {

    String language="en";
    Api APIservice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration_user);
    }
    public void change_language(View view){
        APIservice = RetrofitClient.getInstance().getMyApi();
        if(language.equals("en")){
            Call<Void> call = APIservice.changelng("es");
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
            Locale localizacion = new Locale("es", "ES");

            Locale.setDefault(localizacion);
            Configuration config = new Configuration();
            config.setLocale(localizacion);
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            this.setLanguage("es");
        }
        else{
            Call<Void> call = APIservice.changelng("en");
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
            Locale localizacion = new Locale("en", "US");

            Locale.setDefault(localizacion);
            Configuration config = new Configuration();
            config.setLocale(localizacion);
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            this.setLanguage("en");
        }

    }


    public void setLanguage(String language) {
        this.language = language;
    }
}