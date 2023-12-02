package com.hotelreservationapp.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.hotelreservationapp.R;
import com.hotelreservationapp.activity.HistoryActivity;
import com.hotelreservationapp.model.Reservation;
import com.hotelreservationapp.request.ResponseObject;
import com.hotelreservationapp.service.WebService;
import com.hotelreservationapp.utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationAdapter extends ArrayAdapter<Reservation> {
    public ReservationAdapter(@NonNull Context context, List<Reservation> reservations) {
        super(context, 0, reservations);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
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
        Button confirm = convertView.findViewById(R.id.history_confirm);

        if (reservation.getStatus().equals(Constant.STATUS_PENDING)) {
            confirm.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.red));
            confirm.setText("Hủy đặt phòng");

        } else if (reservation.getStatus().equals(Constant.STATUS_ACCEPTED)) {
            confirm.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.main_color));
            confirm.setText("Xác nhận xong");
        } else {
            confirm.setVisibility(View.GONE);
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirm.getText().equals("Hủy đặt phòng")) {
                    showCancelConfirmationDialog(reservation.getId(), Constant.STATUS_CANCELED, confirm);
                    history_status.setText(Constant.STATUS_CANCELED);
                } else if(confirm.getText().equals("Xác nhận xong")) {
                    changeStatusReservation(reservation.getId(), Constant.STATUS_DONE, confirm);
                    history_status.setText(Constant.STATUS_DONE);
                }
            }
        });

        return convertView;
    }

    private void showCancelConfirmationDialog(int id, String status, Button button) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xác nhận hủy đặt phòng")
                .setMessage("Bạn có chắc chắn muốn hủy đặt phòng?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        changeStatusReservation(id, status, button);
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void changeStatusReservation(int id, String status, Button confirm) {
        WebService.api.changeStatusReservation(id, status).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                ResponseObject responseObject = response.body();
                if(responseObject.getCode() == 200) {
                    if(status.equals(Constant.STATUS_CANCELED)) {
                        confirm.setText("Đã hủy");
                        confirm.setEnabled(false);
                        Toast.makeText(getContext(), "Đã hủy đặt phòng", Toast.LENGTH_SHORT).show();
                    } else if(status.equals(Constant.STATUS_DONE)) {
                        confirm.setText("Đã xong");
                        confirm.setEnabled(false);
                        Toast.makeText(getContext(), "Xác nhận xong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), responseObject.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });
    }

}
