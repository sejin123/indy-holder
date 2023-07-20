package lec.baekseokuni.indyholder.credential;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.co.bdgen.indywrapper.data.Credential;
import lec.baekseokuni.indyholder.databinding.ItemCredentialBinding;

public class CredentialRecyclerViewAdapter extends RecyclerView.Adapter<CredentialRecyclerViewAdapter.ViewHolder> {

    private final List<Credential> credentialList;

    private final View.OnClickListener onNavCred = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    public CredentialRecyclerViewAdapter(List<Credential> items) {
        credentialList = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCredentialBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return credentialList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtCredId;
        public final TextView txtSchemaId;
        public final TextView txtCredDefId;

        public ViewHolder(ItemCredentialBinding binding) {
            super(binding.getRoot());
            txtCredId = binding.txtCredId;
            txtSchemaId = binding.txtSchemaId;
            txtCredDefId = binding.txtCredDefId;
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + txtCredId.getText() + "'";
        }
    }
}