# pulsar-weather-function
Apache Pulsar Function to parse weather JSON


### Raw Topic

* persistent://public/default/aircraftweather

### Output Topics

* persistent://public/default/aircraftweatherdead
* persistent://public/default/aircraftweatherlog
* persistent://public/default/aircraftweather


### Function Deployment

````
bin/pulsar-admin functions status --name Weather


bin/pulsar-admin functions stop --name Weather --namespace default --tenant public
bin/pulsar-admin functions delete --name Weather --namespace default --tenant public

bin/pulsar-admin functions create --auto-ack true --jar /opt/demo/java/pulsar-weather-function/target/weather-1.0.jar --classname "dev.pulsarfunction.weather.WeatherFunction" --dead-letter-topic "persistent://public/default/weatherdead" --inputs "persistent://public/default/weather" --log-topic "persistent://public/default/aircraftweatherlog" --name Weather --namespace default --tenant public --max-message-retries 5



````



### Data parsed by Pulsar Function and sent to persistent://public/default/weather

````
bin/pulsar-client consume "persistent://public/default/weather" -s "weather-cli-consumer" -n 0


````

### Flink SQL

````
sdk use java 8.0.302-open
./bin/sql-client.sh embedded --library /opt/flink/flink-release-1.13.2/flink-1.13.2/sqllib -e /opt/flink/flink-release-1.13.2/flink-1.13.2/conf/sql-client-conf.yaml

CREATE CATALOG pulsar WITH (
   'type' = 'pulsar',
   'service-url' = 'pulsar://pulsar1:6650',
   'admin-url' = 'http://pulsar1:8080',
   'format' = 'json'
);

USE CATALOG pulsar;

SHOW TABLES;


Flink SQL> describe aircraftweather;
+-------------------------+--------+-------+-----+--------+-----------+
|                    name |   type |  null | key | extras | watermark |
+-------------------------+--------+-------+-----+--------+-----------+
|              dewpoint_c | DOUBLE | false |     |        |           |
|              dewpoint_f | DOUBLE | false |     |        |           |
|         dewpoint_string | STRING |  true |     |        |           |
|            heat_index_c |    INT | false |     |        |           |
|            heat_index_f |    INT | false |     |        |           |
|       heat_index_string | STRING |  true |     |        |           |
|           icon_url_base | STRING |  true |     |        |           |
|           icon_url_name | STRING |  true |     |        |           |
|                latitude | DOUBLE | false |     |        |           |
|                location | STRING |  true |     |        |           |
|               longitude | DOUBLE | false |     |        |           |
|                  ob_url | STRING |  true |     |        |           |
|        observation_time | STRING |  true |     |        |           |
| observation_time_rfc822 | STRING |  true |     |        |           |
|             pressure_in | DOUBLE | false |     |        |           |
|             pressure_mb | DOUBLE | false |     |        |           |
|         pressure_string | STRING |  true |     |        |           |
|       relative_humidity |    INT | false |     |        |           |
|              station_id | STRING |  true |     |        |           |
|                  temp_c | DOUBLE | false |     |        |           |
|                  temp_f | DOUBLE | false |     |        |           |
|      temperature_string | STRING |  true |     |        |           |
|     two_day_history_url | STRING |  true |     |        |           |
|           visibility_mi | DOUBLE | false |     |        |           |
|                 weather | STRING |  true |     |        |           |
|            wind_degrees |    INT | false |     |        |           |
|                wind_dir | STRING |  true |     |        |           |
|                 wind_kt |    INT | false |     |        |           |
|                wind_mph | DOUBLE | false |     |        |           |
|             wind_string | STRING |  true |     |        |           |
+-------------------------+--------+-------+-----+--------+-----------+
30 rows in set


