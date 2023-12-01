package com.hotelreservationapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hotelreservationapp.R;
import com.hotelreservationapp.model.Reservation;
import com.hotelreservationapp.utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReservationAdapter extends ArrayAdapter<Reservation> {
    public ReservationAdapter(@NonNull Context context, List<Reservation> reservations) {
        super(context,0, reservations);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.reservations_item_custom, parent, false);
        }

        Reservation reservation = getItem(position);

        TextView history_name_hotel = convertView.findViewById(R.id.history_name_hotel);
        TextView history_status = convertView.findViewById(R.id.history_status);
        ImageView history_img_room = convertView.findViewById(R.id.history_img_room);
        TextView history_name_room = convertView.findViewById(R.id.history_name_room);
        TextView history_room_type = convertView.findViewById(R.id.history_room_type);
        TextView history_dateEnd = convertView.findViewById(R.id.history_dateEnd);
        TextView history_payMethod = convertView.findViewById(R.id.history_payMethod);
        TextView history_price = convertView.findViewById(R.id.history_price);

        history_name_hotel.setText(reservation.getRoom().getHotel().getName());
        history_status.setText(reservation.getStatus());
        history_name_room.setText(reservation.getRoom().getName());
        history_room_type.setText(reservation.getDayStart() + " - " + reservation.getDayEnd());
        history_dateEnd.setText(reservation.getRoom().getRoomType().getName());
        history_payMethod.setText(reservation.getPaymentMethod());
        history_price.setText("$ " + reservation.getPrice());

        Picasso.get().load(Constant.PRE_FIX + "/file/" + reservation.getRoom().getImgRoom()).into(history_img_room);

        return convertView;
    }
}
