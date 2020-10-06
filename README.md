[![Maven Central](https://img.shields.io/maven-central/v/io.quarkiverse.loggingjson/quarkiverse-logging-json?logo=apache-maven&style=for-the-badge)](https://search.maven.org/artifact/io.quarkiverse.loggingjson/quarkiverse-logging-json)
# Quarkiverse Logging Json
Quarkus logging extension outputting the logging in json.

# Configuration
The extension is enabled by default, when added to the project.
Can be disabled using configuration: `quarkus.log.console.json=false`

To see additional configuration options take a look at [Config](runtime/src/main/java/io/quarkiverse/loggingjson/JsonStructuredConfig.java)

# Structured argument
If you want to do structured logging of arguments, then the argument send with your logging, can implement `io.quarkus.logging.json.structured.StructuredArgument`. Then it is possible to use the JsonGenerator to format the argument in json. 

## Simple usage
```java
import static io.quarkus.logging.json.structured.KeyValueStructuredArgument.*;
...
log.info("Test log of structured arg", kv("key", "value"));
```
