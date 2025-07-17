import PureeKMP
import OSLog

final class OSLogBufferedOutput: PureeBufferedOutput {

    override init(uniqueId: String) {
        super.init(uniqueId: uniqueId)
        setFlushInterval(flushIntervalMillis: 3_000)
        setLogsPerFlush(logsPerFlush: 5)
    }

    override func emit(
        logs: [String],
        onSuccess: @escaping () -> Void,
        onFailed: @escaping (KotlinThrowable) -> Void
    ) {
        let stamp = Date().timeIntervalSince1970
        let joined = logs.joined(separator: "|")

        Logger(subsystem: "demo", category: "Puree")
            .debug("🟡 emit START \(stamp, privacy: .public) logs=\(joined, privacy: .public)")

        // 重たい通信
        DispatchQueue.global(qos: .background).asyncAfter(deadline: .now() + 3) {
            Logger(subsystem: "demo", category: "Puree")
                .debug("🟢 emit END   \(stamp, privacy: .public) logs=\(joined, privacy: .public)")
            onSuccess()
        }
    }
}
