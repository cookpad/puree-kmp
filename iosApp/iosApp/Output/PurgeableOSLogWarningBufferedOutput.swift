import PureeKMP

class PurgeableOSLogWarningBufferedOutput: PureeBufferedOutput {

    override init (uniqueId: String) {
        super.init(uniqueId: uniqueId)
        setFlushInterval(flushIntervalMillis: 10000)
        setPurgeableAge(purgeableAgeMillis: 5000)
    }

    override func emit(logs: [String], onSuccess: @escaping () -> Void, onFailed: @escaping (KotlinThrowable) -> Void) {
        Log.logger.warning("Logs: \(logs)")
        onSuccess()
    }
}
