import Foundation
import Puree

class MenuLog: NSObject, PureeLog, Encodable {
    let menuName: String
    
    init(menuName: String) {
        self.menuName = menuName
    }
}
