package lec.baekseokuni.indyholder.credential;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kotlin.Pair;
import kr.co.bdgen.indywrapper.data.Credential;
import lec.baekseokuni.indyholder.R;

public class CredentialActivity extends AppCompatActivity {
    public static final String INTENT_EXTRA_ARG_KEY_CRED = "INTENT_EXTRA_ARG_KEY_CRED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credential);
        ActionBar appBar = getSupportActionBar();
        if (appBar != null) {
            appBar.setTitle("증명서 상세");
            appBar.setDisplayHomeAsUpEnabled(true);
        }
        //증명서 목록에서 받아온 상세 증명서 데이터
        Credential cred = getIntent().getParcelableExtra(INTENT_EXTRA_ARG_KEY_CRED);
        if (cred == null)
            return;
        Map<String, String> attrs = cred.getAttrs();
        if (attrs == null)
            attrs = new HashMap<>();
        ArrayList<Pair<String, String>> nameAndValueList = new ArrayList<>();
        //순서: name, number, exit~, crime_name, date
        nameAndValueList.add(new Pair<>("이름:", attrs.get("name")));
        nameAndValueList.add(new Pair<>("전화번호:", attrs.get("phoneNumber")));
        nameAndValueList.add(new Pair<>("범죄 유무:", attrs.get("existence_of_crime")));
        nameAndValueList.add(new Pair<>("범죄 이름:", attrs.get("crime_name")));
        nameAndValueList.add(new Pair<>("범죄 날짜:", attrs.get("crime_date")));


        RecyclerView rvAttributes = findViewById(R.id.list_attribute);
        AttributeRecyclerViewAdapter adapter = new AttributeRecyclerViewAdapter(nameAndValueList);
        rvAttributes.setAdapter(adapter);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}