import Puree

class OSLogOutput: PureeOutput {
    func emit(log: String) {
        Log.logger.debug("\(log)")
    }
}
