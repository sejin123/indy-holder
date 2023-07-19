package lec.baekseokuni.indyholder.credential;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import kr.co.bdgen.indywrapper.data.Credential;
import kr.co.bdgen.indywrapper.repository.CredentialRepository;
import lec.baekseokuni.indyholder.MyApplication;
import lec.baekseokuni.indyholder.R;

public class CredentialListActivity extends AppCompatActivity {
    private final CredentialRepository repository = new CredentialRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credential_list);

        List<Credential> credentialList = getCredentialList();
        CredentialRecyclerViewAdapter recyclerViewAdapter = new CredentialRecyclerViewAdapter(credentialList);
        RecyclerView recyclerView = findViewById(R.id.list_credential);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private List<Credential> getCredentialList() {
        List<Credential> credentialList = repository.getCredentialList(MyApplication.getWallet(), null);
        return new ArrayList<>(credentialList);
    }
}