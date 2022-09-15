package dev.pulsarfunction.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 {

 "location":"Florala, Florala Municipal Airport, AL",
 "station_id":"K0J4",
 "latitude":31.0425,
 "longitude":-86.31167,
 "observation_time":"Last Updated on Sep 3 2022, 10:58 am CDT",
 "observation_time_rfc822":"Sat, 03 Sep 2022 10:58:00 -0500",
 "weather":"Mostly Cloudy",
 "temperature_string":"86.0 F (30.0 C)",
 "temp_f":86.0,"temp_c":30.0,"relative_humidity":63,
 "wind_string":"Southeast at 3.5 MPH (3 KT)",
 "wind_dir":"Southeast","wind_degrees":120,
 "wind_mph":3.5,"wind_kt":3,"pressure_string":"1019.7 mb",
 "pressure_mb":1019.7,"pressure_in":30.13,"dewpoint_string":"72.0 F (22.2 C)",
 "dewpoint_f":72.0,"dewpoint_c":22.2,"heat_index_string":"92 F (33 C)",
 "heat_index_f":92,"heat_index_c":33,"visibility_mi":10.0,
 "icon_url_base":"https://forecast.weather.gov/images/wtf/small/",
 "two_day_history_url":"https://www.weather.gov/data/obhistory/K0J4.html",
 "icon_url_name":"bkn.png","ob_url":"https://www.weather.gov/data/METAR/K0J4.1.txt",


 */
public class WeatherParserService {

    /**
     * weather parser
     * @param message
     * @return Weather object
     */
    private Weather parseMessage(String message) {
        Weather weather = new Weather();
        if (message == null) {
            return weather;
        }

        RawWeather rawWeather = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            rawWeather = mapper.readValue(message, RawWeather.class);

            if ( rawWeather != null ) {
                weather.setWeather(rawWeather.getWeather());

                weather.setDewpoint_c(rawWeather.getDewpoint_c());
                weather.setDewpoint_f(rawWeather.getDewpoint_f());
                weather.setDewpoint_string(rawWeather.getDewpoint_string());

                weather.setHeat_index_c(rawWeather.getHeat_index_c());
                weather.setHeat_index_f(rawWeather.getHeat_index_f());
                weather.setHeat_index_string(rawWeather.getHeat_index_string());

                weather.setLatitude(rawWeather.getLatitude());
                weather.setLongitude(rawWeather.getLongitude());
                weather.setLocation(rawWeather.getLocation());

                weather.setOb_url(rawWeather.getOb_url());
                weather.setIcon_url_base(rawWeather.getIcon_url_base());
                weather.setIcon_url_name(rawWeather.getIcon_url_name());
                weather.setTwo_day_history_url(rawWeather.getTwo_day_history_url());

                weather.setObservation_time(rawWeather.getObservation_time());
                weather.setObservation_time_rfc822(rawWeather.getObservation_time_rfc822());

                weather.setWind_string(rawWeather.getWind_string());
                weather.setWind_degrees(rawWeather.getWind_degrees());
                weather.setWind_dir(rawWeather.getWind_dir());
                weather.setWind_kt(rawWeather.getWind_kt());
                weather.setWind_mph(rawWeather.getWind_mph());

                weather.setPressure_in(rawWeather.getPressure_in());
                weather.setPressure_string(rawWeather.getPressure_string());
                weather.setPressure_mb(rawWeather.getPressure_mb());

                weather.setRelative_humidity(rawWeather.getRelative_humidity());

                weather.setStation_id(rawWeather.getStation_id());

                weather.setTemp_c(rawWeather.getTemp_c());
                weather.setTemp_f(rawWeather.getTemp_f());
                weather.setTemperature_string(rawWeather.getTemperature_string());

                weather.setVisibility_mi(rawWeather.getVisibility_mi());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return weather;
        }

        return weather;
    }

    /**
     * return a clean aircraft
     *byte[]
     * @param input device as a raw string in bytes
     * @return Observation object
     */
    public Weather deserialize(byte[] input) {
        if (input == null) {
            return null;
        }
        else {
            return parseMessage(new String(input));
        }
    }
}
