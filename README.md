# puree-kmp

<div>
  <img src="https://img.shields.io/maven-central/v/com.cookpad.puree/puree-kmp">
  <img src="https://img.shields.io/badge/Swift_Package_Manager-compatible-orange" >
</div>


Puree-KMP is a structured logging library for Kotlin Multiplatform (KMP) applications, supporting both Android and iOS
platforms. It provides a flexible and extensible framework for capturing, processing, and routing logs to various
destinations.

![](./docs/logo.png)

## Features

- **Cross-Platform**: Works seamlessly on both Android and iOS
- **Filtering**: Log entries can be processed before being sent. You can add common parameters, do random sampling, ...
- **Buffering**: Log entries are stored in a buffer until it's time to send them.
- **Batching**: Multiple log entries are grouped and sent in one request.
- **Retrying**: Automatically retry to send after some backoff time if a transmission error occurred.

![](./docs/overview.png)

## Platform Support

| Platform | Support                       |
|----------|-------------------------------|
| Android  | :white_check_mark: Supported  |
| iOS      | :white_check_mark: Supported  | 
| JVM      | :black_square_button: Planing |
| Desktop  | :black_square_button: Planing |
| Web      | :x: Not Supported             |

## Installation

### Android

```kotlin
dependencies {
    implementation("com.cookpad.puree:puree-kmp:$latestVersion")
}
```

### iOS

Distributed through [Swift Package Manager](https://www.swift.org/documentation/package-manager/).
You can install directly from Xcode by specifying the URL for this repository.

## Usage

Note: If you want to know how to use the library in more detail, please take a look at the demo app. There are separate
versions for Android and iOS.

### 1. Define Log Classes

Create data classes that implement `PureeLog`:

```kotlin
@Serializable
data class ClickLog(
    @SerialName("button_name")
    val buttonName: String,
) : PureeLog
```

### 2. Define filter / output

#### Filter

Implement the `PureeFilter` interface to create custom filters:

```kotlin
class AddTimeFilter : PureeFilter {
    override fun applyFilter(log: JsonObject): JsonObject {
        buildJsonObject {
            put("time", System.currentTimeMillis())
            put("device_id", UUID.randomUUID().toString())
        }.also {
            return JsonObject(log + it)
        }
    }
}
```

#### Output

Implement the `PureeOutput` interface to output logs:

```kotlin
class LogcatOutput : PureeOutput {
    override fun emit(log: JsonObject) {
        Log.d("Puree", log.toString())
    }
}
```

Extend `PureeBufferedOutput` for batch processing:

```kotlin
class LogcatBufferedOutput(uniqueId: String) : PureeBufferedOutput(uniqueId) {
    init {
        flushInterval = 5.seconds
    }

    override fun emit(logs: List<JsonObject>, onSuccess: () -> Unit, onFailed: (Throwable) -> Unit) {
        // Process logs in batch
        Log.d("Puree", "Logs: $logs")
        onSuccess()
    }
}
```

### 3. Configure Puree

Initialize Puree with filters and outputs:

```kotlin
val logger = Puree(
    logStore = DefaultPureeLogStore("log.db"),
)
    .filter(AddTimeFilter(), ClickLog::class)
    .output(LogcatOutput(), ClickLog::class)
    .output(LogcatBufferedOutput("buffered"), ClickLog::class)
    .build()
```

### 4. Send Logs

```kotlin
logger.send(ClickLog(buttonName = "submit"))
```

### 5. Lifecycle Management

Puree automatically integrates with the application lifecycle (for Android only):

- Logs are buffered when the app is in the background
- Buffered logs are processed when the app returns to the foreground
- You can manually control this behavior with `logger.suspend()` and `logger.resume()`

## License

Please do read the [License](./LICENSE) before using and contributing.
