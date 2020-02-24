package com.example.piets.hockeymanagement;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.local.UserIdStorageFactory;
import com.example.piets.hockeymanagement.Admin.AdminMenu_Activity;
import com.example.piets.hockeymanagement.Classes.Defaults;
import com.example.piets.hockeymanagement.Classes.Helper;
import com.example.piets.hockeymanagement.Coach.CoachMenu_Activity;

import static com.example.piets.hockeymanagement.R.mipmap.ic_logo_foreground;

public class Login_Activity extends AppCompatActivity {


    EditText edtEmailLogin , edtPasswordLogin;
    Button btnLogin;
    TextView tvRegister , tvForgotPassword;

    private String email, password;

    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        Backendless.setUrl(Defaults.SERVER_URL);
        Backendless.initApp(getApplicationContext(),
                Defaults.APPLICATION_ID,
                Defaults.API_KEY);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(ic_logo_foreground);
        actionBar.setTitle("Login");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);



        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);
        edtEmailLogin = findViewById(R.id.edt_email_login);
        edtPasswordLogin = findViewById(R.id.edt_password_login);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);
        tvForgotPassword = findViewById(R.id.tv_forgot_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                email = Helper.getText(edtEmailLogin);
                password = Helper.getText(edtPasswordLogin);

                //Backendless service to log in user
                showProgress(true);
                tvLoad.setText("Logging in...please wait...");
                Backendless.UserService.login(email, password, new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response)

                    {       BackendlessUser user = Backendless.UserService.CurrentUser();

                        if( user != null )
                        {
                            String role = (String) user.getProperty("role");
                            if(role.equals("admin"))
                            {
                                Intent intent = new Intent
                                        (Login_Activity.this,
                                                AdminMenu_Activity.class);
                                startActivity(intent);
                            }
                            else if(role.equals("coach"))
                            {
                                Intent intent = new Intent
                                        (Login_Activity.this,
                                                CoachMenu_Activity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Helper.customToastA(Login_Activity.this,"User has not been given a role by Admin!");
                            }
                        }
                        Helper.customToastA(Login_Activity.this,"Logged in.");
                    }

                    @Override
                    public void handleFault(BackendlessFault fault)
                    {
                        Helper.customToastA(Login_Activity.this,"Email and Password should be entered!");
                        showProgress(false);
                    }

                },true);


            }
        });

        //Backendless service to check if the login is valid


        Backendless.UserService.isValidLogin(new AsyncCallback<Boolean>() {
            @Override
            public void handleResponse(Boolean response) {

                if (response) {
                    final String userObjectId = UserIdStorageFactory.instance().getStorage().get();

                    Backendless.Data.of(BackendlessUser.class).findById(userObjectId, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(final BackendlessUser response) {
                            if (response != null) {
                                String role = (String) response.getProperty("role");


                                if (role.equals("admin")) {

                                    Intent intent = new Intent
                                            (Login_Activity.this,
                                                    com.example.piets.hockeymanagement.Admin.AdminMenu_Activity.class);

                                    startActivity(intent);
                                } else if (role.equals("coach"))
                                {
                                    Intent intent = new Intent
                                            (Login_Activity.this,
                                                    com.example.piets.hockeymanagement.Coach.CoachMenu_Activity.class);

                                    startActivity(intent);
                                }
                                else
                                {
                                    Helper.customToastA(Login_Activity.this,"User has not been given a role!");
                                    showProgress(false);
                                    Login_Activity.this.finish();
                                }
                            }

                        }

                        @Override
                        public void handleFault(BackendlessFault fault)
                        {
                            Toast.makeText(getBaseContext(), fault.getMessage(), Toast.LENGTH_LONG).show();
                            showProgress(false);
                        }

                    });
                }
                else
                    {

                    showProgress(false);
                }


            }

            @Override
            public void handleFault(BackendlessFault fault)
            {

            }
        });
    }



    public void registerClick(View view)
    {
        Intent intent = new Intent
                (Login_Activity.this,
                        com.example.piets.hockeymanagement.Register_Activity.class);

        startActivity(intent);
    }

    public void forgotClick(View view)
    {
        email = Helper.getText(edtEmailLogin);
        Backendless.UserService.restorePassword( email, new AsyncCallback<Void>()
        {
            public void handleResponse( Void response )
            {
                Helper.customToastA(Login_Activity.this,"Password reset has been sent to email!");
            }

            public void handleFault( BackendlessFault fault )
            {
                Toast.makeText(getBaseContext(), fault.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {

                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }



}
