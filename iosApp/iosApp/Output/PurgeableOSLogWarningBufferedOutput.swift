import Puree

class PurgeableOSLogWarningBufferedOutput: PureeBufferedOutput {
    open override var flushInterval: Int64 {
        return 10 * 1000
    }
    
    open override var purgeableAge: Any? {
        return 5 * 1000
    }
    
    override func emit(logs: [String], onSuccess: @escaping () -> Void, onFailed: @escaping (KotlinThrowable) -> Void) {
        Puree.logger.warning("Logs: \(logs)")
        onSuccess()
    }
}
