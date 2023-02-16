package akaalwebsoft.com.gurudwara.ViewModle;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import akaalwebsoft.com.gurudwara.common.CommonUtils;
import akaalwebsoft.com.gurudwara.modle.DonationMotiveModle;
import akaalwebsoft.com.gurudwara.modle.SaveDonationReceipt;
import akaalwebsoft.com.gurudwara.network.ApiInterface;
import akaalwebsoft.com.gurudwara.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SavedonationViewModle extends ViewModel {
    Context context;
    ApiInterface apiInterface;

    private MutableLiveData<SaveDonationReceipt> getMachineListModleMutableLiveData;

    public LiveData<SaveDonationReceipt> getMachineListModleLiveData(final Activity activity, String id, String securitypincode, String gurudwaramasterid, String receipttype, String name, String address, String mobileno, String emailid, String donationmasterid, String startingdate, String pathbhogdate, String noofakhandpath, String paymentmode, String currenymasterid, String chequeno,  String amount,String bankname,String branchname, JsonArray jsonArray, String remarks, String pancardnumber, String token, String authorization) {

        getMachineListModleMutableLiveData = new MutableLiveData<>();
//        JsonArray jsonArray = new JsonArray();
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("Amount", receiptamount);
//        jsonObject.addProperty("Number", receiptnumber);
//        jsonArray.add(jsonObject);


        JsonObject jsonObjectall = new JsonObject();
        jsonObjectall.addProperty("id", id);
        jsonObjectall.addProperty("SecurityPincode", securitypincode);
        jsonObjectall.addProperty("GurudwaraMasterId", gurudwaramasterid);
        jsonObjectall.addProperty("ReceiptType", receipttype);
        jsonObjectall.addProperty("Name", name);
        jsonObjectall.addProperty("Address", address);
        jsonObjectall.addProperty("MobileNo", mobileno);
        jsonObjectall.addProperty("EmailID", emailid);
        jsonObjectall.addProperty("GurudwaraDonationMasterId", donationmasterid);
        jsonObjectall.addProperty("AkhandPathStartingDate", startingdate);
        jsonObjectall.addProperty("AkhandPathBhogDate", pathbhogdate);
        jsonObjectall.addProperty("NoOfAkhandPath", noofakhandpath);
        jsonObjectall.addProperty("PaymentMode", paymentmode);
        jsonObjectall.addProperty("CurrencyMasterId", currenymasterid);
        jsonObjectall.addProperty("ChequeNo", chequeno);
        jsonObjectall.addProperty("BankName", bankname);
        jsonObjectall.addProperty("BranchName", branchname);
        jsonObjectall.addProperty("Amount", amount);
//        jsonObjectall.addProperty("ReceiptCurrencyDetails", data);
        jsonObjectall.add("ReceiptCurrencyDetails", jsonArray);
        jsonObjectall.addProperty("Remarks", remarks);
        jsonObjectall.addProperty("PanCardNumber", pancardnumber);


//            Log.e("JSONArray", String.valueOf(jsonArray));
        if (CommonUtils.isNetworkConnected(activity)) {
            String dataType = CommonUtils.returnDataType(activity.getApplicationContext());
            String simtype = CommonUtils.returnsimtype(activity.getApplicationContext());
            if (dataType.equalsIgnoreCase( "wifi")) {
                apiInterface = RetrofitClient.liveapiClient().create(ApiInterface.class);
                apiInterface.savedonation(jsonObjectall, token, authorization).enqueue(new Callback<SaveDonationReceipt>() {
                    @Override
                    public void onResponse(Call<SaveDonationReceipt> call, Response<SaveDonationReceipt> response) {
                        if (response.body() != null) {
                            getMachineListModleMutableLiveData.postValue(response.body());
                        } else {
                            Toast.makeText(activity, "" + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SaveDonationReceipt> call, Throwable t) {

                        Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (dataType.toLowerCase().contains("mobile")) {
                if (simtype.toLowerCase().contains("airtel")) {
                    apiInterface = RetrofitClient.liveapiClientairtel().create(ApiInterface.class);
                    apiInterface.savedonation(jsonObjectall, token, authorization).enqueue(new Callback<SaveDonationReceipt>() {
                        @Override
                        public void onResponse(Call<SaveDonationReceipt> call, Response<SaveDonationReceipt> response) {
                            if (response.body() != null) {
                                getMachineListModleMutableLiveData.postValue(response.body());
                            } else {
                                Toast.makeText(activity, "" + response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<SaveDonationReceipt> call, Throwable t) {

                            Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    apiInterface = RetrofitClient.liveapiClientvodafone().create(ApiInterface.class);
                    apiInterface.savedonation(jsonObjectall, token, authorization).enqueue(new Callback<SaveDonationReceipt>() {
                        @Override
                        public void onResponse(Call<SaveDonationReceipt> call, Response<SaveDonationReceipt> response) {
                            if (response.body() != null) {
                                getMachineListModleMutableLiveData.postValue(response.body());
                            } else {
                                Toast.makeText(activity, "" + response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<SaveDonationReceipt> call, Throwable t) {

                            Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        } else {

            Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

        return getMachineListModleMutableLiveData;

    }


}