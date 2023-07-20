package kr.co.bdgen.indywrapper.data

import com.google.gson.annotations.SerializedName

data class CredentialSearchItem(
    @SerializedName("cred_info")
    val credential: Credential,
)