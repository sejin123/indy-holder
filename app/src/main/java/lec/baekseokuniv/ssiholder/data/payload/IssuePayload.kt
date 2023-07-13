package lec.baekseokuniv.ssiholder.data.payload

data class IssuePayload(
    val credentialJson: String,
    val revocId: String?,
    val revocRegDeltaJson: String?
)