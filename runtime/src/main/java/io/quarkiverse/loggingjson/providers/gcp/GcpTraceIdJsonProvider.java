package io.quarkiverse.loggingjson.providers.gcp;

import java.io.IOException;

import org.jboss.logmanager.ExtLogRecord;

import io.quarkiverse.loggingjson.*;

public class GcpTraceIdJsonProvider implements JsonProvider, Enabled {
    private final String TRACE_ID_ATTRIBUTE = "logging.googleapis.com/trace";
    private final Config.GcpTraceIdConfig config;
    private final String projectId;

    public GcpTraceIdJsonProvider(Config.GcpTraceIdConfig config) {
        this.config = config;
        this.projectId = config.projectId.orElse("default");
    }

    protected String formatTraceId(final String traceId) {
        // Trace IDs are either 64-bit or 128-bit, which is 16-digit hex, or 32-digit hex.
        // If traceId is 64-bit (16-digit hex), then we need to prepend 0's to make a 32-digit hex.
        if (traceId != null && traceId.length() == 16) {
            return "0000000000000000" + traceId;
        }
        return traceId;
    }

    String composeFullTraceName(String traceId) {
        return "projects/" + projectId + "/traces/" + traceId;
    }

    @Override
    public void writeTo(JsonGenerator generator, ExtLogRecord event) throws IOException {
        String traceId = event.getMdc("x-b3-traceid");
        if (traceId != null) {
            JsonWritingUtils.writeStringField(generator, TRACE_ID_ATTRIBUTE, composeFullTraceName(formatTraceId(traceId)));
        }
    }

    @Override
    public boolean isEnabled() {
        return config.enabled.orElse(true);
    }

}
