import Foundation
import PureeKMP

class PeriodicLog: PureeLog, Encodable {
    let sequence: Int
    
    init(sequence: Int) {
        self.sequence = sequence
    }
}

