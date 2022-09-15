bin/pulsar-admin functions stop --name Weather --namespace default --tenant public
bin/pulsar-admin functions delete --name Weather --namespace default --tenant public
bin/pulsar-admin functions create --auto-ack true --jar /opt/demo/java/pulsar-weather-function/target/weather-1.0.jar --classname "dev.pulsarfunction.weather.WeatherFunction" --dead-letter-topic "persistent://public/default/aircraftweatherdead" --inputs "persistent://public/default/weather" --log-topic "persistent://public/default/aircraftweatherlog" --name Weather --namespace default --tenant public --max-message-retries 5
