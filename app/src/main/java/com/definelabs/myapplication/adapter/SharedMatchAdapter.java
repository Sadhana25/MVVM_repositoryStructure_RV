package com.definelabs.myapplication.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.definelabs.myapplication.R;
import com.definelabs.myapplication.database.Match;
import com.definelabs.myapplication.model.Venue;
import java.util.List;
public class SharedMatchAdapter extends RecyclerView.Adapter<SharedMatchAdapter.ViewHolder> {
    FragmentActivity context;
    List<Match> venueList;
    ItemClickListenerSave clickListenersave;

   public SharedMatchAdapter(FragmentActivity context, List<Match> venueList, ItemClickListenerSave clickListenersave) {
        Log.d("response", "MatchesListAdapter: " + venueList.size());
        this.context = context;
        this.venueList = venueList;
        this.clickListenersave= clickListenersave;

    }
    @NonNull
    @Override
    public SharedMatchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new SharedMatchAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SharedMatchAdapter.ViewHolder holder, int position) {

         holder.tv_headerAddress.setText(venueList.get(position).getMatch_name());
        holder.tv_distance.setText(venueList.get(position).getMatch_distance()+" km");
        String address = venueList.get(position).getMatch_address();
        if (address != null) {
            holder.tv_address.setText(address);
        } else {
            holder.tv_address.setText("Address not Available");
        }

        String phone_number = venueList.get(position).getMatch_phone();
        if (phone_number != null) {
            holder.tv_phone_number.setText(phone_number);
        } else {
            holder.tv_phone_number.setText("Details Not Available");
        }
        holder.star_image.setImageResource(R.drawable.ic_save);

        holder.star_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete functionality
                clickListenersave.onMatchClick(venueList.get(position));
            }
        });
    }
    @Override
    public long getItemId(int position) {
        return
                super.getItemId(position);
    }
    @Override
    public int getItemCount() {
       // Log.d("adapter", "getItemCount: " + venueList.size());
        return venueList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_headerAddress;
        TextView tv_distance;
        TextView tv_address;
        TextView tv_phone_number;
        ImageView star_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_headerAddress = itemView.findViewById(R.id.tv_headeraddress);
            tv_distance = itemView.findViewById(R.id.tv_distance);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_phone_number = itemView.findViewById(R.id.tv_contact);
            star_image = itemView.findViewById(R.id.img_star);
        }
    }
    public interface ItemClickListenerSave{
        public void onMatchClick(Match match);

    }


}
