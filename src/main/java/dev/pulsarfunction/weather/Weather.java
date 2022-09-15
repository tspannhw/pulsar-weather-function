package dev.pulsarfunction.weather;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * weather
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather implements Serializable {
    private String location;
    private String station_id;
    private double latitude;
    private double longitude;
    private String observation_time;
    private String observation_time_rfc822;
    private String weather;
    private String temperature_string;
    private double temp_f;
    private double temp_c;
    private int relative_humidity;
    private String wind_string;
    private String wind_dir;
    private int wind_degrees;
    private double wind_mph;
    private int wind_kt;
    private String pressure_string;
    private double pressure_mb;
    private double pressure_in;
    private String dewpoint_string;
    private double dewpoint_f;
    private double dewpoint_c;
    private String heat_index_string;
    private int heat_index_f;
    private int heat_index_c;
    private double visibility_mi;
    private String icon_url_base;
    private String two_day_history_url;
    private String icon_url_name;
    private String ob_url;

    public Weather() {
        super();
    }

    public Weather( String location, String station_id, double latitude, double longitude, String observation_time, String observation_time_rfc822, String weather, String temperature_string, double temp_f, double temp_c, int relative_humidity, String wind_string, String wind_dir, int wind_degrees, double wind_mph, int wind_kt, String pressure_string, double pressure_mb, double pressure_in, String dewpoint_string, double dewpoint_f, double dewpoint_c, String heat_index_string, int heat_index_f, int heat_index_c, double visibility_mi, String icon_url_base, String two_day_history_url, String icon_url_name, String ob_url) {
        super();
        this.location = location;
        this.station_id = station_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.observation_time = observation_time;
        this.observation_time_rfc822 = observation_time_rfc822;
        this.weather = weather;
        this.temperature_string = temperature_string;
        this.temp_f = temp_f;
        this.temp_c = temp_c;
        this.relative_humidity = relative_humidity;
        this.wind_string = wind_string;
        this.wind_dir = wind_dir;
        this.wind_degrees = wind_degrees;
        this.wind_mph = wind_mph;
        this.wind_kt = wind_kt;
        this.pressure_string = pressure_string;
        this.pressure_mb = pressure_mb;
        this.pressure_in = pressure_in;
        this.dewpoint_string = dewpoint_string;
        this.dewpoint_f = dewpoint_f;
        this.dewpoint_c = dewpoint_c;
        this.heat_index_string = heat_index_string;
        this.heat_index_f = heat_index_f;
        this.heat_index_c = heat_index_c;
        this.visibility_mi = visibility_mi;
        this.icon_url_base = icon_url_base;
        this.two_day_history_url = two_day_history_url;
        this.icon_url_name = icon_url_name;
        this.ob_url = ob_url;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Weather.class.getSimpleName() + "[", "]")
                .add("location='" + location + "'")
                .add("station_id='" + station_id + "'")
                .add("latitude=" + latitude)
                .add("longitude=" + longitude)
                .add("observation_time='" + observation_time + "'")
                .add("observation_time_rfc822='" + observation_time_rfc822 + "'")
                .add("weather='" + weather + "'")
                .add("temperature_string='" + temperature_string + "'")
                .add("temp_f=" + temp_f)
                .add("temp_c=" + temp_c)
                .add("relative_humidity=" + relative_humidity)
                .add("wind_string='" + wind_string + "'")
                .add("wind_dir='" + wind_dir + "'")
                .add("wind_degrees=" + wind_degrees)
                .add("wind_mph=" + wind_mph)
                .add("wind_kt=" + wind_kt)
                .add("pressure_string='" + pressure_string + "'")
                .add("pressure_mb=" + pressure_mb)
                .add("pressure_in=" + pressure_in)
                .add("dewpoint_string='" + dewpoint_string + "'")
                .add("dewpoint_f=" + dewpoint_f)
                .add("dewpoint_c=" + dewpoint_c)
                .add("heat_index_string='" + heat_index_string + "'")
                .add("heat_index_f=" + heat_index_f)
                .add("heat_index_c=" + heat_index_c)
                .add("visibility_mi=" + visibility_mi)
                .add("icon_url_base='" + icon_url_base + "'")
                .add("two_day_history_url='" + two_day_history_url + "'")
                .add("icon_url_name='" + icon_url_name + "'")
                .add("ob_url='" + ob_url + "'")
                .toString();
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStation_id() {
        return station_id;
    }

    public void setStation_id(String station_id) {
        this.station_id = station_id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getObservation_time() {
        return observation_time;
    }

    public void setObservation_time(String observation_time) {
        this.observation_time = observation_time;
    }

    public String getObservation_time_rfc822() {
        return observation_time_rfc822;
    }

    public void setObservation_time_rfc822(String observation_time_rfc822) {
        this.observation_time_rfc822 = observation_time_rfc822;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature_string() {
        return temperature_string;
    }

    public void setTemperature_string(String temperature_string) {
        this.temperature_string = temperature_string;
    }

    public double getTemp_f() {
        return temp_f;
    }

    public void setTemp_f(double temp_f) {
        this.temp_f = temp_f;
    }

    public double getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(double temp_c) {
        this.temp_c = temp_c;
    }

    public int getRelative_humidity() {
        return relative_humidity;
    }

    public void setRelative_humidity(int relative_humidity) {
        this.relative_humidity = relative_humidity;
    }

    public String getWind_string() {
        return wind_string;
    }

    public void setWind_string(String wind_string) {
        this.wind_string = wind_string;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public void setWind_dir(String wind_dir) {
        this.wind_dir = wind_dir;
    }

    public int getWind_degrees() {
        return wind_degrees;
    }

    public void setWind_degrees(int wind_degrees) {
        this.wind_degrees = wind_degrees;
    }

    public double getWind_mph() {
        return wind_mph;
    }

    public void setWind_mph(double wind_mph) {
        this.wind_mph = wind_mph;
    }

    public int getWind_kt() {
        return wind_kt;
    }

    public void setWind_kt(int wind_kt) {
        this.wind_kt = wind_kt;
    }

    public String getPressure_string() {
        return pressure_string;
    }

    public void setPressure_string(String pressure_string) {
        this.pressure_string = pressure_string;
    }

    public double getPressure_mb() {
        return pressure_mb;
    }

    public void setPressure_mb(double pressure_mb) {
        this.pressure_mb = pressure_mb;
    }

    public double getPressure_in() {
        return pressure_in;
    }

    public void setPressure_in(double pressure_in) {
        this.pressure_in = pressure_in;
    }

    public String getDewpoint_string() {
        return dewpoint_string;
    }

    public void setDewpoint_string(String dewpoint_string) {
        this.dewpoint_string = dewpoint_string;
    }

    public double getDewpoint_f() {
        return dewpoint_f;
    }

    public void setDewpoint_f(double dewpoint_f) {
        this.dewpoint_f = dewpoint_f;
    }

    public double getDewpoint_c() {
        return dewpoint_c;
    }

    public void setDewpoint_c(double dewpoint_c) {
        this.dewpoint_c = dewpoint_c;
    }

    public String getHeat_index_string() {
        return heat_index_string;
    }

    public void setHeat_index_string(String heat_index_string) {
        this.heat_index_string = heat_index_string;
    }

    public int getHeat_index_f() {
        return heat_index_f;
    }

    public void setHeat_index_f(int heat_index_f) {
        this.heat_index_f = heat_index_f;
    }

    public int getHeat_index_c() {
        return heat_index_c;
    }

    public void setHeat_index_c(int heat_index_c) {
        this.heat_index_c = heat_index_c;
    }

    public double getVisibility_mi() {
        return visibility_mi;
    }

    public void setVisibility_mi(double visibility_mi) {
        this.visibility_mi = visibility_mi;
    }

    public String getIcon_url_base() {
        return icon_url_base;
    }

    public void setIcon_url_base(String icon_url_base) {
        this.icon_url_base = icon_url_base;
    }

    public String getTwo_day_history_url() {
        return two_day_history_url;
    }

    public void setTwo_day_history_url(String two_day_history_url) {
        this.two_day_history_url = two_day_history_url;
    }

    public String getIcon_url_name() {
        return icon_url_name;
    }

    public void setIcon_url_name(String icon_url_name) {
        this.icon_url_name = icon_url_name;
    }

    public String getOb_url() {
        return ob_url;
    }

    public void setOb_url(String ob_url) {
        this.ob_url = ob_url;
    }
}