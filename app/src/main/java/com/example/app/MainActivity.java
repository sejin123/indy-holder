package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.net.URL;

import kr.co.bdgen.indywrapper.data.payload.OfferPayload;
import kr.co.bdgen.indywrapper.repository.CredentialRepository;
import kr.co.bdgen.indywrapper.repository.IssuingRepository;

public class MainActivity extends AppCompatActivity {

    private OfferPayload offer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = (TextView) findViewById(R.id.txt_main);
        String credJsonArray = getCredential();
        text.setText(credJsonArray);

        //deeplink 처리
        //deeplink = indy://holder?secret=blarblar
        //scheme = indy
        //host = holder
        //queryparameter = secret

        Uri data = getIntent().getData();
        getIntent().getData();

        if(data == null) {
            return;
        }

        String secret = data.getQueryParameter("secret");
        Log.d("[SUCCESS]", secret);

        //1. offer 받기
        IssuingRepository repository = new IssuingRepository();
        repository.reuqestOffer(
                secret,
                offerPayload -> {
                    Log.d("[SUCCESS]", offerPayload.getCredDefJson() + "\n" + offerPayload.getCredOfferJson());
                    offer = offerPayload;

                    //request and issue credential
                    repository.requestCredential(
                            MyApplication.getWallet(),
                            MyApplication.getDid(this),
                            MyApplication.getMasterSecret(this),
                            secret,
                            offer,
                            (credentialInfo, issuePayload) -> {
                                Log.d(
                                        "[SUCCESS]",
                                        credentialInfo.getCredReqMetadataJson() +
                                                "\n" +
                                                credentialInfo.getCredReqJson() +
                                                "\n" +
                                                credentialInfo.getTestCredDefId() +
                                                "\n" +
                                                issuePayload.getCredentialJson()
                                );

                                //3. store credential
                                repository.storeCredential(
                                        MyApplication.getWallet(),
                                        credentialInfo,
                                        issuePayload,
                                        cred -> {
                                            Log.i("[SUCCESS]", "credential = " + cred);
                                            return null;
                                        },
                                        error -> {
                                            Log.e("[ERROR]", error.getMessage(), error);
                                            return null;
                                        }
                                );
                                return null;

                            },
                            throwable -> {
                                Log.e("[ERROR]", throwable.getMessage(), throwable);
                                return null;
                            }
                    );
                    return null;
                },
                error -> {
                    Log.e("[ERROR]", error.getMessage(), error);
                    return null;
                }
        );


    }

    private String getCredential() {
        String credential;
        CredentialRepository credentialRepository = new CredentialRepository();
        credential = credentialRepository.getRawCredentials(
                MyApplication.getWallet(),
                "{}"
        );
        return credential;
    }
}