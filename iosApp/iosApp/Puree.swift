import Foundation
import os
import Puree

final class Puree {
    static let logger = Logger(subsystem: "com.cookpad.puree", category: "default")

    private let pureeLogger: PureeLogger = {
        let logStore = DefaultPureeLogStore(dbName: "puree.db")

        return Puree_(logStore: logStore)
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
