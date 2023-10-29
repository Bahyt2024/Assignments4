
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


    class WeatherData {
        private double temperature;
        private double humidity;
        private double windSpeed;


        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public double getHumidity() {
            return humidity;
        }

        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }

        public double getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(double windSpeed) {
            this.windSpeed = windSpeed;
        }

    }


    interface WeatherAPIAdapter {
        WeatherData fetchWeatherData();
    }


    class OpenWeatherMapAdapter implements WeatherAPIAdapter {
        private OpenWeatherMapAPI api;

        public WeatherData fetchWeatherData() {

            OpenWeatherMapData apiData = api.fetchData("Altay");

            WeatherData commonData = new WeatherData();
            commonData.setTemperature(apiData.getTemperature());
            commonData.setHumidity(apiData.getHumidity());
            commonData.setWindSpeed(apiData.getWindSpeed());
            // Map other fields as necessary

            return commonData;
        }
    }


    class AccuWeatherAdapter implements WeatherAPIAdapter {
        private AccuWeatherAPI api;

        public WeatherData fetchWeatherData() {
            AccuWeatherData apiData = api.getData();

            WeatherData commonData = new WeatherData();
            commonData.setTemperature(apiData.getTemperatureCelsius());
            commonData.setHumidity(apiData.getHumidityPercentage());
            commonData.setWindSpeed(apiData.getWindSpeedKmph());


            return commonData;
        }
    }


    public class Assignment4 {
        public static void main(String[] args) {
            WeatherAPIAdapter chosenAdapter = new OpenWeatherMapAdapter();
            WeatherData weatherData = chosenAdapter.fetchWeatherData();


            System.out.println("Temperature: " + weatherData.getTemperature());
            System.out.println("Humidity: " + weatherData.getHumidity());
            System.out.println("Wind Speed: " + weatherData.getWindSpeed());

        }
    }
class OpenWeatherMapData {
    private double temperature;
    private double humidity;
    private double windSpeed;


    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

}

class AccuWeatherAPI {


    public AccuWeatherData getData() {

        AccuWeatherData mockData = new AccuWeatherData();
        mockData.setTemperatureCelsius(25.5);
        mockData.setHumidityPercentage(70.2);
        mockData.setWindSpeedKmph(15.8);


        return mockData;
    }
}


class AccuWeatherData {
    private double temperatureCelsius;
    private double humidityPercentage;
    private double windSpeedKmph;


    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }

    public void setTemperatureCelsius(double temperatureCelsius) {
        this.temperatureCelsius = temperatureCelsius;
    }

    public double getHumidityPercentage() {
        return humidityPercentage;
    }

    public void setHumidityPercentage(double humidityPercentage) {
        this.humidityPercentage = humidityPercentage;
    }

    public double getWindSpeedKmph() {
        return windSpeedKmph;
    }

    public void setWindSpeedKmph(double windSpeedKmph) {
        this.windSpeedKmph = windSpeedKmph;
    }
}

class OpenWeatherMapAPI {
    private static final String API_KEY = "4df8ebc62fe352d855a003bc56248be6\n";
    private static final String API_ENDPOINT = "https://api.openweathermap.org/data/2.5/weather?" + API_KEY;

    public OpenWeatherMapData fetchData(String cityName) {
        OpenWeatherMapData openWeatherMapData = new OpenWeatherMapData();

        try {
            URL url = new URL(API_ENDPOINT.replace("CityName", cityName));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();


                openWeatherMapData.setTemperature(25.0);
                openWeatherMapData.setHumidity(60.0);
                openWeatherMapData.setWindSpeed(10.5); }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return openWeatherMapData;
    }
}

