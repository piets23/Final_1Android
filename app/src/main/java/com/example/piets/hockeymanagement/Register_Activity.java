package com.example.piets.hockeymanagement;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.piets.hockeymanagement.Classes.Defaults;
import com.example.piets.hockeymanagement.Classes.Helper;

import static com.example.piets.hockeymanagement.R.mipmap.ic_logo_foreground;

public class Register_Activity extends AppCompatActivity {


    EditText edtEmailRegister, edtNameRegister, edtSurnameRegister, edtPasswordRegister,
            edtRetypePasswordRegister;

    Button btnRegister;

    private String email, name, surname, password, retypePassword;

    //Saving instance




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        Backendless.setUrl(Defaults.SERVER_URL);
        Backendless.initApp(getApplicationContext(),
                Defaults.APPLICATION_ID,
                Defaults.API_KEY);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(ic_logo_foreground);
        actionBar.setTitle("Register");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);



        edtEmailRegister =(EditText) findViewById(R.id.edt_email_register);
        edtNameRegister =(EditText) findViewById(R.id.edt_name_register);
        edtSurnameRegister =(EditText) findViewById(R.id.edt_surname_register);
        edtPasswordRegister =(EditText) findViewById(R.id.edt_password_register);
        edtRetypePasswordRegister =(EditText) findViewById(R.id.edt_retype_password_register);
        btnRegister =(Button) findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                email = Helper.getText(edtEmailRegister);
                name = Helper.getText(edtNameRegister);
                surname = Helper.getText(edtSurnameRegister);
                password = Helper.getText(edtRetypePasswordRegister);
                retypePassword = Helper.getText(edtRetypePasswordRegister);

                 if(email.isEmpty()|| name.isEmpty() || surname.isEmpty()||password.isEmpty() || retypePassword.isEmpty())
                 {
                     Helper.customToastA(Register_Activity.this,"All the fields is not completed!");
                 }
                 else if (retypePassword != password)
                 {
                     Helper.customToastA(Register_Activity.this,"The passwords did not match!");
                 }
                 else
                     {

                     BackendlessUser user = new BackendlessUser();
                     user.setPassword(password);
                     user.setProperty("email", email);
                     user.setProperty("name", name);
                     user.setProperty("surname", surname);


                     //Backendless service to register user

                     Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                         @Override
                         public void handleResponse(BackendlessUser response) {
                             Helper.customToastA(Register_Activity.this,"User has been registered");
                         }

                         @Override
                         public void handleFault(BackendlessFault fault) {
                             Toast.makeText(getBaseContext(), fault.getMessage(), Toast.LENGTH_LONG).show();
                         }


                     });
                 }
            }
        });


    }
}

