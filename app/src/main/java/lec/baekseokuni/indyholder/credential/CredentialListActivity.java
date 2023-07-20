package lec.baekseokuni.indyholder.credential;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import kr.co.bdgen.indywrapper.data.Credential;
import kr.co.bdgen.indywrapper.repository.CredentialRepository;
import lec.baekseokuni.indyholder.MyApplication;
import lec.baekseokuni.indyholder.R;

public class CredentialListActivity extends AppCompatActivity {
    CredentialRepository repository = new CredentialRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credential_list);
        ActionBar appBar = getSupportActionBar();
        if (appBar != null) {
            appBar.setTitle("증명서 목록");
            appBar.setDisplayHomeAsUpEnabled(true);
        }
        RecyclerView rvCredList = findViewById(R.id.list_credential);
        List<Credential> credentialList = getCredentialList();
        CredentialRecyclerViewAdapter adapter = new CredentialRecyclerViewAdapter(credentialList);
        rvCredList.setAdapter(adapter);
    }

    private List<Credential> getCredentialList() {
        List<Credential> credentialList = repository.getCredentialList(MyApplication.getWallet(), null);
        return credentialList;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}