import Foundation
import PureeKMP

class ClickLog: PureeLog, Encodable {
    let buttonName: String
    
    init(buttonName: String) {
        self.buttonName = buttonName
    }
}
