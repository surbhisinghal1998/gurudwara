package akaalwebsoft.com.gurudwara.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import akaalwebsoft.com.gurudwara.network.ApiInterface;
import akaalwebsoft.com.gurudwara.network.RetrofitClient;


public class CommonUtils {


    public static int TYPE_NOT_CONNECTED = 0;
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static String status = "";
    private static ApiInterface apiInterface;
    private static final String DEBUG_TAG = "NetworkStatusExample";
    //...



public static String returnsimtype(Context context){
    TelephonyManager telemamanger = (TelephonyManager)
            context.getSystemService(Context.TELEPHONY_SERVICE);

   return  telemamanger.getSimOperatorName();
//    Toast.makeText(AmountActivity.this,simOperatorName,Toast.LENGTH_SHORT).show();
}

    public static String returnDataType(Context ctx){
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

        return info.getTypeName();

    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

//        if (info.getType() == ConnectivityManager.TYPE_WIFI) {
//            apiInterface = RetrofitClient.liveapiClient().create(ApiInterface.class);
//            status = "WIFI";
////            return cm.getActiveNetworkInfo() != null;
//        } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
//            status = "mobile";
////            return cm.getActiveNetworkInfo() != null;
//        }
        return info != null;
    }
//
//    public static NetworkInfo getNetworkInfo(Context context) {
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        return cm.getActiveNetworkInfo();
//    }

    /**
     * Check if there is any connectivity
     *
     * @param context
     * @return
     */
//    public static boolean isConnected(Context context) {
//        NetworkInfo info = CommonUtils.getNetworkInfo(context);
//        return (info != null && info.isConnected());
//    }

    /**
     * Check if there is any connectivity to a Wifi network
     *
     * @param context
     * @return
     */
//    public static boolean isConnectedWifi(Context context) {
//        NetworkInfo info = CommonUtils.getNetworkInfo(context);
//        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
//    }

    /**
     * Check if there is any connectivity to a mobile network
     *
     * @param context
     * @return
     */
//    public static boolean isConnectedMobile(Context context) {
//        NetworkInfo info = CommonUtils.getNetworkInfo(context);
//        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
//    }


//    public static int getConnectivityStatus(Context context) {
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        if (null != activeNetwork) {
//            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
//                return TYPE_WIFI;
//
//            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
//                return TYPE_MOBILE;
//        }
//        return TYPE_NOT_CONNECTED;
//    }
//
//    public static String getConnectivityStatusString(Context context) {
//        int conn = CommonUtils.getConnectivityStatus(context);
//        String status = null;
//        if (conn == CommonUtils.TYPE_WIFI) {
//            status = "Wifi enabled";
//        } else if (conn == CommonUtils.TYPE_MOBILE) {
//            status = "Mobile data enabled";
//        } else if (conn == CommonUtils.TYPE_NOT_CONNECTED) {
//            status = "Not connected to Internet";
//        }
//        return status;
//    }
}


