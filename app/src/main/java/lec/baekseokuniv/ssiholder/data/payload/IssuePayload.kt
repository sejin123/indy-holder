package lec.baekseokuniv.ssiholder.data.payload

data class IssuePayload(
    val credJson: String,
    val revocationId: String?,
    val revocationRegData: String?
)