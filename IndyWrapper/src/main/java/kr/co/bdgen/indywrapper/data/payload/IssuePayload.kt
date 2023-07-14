package kr.co.bdgen.indywrapper.data.payload

data class IssuePayload(
    val credentialJson: String,
    val revocId: String?,
    val revocRegDeltaJson: String?
)