import PureeKMP

class OSLogOutput: PureeOutput {
    func emit(log: String) {
        Log.logger.debug("\(log)")
    }
}
