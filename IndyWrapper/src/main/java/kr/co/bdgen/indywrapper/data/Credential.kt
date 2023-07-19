package kr.co.bdgen.indywrapper.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

/**
 * json 내 key: cred_info
 */
@Parcelize
data class Credential(
    @SerializedName("referent") val id: String,
    @SerializedName("schema_id") val schemaId: String,
    @SerializedName("cred_def_id") val credDefId: String,
    @SerializedName("rev_reg_id") val revRegId: String? = null,
    @SerializedName("cred_rev_id") val credRevId: String? = null,
    val attrs: MutableMap<String, String>? = null,
) : Parcelable, Serializable