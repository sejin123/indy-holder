package kr.co.bdgen.indywrapper.data.argument

data class CredentialVerifyPostVerifyProofArgs(
    val proofRequestJson: String,
    val proofJson: String,
    val schemas: String,
    val credDefs: String,
    val revocRegDefs: String,
    val revocRegs: String,
)
