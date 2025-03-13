import Puree

class OSLogOutput: PureeOutput {
    func emit(log: [String : Kotlinx_serialization_jsonJsonElement]) {
        print("OSLogOutput: \(log)")
    }
}
