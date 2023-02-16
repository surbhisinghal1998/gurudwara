package akaalwebsoft.com.gurudwara.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import akaalwebsoft.com.gurudwara.R;
import akaalwebsoft.com.gurudwara.modle.DonationMotiveModle;

public class DonationMotiveAdapter extends RecyclerView.Adapter<DonationMotiveAdapter.MyviewHolder> {
    Context context;
    List<DonationMotiveModle.Datum> donationMotiveModles = new ArrayList<>();
    DonationInterface donationInterface;

    public DonationMotiveAdapter(Context context, List<DonationMotiveModle.Datum> donationMotiveModles, DonationInterface donationInterface) {
        this.context = context;
        this.donationMotiveModles = donationMotiveModles;
        this.donationInterface = donationInterface;
    }

    @NonNull
    @Override
    public DonationMotiveAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.donationmotive, parent, false);
        return new DonationMotiveAdapter.MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DonationMotiveAdapter.MyviewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.donationname.setText(donationMotiveModles.get(position).getDonationName());
//        holder.image.setImageResource(imagelist.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donationInterface.donationmethod(position, holder.donationname.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return donationMotiveModles.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView donationname;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            donationname = itemView.findViewById(R.id.donationname);
        }
    }

    public interface DonationInterface {
        void donationmethod(int position, String name);
    }
}
