import Foundation
import Puree

class MenuLog: PureeLog, Encodable {
    let menuName: String
    
    init(menuName: String) {
        self.menuName = menuName
    }
}
