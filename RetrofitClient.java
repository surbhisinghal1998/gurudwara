package akaalwebsoft.com.gurudwara.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import akaalwebsoft.com.gurudwara.common.CommonUtils;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static String liveUrl = "https://testac.akaalwebsoft.com/API/Data/";
//    public static final String API_BASE_URL_Riope = "https://dev.riope.in/api/";

    final static String userName        = "ventureinfotek\\dsgmc";
    final static String password        = "U$er@12345";
    final static String proxyHostAirtel = "192.168.153.200"; // for Airtel sim
    final static String proxyHostVoda   = "192.168.99.7"; // for Vodafone sim
    final static int proxyPort          = 8080;
//    final static int proxyPortvoda      = 3603;

    static Authenticator proxyAuthenticator = new Authenticator() {
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
            String credential = Credentials.basic(userName, password);

            return response.request().newBuilder()
                    .header("Proxy-Authorization", credential)
                    .build();
        }
    };


//    public static String liveUrl = "https://testac.akaalwebsoft.com/API/Data/";
//    public static String liveUrl = "https://192.168.153.200:8080/";
    public static Retrofit liveretrofit = null;

    public static Retrofit liveapiClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NotNull String s) {
                Log.d("log: ", s);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging).connectTimeout(500, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build();

        if (liveretrofit == null) {

            liveretrofit = new Retrofit.Builder()
                    .baseUrl(liveUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory
                            .create(gson))
                    .build();

        }
        return liveretrofit;
    }

    public static Retrofit liveapiClientairtel() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NotNull String s) {
                Log.d("log: ", s);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client1 = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(1500, TimeUnit.SECONDS).writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .proxy(new java.net.Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHostAirtel, proxyPort)))
                .proxyAuthenticator(proxyAuthenticator)
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .build();


        if (liveretrofit == null) {

            liveretrofit = new Retrofit.Builder()
                    .baseUrl(liveUrl)
                    .client(client1)
                    .addConverterFactory(GsonConverterFactory
                            .create(gson))
                    .build();

        }
        return liveretrofit;
    }

    public static Retrofit liveapiClientvodafone() {
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//            @Override
//            public void log(@NotNull String s) {
//                Log.d("log: ", s);
//            }
//        });
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(logging).connectTimeout(500, TimeUnit.SECONDS)
//                .callTimeout(1, TimeUnit.MINUTES)
//                .proxy(new java.net.Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHostVoda, proxyPort)))
//                .proxyAuthenticator(proxyAuthenticator)
//                .protocols(Arrays.asList(Protocol.HTTP_1_1))
//                .build();
//
//        if (liveretrofit == null) {
//
//            liveretrofit = new Retrofit.Builder()
//                    .baseUrl(liveUrl)
//                    .client(client)
//                    .addConverterFactory(GsonConverterFactory
//                            .create(gson))
//                    .build();
//
//        }
//        return liveretrofit;
//    }
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NotNull String s) {
                Log.d("log: ", s);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client1 = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(1500, TimeUnit.SECONDS).writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .proxy(new java.net.Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHostVoda, proxyPort)))
                .proxyAuthenticator(proxyAuthenticator)
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .build();


        if (liveretrofit == null) {

            liveretrofit = new Retrofit.Builder()
                    .baseUrl(liveUrl)
                    .client(client1)
                    .addConverterFactory(GsonConverterFactory
                            .create(gson))
                    .build();

        }
        return liveretrofit;
    }

}
