package akaalwebsoft.com.gurudwara;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import akaalwebsoft.com.gurudwara.ViewModle.DonationMotiveViewModle;
import akaalwebsoft.com.gurudwara.ViewModle.GetCurrenyListViewModel;
import akaalwebsoft.com.gurudwara.ViewModle.GetPaymentmodeViewModle;
import akaalwebsoft.com.gurudwara.ViewModle.LastDataInViewModel;
import akaalwebsoft.com.gurudwara.ViewModle.LogoutViewModel;
import akaalwebsoft.com.gurudwara.ViewModle.SavedonationViewModle;
import akaalwebsoft.com.gurudwara.ViewModle.SecurityPinViewModel;
import akaalwebsoft.com.gurudwara.adapter.CurrentcyAdapter;
import akaalwebsoft.com.gurudwara.common.CommonUtils;
import akaalwebsoft.com.gurudwara.common.SharedPreference;
import akaalwebsoft.com.gurudwara.modle.DonationMotiveModle;
import akaalwebsoft.com.gurudwara.modle.GetCurrentcyList;
import akaalwebsoft.com.gurudwara.modle.GetPaymentModeListModle;
import akaalwebsoft.com.gurudwara.modle.LastReceipt;
import akaalwebsoft.com.gurudwara.modle.LogoutModel;
import akaalwebsoft.com.gurudwara.modle.ReceiptCurrency;
import akaalwebsoft.com.gurudwara.modle.SaveDonationReceipt;
import akaalwebsoft.com.gurudwara.modle.SecurityPinModel;

public class SetReceipt extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ProgressBar pBar3;
    TextView gurudwaraname, chequenotext, banknametext, branchtext, currencytext, gurudwaraaddress, cardtext;
    String gurudwaranamestring, auth, donationmasterid, token, currencymaster;
    Spinner paymentmode, motivemode;
    EditText nameedit, addressedit, mobileedit, emailedit, securityedit, amountedit, chequenuedit, banknameedit, branchedit, remarkedit, pancardedit, cardedit;
    Button submit;
    ProgressDialog pd;
    ImageView backbutton, logout;
    GetPaymentmodeViewModle getPaymentmodeViewModle;
    SecurityPinViewModel securityPinViewModel;
    LogoutViewModel logoutViewModel;
    LastDataInViewModel lastDataInViewModel;
    SavedonationViewModle savedonationViewModle;
    Boolean isFirstTim;
    DonationMotiveViewModle donationMotiveViewModle;
    //    String item = "Select";
    String item = "Select";
    String name;
    String payment = item;
    String motiveitem = "";
    String curency;
    LinearLayout layout_list;
    CurrentcyAdapter currentcyAdapter;
    GetCurrenyListViewModel getCurrenyListViewModel;
    //    List<ReceiptCurrency> receiptCurrencies = new ArrayList<>();
    List<ReceiptCurrency> receiptCurrencies = new ArrayList<>();
    Dialog dialog;
    SharedPreference preference;
    List<GetCurrentcyList.Datum> getcurrency = new ArrayList<GetCurrentcyList.Datum>();
    int donationmotiveid;
    Button button_add;
    TableLayout displayLinear;
    EditText tv;
    String package_name = "";
    public static String param_status = "false";
    public static String sim_status = "false";
    JsonArray jsonArray = new JsonArray();
    JsonObject obj;


//     = new JsonObject();
//    JSONObject obj = new JSONObject();

    //    private Printer printer = null;
//    private PrintTask printTask = null;
//
//    CursorLoader cursorLoader;
//    public static String param_status = "false";
//    public static String sim_status = "false";
//
    Handler handler;
    Runnable postToServerRunnable;
    EditText editamount, editnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_receipt);
        preference = SharedPreference.getInstance(this);
        preference.setMotiveCatagory("Select");
        isFirstTim = false;
