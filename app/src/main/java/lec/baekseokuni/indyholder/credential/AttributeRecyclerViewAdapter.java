package lec.baekseokuni.indyholder.credential;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kotlin.Pair;
import lec.baekseokuni.indyholder.databinding.ItemAttributeBinding;

public class AttributeRecyclerViewAdapter extends RecyclerView.Adapter<AttributeRecyclerViewAdapter.ViewHolder> {
    private final List<Pair<String, String>> attrList = new ArrayList<>();

    public AttributeRecyclerViewAdapter(Map<String, String> attributes) {
        attributes.forEach((key, value) -> attrList.add(new Pair<>(key, value)));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemAttributeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pair<String, String> nameAndValue = attrList.get(position);
        holder.txtAttrName.setText(nameAndValue.getFirst());
        holder.txtAttrValue.setText(nameAndValue.getSecond());
    }

    @Override
    public int getItemCount() {
        return attrList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtAttrName;
        public final TextView txtAttrValue;

        public ViewHolder(ItemAttributeBinding binding) {
            super(binding.getRoot());
            txtAttrName = binding.txtAttributeName;
            txtAttrValue = binding.txtAttributeValue;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + txtAttrName.getText() + "'";
        }
    }
}
