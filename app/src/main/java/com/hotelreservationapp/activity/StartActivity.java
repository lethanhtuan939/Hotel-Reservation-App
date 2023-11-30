package com.hotelreservationapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hotelreservationapp.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button button = (Button) findViewById(R.id.btnStart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, SigninActivity.class);

                startActivity(intent);
            }
        });
    }

//    class FetchLocation extends Thread {
//        String data = "";
//
//        @Override
//        public void run() {
//
//            try {
//                URL url = new URL(Constant.PRE_FIX + "/api/v1/locations");
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                InputStream inputStream = httpURLConnection.getInputStream();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//
//                String line = "";
//                while ((line = bufferedReader.readLine()) != null) {
//                    data += line;
//                }
//
//                bufferedReader.close();
//                if (!data.isEmpty()) {
//                    JSONObject jsonObject = new JSONObject(data);
//                    JSONArray response = jsonObject.getJSONArray("data");
//                    locations.clear();
//                    for (int i = 0; i < response.length(); i++) {
//                        JSONObject object = response.getJSONObject(i);
//
//                        String locationName = object.getString("name");
//                        int idLocation = object.getInt("id");
//
//                        Location location = new Location(idLocation, locationName);
//
//                        locations.add(location);
//                    }
//                }
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
}