package lec.baekseokuni.indyholder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    View.OnClickListener onStartDeeplink = v -> {
        String testDeeplink = "indy://holder?secret=test1";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(testDeeplink));
        startActivity(intent);
        finish();
    };
    View.OnClickListener onNavToCredList = v -> {

    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnIssueTest = findViewById(R.id.btn_issue_test_cred);
        btnIssueTest.setOnClickListener(onStartDeeplink);
    }
}
