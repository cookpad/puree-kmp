import Foundation
import PureeKMP

class MenuLog: PureeLog, Encodable {
    let menuName: String
    
    init(menuName: String) {
        self.menuName = menuName
    }
}
