package lec.baekseokuni.indyholder.credential;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.function.Consumer;

import kr.co.bdgen.indywrapper.data.Credential;
import lec.baekseokuni.indyholder.databinding.ItemCredentialBinding;

public class CredentialRecyclerViewAdapter extends RecyclerView.Adapter<CredentialRecyclerViewAdapter.ViewHolder> {

    private List<Credential> credentialList;
    @Nullable
    private Consumer<Credential> onDeleteCred;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCredentialBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Credential credData = credentialList.get(position);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), CredentialActivity.class);
            intent.putExtra(CredentialActivity.INTENT_EXTRA_ARG_KEY_CRED, (Parcelable) credData);
            v.getContext().startActivity(intent);
        });
        holder.binding.btnDeleteCred.setOnClickListener(v -> {
            if (onDeleteCred == null)
                return;
            onDeleteCred.accept(credData);
        });
        holder.binding.txtCredId.setText(credData.getId());
        holder.binding.txtSchemaId.setText(credData.getSchemaId());
        holder.binding.txtCredDefId.setText(credData.getCredDefId());
    }

    @Override
    public int getItemCount() {
        return credentialList.size();
    }

    public void setCredentialList(List<Credential> credentialList) {
        this.credentialList = credentialList;
        notifyDataSetChanged();
    }

    public void setOnDeleteCred(@Nullable Consumer<Credential> onDeleteCred) {
        this.onDeleteCred = onDeleteCred;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ItemCredentialBinding binding;

        public ViewHolder(ItemCredentialBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}