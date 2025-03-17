import Puree

class OSLogBufferedOutput: PureeBufferedOutput {
    open override var flushInterval: Int64 {
        return 5000
    }
    
    override func emit(logs: [String], onSuccess: @escaping () -> Void, onFailed: @escaping (KotlinThrowable) -> Void) {
        Puree.logger.debug("Logs: \(logs)")
        onSuccess()
    }
}
