package lec.baekseokuni.indyholder.credential;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kr.co.bdgen.indywrapper.data.Credential;
import lec.baekseokuni.indyholder.databinding.ItemCredentialBinding;

import java.util.List;

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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Credential credData = credentialList.get(position);
        holder.txtCredId.setText(credData.getId());
        holder.txtSchemaId.setText(credData.getSchemaId());
        holder.txtCredDefId.setText(credData.getCredDefId());
        holder.itemView.setOnClickListener(onNavCred);
    }

    @Override
    public int getItemCount() {
        return credentialList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtCredId;
        public final TextView txtSchemaId;
        public final TextView txtCredDefId;

        public ViewHolder(ItemCredentialBinding binding) {
            super(binding.getRoot());
            txtCredId = binding.txtCredId;
            txtSchemaId = binding.txtSchemaId;
            txtCredDefId = binding.txtCredDefId;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + txtCredId.getText() + "'";
        }
    }
}