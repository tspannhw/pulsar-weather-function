package dev.pulsarfunction.weather;

import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.impl.schema.JSONSchema;
import org.apache.pulsar.functions.api.Context;
import org.apache.pulsar.functions.api.Function;

import java.util.List;
import java.util.UUID;

/**
 * Weather Function
 **/
public class WeatherFunction implements Function<byte[], Void> {

    public static final String JAVA = "Java";
    public static final String LANGUAGE = "language";
    public static final String MESSAGE_JSON = "Receive message JSON:";
    public static final String ERROR = "ERROR:";
    public static final String PERSISTENT_PUBLIC_DEFAULT = "persistent://public/default/aircraftweather";
    public static final String STATION_ID = "STATION_ID";

    /**
     * PROCESS
     */
    @Override
    public Void process(byte[] input, Context context) {
        if (input == null || context == null) {
            return null;
        }

        WeatherParserService service = new WeatherParserService();
        Weather weather = service.deserialize(input);

        if (weather != null ) {
            if (context.getLogger() != null && context.getLogger().isDebugEnabled()) {
                context.getLogger().debug(MESSAGE_JSON + weather.toString());
            }
            try {
                context.newOutputMessage(PERSISTENT_PUBLIC_DEFAULT, JSONSchema.of(Weather.class))
                        .key(UUID.randomUUID().toString())
                        .property(LANGUAGE, JAVA)
                        .value(weather)
                        .send();
            } catch (PulsarClientException ex) {
                if (context.getLogger() != null) {
                    context.getLogger().error(ERROR + ex.getLocalizedMessage());
                }
            }
        }
        return null;
    }
}