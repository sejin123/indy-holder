package kr.co.bdgen.indywrapper.api

import kr.co.bdgen.indywrapper.data.argument.IssueArg
import kr.co.bdgen.indywrapper.data.payload.IssuePayload
import kr.co.bdgen.indywrapper.data.payload.OfferPayload
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface IssuerApi {
    /**
     * 1. request offer to issue credential
     */
    @POST("/credential/issue/offer")
    fun postOffer(@Query("secret") issueOfferArg: String): Call<OfferPayload>

    /**
     * 2. issue credential
     */
    @POST("/credential/issue")
    fun postIssue(@Body issueArg: IssueArg): Call<IssuePayload>
}