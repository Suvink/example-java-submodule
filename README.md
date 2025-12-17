# Java Library

A simple utility library for string operations with Apache Synapse integration support.

## Features

### StringUtils
Basic string utility methods:
- `toUpperCase(String input)` - Converts a string to uppercase
- `reverse(String input)` - Reverses a string

### StringReverser (Synapse Mediator)
Custom Apache Synapse mediator for reversing strings in integration flows.

**Usage in Synapse Configuration:**
```xml
<class name="com.example.library.StringReverser">
    <property name="input" value="${params.queryParams.string}"/>
</class>
```

The mediator will:
1. Accept an input string via the `input` property
2. Reverse the string using `StringBuilder`
3. Store the result in the message context as `reversedString`

**Example Integration:**
```xml
<resource methods="GET" uri-template="/hello">
    <inSequence>
        <class name="com.example.library.StringReverser">
            <property name="input" value="${params.queryParams.string}"/>
        </class>
        <payloadFactory media-type="json">
            <format>
                {
                    "original": "$1",
                    "reversed": "$2"
                }
            </format>
            <args>
                <arg evaluator="xml" expression="${params.queryParams.string}"/>
                <arg evaluator="xml" expression="$ctx:reversedString"/>
            </args>
        </payloadFactory>
        <respond/>
    </inSequence>
</resource>
```

## Build

```bash
mvn clean install
```

## Dependencies

- Apache Synapse Core 4.0.0-wso2v115 (provided scope)
- Java 11+

## Installation

After building, copy the generated JAR file from `target/java-library-1.0.0.jar` to your WSO2 integration runtime's `lib` directory.
