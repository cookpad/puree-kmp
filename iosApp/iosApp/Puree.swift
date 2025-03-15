import Puree

final class Puree {
    static let logger: PureeLogger = {
        let logStore = DefaultPureeLogStore(dbName: "puree.db")
        let logSerializer = DefaultPureeLogSerializer()
        
        return Puree_(
            logStore: logStore,
            logSerializer: logSerializer,
            lifecycle: DefaultLifecycleOwner_iosKt.defaultLifecycleOwner.lifecycle
        )
        .output(output: OSLogOutput(), logType: ClickLog.self)
        .build()
    }()
    
    static func send<T: PureeLog>(_ log: T) {
        logger.postLog(log: log, clazz: PlatformClass(clazz: T.self))
    }
}
