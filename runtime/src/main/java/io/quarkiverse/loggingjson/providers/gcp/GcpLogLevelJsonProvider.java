package io.quarkiverse.loggingjson.providers.gcp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jboss.logmanager.ExtLogRecord;
import org.jboss.logmanager.Level;

import io.quarkiverse.loggingjson.*;

public class GcpLogLevelJsonProvider implements JsonProvider, Enabled {
    /**
     * The JSON field name for the log level (severity).
     */
    private static final String SEVERITY_ATTRIBUTE = "severity";
    private static final Map<Level, String> logbackToSeverityMap;

    static {
        logbackToSeverityMap = new HashMap<>();
        logbackToSeverityMap.put(Level.TRACE, "DEBUG");
        logbackToSeverityMap.put(Level.DEBUG, "DEBUG");
        logbackToSeverityMap.put(Level.INFO, "INFO");
        logbackToSeverityMap.put(Level.WARN, "WARNING");
        logbackToSeverityMap.put(Level.ERROR, "ERROR");
    }

    private final Config.FieldConfig config;

    public GcpLogLevelJsonProvider(Config.FieldConfig config) {
        this.config = config;
    }

    @Override
    public boolean isEnabled() {
        return config.enabled.orElse(true);
    }

    @Override
    public void writeTo(JsonGenerator generator, ExtLogRecord event) throws IOException {
        JsonWritingUtils.writeStringField(generator, SEVERITY_ATTRIBUTE,
                logbackToSeverityMap.getOrDefault(event.getLevel(), "DEFAULT"));
    }
}