//        donationmotiveid = 1;
//        preference.setMotiveCatagory("LANGAR BHETA");
        getPaymentmodeViewModle = new ViewModelProvider(SetReceipt.this).get(GetPaymentmodeViewModle.class);
        securityPinViewModel = new ViewModelProvider(SetReceipt.this).get(SecurityPinViewModel.class);
        savedonationViewModle = new ViewModelProvider(SetReceipt.this).get(SavedonationViewModle.class);
        logoutViewModel = new ViewModelProvider(SetReceipt.this).get(LogoutViewModel.class);
        getCurrenyListViewModel = new ViewModelProvider(SetReceipt.this).get(GetCurrenyListViewModel.class);
        donationMotiveViewModle = new ViewModelProvider(SetReceipt.this).get(DonationMotiveViewModle.class);
        lastDataInViewModel = new ViewModelProvider(SetReceipt.this).get(LastDataInViewModel.class);

        getintentdata();
        id();
        gurudwaraaddress.setText(preference.getGurudwaraAddress());
        setdata();


//        spinnerlist();


        List<String> motivecatagories = new ArrayList<String>();
        List<Integer> motivecatagoriesid = new ArrayList<Integer>();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SetReceipt.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.custom_dialog, viewGroup, false);
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                Button btn_yes = dialogView.findViewById(R.id.btn_yes);
                Button btn_no = dialogView.findViewById(R.id.btn_no);
                btn_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                btn_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        logoutViewModel.logoutModelLiveData(SetReceipt.this, preference.getAuth(), preference.getAccessToken()).observe(SetReceipt.this, new Observer<LogoutModel>() {
                            @Override
                            public void onChanged(LogoutModel logoutModel) {
                                if (logoutModel.getStatuscode().equalsIgnoreCase("success")) {
                                    Intent intent = new Intent(SetReceipt.this, LoginScreen.class);
                                    startActivity(intent);
                                    preference.setAccessToken("");
                                    finish();
                                } else {

                                }
                            }
                        });
                    }
                });


            }
        });
        motivemode.setOnItemSelectedListener(this);
//        pd = new ProgressDialog(SetReceipt.this);
//        pd.setMessage("Loading");
//        pd.show();
//        if (pd.isShowing()) {
//            onBackPressed();
//        }

        donationMotiveViewModle.getMachineListModleLiveData(this, preference.getAccessToken(), preference.getAuth()).observe(this, new Observer<DonationMotiveModle>() {
            @Override
            public void onChanged(DonationMotiveModle donationMotiveModle) {

                if (donationMotiveModle.getStatuscode().equalsIgnoreCase("success")) {
//                    pd.dismiss();

//                    donationmotiveid = 181;

//                    preference.setMotiveCatagory("Select");
                    motivecatagories.add("Select");
                    for (int j = 0; j < donationMotiveModle.getData().size(); j++) {
                        donationmasterid = donationMotiveModle.getData().get(j).getDonationMasterId().toString();
                        donationmotiveid = donationMotiveModle.getData().get(j).getId();
//                        preference.setMotiveCatagory(donationMotiveModle.getData().get(j).getDonationName());
                        motivecatagories.add(donationMotiveModle.getData().get(j).getDonationName());
//                        motivecatagoriesid.add(donationMotiveModle.getData().get(j).getDonationMasterId());

                        ArrayAdapter<String> ads = new ArrayAdapter<String>(SetReceipt.this, android.R.layout.simple_spinner_item, motivecatagories) {
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                View v = null;
                                // If this is the initial dummy entry, make it hidden
                                if (position == 0) {
                                    TextView tv = new TextView(getContext());
                                    tv.setHeight(0);
                                    tv.setVisibility(View.GONE);
                                    v = tv;
                                } else {
                                    // Pass convertView as null to prevent reuse of special case views
                                    v = super.getDropDownView(position, null, parent);
                                }
                                // Hide scroll bar because it appears sometimes unnecessarily, this does not prevent scrolling
                                parent.setVerticalScrollBarEnabled(false);
                                return v;
                            }
                        };
                        ads.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        motivemode.setAdapter(ads);
//                        if (donationMotiveModle.getData().get(j).getDonationName().equalsIgnoreCase(motiveitem)) {
//                            donationmasterid = donationMotiveModle.getData().get(j).getDonationMasterId().toString();
//                            preference.setMotiveCatagory(donationMotiveModle.getData().get(j).getDonationName());
//                        }
//                        if (item.equalsIgnoreCase(motivecatagories.get(j))) {
//                            donationmotiveid = motivecatagoriesid.get(j);
//                            preference.setMotiveCatagory(motivecatagories.get(j));
//                        }


                    }
//                    pd.dismiss();
                } else {
                    onBackPressed();
                    Toast.makeText(SetReceipt.this, donationMotiveModle.getMessage(), Toast.LENGTH_LONG).show();
//

                }

            }
        });

        paymentmode.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        getPaymentmodeViewModle.getMachineListModleLiveData(this, preference.getAuth()).observe(this, new Observer<GetPaymentModeListModle>() {
            @Override
            public void onChanged(GetPaymentModeListModle getPaymentModeListModle) {
                if (getPaymentModeListModle.getStatuscode().equalsIgnoreCase("Success")) {
                    categories.add("Select");
                    for (int i = 0; i < getPaymentModeListModle.getData().size(); i++) {
                        categories.add(getPaymentModeListModle.getData().get(i).getName().toString());

                        ArrayAdapter<String> ad = new ArrayAdapter<String>(SetReceipt.this, android.R.layout.simple_spinner_item, categories) {
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                View v = null;
                                // If this is the initial dummy entry, make it hidden
                                if (position == 0) {
                                    TextView tv = new TextView(getContext());
                                    tv.setHeight(0);
                                    tv.setVisibility(View.GONE);
                                    v = tv;
                                } else {
                                    // Pass convertView as null to prevent reuse of special case views
                                    v = super.getDropDownView(position, null, parent);
                                }
                                // Hide scroll bar because it appears sometimes unnecessarily, this does not prevent scrolling
                                parent.setVerticalScrollBarEnabled(false);
                                return v;
                            }
                        };
                        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        paymentmode.setAdapter(ad);
                    }
                    Log.e("itemss", payment);
                }

            }
        });
