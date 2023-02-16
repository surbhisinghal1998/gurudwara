package akaalwebsoft.com.gurudwara.network;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import akaalwebsoft.com.gurudwara.common.Constant;
import akaalwebsoft.com.gurudwara.modle.AkhandPathDateModel;
import akaalwebsoft.com.gurudwara.modle.AkhandPathSummaryModel;
import akaalwebsoft.com.gurudwara.modle.DonationMotiveModle;
import akaalwebsoft.com.gurudwara.modle.GetCurrentcyList;
import akaalwebsoft.com.gurudwara.modle.GetMachineListModle;
import akaalwebsoft.com.gurudwara.modle.GetPaymentModeListModle;
import akaalwebsoft.com.gurudwara.modle.GetReceiptDetail;
import akaalwebsoft.com.gurudwara.modle.LariVaarModel;
import akaalwebsoft.com.gurudwara.modle.LastReceipt;
import akaalwebsoft.com.gurudwara.modle.LogoutModel;
import akaalwebsoft.com.gurudwara.modle.SaveDonationReceipt;
import akaalwebsoft.com.gurudwara.modle.SecurityPinModel;
import akaalwebsoft.com.gurudwara.modle.UserSummaryModel;
import akaalwebsoft.com.gurudwara.modle.ValidateUserCredentialModle;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @Headers("Accept:application/json")
    @GET("GetMachineList")
    Call<GetMachineListModle> getmachinelist(
            @Header(Constant.AUTHORIZATION) String authorization);

    @Headers("Accept:application/json")
    @GET("GetCurrencyList")
    Call<GetCurrentcyList> getcurrencylist(
            @Header(Constant.AUTHORIZATION) String authorization);

    @Headers("Accept:application/json")
    @GET("GetPaymentModeList")
    Call<GetPaymentModeListModle> getpaymentmodelist(
            @Header(Constant.AUTHORIZATION) String authorization);

    @Headers("Accept:application/json")
    @GET("UserCollectionSummaryForCurrentDay")
    Call<UserSummaryModel> usersummary(
            @Header(Constant.AUTHORIZATION) String authorization,
            @Header(Constant.ACCESSTOKEN) String accessToken);

    @Headers("Accept:application/json")
    @GET("GetLastReceiptByMobileNo")
    Call<LastReceipt> lastreceipt(
            @Query(Constant.MOBILENO) String MobileNo,
            @Header(Constant.AUTHORIZATION) String authorization,
            @Header(Constant.ACCESSTOKEN) String accessToken);


    @Headers("Accept:application/json")
    @GET("GetAvailableAkhandPathBookingCount")
    Call<AkhandPathDateModel> akhandpathdate(
            @Query("BookingDate") String BookingDate,
            @Header(Constant.AUTHORIZATION) String authorization,
            @Header(Constant.ACCESSTOKEN) String accessToken);

    @Headers("Accept:application/json")
    @GET("UserAkhandPathCollectionSummaryForCurrentDay")
    Call<AkhandPathSummaryModel> userakhandpathsummary(
            @Header(Constant.AUTHORIZATION) String authorization,
            @Header(Constant.ACCESSTOKEN) String accessToken);

    @Headers("Accept:application/json")
    @POST("LogoutMobileUser")
    Call<LogoutModel> logout(
            @Header(Constant.AUTHORIZATION) String authorization,
            @Header(Constant.ACCESSTOKEN) String accessToken);

    @Headers("Accept:application/json")
    @GET("GetReceiptDetails")
    Call<GetReceiptDetail> getrecipt(
            @Query(Constant.RECEIPTID) String ReceiptId,
            @Header(Constant.AUTHORIZATION) String authorization,
            @Header(Constant.ACCESSTOKEN) String accessToken);

    @Headers("Accept:application/json")
    @GET("IsValidSecurityPincode")
    Call<SecurityPinModel> securityPin(
            @Query(Constant.SECURITYPINCODE) String SecurityPincode,
            @Header(Constant.AUTHORIZATION) String authorization,
            @Header(Constant.ACCESSTOKEN) String accessToken);


    @Headers("Accept:application/json")
    @GET("ValidateUserCredentials")
    Call<ValidateUserCredentialModle> validateusecred(
            @Query(Constant.USERNAME) String UserName,
            @Query(Constant.LOGINPASSWORD) String LoginPassword,
            @Query(Constant.DEVIEID) String DeviceId,
            @Header(Constant.AUTHORIZATION) String authorization);

    @Headers("Accept:application/json")
    @GET("GetGurudwaraDonationMasterList")
    Call<DonationMotiveModle> donationmotive(
            @Header(Constant.ACCESSTOKEN) String accesstoken,
            @Header(Constant.AUTHORIZATION) String authorization);

    //    @FormUrlEncoded
    @Headers("Accept:application/json")
//    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST("SaveDonationReceiptFromMachine")
    Call<SaveDonationReceipt> savedonation(@Body JsonObject jsonObject,
//            @Field(Constant.ID) String id,
//            @Field(Constant.SECURITYPINCODE) String SecurityPincode,
//            @Field(Constant.GURUDWARAMASTERID) String GurudwaraMasterId,
//            @Field(Constant.RECYPTTYPE) String ReceiptType,
//            @Field(Constant.NAME) String name,
//            @Field(Constant.ADDRESS) String Address,
//            @Field(Constant.MOBILENO) String MobileNo,
//            @Field(Constant.EMAILID) String EmailID,
//            @Field(Constant.GURUDWARADONATIONMASTERID) String GurudwaraDonationMasterId,
//            @Field(Constant.AKHANDPATHSTARTINGDATE) String AkhandPathStartingDate,
//            @Field(Constant.AKHANDPATHBHOGDATE) String AkhandPathBhogDate,
//            @Field(Constant.NOOFAKHANDPATH) String NoOfAkhandPath,
//            @Field(Constant.PAYMENTMODE) String PaymentMode,
//            @Field(Constant.CURRENTMASTERID) String CurrencyMasterId,
//            @Field(Constant.CHEQUENO) String ChequeNo,
//            @Field(Constant.BANKNAME) String BankName,
//            @Field(Constant.BRANCHNAME) String BranchName,
//            @Field(Constant.AMOUNT) String Amount,
//            @Body JSONArray jsonArray,
////            @Field(Constant.RECEIPT) JSONObject ReceiptCurrencyDetails,
//            @Field(Constant.REMARKS) String Remarks,
//            @Field(Constant.PANCARDNUMBER) String PanCardNumber,
                                           @Header(Constant.ACCESSTOKEN) String accesstoken,
                                           @Header(Constant.AUTHORIZATION) String authorization);


    @Headers("Accept:application/json")
//    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST("SaveAkhandPathReceiptFromMachine")
    Call<SaveDonationReceipt> saveakhanpathdonation(@Body JsonObject jsonObject,

                                                    @Header(Constant.ACCESSTOKEN) String accesstoken,
                                                    @Header(Constant.AUTHORIZATION) String authorization);

    @Headers("Accept:application/json")
//    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST("SaveAkhandPathLariVaarReceiptFromMachine")
    Call<LariVaarModel> larivar(@Body JsonObject jsonObject,
                                @Header(Constant.ACCESSTOKEN) String accesstoken,
                                @Header(Constant.AUTHORIZATION) String authorization);


}
