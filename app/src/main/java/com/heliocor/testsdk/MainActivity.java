package com.heliocor.testsdk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.heliocor.dokstor.init.DokstorSDK;
import com.heliocor.dokstor.interfaces.OnBoardProcessListener;
import com.heliocor.dokstor.interfaces.ProcessIdentityCardListener;
import com.heliocor.dokstor.interfaces.ProcessPassportListener;
import com.heliocor.dokstor.model.Address;
import com.heliocor.dokstor.model.ApiResponse;
import com.heliocor.dokstor.model.DKSTRUser;
import com.heliocor.dokstor.model.IdentityCard;
import com.heliocor.dokstor.model.Passport;
import com.heliocor.dokstor.model.Selfie;
import com.heliocor.dokstorid.init.DokstorID;
import com.heliocor.dokstorid.interfaces.ProcessIDCardListener;
import com.heliocor.dokstorid.utils.Constants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * Init singleton DokstorSDK instance
         */
        DokstorSDK.getInstance().initialize("TOKEN_AUTH_DOKSTOR");

        /**
         * Prepare onBoard Listener
         */
        final OnBoardProcessListener onBoardProcessListener = new OnBoardProcessListener() {
            @Override
            public void onPassportCaptured(Passport passport) {
                //  TODO Do what you need with the passport obtained
            }

            @Override
            public void onIdentityCardCaptured(IdentityCard identityCard) {
                //  TODO Do what you need with the IdentityCard obtained
            }

            @Override
            public void onSelfieCaptured(Selfie selfie) {
                //  TODO Do what you need with the Selfie obtained
            }

            @Override
            public void onUserDataCaptured(DKSTRUser dkstrUser) {
                //  TODO Do what you need with the User obtained
            }

            @Override
            public void onPersonalAddressCaptured(Address address) {
                //  TODO Do what you need with the Address obtained
            }

            @Override
            public void onCompleted(ApiResponse apiResponse) {
                //  TODO Do what you need with the ApiResponse obtained
            }

            @Override
            public void onError(String error) {
                //  TODO Do what you need with the error
            }
        };


        Button btn_onBoardNFC = findViewById(R.id.btn_onBoardNFC);
        btn_onBoardNFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Call onBoard with UI process
                 */
                DokstorSDK.getInstance().startOnBoardProcess(onBoardProcessListener, getApplicationContext(), true, "john.doe@example.com");
            }
        });
        Button btn_onBoard = findViewById(R.id.btn_onBoard);
        btn_onBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DokstorSDK.getInstance().startOnBoardProcess(onBoardProcessListener, getApplicationContext(), false, "john.doe@example.com");
            }
        });


        final ProcessPassportListener processPassportListener = new ProcessPassportListener() {
            @Override
            public void onResponse(Passport passport) {
                //TODO

            }

            @Override
            public void onError(String error) {
                //TODO

            }
        };
        final ProcessIdentityCardListener processIdentityCardListener = new ProcessIdentityCardListener() {
            @Override
            public void onResponse(IdentityCard identityCard) {
                Log.d("address", identityCard.toString());

            }

            @Override
            public void onError(String error) {
                Log.d("address", error);

            }
        };

        Button btn_read_passport = findViewById(R.id.btn_read_passport);
        btn_read_passport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DokstorSDK.getInstance().processPassportUI(processPassportListener, getApplicationContext(), false);
            }
        });
        Button btn_read_passport_with_nfc = findViewById(R.id.btn_read_passport_with_nfc);
        btn_read_passport_with_nfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DokstorSDK.getInstance().processPassportUI(processPassportListener, getApplicationContext(), true);
            }
        });
        Button btn_read_idcard = findViewById(R.id.btn_read_idcard);
        btn_read_idcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DokstorSDK.getInstance().processIdentityCardUI(processIdentityCardListener, getApplicationContext());
            }
        });

        final ProcessIdentityCardListener processIdentityCardListenerJP = new ProcessIdentityCardListener() {
            @Override
            public void onResponse(IdentityCard identityCard) {
                Log.d("Name ", identityCard.getName());
                Log.d("DocNumber ", identityCard.getDocumentNumber());
                Log.d("Birthdate ", identityCard.getBirthDate());
                Log.d("Expiration Date ", identityCard.getExpirationDate());
            }

            @Override
            public void onError(String error) {

            }
        };
        Button btn_process_jpn_id = findViewById(R.id.btn_process_jpn_id);
        btn_process_jpn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DokstorSDK.getInstance().processIdentityCardJPNUI(processIdentityCardListenerJP, getApplicationContext(), false, "john.doe@example.com");
            }
        });


        final ProcessIDCardListener processIDCardListener = new ProcessIDCardListener() {
            @Override
            public void onResponse(com.heliocor.dokstorid.model.IdentityCard identityCard) {
                com.heliocor.dokstorid.model.IdentityCard iD = identityCard;

            }

            @Override
            public void onError(String error) {

            }
        };

        Button btn_process_id = findViewById(R.id.btn_process_id);
        btn_process_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    In case
                DokstorID.getInstance().processIdentityCardUI(processIDCardListener, getApplicationContext(), Constants.IDENTITYCARD, false);
            }
        });

        Button btn_process_passport = findViewById(R.id.btn_process_passport);
        btn_process_passport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DokstorID.getInstance().processIdentityCardUI(processIDCardListener, getApplicationContext(), Constants.PASSPORT, false);
            }
        });
        Button btn_process_driveringLicence = findViewById(R.id.btn_process_driveringLicence);
        btn_process_driveringLicence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DokstorID.getInstance().processIdentityCardUI(processIDCardListener, getApplicationContext(), Constants.DRIVINGLICENCE, false);
            }
        });


    }
}