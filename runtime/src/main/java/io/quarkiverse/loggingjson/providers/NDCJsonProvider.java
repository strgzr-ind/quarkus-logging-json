package io.quarkiverse.loggingjson.providers;

import java.io.IOException;

import org.jboss.logmanager.ExtLogRecord;

import io.quarkiverse.loggingjson.JsonGenerator;
import io.quarkiverse.loggingjson.JsonProvider;
import io.quarkiverse.loggingjson.JsonWritingUtils;

public class NDCJsonProvider implements JsonProvider {

    public static final String FIELD_NDC = "ndc";

    @Override
    public void writeTo(JsonGenerator generator, ExtLogRecord event) throws IOException {
        if (event.getNdc() != null && !"".equals(event.getNdc())) {
            JsonWritingUtils.writeStringField(generator, FIELD_NDC, event.getNdc());
        }
    }
}