//        categories.add("Cash");
        buttonclick();
        handler = new Handler();
        postToServerRunnable = new Runnable() {
            @Override
            public void run() {
                // TODO: PUT CODE HERE TO HANDLE CURRENT VALUE OF EDIT TEXT AND SEND TO SERVER
            }
        };
        getmobilenumber();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void getmobilenumber() {

        mobileedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (mobileedit.getText().length() == 10 && isFirstTim == false) {
                    apicallhere();
                }


            }


            @Override
            public void afterTextChanged(Editable editable) {
            }

        });
    }

    private void apicallhere() {

        lastDataInViewModel.getReceiptDetailLiveData(SetReceipt.this, mobileedit.getText().toString(), preference.getAuth(), preference.getAccessToken()).observe(SetReceipt.this, new Observer<LastReceipt>() {
            @Override
            public void onChanged(LastReceipt lastReceipt) {
                if (lastReceipt.getStatuscode().equalsIgnoreCase("success")) {
                    nameedit.setText(lastReceipt.getData().getName());
                    addressedit.setText(lastReceipt.getData().getAddress());
                    mobileedit.setText(lastReceipt.getData().getMobileNo());
                    emailedit.setText(lastReceipt.getData().getEmailID());

                    remarkedit.setText(lastReceipt.getData().getRemarks());
                    pancardedit.setText(lastReceipt.getData().getPanCardNumber());
                    isFirstTim = true;
//
                } else {
                    isFirstTim = false;
                }
            }
        });

    }

    private void buttonclick() {

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amt = amountedit.getText().toString();

                int i = Integer.parseInt(amt);
                if (i == 0) {
                    Toast.makeText(SetReceipt.this, "Amount cannot be Zero", Toast.LENGTH_SHORT).show();
                }
//                if (amt.trim().length() == 0) {
//                    Toast.makeText(SetReceipt.this, "Amount cannot be Zero", Toast.LENGTH_SHORT).show();
//                }
                if (nameedit.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(SetReceipt.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                } else if (securityedit.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(SetReceipt.this, "Please Enter Security Pin", Toast.LENGTH_SHORT).show();
                } else if (amt.equalsIgnoreCase("") || amt.equalsIgnoreCase("0")) {
                    Toast.makeText(SetReceipt.this, "Amount cannot be Zero", Toast.LENGTH_SHORT).show();
                } else if (payment.equalsIgnoreCase("Select")) {
                    Toast.makeText(SetReceipt.this, "Please select Payment Mode", Toast.LENGTH_SHORT).show();
//                    Foreign Currency
//                } else if (preference.getMotiveCatagory().equalsIgnoreCase("Select")) {
//                    Toast.makeText(SetReceipt.this, "Please select Donation Motive", Toast.LENGTH_SHORT).show();
                } else if (motiveitem.equalsIgnoreCase("Select")) {
                    Toast.makeText(SetReceipt.this, "Please select Donation Motive", Toast.LENGTH_SHORT).show();

                }
//                else if (!payment.equalsIgnoreCase("Foreign Currency")) {
                else if (mobileedit.getText().length() < 10) {
                    Toast.makeText(SetReceipt.this, "Mobile length must be 10", Toast.LENGTH_SHORT).show();
                }
//                    Foreign Currency
//                }

                else {
                    securityPinViewModel.securityPinModelLiveData(SetReceipt.this, securityedit.getText().toString(), preference.getAuth(), preference.getAccessToken()).observe(SetReceipt.this, new Observer<SecurityPinModel>() {
                        @Override
                        public void onChanged(SecurityPinModel securityPinModel) {
                            if (securityPinModel.getStatuscode().equalsIgnoreCase("success")) {
                                if (securityPinModel.getData().getIsValidSecurityPincode() == true) {
                                    if (payment.equalsIgnoreCase("Card")) {
                                        String txn_type = "SALE";
                                        functionPaybyCard(amt, txn_type);


                                    } else if (payment.equalsIgnoreCase("UPI")) {
                                        onClickPayByBqrorUpi();
                                    } else {

                                        if (chequenuedit.getVisibility() == View.VISIBLE) {

                                            if (chequenuedit.getText().length() != 6) {

                                                Toast.makeText(SetReceipt.this, "Please enter correct Cheque Number", Toast.LENGTH_SHORT).show();
                                            } else {
                                                savecardapi();
                                            }
                                        } else {
                                            savecardapi();
                                        }
                                    }
                                } else {
                                    Toast.makeText(SetReceipt.this, "Please enter valid Security Pin", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }


//                onprintclick();


            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addView();


//                for (int i=1;i<=obj.size();i++){

//                    jsonArray.add(obj);
//                }


            }
        });
    }

    public void showDialoguePaper() {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    final Dialog dialog = new Dialog(getApplicationContext());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.layout_customerdialog);
                    TextView text = (TextView) dialog.findViewById(R.id.textDialog);

                    text.setText("Please insert paper roll");
                    // dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL);
                    dialog.show();
                    dialog.setCancelable(false);
                    dialog.setCanceledOnTouchOutside(false);
                    TextView declineButton = (TextView) dialog.findViewById(R.id.declineButton);
                    TextView textview_no = (TextView) dialog.findViewById(R.id.textview_no);
                    textview_no.setVisibility(View.GONE);
                    declineButton.setText("OK");
                    textview_no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();

                        }
                    });
                    declineButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();

                        }
                    });
                }

            });
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void addView() {

        final View cricketerView = getLayoutInflater().inflate(R.layout.row_add_amount, null, false);
        editamount = cricketerView.findViewById(R.id.edit_amount);
        editnumber = cricketerView.findViewById(R.id.edit_number);


//        Log.e("Get Count", String.valueOf(layout_list.getChildCount()));

        layout_list.addView(cricketerView);
        obj = new JsonObject();
        Log.e("Get Count", String.valueOf(layout_list.getChildCount()));

        editamount.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                obj.addProperty("Amount", editamount.getText().toString());
//             jsonArray.add(obj);


            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                Log.e("amount", editamount.getText().toString());
//                receiptCurrencies.get(layout_list.getChildCount()).setAmount(editamount.getText().toString());


            }
        });
        editnumber.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
