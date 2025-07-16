import PureeKMP
import OSLog          // ← Xcode 14 以降なら unified logging

final class OSLogBufferedOutput: PureeBufferedOutput {

    override init(uniqueId: String) {
        super.init(uniqueId: uniqueId)
        setFlushInterval(flushIntervalMillis: 3_000)   // 3 秒で自動 flush
        setLogsPerFlush(logsPerFlush: 5)
    }

    /// ⚠️ emit() を **3 秒ブロック**させて、次の requestFlush が割り込める余地を作る
    override func emit(
        logs: [String],
        onSuccess: @escaping () -> Void,
        onFailed: @escaping (KotlinThrowable) -> Void
    ) {
        let stamp = Date().timeIntervalSince1970
        let joined = logs.joined(separator: "|")

        Logger(subsystem: "demo", category: "Puree")
            .debug("🟡 emit START \(stamp, privacy: .public) logs=\(joined, privacy: .public)")

        // “重たいネット通信” を模倣
        DispatchQueue.global(qos: .background).asyncAfter(deadline: .now() + 3) {
            Logger(subsystem: "demo", category: "Puree")
                .debug("🟢 emit END   \(stamp, privacy: .public) logs=\(joined, privacy: .public)")
            onSuccess()         // ※ 成功にして DB 削除を発火させる
        }
    }
}
