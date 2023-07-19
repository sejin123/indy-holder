package lec.baekseokuni.indyholder.issue;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import kr.co.bdgen.indywrapper.data.argument.CredentialInfo;
import kr.co.bdgen.indywrapper.data.payload.IssuePayload;
import kr.co.bdgen.indywrapper.data.payload.OfferPayload;
import kr.co.bdgen.indywrapper.repository.IssuingRepository;
import lec.baekseokuni.indyholder.MyApplication;
import lec.baekseokuni.indyholder.R;

public class IssueActivity extends AppCompatActivity {
    private final IssuingRepository repository = new IssuingRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);

        //deeplink 처리
        //deeplink = indy://holder?secret=blarblar
        // scheme = indy
        // host = holder
        // queryParameter = secret
        Uri data = getIntent().getData();
        if (data == null)
            return;
        String secret = data.getQueryParameter("secret");
        Log.d("[SUCCESS/DEEPLINK]", secret);

        //증명서 발급 과정: offer -> issue -> store
        getOffer(secret);
    }

    private void getOffer(String secret) {
        //1. offer 받기
        repository.requestOffer(
                secret,
                offerPayload -> {
                    //응답 성공 시
                    issueCredential(secret, offerPayload);
                    Log.d("[SUCCESS/OFFER]", "credDef = " + offerPayload.getCredDefJson() + "\n" + "offer = " + offerPayload.getCredOfferJson());
                    return null;
                },
                error -> {
                    //응답 실패 시
                    Log.e("[ERROR!/OFFER]", error.getMessage(), error);
                    return null;
                }
        );
    }

    private void issueCredential(String secret, OfferPayload offerPayload) {
        //2. request and issue credential
        repository.requestCredential(
                MyApplication.getWallet(),
                MyApplication.getDid(this),
                MyApplication.getMasterSecret(this),
                secret,
                offerPayload,
                (credentialInfo, issuePayload) -> {
                    //발급 성공 시
                    storeCredential(credentialInfo, issuePayload);
                    Log.d("[SUCCESS/ISSUE]", "credReqMeta = " + credentialInfo.getCredReqMetadataJson() + "\n" + "credReq = " + credentialInfo.getCredReqJson() + "\n" + "credDef = " + credentialInfo.getCredDefJson() + "\n" + "cred = " + issuePayload.getCredentialJson());
                    return null;
                },
                error -> {
                    //발급 실패 시
                    Log.e("[ERROR!/ISSUE]", error.getMessage(), error);
                    return null;
                }
        );
    }

    private void storeCredential(CredentialInfo credentialInfo, IssuePayload issuePayload) {
        //3. store credential
        repository.storeCredential(
                MyApplication.getWallet(),
                credentialInfo,
                issuePayload,
                cred -> {
                    //저장 성공 시
                    Log.i("[SUCCESS/STORE]", "credId(referent) = " + cred);
                    return null;
                },
                error -> {
                    //저장 실패 시
                    Log.e("[ERROR!/STORE]", error.getMessage(), error);
                    return null;
                }
        );
    }
}