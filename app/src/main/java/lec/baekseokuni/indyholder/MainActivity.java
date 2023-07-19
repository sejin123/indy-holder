package lec.baekseokuni.indyholder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import lec.baekseokuni.indyholder.credential.CredentialActivity;

public class MainActivity extends AppCompatActivity {
    View.OnClickListener onStartDeeplink = new View.OnClickListener() {
        final String testDeeplink = "indy://holder?secret=test1";

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(testDeeplink));
            startActivity(intent);
            finish();
        }
    };
    View.OnClickListener onNavToCredList = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), CredentialActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //버튼 클릭 기능 구현
        Button issueTest = findViewById(R.id.btn_issue_test_cred);
        Button navCredList = findViewById(R.id.btn_nav_to_cred_list);
        issueTest.setOnClickListener(onStartDeeplink);
        navCredList.setOnClickListener(onNavToCredList);
    }
}
