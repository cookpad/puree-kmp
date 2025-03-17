import Foundation
import Puree

class ClickLog: NSObject, PureeLog, Encodable {
    let buttonName: String
    
    init(buttonName: String) {
        self.buttonName = buttonName
    }
}
