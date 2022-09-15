package dev.pulsarfunction.weather;

import org.apache.pulsar.common.functions.FunctionConfig;
import org.apache.pulsar.functions.LocalRunner;
import org.apache.pulsar.functions.api.BaseContext;
import org.apache.pulsar.functions.api.Context;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.mock;


/**
 * testing
 */
public class WeatherFunctionTest {

    protected Context ctx;

    protected void init(Context ctx) {
        this.ctx = ctx;
    }

    /**
     *
     * persistent://public/default/weather
     */
    public static String JSON_STRING = "{\"noNamespaceSchemaLocation\":null,\"version\":1.0,\"credit\":\"NOAA's National Weather Service\",\"credit_URL\":\"https://weather.gov/\",\"image\":{\"url\":\"https://weather.gov/images/xml_logo.gif\",\"title\":\"NOAA's National Weather Service\",\"link\":\"https://www.weather.gov\"},\"suggested_pickup\":\"15 minutes after the hour\",\"suggested_pickup_period\":60,\"location\":\"Todd Field Airport, MN\",\"station_id\":\"K14Y\",\"latitude\":45.90171,\"longitude\":-94.87275,\"observation_time\":\"Last Updated on Sep 12 2022, 5:15 pm CDT\",\"observation_time_rfc822\":\"Mon, 12 Sep 2022 17:15:00 -0500\",\"weather\":\"Fair\",\"temperature_string\":\"75.0 F (24.0 C)\",\"temp_f\":75.0,\"temp_c\":24.0,\"relative_humidity\":29,\"wind_string\":\"Northwest at 3.5 MPH (3 KT)\",\"wind_dir\":\"Northwest\",\"wind_degrees\":310,\"wind_mph\":3.5,\"wind_kt\":3,\"pressure_in\":29.92,\"dewpoint_string\":\"41.0 F (5.0 C)\",\"dewpoint_f\":41.0,\"dewpoint_c\":5.0,\"visibility_mi\":10.0,\"icon_url_base\":\"https://forecast.weather.gov/images/wtf/small/\",\"two_day_history_url\":\"https://www.weather.gov/data/obhistory/K14Y.html\",\"icon_url_name\":\"skc.png\",\"ob_url\":\"https://www.weather.gov/data/METAR/K14Y.1.txt\",\"disclaimer_url\":\"https://www.weather.gov/disclaimer.html\",\"copyright_url\":\"https://www.weather.gov/disclaimer.html\",\"privacy_policy_url\":\"https://www.weather.gov/notice.html\"}";

    /**
     *
     * @param msg
     */
    protected void log(String msg) {
        if (ctx != null && ctx.getLogger() != null) {
            ctx.getLogger().info(String.format("Function: [%s, id: %s, instanceId: %d of %d] %s",
                    ctx.getFunctionName(), ctx.getFunctionId(), ctx.getInstanceId(), ctx.getNumInstances(), msg));
        }
    }
    @Test
    public void testWeatherFunction() {
        WeatherFunction func = new WeatherFunction();
        try {
            func.process(JSON_STRING.getBytes(StandardCharsets.UTF_8), mock(Context.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args   string arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // multiple topics
        Collection<String> inputTopics = new ArrayList<String>();
        inputTopics.add("persistent://public/default/weather");

        FunctionConfig functionConfig = FunctionConfig.builder()
                .className(WeatherFunction.class.getName())
                .inputs(inputTopics)
                .name("WeatherFunction")
                .runtime(FunctionConfig.Runtime.JAVA)
                .cleanupSubscription(true)
                .tenant("public")
                .namespace("default")
                .subName("weather-sub-test")
                .maxMessageRetries(3)
                .autoAck(true)
                .build();

        LocalRunner localRunner = LocalRunner.builder()
                .brokerServiceUrl("pulsar://pulsar1:6650")
                .functionConfig(functionConfig)
                .build();

        localRunner.start(false);
        Thread.sleep(30000);
        localRunner.stop();
        System.exit(0);
    }
}