import PureeKMP

class OSLogBufferedOutput: PureeBufferedOutput {
    override init (uniqueId: String) {
        super.init(uniqueId: uniqueId)
        setFlushInterval(flushIntervalMillis: 5000)
    }

    override func emit(logs: [String], onSuccess: @escaping () -> Void, onFailed: @escaping (KotlinThrowable) -> Void) {
        Log.logger.debug("Logs: \(logs)")
        onSuccess()
    }
}
