package com.example.piets.hockeymanagement.Admin;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.piets.hockeymanagement.Classes.FragmentBase;
import com.example.piets.hockeymanagement.Classes.Helper;
import com.example.piets.hockeymanagement.Classes.Opponent;
import com.example.piets.hockeymanagement.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddOpponent_Fragment extends FragmentBase
{
     EditText edtOpponentName;
     Button btnSubmitOpponent;
     private String opponentName;


    public AddOpponent_Fragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_opponent_, container, false);

        edtOpponentName = rootView.findViewById(R.id.edt_opponent_name);
        btnSubmitOpponent = rootView.findViewById(R.id.btn_submit_opponent);

        btnSubmitOpponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                opponentName = Helper.getText(edtOpponentName);
                if(opponentName.isEmpty())
                {
                    Toast.makeText(getActivity(), "Field must have a value!",
                            Toast.LENGTH_LONG).show();
                }
                else
                {
                   Opponent opponent = new Opponent();
                   opponent.setName(opponentName);

                    // save object asynchronously

                    showProgressDialog("Saving Opponent...");
                    Backendless.Persistence.save( opponent, new AsyncCallback<Opponent>() {
                        public void handleResponse( Opponent response )
                        {
                            hideProgressDialog();

                        }

                        public void handleFault( BackendlessFault fault )
                        {
                            hideProgressDialog();
                            Toast.makeText(getActivity().getBaseContext(), fault.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });
                }
            }
        });

        return rootView;

    }


}