//                receiptCurrencies.add(new ReceiptCurrency("Number", editnumber.getText().toString()));
                obj.addProperty("Number", editnumber.getText().toString());
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                Log.e("number", editnumber.getText().toString());

            }
        });

//        for (int i = 0; i < obj.size(); i++) {
        jsonArray.add(obj);
//        }
//            for (int i = 0; i < obj.size(); i++) {
//                receiptCurrencies.get(i).setAmount(obj.get("Amount").toString());
//                receiptCurrencies.get(i).setNumber(obj.get("Number").toString());
//            }


    }


    private void setdata() {
        gurudwaraname.setText(preference.getGurudwaraname());
//        donationmotiveid = 1;

    }

    private void getintentdata() {
        gurudwaranamestring = getIntent().getStringExtra("gurudwaranamestring");
        auth = getIntent().getStringExtra("auth");
//        donationmasterid = getIntent().getStringExtra("donationmasterid");
        token = getIntent().getStringExtra("token");
    }

    private void id() {
        pBar3 = findViewById(R.id.pBar3);
        gurudwaraname = findViewById(R.id.gurudwaraname);
        logout = findViewById(R.id.logout);
        paymentmode = (Spinner) findViewById(R.id.paymentmode);
        motivemode = (Spinner) findViewById(R.id.motivemode);
        chequenotext = findViewById(R.id.chequenotext);
//        banknametext = findViewById(R.id.banknametext);
        layout_list = findViewById(R.id.layout_list);
//        branchtext = findViewById(R.id.branchtext);
        nameedit = findViewById(R.id.nameedit);
        addressedit = findViewById(R.id.addressedit);
        mobileedit = findViewById(R.id.mobileedit);
        emailedit = findViewById(R.id.emailedit);
        securityedit = findViewById(R.id.securityedit);
        amountedit = findViewById(R.id.amountedit);
        chequenuedit = findViewById(R.id.chequenuedit);
//        banknameedit = findViewById(R.id.banknameedit);
//        branchedit = findViewById(R.id.branchedit);
        remarkedit = findViewById(R.id.remarkedit);
        button_add = findViewById(R.id.button_add);
        pancardedit = findViewById(R.id.pancardedit);
        currencytext = findViewById(R.id.currencytext);
        submit = findViewById(R.id.submit);
//        cardedit = findViewById(R.id.cardedit);
//        cardtext = findViewById(R.id.cardtext);
        gurudwaraaddress = findViewById(R.id.gurudwaraaddress);
        backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        item = adapterView.getItemAtPosition(i).toString();

        if (item.equalsIgnoreCase("Cash") || item.equalsIgnoreCase("Card") || item.equalsIgnoreCase("Cheque") || item.equalsIgnoreCase("Draft") || item.equalsIgnoreCase("UPI") || item.equalsIgnoreCase("Foreign Currency")) {
            payment = item;
        } else {
            motiveitem = item;

            donationMotiveViewModle.getMachineListModleLiveData(this, preference.getAccessToken(), preference.getAuth()).observe(this, new Observer<DonationMotiveModle>() {
                @Override
                public void onChanged(DonationMotiveModle donationMotiveModle) {
                    if (donationMotiveModle.getStatuscode().equalsIgnoreCase("Success")) {
                        donationmotiveid = 181;
//                        preference.setMotiveCatagory("Select");

                        for (int j = 0; j < donationMotiveModle.getData().size(); j++) {
                            if (donationMotiveModle.getData().get(j).getDonationName().equalsIgnoreCase(motiveitem)) {
                                donationmasterid = donationMotiveModle.getData().get(j).getDonationMasterId().toString();
                                donationmotiveid = donationMotiveModle.getData().get(j).getId();
                                preference.setMotiveCatagory(donationMotiveModle.getData().get(j).getDonationName());
                            }
//                        if (item.equalsIgnoreCase(motivecatagories.get(j))) {
//                            donationmotiveid = motivecatagoriesid.get(j);
//                            preference.setMotiveCatagory(motivecatagories.get(j));
//                        }


                        }
                    }
                }
            });
        }
//        motiveitem = adapterView.getItemAtPosition(i).toString();
//        if(item.equalsIgnoreCase("LANGAR BHETA")){
//            preference.setMotiveCatagory("LANGAR BHETA");
//            donationmotiveid = 1;
//        }


        if (payment.equalsIgnoreCase("Cash")) {
            chequenotext.setVisibility(View.GONE);
            chequenuedit.setVisibility(View.GONE);
           /* cardtext.setVisibility(View.GONE);
            cardedit.setVisibility(View.GONE);*/
            currencytext.setVisibility(View.GONE);
            layout_list.setVisibility(View.GONE);
//            banknameedit.setVisibility(View.GONE);
//            banknametext.setVisibility(View.GONE);
//            branchtext.setVisibility(View.GONE);
//            branchedit.setVisibility(View.GONE);


        }

        if (payment.equalsIgnoreCase("Card")) {

            chequenotext.setVisibility(View.GONE);
            chequenuedit.setVisibility(View.GONE);
            currencytext.setVisibility(View.GONE);
            layout_list.setVisibility(View.GONE);


        }

        if (payment.equalsIgnoreCase("UPI")) {

            chequenotext.setVisibility(View.GONE);
            chequenuedit.setVisibility(View.GONE);
            currencytext.setVisibility(View.GONE);
            layout_list.setVisibility(View.GONE);


        }


        if (payment.equalsIgnoreCase("Cheque")) {
            chequenotext.setText("Cheque No. *");
            chequenuedit.setHint("Enter Cheque No.");
            chequenotext.setVisibility(View.VISIBLE);
            chequenuedit.setVisibility(View.VISIBLE);
          /*  cardedit.setVisibility(View.GONE);
            cardtext.setVisibility(View.GONE);*/
//            banknameedit.setVisibility(View.VISIBLE);
            currencytext.setVisibility(View.GONE);
            layout_list.setVisibility(View.GONE);
//            banknametext.setVisibility(View.VISIBLE);
//            branchtext.setVisibility(View.VISIBLE);
//            branchedit.setVisibility(View.VISIBLE);
        }
        if (payment.equalsIgnoreCase("Draft")) {
            chequenotext.setText("Cheque No.");
            chequenotext.setVisibility(View.VISIBLE);
            chequenuedit.setVisibility(View.VISIBLE);
          /*  cardedit.setVisibility(View.GONE);
            cardtext.setVisibility(View.GONE);*/
//            banknameedit.setVisibility(View.VISIBLE);
            currencytext.setVisibility(View.GONE);
            layout_list.setVisibility(View.GONE);
//            banknametext.setVisibility(View.VISIBLE);
//            branchtext.setVisibility(View.VISIBLE);
//            branchedit.setVisibility(View.VISIBLE);
        }

        if (payment.equalsIgnoreCase("Foreign Currency")) {
            currencytext.setVisibility(View.VISIBLE);
            layout_list.setVisibility(View.VISIBLE);
            chequenotext.setVisibility(View.GONE);
//            cardtext.setVisibility(View.GONE);
            chequenuedit.setVisibility(View.GONE);
//            cardedit.setVisibility(View.GONE);
//            banknameedit.setVisibility(View.GONE);
//            banknametext.setVisibility(View.GONE);
//            branchtext.setVisibility(View.GONE);
//            branchedit.setVisibility(View.GONE);
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.foreign);
            RecyclerView recyclerView = dialog.findViewById(R.id.recycler);
            getCurrenyListViewModel.getMachineListModleLiveData(SetReceipt.this, preference.getAuth()).observe(SetReceipt.this, new Observer<GetCurrentcyList>() {
                @Override
                public void onChanged(GetCurrentcyList getCurrentcyList) {
                    if (getCurrentcyList.getStatuscode().equalsIgnoreCase("Success")) {
                        getcurrency = getCurrentcyList.getData();
                        currentcyAdapter = new CurrentcyAdapter(SetReceipt.this, getcurrency, new CurrentcyAdapter.currentinterface() {
                            @Override
                            public void currencymethod(int position, String name) {
                                curency = getcurrency.get(position).getName();
                                currencymaster = getcurrency.get(position).getId().toString();
//                                Toast.makeText(S etReceipt.this, currencymaster, Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                        recyclerView.setAdapter(currentcyAdapter);
                    }
                }
            });
            dialog.show();

        }

        Log.e("payment", payment);
        Log.e("motiveitem", "" + motiveitem.toString());
    }

    public void functionPaybyCard(String amountedit, String txn_type) {
        amountedit = getConvertDoubleval(amountedit);
        String transaction_id = getTransactionId();
        package_name = BuildConfig.APPLICATION_ID;
        String receipt = "YES";
        String CUSTOM_ACTION = "cn.desert.newpos.payui.master.YOUR_ACTION";
        Intent i = new Intent();
        i.setAction(CUSTOM_ACTION);
        i.putExtra("amount", amountedit);// Transaction Amount
        i.putExtra("result_mode", true);// Always true
        i.putExtra("action", "inApp");// InApp indication
        i.putExtra("txn_type", txn_type);// Mode of transaction
        i.putExtra("transaction_id", transaction_id); // Reference Id
        i.putExtra("package", package_name);
        i.putExtra("receipt", receipt);
        i.putExtra("receipt_print", "Yes");
        startActivityForResult(i, 600);
//
//
    }

//    public void functionPaybyBqrorUpi(String amt, String txn_type) {
//        finish();
//        amt = getConvertDoubleval(amt);
//        String receipt = "YES";
//        String CUSTOM_ACTION = "com.example.menusample.YOUR_ACTION_BQR";
//        package_name = com.pos.device.BuildConfig.APPLICATION_ID;
//        Intent i = new Intent();
//        i.setAction(CUSTOM_ACTION);
//        i.putExtra("amount", amt);
//        i.putExtra("txn_type", txn_type);
//        i.putExtra("action", "inApp");
//        i.putExtra("package", package_name);
//        i.putExtra("result_mode", true);
//        i.putExtra("receipt", receipt);
//        startActivity(i);
//
//
//    }

    public void functionPaybyBqrorUpi(String amt, String txn_type) {
        amt = getConvertDoubleval(amt);
        String CUSTOM_ACTION = "com.example.menusample.YOUR_ACTION_BQR";
        package_name = BuildConfig.APPLICATION_ID;
        Intent i = new Intent();
        i.setAction(CUSTOM_ACTION);
        i.putExtra("amount", amt);
        i.putExtra("txn_type", "SALE");
        i.putExtra("action", "inApp");
        i.putExtra("package", package_name);
        i.putExtra("result_mode", true);
        i.putExtra("receipt", "NO");
        startActivityForResult(i, 601);
    }

    public void onClickPayByBqrorUpi() {

        String amt = amountedit.getText().toString();
        if (amt.isEmpty()) {
            Toast.makeText(SetReceipt.this, "Enter Amount", Toast.LENGTH_SHORT).show();

        } else {
            String txn_type = "SALE";
            functionPaybyBqrorUpi(amt, txn_type);
        }

    }


//
//    public void functionPaybyCard(String amt, String txn_type) {
//        amt = getConvertDoubleval(amt);
//        String transaction_id = getTransactionId();
//        package_name = BuildConfig.APPLICATION_ID;
//        String receipt = "YES";
//
//        String CUSTOM_ACTION = "cn.desert.newpos.payui.master.YOUR_ACTION";
//        Intent i = new Intent();
//        i.setAction(CUSTOM_ACTION);
//        i.putExtra("amount", amt);
//        i.putExtra("result_mode", true);
//        i.putExtra("action", "inapp");
//        i.putExtra("txn_type", txn_type);
//        i.putExtra("transaction_id", transaction_id); // Reference Id
//        i.putExtra("package", package_name);
//        i.putExtra("receipt", receipt);
//        startActivityForResult(i, 600);
//      ;
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (data != null) {
                if (requestCode == 600) {
                    if (data.hasExtra("result_code")) {
                        boolean datas = data.getBooleanExtra("result_code", false);
                        if (data.getBooleanExtra("result_code", false)) {
                            String resp_data = data.getStringExtra("resp_data");
                            String card = data.getStringExtra("card_no");
                            int start_no = card.length() - 4;
                            int end = card.length();
                            card = card.substring(start_no, end);
                            chequenuedit.setText(card);
                            if (chequenuedit.getVisibility() == View.VISIBLE) {

                                if (chequenuedit.getText().length() != 6) {

                                    Toast.makeText(SetReceipt.this, "Please enter correct Cheque Number", Toast.LENGTH_SHORT).show();
                                } else {
                                    savecardapi();
                                }
                            } else {
                                savecardapi();
                            }
//                            data.setClass(this, InAppApprovedActivity.class);
//                            startActivity(data);
                        } else {
                            Toast.makeText(getApplicationContext(), data.getStringExtra("message"), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Empty result", Toast.LENGTH_LONG).show();
                    }

                } else if (requestCode == 601) {
//                    Toast.makeText(getApplicationContext(), "testing 123", Toast.LENGTH_LONG).show();

                    if (data.hasExtra("result_code")) {
                        boolean datas = data.getBooleanExtra("result_code", false);
                        if (data.getBooleanExtra("result_code", false)) {
                            String resp_data = data.getStringExtra("resp_data");
                            savecardapi();
                        } else {
                            Toast.makeText(getApplicationContext(), data.getStringExtra("message"), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Empty result", Toast.LENGTH_LONG).show();
                    }
                }
            } else {
                Toast.makeText(getApplicationContext(), "Data null", Toast.LENGTH_LONG).show();
            }
        } else
            Toast.makeText(getApplicationContext(), "Result Failed", Toast.LENGTH_LONG).show();
    }

    private void savecardapi() {
//
        if (CommonUtils.isNetworkConnected(SetReceipt.this)) {
            ProgressDialog pdf = new ProgressDialog(SetReceipt.this);
            pdf.setMessage("Please wait");
            pdf.show();
            submit.setVisibility(View.INVISIBLE);
//        {
            savedonationViewModle.getMachineListModleLiveData(SetReceipt.this, "0", securityedit.getText().toString(), "0", "Donation", nameedit.getText().toString(), addressedit.getText().toString(), mobileedit.getText().toString(), emailedit.getText().toString(), "" + donationmotiveid, null, null, "0", payment, currencymaster, chequenuedit.getText().toString(), amountedit.getText().toString(), "test", "test", jsonArray, remarkedit.getText().toString(), pancardedit.getText().toString(), preference.getAccessToken(), preference.getAuth()).observe(SetReceipt.this, new Observer<SaveDonationReceipt>() {
                @Override
                public void onChanged(SaveDonationReceipt saveDonationReceipt) {

                    if (saveDonationReceipt.getStatuscode().equalsIgnoreCase("Success")) {
                        submit.setVisibility(View.VISIBLE);
                        Toast.makeText(SetReceipt.this, "Sucessfully donate", Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(SetReceipt.this, GetReceipt.class);
                        pdf.dismiss();
                        intent.putExtra("receiptid", saveDonationReceipt.getData().toString());
                        intent.putExtra("auth", auth);
                        intent.putExtra("token", token);
                        startActivity(intent);
                        finish();
                    } else {
                        submit.setVisibility(View.VISIBLE);

//                        if (pd.isShowing()) {
//                            pd.dismiss();
//                        }

                        pdf.dismiss();
//                    onBackPressed();
                        Toast.makeText(SetReceipt.this, saveDonationReceipt.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Intent intent = new Intent(SetReceipt.this, TotalCollection.class);
            startActivity(intent);
            finish();
        }

    }


    public String getConvertDoubleval(String amount) {
        Log.e("tag", "print amount" + amount);
        if (amount.length() == 0) {
            amount = "0";
        }
        DecimalFormat formatter = new DecimalFormat("#0.00");
        double d = Double.parseDouble(amount);
        amount = formatter.format(d);
        Log.e("tag", "print new amount" + amount);
        return amount;
    }

    public String getTransactionId() {
        long number = (long) Math.floor(Math.random() * 900000L) + 100000L;
        String transaction_id = addTxnNo();
        return transaction_id;
    }

    public String addTxnNo() {
        int currenNo = 1;
        int nextNo = (currenNo + 1) % 1000000;
        return String.format("%06d", nextNo);
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}

