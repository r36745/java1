package rosemak.weekfour;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//Steven Roseman
//Java1


public class MainActivity extends Activity {
    TextView cityTemp, chillDirection, weatherSpeed;
    public static final String TAG = MainActivity.class.getSimpleName();
    protected JSONObject weatherData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cityTemp = (TextView) findViewById(R.id.weather_cityTemp);
        chillDirection = (TextView) findViewById(R.id.weather_chillDirection);
        weatherSpeed = (TextView) findViewById(R.id.weather_cityWindSpeed);


        if (isNetworkAvailable()) {

            GetWeather getWeatherTask = new GetWeather();
            getWeatherTask.execute();
        } else {

            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        }



    }





    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    private class GetWeather extends WeatherTask {

        @Override
        protected JSONObject doInBackground(Object[] objects) {

            int responseCode = -1;
            JSONObject jsonResponse = null;


            try {

                URL url = new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%3D2502265&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {

                    InputStream inputStream = connection.getInputStream();

                    String readerReader = IOUtils.toString(inputStream);

                    jsonResponse = new JSONObject(readerReader);

                    jsonResponse = (jsonResponse != null) ? jsonResponse.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("wind") : null;
                    Log.i(TAG, "jsonResponse" + jsonResponse.toString());

                } else {
                    Log.i(TAG, "Error, unsuccessful response code: " + responseCode);
                }

            } catch (MalformedURLException e) {

                Log.e(TAG, "Exception caught:", e);
            } catch (IOException e) {
                Log.e(TAG, "Exception caught: ", e);
            } catch (Exception e) {
                Log.e(TAG, "Exception caught: ", e);
            }

            return jsonResponse;


        }

        @Override
        protected void onPostExecute(JSONObject result) {


            weatherData = result;


            try {
                String chill = weatherData.getString("chill");
                cityTemp.setText(chill);
                String direction = weatherData.getString("direction");
                chillDirection.setText(direction);
                String windSpeed = weatherData.getString("speed");
                weatherSpeed.setText(windSpeed);


            }catch (Exception e) {

                Log.e(TAG, "Error Exception");
            }





        }

    }




}
