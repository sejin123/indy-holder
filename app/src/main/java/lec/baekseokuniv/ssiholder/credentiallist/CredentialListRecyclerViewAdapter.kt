package lec.baekseokuniv.ssiholder.credentiallist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import lec.baekseokuniv.ssiholder.data.Credential
import lec.baekseokuniv.ssiholder.databinding.ItemCredentialBinding

class CredentialListRecyclerViewAdapter(
    private val itemList: List<Credential>
) : RecyclerView.Adapter<CredentialListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCredentialBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.txtId.text = item.id
        holder.txtName.text = item.name
    }

    override fun getItemCount(): Int = itemList.size

    inner class ViewHolder(binding: ItemCredentialBinding) : RecyclerView.ViewHolder(binding.root) {
        val txtId: TextView = binding.txtCredentialId
        val txtName: TextView = binding.txtCredentialName

        override fun toString(): String {
            return "${super.toString()} '${txtName.text}'"
        }
    }
}