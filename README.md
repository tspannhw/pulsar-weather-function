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


