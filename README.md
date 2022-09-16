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

select temperature_string, weather, wind_string, pressure_string, dewpoint_string, heat_index_string, observation_time, latitude, longitude, location from aircraftweather;

select max(temp_f) as MaxTemp, location from aircraftweather where temp_f is not null and location is not null group by location;

select  temperature_string, weather, wind_string, 
       pressure_string, dewpoint_string, heat_index_string, 
       observation_time, 
       latitude, longitude, location, alt_baro,
       gs,
       alt_geom,
       baro_rate,
       mach, 
       hex, flight
FROM aircraft FULL JOIN aircraftweather ON aircraft.lat = aircraftweather.latitude
and aircraft.lon = aircraftweather.;



select  alt_baro,
       gs,
       alt_geom,
       baro_rate,
       mach, 
       hex, flight,temperature_string, weather, wind_string, 
       pressure_string, dewpoint_string, heat_index_string, 
       observation_time, 
       latitude, longitude, location
FROM aircraft  JOIN aircraftweather ON aircraft.lat = aircraftweather.latitude
and aircraft.lon = aircraftweather.longitude;


select  alt_baro,
       gs,
       alt_geom,
       baro_rate,
       mach, 
       hex, flight,temperature_string, weather, wind_string, 
       pressure_string, dewpoint_string, heat_index_string, 
       observation_time, 
       latitude, longitude, location
FROM aircraft  LEFT JOIN aircraftweather ON aircraft.lat = aircraftweather.latitude
and aircraft.lon = aircraftweather.longitude;


CREATE TABLE airweather3 (
`dewpoint_c` DOUBLE,                      
`dewpoint_f` DOUBLE,                      
`dewpoint_string` STRING,                       
`heat_index_c` INT,                      
`heat_index_f` INT,                      
`heat_index_string` STRING,                       
`icon_url_base` STRING,                       
`icon_url_name` STRING,                       
`latitude` DOUBLE,                      
`location` STRING,                       
`longitude` DOUBLE,                      
`ob_url` STRING,                       
`observation_time` STRING,                       
`observation_time_rfc822` STRING,                       
`pressure_in` DOUBLE,                      
`pressure_mb` DOUBLE,                      
`pressure_string` STRING,                       
`relative_humidity` INT,                      
`station_id` STRING,                       
`temp_c` DOUBLE,                      
`temp_f` DOUBLE,                      
`temperature_string` STRING,                       
`two_day_history_url` STRING,                       
`visibility_mi` DOUBLE,                      
`weather` STRING,                       
`wind_degrees` INT,                      
`wind_dir` STRING,                       
`wind_kt` INT,                      
`wind_mph` DOUBLE,                      
`wind_string` STRING,
`publishTime` TIMESTAMP(3) WITH LOCAL TIME ZONE NOT NULL,
`currentDate` DATE,
`currentTime` TIME,
`currentTimeStamp` TIMESTAMP(3)
) WITH (
  'connector' = 'pulsar',
  'key.format' = 'json',
  'value.format' = 'json',
  'service-url' = 'pulsar://pulsar1:6650',
  'admin-url' = 'http://pulsar1:8080',
  'scan.startup.mode' = 'earliest' 
);



CREATE TABLE airweather3 (
`dewpoint_c` DOUBLE,                      
`dewpoint_f` DOUBLE,                      
`dewpoint_string` STRING,                       
`heat_index_c` INT,                      
`heat_index_f` INT,                      
`heat_index_string` STRING,                       
`icon_url_base` STRING,                       
`icon_url_name` STRING,                       
`latitude` DOUBLE,                      
`location` STRING,                       
`longitude` DOUBLE,                      
`ob_url` STRING,                       
`observation_time` STRING,                       
`observation_time_rfc822` STRING,                       
`pressure_in` DOUBLE,                      
`pressure_mb` DOUBLE,                      
`pressure_string` STRING,                       
`relative_humidity` INT,                      
`station_id` STRING,                       
`temp_c` DOUBLE,                      
`temp_f` DOUBLE,                      
`temperature_string` STRING,                       
`two_day_history_url` STRING,                       
`visibility_mi` DOUBLE,                      
`weather` STRING,                       
`wind_degrees` INT,                      
`wind_dir` STRING,                       
`wind_kt` INT,                      
`wind_mph` DOUBLE,                      
`wind_string` STRING
) WITH (
  'connector' = 'pulsar',
  'key.format' = 'json',
  'value.format' = 'json',
  'service-url' = 'pulsar://pulsar1:6650',
  'admin-url' = 'http://pulsar1:8080',
  'scan.startup.mode' = 'earliest' 
);

````

#### in progress

````

