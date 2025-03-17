import Foundation
import Puree

class PeriodicLog: NSObject, PureeLog, Encodable {
    let sequence: Int
    
    init(sequence: Int) {
        self.sequence = sequence
    }
}

