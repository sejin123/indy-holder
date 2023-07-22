package lec.baekseokuni.indyholder.credential;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import kotlin.Pair;
import lec.baekseokuni.indyholder.databinding.ItemAttributeBinding;

public class AttributeRecyclerViewAdapter extends RecyclerView.Adapter<AttributeRecyclerViewAdapter.ViewHolder> {
    private final List<Pair<String, String>> attrList;

    public AttributeRecyclerViewAdapter(List<Pair<String, String>> attributes) {
        attrList = attributes;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemAttributeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pair<String, String> attribute = attrList.get(position);
        holder.txtAttrName.setText(attribute.getFirst());
        holder.txtAttrValue.setText(attribute.getSecond());
    }

    @Override
    public int getItemCount() {
        return attrList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtAttrName;
        public final TextView txtAttrValue;

        public ViewHolder(ItemAttributeBinding binding) {
            super(binding.getRoot());
            txtAttrName = binding.txtAttributeName;
            txtAttrValue = binding.txtAttributeValue;
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + txtAttrName.getText() + "'";
        }
    }
}
