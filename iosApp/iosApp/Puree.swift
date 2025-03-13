import Puree

final class Puree {
    static let logger: PureeLogger = {
        let logStore = DefaultPureeLogStore(dbName: "puree.db")
        let logSerializer = DefaultPureeLogSerializer()
        
        return PureeLogger.Builder(
            logStore: logStore,
            logSerializer: DefaultPureeLogSerializer(),
            lifecycle: DefaultLifecycleOwner_iosKt.defaultLifecycleOwner.lifecycle
        )
        .build()
    }()
    
    static func send<T: PureeLog>(_ log: T) {
        logger.postLog(log: log)
    }
}
