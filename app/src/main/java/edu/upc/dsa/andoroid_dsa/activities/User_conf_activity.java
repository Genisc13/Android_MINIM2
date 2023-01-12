package edu.upc.dsa.andoroid_dsa.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.List;
import java.util.Locale;

import edu.upc.dsa.andoroid_dsa.Api;
import edu.upc.dsa.andoroid_dsa.R;
import edu.upc.dsa.andoroid_dsa.RetrofitClient;
import edu.upc.dsa.andoroid_dsa.models.Gadget;
import edu.upc.dsa.andoroid_dsa.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_conf_activity extends AppCompatActivity {

    String language="en-us";
    Api APIservice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration_user);
    }
    public void change_language(View view){
        APIservice = RetrofitClient.getInstance().getMyApi();
        if(language.equals("en-us")){
            Call<Void> call = APIservice.changelng("es");
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
            setLocale("es");
            this.setLanguage("es");
        }
        else{
            Call<Void> call = APIservice.changelng("en-us");
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
            setLocale("default");
            this.setLanguage("en-us");
        }

    }


    public void setLanguage(String language) {
        this.language = language;
    }

    private void setLocale(String language) {
        Resources resources = getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale(language);
        resources.updateConfiguration(configuration, metrics);
        onConfigurationChanged(configuration);
        Intent intentRegister = new Intent(User_conf_activity.this, User_conf_activity.class);
        User_conf_activity.this.startActivity(intentRegister);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfiguration) {
        super.onConfigurationChanged(newConfiguration);
        //set strings from resources

    }
}