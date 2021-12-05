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
import com.definelabs.myapplication.repository.SRepository;
import com.definelabs.myapplication.database.AppDatabase;
import com.definelabs.myapplication.database.Match;
import com.definelabs.myapplication.model.Venue;

import java.util.List;

public class MatchesListAdapter extends RecyclerView.Adapter<MatchesListAdapter.ViewHolder> {
    private static String TAG=MatchesListAdapter.class.getName();
    FragmentActivity context;
    List<Venue> venueList;
    SRepository sRepository;
    ItemClickListener clickListener;
    Match match;
    public MatchesListAdapter(FragmentActivity context, List<Venue> venueList, ItemClickListener clickListener) {
        Log.d("response", "MatchesListAdapter: " + venueList.size());
        this.context = context;
        this.venueList = venueList;
        this.clickListener=clickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_headerAddress.setText(venueList.get(position).getName().toString());
        holder.tv_distance.setText(venueList.get(position).getLocation().getDistance().toString()+" km");
        String address = venueList.get(position).getLocation().getAddress();
        if (address != null) {
            holder.tv_address.setText(address);
        } else {
            holder.tv_address.setText("Address not Available");
        }
        String phone_number = venueList.get(position).getContact().getPhone();
        if (phone_number != null) {
            Log.d(TAG, "onBindViewHolder: +phone_number"+phone_number);
            holder.tv_phone_number.setText(phone_number);
        } else {
            holder.tv_phone_number.setText("Details Not Available");
        }

        // logic to check star and active or not
         // background thread to check data
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "run: " + venueList.get(position).getId());
                    Boolean status=AppDatabase.getInstance(context).matchDao()
                            .dataExist(venueList.get(position).getId());

                   // Boolean status=
                  //  Log.d(TAG, "run: " + todo.toString());
                    Log.d(TAG, "run: "+status.toString()+position);
                    if (status==true) {
                       holder.star_image.setImageResource(R.drawable.ic_save);

                    }
                    else {
                        holder.star_image.setImageResource(R.drawable.ic_starall);
                    }

                }
            }).start();

           holder.star_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.star_image.setImageResource(R.drawable.ic_save);
                Match match=new Match();
                String match_id=venueList.get(position).getId();
                String header_name=venueList.get(position).getName();
                String address=venueList.get(position).getLocation().getAddress();
                int distance=venueList.get(position).getLocation().getDistance();
                String phone_number=venueList.get(position).getContact().getPhone();
                match.setMatch_id(match_id);
                match.setMatch_name(header_name);
                match.setMatch_distance(distance);
                match.setMatch_phone(phone_number);
                match.setMatch_address(address);
                Log.d(TAG, "onClick: "+match_id+header_name+distance+phone_number+address);
                //checkIdExist();
                clickListener.onMatchClick(match);
            }
        });

    }
    @Override
    public int getItemCount() {
        Log.d("adapter", "getItemCount: " + venueList.size());
        return venueList.size();

    }
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
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
    public interface ItemClickListener{
        public void onMatchClick(Match match);
    }


}
