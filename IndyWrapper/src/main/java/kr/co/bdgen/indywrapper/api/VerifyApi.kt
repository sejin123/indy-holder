package kr.co.bdgen.indywrapper.api

import kr.co.bdgen.indywrapper.data.argument.CredentialVerifyPostVerifyProofArgs
import kr.co.bdgen.indywrapper.data.payload.ProofRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface VerifyApi {
    /**
     * 1. request offer to issue credential
     */
    @POST("/credential/verify/create-proof")
    fun postCreateProof(@Query("schemaId") schemaId: String): Call<ProofRequest>

    /**
     * 2. issue credential
     */
    @POST("/credential/verify/verify-proof")
    fun postVerifyProof(@Body args: CredentialVerifyPostVerifyProofArgs): Call<Boolean>

    @GET("/credential/schema/schema-json")
    fun getSchemaJson(@Query("ids") ids: List<String>): Call<Map<String, String>>

    @GET("/credential/definition/definition-json")
    fun getDefinitionJson(@Query("ids") ids: List<String>): Call<Map<String, String>>
}