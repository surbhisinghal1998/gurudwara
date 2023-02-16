package akaalwebsoft.com.gurudwara.ViewModle;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import akaalwebsoft.com.gurudwara.common.CommonUtils;
import akaalwebsoft.com.gurudwara.modle.AkhandPathDateModel;
import akaalwebsoft.com.gurudwara.network.ApiInterface;
import akaalwebsoft.com.gurudwara.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AkhandPathDateViewModel extends ViewModel {
    Context context;


    ApiInterface apiInterface;

    private MutableLiveData<AkhandPathDateModel> userSummaryModelMutableLiveData;

    public LiveData<AkhandPathDateModel> userSummaryModelLiveData(final Activity activity, String BookingDate, String authorization, String accessToken) {

        userSummaryModelMutableLiveData = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {
            String dataType = CommonUtils.returnDataType(activity.getApplicationContext());
            String simtype = CommonUtils.returnsimtype(activity.getApplicationContext());
            if (dataType.equalsIgnoreCase("wifi")) {
                apiInterface = RetrofitClient.liveapiClient().create(ApiInterface.class);
                apiInterface.akhandpathdate(BookingDate, authorization, accessToken).enqueue(new Callback<AkhandPathDateModel>() {
                    @Override
                    public void onResponse(Call<AkhandPathDateModel> call, Response<AkhandPathDateModel> response) {
                        if (response.body() != null) {

                            userSummaryModelMutableLiveData.postValue(response.body());
                        } else {
                            Toast.makeText(activity, "" + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AkhandPathDateModel> call, Throwable t) {
                        Log.e("message", t.getMessage());
                        Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            } else if (dataType.toLowerCase().contains("mobile")) {
                if (simtype.toLowerCase().contains("airtel")) {
                    apiInterface = RetrofitClient.liveapiClientairtel().create(ApiInterface.class);
                    apiInterface.akhandpathdate(BookingDate, authorization, accessToken).enqueue(new Callback<AkhandPathDateModel>() {
                        @Override
                        public void onResponse(Call<AkhandPathDateModel> call, Response<AkhandPathDateModel> response) {
                            if (response.body() != null) {

                                userSummaryModelMutableLiveData.postValue(response.body());
                            } else {
                                Toast.makeText(activity, "" + response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<AkhandPathDateModel> call, Throwable t) {
                            Log.e("message", t.getMessage());
                            Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    apiInterface = RetrofitClient.liveapiClientvodafone().create(ApiInterface.class);
                    apiInterface.akhandpathdate(BookingDate, authorization, accessToken).enqueue(new Callback<AkhandPathDateModel>() {
                        @Override
                        public void onResponse(Call<AkhandPathDateModel> call, Response<AkhandPathDateModel> response) {
                            if (response.body() != null) {

                                userSummaryModelMutableLiveData.postValue(response.body());
                            } else {
                                Toast.makeText(activity, "" + response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<AkhandPathDateModel> call, Throwable t) {
                            Log.e("message", t.getMessage());
                            Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        } else {
            Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();

        }
            return userSummaryModelMutableLiveData;


    }

}
