import Foundation
import os
import Puree

final class Log {
    static let logger = Logger(subsystem: "com.cookpad.puree", category: "default")

    private class DefaultPureeLogSerializer: PureeLogSerializer {
        func serialize(log: any PureeLog, platformClass: PlatformClass<any PureeLog>) -> String {
            return (log as? PureeLog & Encodable)?.encode() ?? ""
        }
    }

    private let pureeLogger: PureeLogger = {
        let logStore = DefaultPureeLogStore(dbName: "puree.db")
        let logSerializer = DefaultPureeLogSerializer()

        return Puree(logStore: logStore, logSerializer: logSerializer)
            .filter(filter: AddTimeFilter(), logTypes: [ClickLog.self, MenuLog.self, PeriodicLog.self])
            .output(output: OSLogOutput(), logTypes: [ClickLog.self, MenuLog.self, PeriodicLog.self])
            .output(output: OSLogBufferedOutput(uniqueId: "buffered"), logTypes: [ClickLog.self, MenuLog.self])
            .output(output: PurgeableOSLogWarningBufferedOutput(uniqueId: "purgeable"), logTypes: [PeriodicLog.self])
            .build()
    }()

    func send<T: PureeLog & Encodable>(_ log: T) {
        pureeLogger.postLog(log: log, platformClass: PlatformClass(clazz: T.self))
    }
}

extension PureeLog where Self: Encodable {
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
