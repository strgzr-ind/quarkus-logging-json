package io.quarkiverse.loggingjson.providers.gcp;

import java.io.IOException;

import org.jboss.logmanager.ExtLogRecord;

import io.quarkiverse.loggingjson.*;

public class GcpSpanIdJsonProvider implements JsonProvider, Enabled {
    private final String SPAN_ID_ATTRIBUTE = "logging.googleapis.com/spanId";
    private final Config.FieldConfig config;

    public GcpSpanIdJsonProvider(Config.FieldConfig config) {
        this.config = config;
    }

    @Override
    public void writeTo(JsonGenerator generator, ExtLogRecord event) throws IOException {

        String spanId = event.getMdc("x-b3-spanid");
        if (spanId != null) {
            JsonWritingUtils.writeStringField(generator, SPAN_ID_ATTRIBUTE, spanId);
        }
    }

    @Override
    public boolean isEnabled() {
        return config.enabled.orElse(true);
    }

}
