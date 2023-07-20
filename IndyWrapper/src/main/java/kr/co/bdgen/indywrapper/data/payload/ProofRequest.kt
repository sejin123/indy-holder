package kr.co.bdgen.indywrapper.data.payload

import com.google.gson.annotations.SerializedName

data class ProofRequest(
    val nonce: String,
    val name: String,
    val version: String,
    @SerializedName("requested_attributes")
    val requestedAttributes: Map<String, RequestedAttribute>,
    @SerializedName("requested_predicates")
    val requestedPredicates: Map<String, RequestedPredicate>,
)

data class RequestedAttribute(
    val name: String,
    val restriction: Restriction? = null,
)

data class RequestedPredicate(
    val name: String,
    @SerializedName("p_type")
    val pType: String,
    @SerializedName("p_value")
    val pValue: String,
    val restriction: Restriction?,
)

data class Restriction(
    @SerializedName("issuer_did")
    val issuerDid: String? = null,
    @SerializedName("schema_id")
    val schemaId: String? = null,
    @SerializedName("cred_def_id")
    val credDefId: String? = null,
)
