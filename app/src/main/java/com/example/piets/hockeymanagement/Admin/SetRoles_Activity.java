package com.example.piets.hockeymanagement.Admin;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.piets.hockeymanagement.Classes.BaseActivity;
import com.example.piets.hockeymanagement.Classes.Helper;
import com.example.piets.hockeymanagement.Classes.Users;
import com.example.piets.hockeymanagement.R;

import java.util.List;

import static com.example.piets.hockeymanagement.R.mipmap.ic_logo_foreground;

public class SetRoles_Activity extends BaseActivity {

    TextView tvlblChoose, tvlblChangerole;
    Spinner spnUser;
    RadioButton rbtnNone, rbtnCoach, rbtnAdmin;
    Button btnSaveChanges;
    Users userspn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_roles_);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(ic_logo_foreground);
        actionBar.setTitle("Set Roles");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);

        tvlblChoose = findViewById(R.id.tvlblChoose);
        tvlblChangerole = findViewById(R.id.tvlblChangeRole);

        spnUser =  findViewById(R.id.spnUser);

        rbtnNone = findViewById(R.id.rbtnNone);
        rbtnCoach = findViewById(R.id.rbtnCoach);
        rbtnAdmin = findViewById(R.id.rbtnAdmin);

        btnSaveChanges = findViewById(R.id.btnSaveChanges);


        //Add all users to the spinner
        showProgressDialog("Loading Users....");
        Backendless.Data.of( Users.class ).find(
                new AsyncCallback<List<Users>>(){
                    @Override
                    public void handleResponse( List<Users> users )
                    {
                        // the "Users" collection now contains instances of the Contact class.
                        // each instance represents an object stored on the server.
                        ArrayAdapter userAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner, users);
                        spnUser =(Spinner) findViewById(R.id.spnUser);
                        spnUser.setAdapter(userAdapter);
                        hideProgressDialog();

                    }
                    @Override
                    public void handleFault( BackendlessFault fault )
                    {
                        hideProgressDialog();
                        Toast.makeText(SetRoles_Activity.this, fault.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        btnSaveChanges.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                userspn = (Users) ( (Spinner) findViewById(R.id.spnUser) ).getSelectedItem();
                String role = "none";
                if(rbtnAdmin.isChecked())
                {
                    role = "admin";
                }
                else if (rbtnCoach.isChecked())
                {
                    role = "coach";
                }
                else if(rbtnNone.isChecked())
                {
                    role = "none";
                }
                else
                {
                    Helper.customToastA(SetRoles_Activity.this,"User has not been given a role!");
                }
                //final Users user = new Users();
                //user.setRole(role);
                final String finalRole = role;
                Backendless.Persistence.save( userspn, new AsyncCallback<Users>() {
                    public void handleResponse( Users response )
                    {
                        userspn.setRole(finalRole);

                        showProgressDialog("Assigning Role....");
                        Backendless.Persistence.save( userspn, new AsyncCallback<Users>() {
                            @Override
                            public void handleResponse( Users response )
                            {
                                hideProgressDialog();
                                Helper.customToastA(SetRoles_Activity.this,"User's Role has been Updated");
                            }
                            @Override
                            public void handleFault( BackendlessFault fault )
                            {
                                Helper.customToastA(SetRoles_Activity.this,"User's Role has not been Updated");
                            }
                        } );
                    }

                    @Override
                    public void handleFault( BackendlessFault fault )
                    {
                        // an error has occurred, the error code can be retrieved with fault.getCode()

                    }
                });
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
}
