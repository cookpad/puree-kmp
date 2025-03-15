import Puree

class OSLogOutput: PureeOutput {
    func emit(log: String) {
        Puree.logger.debug("\(log)")
    }
}
