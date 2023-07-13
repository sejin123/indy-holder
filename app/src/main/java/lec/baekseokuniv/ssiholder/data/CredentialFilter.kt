package lec.baekseokuniv.ssiholder.data

data class CredentialFilter(
    val schema_id: String,
    val schema_name: String? = null,
    val schema_version: String? = null,
    val cred_def_id: String? = null
)
