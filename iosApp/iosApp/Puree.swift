import Foundation
import os
import PureeKMP

final class Log {
    static let logger = Logger(subsystem: "com.cookpad.puree", category: "default")

    private class DefaultPureeLogSerializer: PureeLogSerializer {
        func serialize(log: any PureeLog, platformClass: PlatformClass<any PureeLog>) -> String {
            return (log as? PureeLog & Encodable)?.encode() ?? ""
        }
    }

    private let pureeLogger: PureeLogger = {
        let logStore = PlatformDefaultPureeLogStore(dbName: "puree.db")
        let logSerializer = DefaultPureeLogSerializer()

        return Puree(logStore: logStore, logSerializer: logSerializer)
            .output(output: OSLogBufferedOutput(uniqueId: "buffered"), logTypes: [ClickLog.self, MenuLog.self])
            .defaultOutput(outputs: [OSLogOutput()].toKotlinArray())
            .defaultFilter(filters: [AddTimeFilter()].toKotlinArray())
            .build()
    }()

    func send<T: PureeLog & Encodable>(_ log: T) {
        pureeLogger.postLog(log: log, platformClass: PlatformClass(clazz: T.self))
    }

    func resume() {
        pureeLogger.resume()
    }

    func suspend() {
        pureeLogger.suspend()
    }

    func flush() {
        pureeLogger.flush()
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

extension Array {
     func toKotlinArray<Item: AnyObject>() -> KotlinArray<Item> {
        return KotlinArray(size: Int32(self.count)) { (i: KotlinInt) in
            guard let item = self[i.intValue] as? Item else {
                 fatalError("Element at index \(i) cannot be cast to \(Item.self)")
             }
             return item
        }
    }
}
