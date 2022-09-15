package dev.pulsarfunction.weather;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Image implements Serializable{
    public String url;
    public String title;
    public String link;
}
