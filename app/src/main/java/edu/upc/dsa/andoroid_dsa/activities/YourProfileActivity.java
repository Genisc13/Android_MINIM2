package edu.upc.dsa.andoroid_dsa.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

import edu.upc.dsa.andoroid_dsa.Api;
import edu.upc.dsa.andoroid_dsa.R;
import edu.upc.dsa.andoroid_dsa.RetrofitClient;
import edu.upc.dsa.andoroid_dsa.models.Gadget;
import edu.upc.dsa.andoroid_dsa.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YourProfileActivity extends AppCompatActivity{
    public String idUser;
    public String username;
    public String surname;
    public String birthday;
    public String email;
    public String password;
    public String coins;
    EditText editorTitle,editorUsername,editorSurname,editorBirthday,editorEmail,editorPassword,editor_coins;
    public List<Gadget> gadgetsOfTheUser;
    private RecyclerView recyclerViewGadgets;
    private RecyclerViewAdapter adapterGadgets;
    Api APIservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.your_profile_main);
        this.getVariables();
        editorTitle = (EditText) findViewById (R.id.title_profile);
        editorUsername = (EditText) findViewById (R.id.user_name);
        editorSurname = (EditText) findViewById (R.id.sur_name);
        editorBirthday = (EditText) findViewById (R.id.birth_day);
        editorEmail = (EditText) findViewById (R.id.e_mail);
        editorPassword = (EditText) findViewById (R.id.pass_word);
        editor_coins = (EditText) findViewById(R.id.co_ins);
        this.updateLabels();
        this.getUserIdFromDashboard();
        //recyclerViewGadgets=(RecyclerView)findViewById(R.id.recyclerGadgetYourProfile);
        //Log.d("DDDD", ""+recyclerViewGadgets);
        //recyclerViewGadgets.setLayoutManager(new LinearLayoutManager(this));
        APIservice = RetrofitClient.getInstance().getMyApi();
        Call<List<Gadget>> call = APIservice.purchasedGadgets(this.idUser);

        try {
            this.gadgetsOfTheUser=call.execute().body();
            //adapterGadgets = new RecyclerViewAdapter(call.execute().body(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //recyclerViewGadgets.setAdapter(adapterGadgets);

    }
    public void updateLabels(){
        String updateTitle =getString(R.string.updating_title);
        updateTitle=this.username+" !";
        this.editorTitle.setText(updateTitle);
        String updateUsername =getString(R.string.updating_username);
        updateUsername=this.username;
        this.editorUsername.setText(updateUsername);
        String updateSurname =getString(R.string.updating_surname);
        updateSurname=this.surname;
        this.editorSurname.setText(updateSurname);
        String updateBirthday =getString(R.string.updating_birthday);
        updateBirthday=this.birthday;
        this.editorBirthday.setText(updateBirthday);
        String updateEmail =getString(R.string.updating_email);
        updateEmail=this.email;
        this.editorEmail.setText(updateEmail);
        String updatePassword =getString(R.string.updating_password);
        updatePassword=this.password;
        this.editorPassword.setText(updatePassword);
        String update_coins= getString(R.string.updating_coins);
        update_coins=this.coins;
        this.editor_coins.setText(update_coins);
    }

    public void getVariables() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInformation", Context.MODE_PRIVATE);
        this.username = sharedPreferences.getString("username", null).toString();
        this.surname = sharedPreferences.getString("surname", null).toString();
        this.birthday = sharedPreferences.getString("birthday", null).toString();
        this.email = sharedPreferences.getString("email", null).toString();
        this.password = sharedPreferences.getString("password", null).toString();
        this.coins=sharedPreferences.getString("coins",null).toString();
    }

    public void Return(View view){
        Intent intentRegister = new Intent(YourProfileActivity.this, DashBoardActivity.class);
        YourProfileActivity.this.startActivity(intentRegister);
    }
    public void enterConfiguration(View view){
        Intent intentRegister = new Intent(YourProfileActivity.this, User_conf_activity.class);
        YourProfileActivity.this.startActivity(intentRegister);
    }

    /*
    public void editUser(View view){
        String userEdit=editorUsername.getText().toString();
        String surnameEdit=editorSurname.getText().toString();
        String birthdayEdit=editorBirthday.getText().toString();
        String emailEdit=editorEmail.getText().toString();
        String passwordEdit=editorPassword.getText().toString();
        if(this.username.equals(userEdit) && this.surname.equals(surnameEdit) && this.birthday.equals(birthdayEdit) && this.email.equals(emailEdit) && this.password.equals(passwordEdit)){
            return;
        }
        else {
            User user = new User(userEdit,surnameEdit,birthdayEdit,emailEdit,passwordEdit,Integer.valueOf(this.coins));
            APIservice = RetrofitClient.getInstance().getMyApi();
            Call<Void> call= APIservice.updateUser(user);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(YourProfileActivity.this,"The user was updated correctly",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(YourProfileActivity.this,"The user was not updated correctly",Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
    */
    public void getUserIdFromDashboard(){
        SharedPreferences sharedPreferences = getSharedPreferences("userId", Context.MODE_PRIVATE);
        this.idUser = sharedPreferences.getString("userId", null).toString();
    }

    public void openGadgetsOfUser(View view){
        if(this.gadgetsOfTheUser==null){
            Toast.makeText(YourProfileActivity.this,"You have not bought any gadgets, go to the shop!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent =new Intent(YourProfileActivity.this, GadgetsOfTheUser.class);
        this.saveUserIdAndName(this.idUser,this.username);
        this.startActivity(intent);
    }
    public void saveUserIdAndName(String userId, String username){
        SharedPreferences sharedPreferences= getSharedPreferences("userIdAndUsername", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString("userId", userId);
        editor.putString("name",username);
        Log.i("SAVING: ",userId);
        Log.i("SAVING: ",username);
        editor.apply();
    }

}
