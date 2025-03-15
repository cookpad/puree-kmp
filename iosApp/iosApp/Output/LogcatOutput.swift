import Puree

class OSLogOutput: PureeOutput {
    func emit(log: String) {
        print("OSLogOutput: \(log)")
    }
}
