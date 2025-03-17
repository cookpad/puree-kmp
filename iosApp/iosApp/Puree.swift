import Foundation
import os
import Puree

final class Puree {
    static let logger = Logger(subsystem: "com.cookpad.puree", category: "default")
    
    private let pureeLogger: PureeLogger = {
        let logStore = DefaultPureeLogStore(dbName: "puree.db")
        let logSerializer = CustomPureeLogSerializer()
        
        return Puree_(
            logStore: logStore,
            logSerializer: logSerializer,
            lifecycle: DefaultLifecycleOwner_iosKt.defaultLifecycleOwner.lifecycle
        )
        .filter(filter: AddTimeFilter(), logTypes: [ClickLog.self, MenuLog.self, PeriodicLog.self])
        .output(output: OSLogOutput(), logTypes: [ClickLog.self, MenuLog.self, PeriodicLog.self])
        .output(output: OSLogBufferedOutput(uniqueId: "buffered"), logTypes: [ClickLog.self, MenuLog.self])
        .output(output: PurgeableOSLogWarningBufferedOutput(uniqueId: "purgeable"), logTypes: [PeriodicLog.self])
        .build()
    }()
    
    func send<T: PureeLog & Encodable>(_ log: T) {
        pureeLogger.postLog(log: log.encode(), clazz: T.self)
    }
}

class CustomPureeLogSerializer: PureeLogSerializer {
    func serialize(log: Any, platformClass: PlatformClass<AnyObject>) -> String {
        return (log as? String) ?? ""
    }
}

extension Encodable {
    func encode() -> String {
        let encoder = JSONEncoder()
        encoder.keyEncodingStrategy = .convertToSnakeCase
        guard let data = try? encoder.encode(self),
              let jsonString = String(data: data, encoding: .utf8) else {
            return ""
        }
        return jsonString
    }
}
