package lec.baekseokuniv.ssiholder.api

import lec.baekseokuniv.ssiholder.data.argument.IssueArg
import lec.baekseokuniv.ssiholder.data.argument.IssueOfferArg
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IssuerApi {
    /**
     * 1. request offer to issue credential
     */
    @POST("/credential/issue/offer")
    fun postOffer(@Body issueOfferArg: IssueOfferArg): Call<String?>

    /**
     * 2. issue credential
     */
    @POST("/credential/issue")
    fun postIssue(@Body issueArg: IssueArg): Call<Void>
}