select  temperature_string, weather, wind_string, 
       pressure_string, dewpoint_string, heat_index_string, 
       observation_time, 
       latitude, longitude, location, alt_baro,
       gs,
       alt_geom,
       baro_rate,
       mach, 
       hex, flight
FROM aircraft FULL JOIN aircraftweather ON aircraft.lat = aircraftweather.latitude
and aircraft.lon = aircraftweather.;



select  alt_baro,
       gs,
       alt_geom,
       baro_rate,
       mach, 
       hex, flight,temperature_string, weather, wind_string, 
       pressure_string, dewpoint_string, heat_index_string, 
       observation_time, 
       latitude, longitude, location
FROM aircraft  JOIN aircraftweather ON aircraft.lat = aircraftweather.latitude
and aircraft.lon = aircraftweather.longitude;


select  alt_baro,
       gs,
       alt_geom,
       baro_rate,
       mach, 
       hex, flight,temperature_string, weather, wind_string, 
       pressure_string, dewpoint_string, heat_index_string, 
       observation_time, 
       latitude, longitude, location
FROM aircraft  LEFT JOIN aircraftweather ON aircraft.lat = aircraftweather.latitude
and aircraft.lon = aircraftweather.longitude;


select COALESCE(location,station_id,'?') || ' ' || cast(lat as string) || ',' || cast(lon as string) as Location, 
       COALESCE(flight,'-','-') || ' ' || COALESCE(hex, '-','-') as FlightNum, 
       cast(alt_baro  as string) || ' ' ||  cast(alt_geom as string) as Altitude, 
       gs as Speed,
       temperature_string || weather as Weather, 
       mach, pressure_string, dewpoint_string, heat_index_string, wind_string, baro_rate
FROM aircraft  LEFT JOIN aircraftweather ON aircraft.lat = aircraftweather.latitude
and aircraft.lon = aircraftweather.longitude;

select alt_baro, hex, flight, lat, lon, station_id, location, latitude, longitude
FROM aircraft  FULL JOIN aircraftweather ON aircraft.lat = aircraftweather.latitude
and aircraft.lon = aircraftweather.longitude;

set table.dynamic-table-options.enabled = true;

select alt_baro, hex, flight, lat, lon
qfrom aircraft /*+ OPTIONS('scan.startup.mode' = 'earliest') */ 
where lat is not null

select COALESCE(location,station_id,'?') || ' ' || cast(lat as string) || ',' || cast(lon as string) as Location, 
       COALESCE(flight,'-','-') || ' ' || COALESCE(hex, '-','-') as FlightNum, 
       cast(alt_baro  as string) || ' ' ||  cast(alt_geom as string) as Altitude, 
       gs as Speed,
       temperature_string || weather as Weather, 
       mach, pressure_string, dewpoint_string, heat_index_string, wind_string, baro_rate
FROM aircraft /*+ OPTIONS('scan.startup.mode' = 'earliest') */   LEFT JOIN aircraftweather /*+ OPTIONS('scan.startup.mode' = 'earliest') */  ON aircraft.lat = aircraftweather.latitude
and aircraft.lon = aircraftweather.longitude;

select COALESCE(location,station_id,'?') || ' ' || cast(lat as string) || ',' || cast(lon as string) as Location, 
       COALESCE(flight,'-','-') || ' ' || COALESCE(hex, '-','-') as FlightNum, 
       cast(alt_baro  as string) || ' ' ||  cast(alt_geom as string) as Altitude, 
       gs as Speed,
       temperature_string || weather as Weather, 
       mach, pressure_string, dewpoint_string, heat_index_string, wind_string, baro_rate
FROM aircraft /*+ OPTIONS('scan.startup.mode' = 'earliest') */   LEFT JOIN aircraftweather /*+ OPTIONS('scan.startup.mode' = 'earliest') */  ON aircraft.lat = aircraftweather.latitude
and aircraft.lon = aircraftweather.longitude
WHERE weather is not null;


select alt_baro, hex, flight, lat, lon
from aircraft /*+ OPTIONS('scan.startup.mode' = 'earliest') */ 
where lat is not null;

select latitude, longitude, location, temperature_string, weather, wind_string, 
       pressure_string, dewpoint_string, heat_index_string, 
       observation_time
from aircraftweather /*+ OPTIONS('scan.startup.mode' = 'earliest') */ ;


--- order

CREATE CATALOG pulsar WITH (
   'type' = 'pulsar',
   'service-url' = 'pulsar://pulsar1:6650',
   'admin-url' = 'http://pulsar1:8080',
   'format' = 'json'
);

USE CATALOG pulsar;

set table.dynamic-table-options.enabled = true;


