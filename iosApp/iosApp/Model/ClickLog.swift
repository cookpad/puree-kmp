import Foundation
import Puree

class ClickLog: NSObject, PureeLog {
    let buttonName: String
    
    init(buttonName: String) {
        self.buttonName = buttonName
    }
}
