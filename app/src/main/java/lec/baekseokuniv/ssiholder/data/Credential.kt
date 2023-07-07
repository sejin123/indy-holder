package lec.baekseokuniv.ssiholder.data

//FIXME: 데이터 분석 후 구조 수정 예정 (20230706 ksh)
data class Credential(
    val id: String,
    val name: String,
    val schemaId: String,
    val credentialDefinitionId: String,
    val values: MutableMap<String, String>? = null
)