select latitude, longitude, location, temperature_string, weather, wind_string,
        pressure_string, dewpoint_string, heat_index_string,
        observation_time
 from aircraftweather /*+ OPTIONS('scan.startup.mode' = 'earliest') */ where location like '%New%Jersey%' or location like '%NJ%';

INSERT INTO weathernj 
SELECT *
FROM aircraftweather
WHERE `location` is not null and `location` <> 'null' and trim(`location`) <> '' and `location` like '%NJ'
WITH (
  'connector'='pulsar',
  'value.format'='json'
 );

select distinct location from aircraftweather /*+ OPTIONS('scan.startup.mode' = 'earliest') */;


select CURRENT_DATE, CURRENT_TIME, CURRENT_TIMESTAMP, location  from aircraftweather /*+ OPTIONS('scan.startup.mode' = 'earliest') */;

See

https://github.com/tspannhw/SmartWeather

https://nightlies.apache.org/flink/flink-docs-master/docs/dev/table/sql/queries/hints/

https://github.com/tspannhw/SmartWeather/blob/a901efa0d015cd23bb79784ee27556efefea1b1b/flink.sql

https://github.com/tspannhw/CloudDemo2021/blob/main/flinksql/weathernj.sql

https://github.com/tspannhw/ClouderaFlinkSQLForPartners

https://streamnative.io/blog/release/2022-08-30-announcing-the-flink-pulsar-sink-connector/

https://hub.streamnative.io/data-processing/pulsar-flink/1.15.0.1/

https://github.com/streamnative/flink/blob/develop/docs/content/docs/connectors/table/pulsar.md

https://github.com/streamnative/flink-example

https://github.com/streamnative/flink-example/blob/main/sql-examples/sql-example.md


source.start.message-id = earliest

  'source.start.message-id' = 'earliest' ,


set table.dynamic-table-options.enabled = true;

https://nightlies.apache.org/flink/flink-docs-release-1.13/docs/dev/table/config/

for pre 1.15
https://hub.streamnative.io/data-processing/pulsar-flink/1.13/

'scan.startup.mode' = 'earliest' 



````

### Approximate Lat/Long Matches

````

CREATE CATALOG pulsar WITH (
   'type' = 'pulsar',
   'service-url' = 'pulsar://pulsar1:6650',
   'admin-url' = 'http://pulsar1:8080',
   'format' = 'json'
);

USE CATALOG pulsar;

set table.dynamic-table-options.enabled = true;



select COALESCE(location,station_id,'?') || ' ' || cast(lat as string) || ',' || cast(lon as string) as Location, 
       COALESCE(flight,'-','-') || ' ' || COALESCE(hex, '-','-') as FlightNum, 
       cast(alt_baro  as string) || ' ' ||  cast(alt_geom as string) as Altitude, 
       gs as Speed,
       temperature_string || weather as Weather, 
       mach, pressure_string, dewpoint_string, heat_index_string, wind_string, baro_rate,
       NOW() as now, longitude, latitude
FROM aircraft /*+ OPTIONS('scan.startup.mode' = 'earliest') */,
aircraftweather /*+ OPTIONS('scan.startup.mode' = 'earliest') */ 
WHERE (aircraft.lat > aircraftweather.latitude - 0.3) 
and (aircraft.lat < aircraftweather.latitude + 0.3)
and (aircraft.lon < aircraftweather.longitude + 0.3) 
and (aircraft.lon > aircraftweather.longitude - 0.3);


===


select COALESCE(location,station_id,'?') || ' ' || cast(lat as string) || ',' || cast(lon as string) as PlaneLocation, 
       cast(latitude  as string)|| ',' || cast(longitude as string) as WeatherLocation,
       COALESCE(flight,'-','-') || ' * ' || COALESCE(hex, '-','-') as FlightNum, 
       cast(alt_baro  as string) || ' / ' ||  cast(alt_geom as string) as Altitude, 
       gs as Speed,
       temperature_string || weather as Weather, 
       mach, pressure_string, dewpoint_string, heat_index_string, wind_string, baro_rate,
       NOW() as now
FROM aircraft /*+ OPTIONS('scan.startup.mode' = 'earliest') */,
aircraftweather /*+ OPTIONS('scan.startup.mode' = 'earliest') */ 
WHERE (aircraft.lat > aircraftweather.latitude - 0.1) 
and (aircraft.lat < aircraftweather.latitude + 0.1)
and (aircraft.lon < aircraftweather.longitude + 0.1) 
and (aircraft.lon > aircraftweather.longitude - 0.1);



````

### References

* https://youtu.be/2PO15zb7irg
* https://github.com/tspannhw/ClouderaFlinkSQLForPartners
