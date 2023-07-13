package lec.baekseokuniv.ssiholder.data

data class Credential(
    val referent: String,
    val schema_id: String,
    val cred_def_id: String,
    val rev_reg_id: String? = null,
    val cred_rev_id: String? = null,
    val attrs: MutableMap<String, String>? = null
)