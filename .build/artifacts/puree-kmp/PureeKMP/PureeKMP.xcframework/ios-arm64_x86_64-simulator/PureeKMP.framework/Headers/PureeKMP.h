#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class NSArray, PKMPKotlinArray<T>, PKMPKotlinEnum<E>, PKMPKotlinEnumCompanion, PKMPKotlinException, PKMPKotlinIllegalStateException, PKMPKotlinNothing, PKMPKotlinRuntimeException, PKMPKotlinThrowable, PKMPKotlinx_datetimeInstant, PKMPKotlinx_datetimeInstantCompanion, PKMPKotlinx_serialization_coreSerialKind, PKMPKotlinx_serialization_coreSerializersModule, PKMPLifecycle_commonAtomicReference<V>, PKMPLifecycle_commonLifecycle, PKMPLifecycle_commonLifecycleState, PKMPPlatformClass<T>, PKMPPuree, PKMPPureeBufferedLog, PKMPPureeBufferedOutput, PKMPPureeLogger;

@protocol PKMPKotlinAnnotation, PKMPKotlinAppendable, PKMPKotlinComparable, PKMPKotlinIterator, PKMPKotlinKAnnotatedElement, PKMPKotlinKClass, PKMPKotlinKClassifier, PKMPKotlinKDeclarationContainer, PKMPKotlinx_coroutines_coreFlow, PKMPKotlinx_coroutines_coreFlowCollector, PKMPKotlinx_coroutines_coreSharedFlow, PKMPKotlinx_coroutines_coreStateFlow, PKMPKotlinx_datetimeDateTimeFormat, PKMPKotlinx_serialization_coreCompositeDecoder, PKMPKotlinx_serialization_coreCompositeEncoder, PKMPKotlinx_serialization_coreDecoder, PKMPKotlinx_serialization_coreDeserializationStrategy, PKMPKotlinx_serialization_coreEncoder, PKMPKotlinx_serialization_coreKSerializer, PKMPKotlinx_serialization_coreSerialDescriptor, PKMPKotlinx_serialization_coreSerializationStrategy, PKMPKotlinx_serialization_coreSerializersModuleCollector, PKMPLifecycle_commonLifecycleObserver, PKMPLifecycle_commonLifecycleOwner, PKMPPureeFilter, PKMPPureeLog, PKMPPureeLogSerializer, PKMPPureeLogStore, PKMPPureeOutput;

NS_ASSUME_NONNULL_BEGIN
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunknown-warning-option"
#pragma clang diagnostic ignored "-Wincompatible-property-type"
#pragma clang diagnostic ignored "-Wnullability"

#pragma push_macro("_Nullable_result")
#if !__has_feature(nullability_nullable_result)
#undef _Nullable_result
#define _Nullable_result _Nullable
#endif

__attribute__((swift_name("KotlinBase")))
@interface PKMPBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end

@interface PKMPBase (PKMPBaseCopying) <NSCopying>
@end

__attribute__((swift_name("KotlinMutableSet")))
@interface PKMPMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end

__attribute__((swift_name("KotlinMutableDictionary")))
@interface PKMPMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end

@interface NSError (NSErrorPKMPKotlinException)
@property (readonly) id _Nullable kotlinException;
@end

__attribute__((swift_name("KotlinNumber")))
@interface PKMPNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end

__attribute__((swift_name("KotlinByte")))
@interface PKMPByte : PKMPNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end

__attribute__((swift_name("KotlinUByte")))
@interface PKMPUByte : PKMPNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end

__attribute__((swift_name("KotlinShort")))
@interface PKMPShort : PKMPNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end

__attribute__((swift_name("KotlinUShort")))
@interface PKMPUShort : PKMPNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end

__attribute__((swift_name("KotlinInt")))
@interface PKMPInt : PKMPNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end

__attribute__((swift_name("KotlinUInt")))
@interface PKMPUInt : PKMPNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end

__attribute__((swift_name("KotlinLong")))
@interface PKMPLong : PKMPNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end

__attribute__((swift_name("KotlinULong")))
@interface PKMPULong : PKMPNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end

__attribute__((swift_name("KotlinFloat")))
@interface PKMPFloat : PKMPNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end

__attribute__((swift_name("KotlinDouble")))
@interface PKMPDouble : PKMPNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end

__attribute__((swift_name("KotlinBoolean")))
@interface PKMPBoolean : PKMPNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end

__attribute__((swift_name("Lifecycle_commonLifecycleOwner")))
@protocol PKMPLifecycle_commonLifecycleOwner
@required
@property (readonly) PKMPLifecycle_commonLifecycle *lifecycle __attribute__((swift_name("lifecycle")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DefaultLifecycleOwner")))
@interface PKMPDefaultLifecycleOwner : PKMPBase <PKMPLifecycle_commonLifecycleOwner>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (readonly) PKMPLifecycle_commonLifecycle *lifecycle __attribute__((swift_name("lifecycle")));
@end

__attribute__((swift_name("KotlinThrowable")))
@interface PKMPKotlinThrowable : PKMPBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(PKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(PKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));

/**
 * @note annotations
 *   kotlin.experimental.ExperimentalNativeApi
*/
- (PKMPKotlinArray<NSString *> *)getStackTrace __attribute__((swift_name("getStackTrace()")));
- (void)printStackTrace __attribute__((swift_name("printStackTrace()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) PKMPKotlinThrowable * _Nullable cause __attribute__((swift_name("cause")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
- (NSError *)asError __attribute__((swift_name("asError()")));
@end

__attribute__((swift_name("KotlinException")))
@interface PKMPKotlinException : PKMPKotlinThrowable
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(PKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(PKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LogNotRegisteredException")))
@interface PKMPLogNotRegisteredException : PKMPKotlinException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithCause:(PKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(PKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Puree")))
@interface PKMPPuree : PKMPBase
- (instancetype)initWithLogStore:(id<PKMPPureeLogStore>)logStore logSerializer:(id<PKMPPureeLogSerializer>)logSerializer __attribute__((swift_name("init(logStore:logSerializer:)"))) __attribute__((objc_designated_initializer));
- (PKMPPureeLogger *)build __attribute__((swift_name("build()")));
- (PKMPPuree *)defaultFilterFilters:(PKMPKotlinArray<id<PKMPPureeFilter>> *)filters __attribute__((swift_name("defaultFilter(filters:)")));
- (PKMPPuree *)defaultOutputOutputs:(PKMPKotlinArray<id<PKMPPureeOutput>> *)outputs __attribute__((swift_name("defaultOutput(outputs:)")));
- (PKMPPuree *)filterFilter:(id<PKMPPureeFilter>)filter logTypes:(NSArray *)logTypes __attribute__((swift_name("filter(filter:logTypes:)")));
- (PKMPPuree *)outputOutput:(id<PKMPPureeOutput>)output logTypes:(NSArray *)logTypes __attribute__((swift_name("output(output:logTypes:)")));
@end

__attribute__((swift_name("PureeFilter")))
@protocol PKMPPureeFilter
@required
- (NSString * _Nullable)applyFilterLog:(NSString *)log __attribute__((swift_name("applyFilter(log:)")));
@end

__attribute__((swift_name("PureeLog")))
@protocol PKMPPureeLog
@required
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PureeLogger")))
@interface PKMPPureeLogger : PKMPBase
- (void)flush __attribute__((swift_name("flush()")));
- (void)postLogLog:(id<PKMPPureeLog>)log platformClass:(PKMPPlatformClass<id<PKMPPureeLog>> *)platformClass __attribute__((swift_name("postLog(log:platformClass:)")));
- (void)resume __attribute__((swift_name("resume()")));
- (void)suspend __attribute__((swift_name("suspend()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkippedLogException")))
@interface PKMPSkippedLogException : PKMPKotlinException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithCause:(PKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(PKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PureeBufferedLog")))
@interface PKMPPureeBufferedLog : PKMPBase
- (instancetype)initWithId:(int64_t)id createdAt:(PKMPKotlinx_datetimeInstant *)createdAt log:(NSString *)log __attribute__((swift_name("init(id:createdAt:log:)"))) __attribute__((objc_designated_initializer));
- (PKMPPureeBufferedLog *)doCopyId:(int64_t)id createdAt:(PKMPKotlinx_datetimeInstant *)createdAt log:(NSString *)log __attribute__((swift_name("doCopy(id:createdAt:log:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) PKMPKotlinx_datetimeInstant *createdAt __attribute__((swift_name("createdAt")));
@property (readonly) int64_t id __attribute__((swift_name("id")));
@property (readonly) NSString *log __attribute__((swift_name("log")));
@end

__attribute__((swift_name("PureeOutput")))
@protocol PKMPPureeOutput
@required
- (void)emitLog:(NSString *)log __attribute__((swift_name("emit(log:)")));
@end

__attribute__((swift_name("PureeBufferedOutput")))
@interface PKMPPureeBufferedOutput : PKMPBase <PKMPPureeOutput>
- (instancetype)initWithUniqueId:(NSString *)uniqueId __attribute__((swift_name("init(uniqueId:)"))) __attribute__((objc_designated_initializer));
- (void)emitLog:(NSString *)log __attribute__((swift_name("emit(log:)")));
- (void)emitLogs:(NSArray<NSString *> *)logs onSuccess:(void (^)(void))onSuccess onFailed:(void (^)(PKMPKotlinThrowable *))onFailed __attribute__((swift_name("emit(logs:onSuccess:onFailed:)")));
@property int64_t exponentialBackoffBase __attribute__((swift_name("exponentialBackoffBase")));
@property int64_t flushInterval __attribute__((swift_name("flushInterval")));
@property int32_t logsPerFlush __attribute__((swift_name("logsPerFlush")));
@property int64_t maxFlushSizeInBytes __attribute__((swift_name("maxFlushSizeInBytes")));
@property int32_t maxRetryCount __attribute__((swift_name("maxRetryCount")));
@property id _Nullable purgeableAge __attribute__((swift_name("purgeableAge")));
@end

__attribute__((swift_name("PureeLogSerializer")))
@protocol PKMPPureeLogSerializer
@required
- (NSString *)serializeLog:(id<PKMPPureeLog>)log platformClass:(PKMPPlatformClass<id<PKMPPureeLog>> *)platformClass __attribute__((swift_name("serialize(log:platformClass:)")));
@end

__attribute__((swift_name("PureeLogStore")))
@protocol PKMPPureeLogStore
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)addOutputId:(NSString *)outputId bufferedLog:(PKMPPureeBufferedLog *)bufferedLog completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("add(outputId:bufferedLog:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getOutputId:(NSString *)outputId maxCount:(int32_t)maxCount completionHandler:(void (^)(NSArray<PKMPPureeBufferedLog *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("get(outputId:maxCount:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)purgeLogsWithAgeOutputId:(NSString *)outputId now:(PKMPKotlinx_datetimeInstant *)now age:(int64_t)age completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("purgeLogsWithAge(outputId:now:age:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)removeOutputId:(NSString *)outputId bufferedLogs:(NSArray<PKMPPureeBufferedLog *> *)bufferedLogs completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("remove(outputId:bufferedLogs:completionHandler:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PlatformDefaultPureeLogStore")))
@interface PKMPPlatformDefaultPureeLogStore : PKMPBase <PKMPPureeLogStore>
- (instancetype)initWithDbName:(NSString *)dbName __attribute__((swift_name("init(dbName:)"))) __attribute__((objc_designated_initializer));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)addOutputId:(NSString *)outputId bufferedLog:(PKMPPureeBufferedLog *)bufferedLog completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("add(outputId:bufferedLog:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getOutputId:(NSString *)outputId maxCount:(int32_t)maxCount completionHandler:(void (^)(NSArray<PKMPPureeBufferedLog *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("get(outputId:maxCount:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)purgeLogsWithAgeOutputId:(NSString *)outputId now:(PKMPKotlinx_datetimeInstant *)now age:(int64_t)age completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("purgeLogsWithAge(outputId:now:age:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)removeOutputId:(NSString *)outputId bufferedLogs:(NSArray<PKMPPureeBufferedLog *> *)bufferedLogs completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("remove(outputId:bufferedLogs:completionHandler:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PlatformClass")))
@interface PKMPPlatformClass<T> : PKMPBase
- (instancetype)initWithClazz:(Class)clazz __attribute__((swift_name("init(clazz:)"))) __attribute__((objc_designated_initializer));
@property (readonly) NSString *simpleName __attribute__((swift_name("simpleName")));
@end

@interface PKMPPureeLogger (Extensions)
- (void)sendLog:(id<PKMPPureeLog>)log clazz:(Class)clazz __attribute__((swift_name("send(log:clazz:)")));
@end

@interface PKMPPureeBufferedOutput (Extensions)
- (void)setExponentialBackoffBaseExponentialBackoffBaseMillis:(uint64_t)exponentialBackoffBaseMillis __attribute__((swift_name("setExponentialBackoffBase(exponentialBackoffBaseMillis:)")));
- (void)setFlushIntervalFlushIntervalMillis:(int64_t)flushIntervalMillis __attribute__((swift_name("setFlushInterval(flushIntervalMillis:)")));
- (void)setLogsPerFlushLogsPerFlush:(uint32_t)logsPerFlush __attribute__((swift_name("setLogsPerFlush(logsPerFlush:)")));
- (void)setMaxRetryCountMaxRetryCount:(uint32_t)maxRetryCount __attribute__((swift_name("setMaxRetryCount(maxRetryCount:)")));
- (void)setPurgeableAgePurgeableAgeMillis:(uint64_t)purgeableAgeMillis __attribute__((swift_name("setPurgeableAge(purgeableAgeMillis:)")));
@end

__attribute__((swift_name("Lifecycle_commonLifecycle")))
@interface PKMPLifecycle_commonLifecycle : PKMPBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));

/**
 * @note annotations
 *   androidx.annotation.MainThread
*/
- (void)addObserverObserver:(id<PKMPLifecycle_commonLifecycleObserver>)observer __attribute__((swift_name("addObserver(observer:)")));

/**
 * @note annotations
 *   androidx.annotation.MainThread
*/
- (void)removeObserverObserver:(id<PKMPLifecycle_commonLifecycleObserver>)observer __attribute__((swift_name("removeObserver(observer:)")));
@property (readonly) PKMPLifecycle_commonLifecycleState *currentState __attribute__((swift_name("currentState")));
@property (readonly) id<PKMPKotlinx_coroutines_coreStateFlow> currentStateFlow __attribute__((swift_name("currentStateFlow")));
@property PKMPLifecycle_commonAtomicReference<id> *internalScopeRef __attribute__((swift_name("internalScopeRef")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface PKMPKotlinArray<T> : PKMPBase
+ (instancetype)arrayWithSize:(int32_t)size init:(T _Nullable (^)(PKMPInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (T _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<PKMPKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(T _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end

__attribute__((swift_name("KotlinComparable")))
@protocol PKMPKotlinComparable
@required
- (int32_t)compareToOther:(id _Nullable)other __attribute__((swift_name("compareTo(other:)")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable(with=NormalClass(value=kotlinx/datetime/serializers/InstantIso8601Serializer))
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_datetimeInstant")))
@interface PKMPKotlinx_datetimeInstant : PKMPBase <PKMPKotlinComparable>
@property (class, readonly, getter=companion) PKMPKotlinx_datetimeInstantCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)compareToOther:(PKMPKotlinx_datetimeInstant *)other __attribute__((swift_name("compareTo(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (PKMPKotlinx_datetimeInstant *)minusDuration:(int64_t)duration __attribute__((swift_name("minus(duration:)")));
- (int64_t)minusOther:(PKMPKotlinx_datetimeInstant *)other __attribute__((swift_name("minus(other:)")));
- (PKMPKotlinx_datetimeInstant *)plusDuration:(int64_t)duration __attribute__((swift_name("plus(duration:)")));
- (int64_t)toEpochMilliseconds __attribute__((swift_name("toEpochMilliseconds()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int64_t epochSeconds __attribute__((swift_name("epochSeconds")));
@property (readonly) int32_t nanosecondsOfSecond __attribute__((swift_name("nanosecondsOfSecond")));
@end

__attribute__((swift_name("KotlinRuntimeException")))
@interface PKMPKotlinRuntimeException : PKMPKotlinException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(PKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(PKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("KotlinIllegalStateException")))
@interface PKMPKotlinIllegalStateException : PKMPKotlinRuntimeException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(PKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(PKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.4")
*/
__attribute__((swift_name("KotlinCancellationException")))
@interface PKMPKotlinCancellationException : PKMPKotlinIllegalStateException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(PKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(PKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("Lifecycle_commonLifecycleObserver")))
@protocol PKMPLifecycle_commonLifecycleObserver
@required
@end

__attribute__((swift_name("KotlinEnum")))
@interface PKMPKotlinEnum<E> : PKMPBase <PKMPKotlinComparable>
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) PKMPKotlinEnumCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)compareToOther:(E)other __attribute__((swift_name("compareTo(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) int32_t ordinal __attribute__((swift_name("ordinal")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Lifecycle_commonLifecycle.State")))
@interface PKMPLifecycle_commonLifecycleState : PKMPKotlinEnum<PKMPLifecycle_commonLifecycleState *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) PKMPLifecycle_commonLifecycleState *destroyed __attribute__((swift_name("destroyed")));
@property (class, readonly) PKMPLifecycle_commonLifecycleState *initialized __attribute__((swift_name("initialized")));
@property (class, readonly) PKMPLifecycle_commonLifecycleState *created __attribute__((swift_name("created")));
@property (class, readonly) PKMPLifecycle_commonLifecycleState *started __attribute__((swift_name("started")));
@property (class, readonly) PKMPLifecycle_commonLifecycleState *resumed __attribute__((swift_name("resumed")));
+ (PKMPKotlinArray<PKMPLifecycle_commonLifecycleState *> *)values __attribute__((swift_name("values()")));
- (BOOL)isAtLeastState:(PKMPLifecycle_commonLifecycleState *)state __attribute__((swift_name("isAtLeast(state:)")));
@end

__attribute__((swift_name("Kotlinx_coroutines_coreFlow")))
@protocol PKMPKotlinx_coroutines_coreFlow
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)collectCollector:(id<PKMPKotlinx_coroutines_coreFlowCollector>)collector completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("collect(collector:completionHandler:)")));
@end

__attribute__((swift_name("Kotlinx_coroutines_coreSharedFlow")))
@protocol PKMPKotlinx_coroutines_coreSharedFlow <PKMPKotlinx_coroutines_coreFlow>
@required
@property (readonly) NSArray<id> *replayCache __attribute__((swift_name("replayCache")));
@end

__attribute__((swift_name("Kotlinx_coroutines_coreStateFlow")))
@protocol PKMPKotlinx_coroutines_coreStateFlow <PKMPKotlinx_coroutines_coreSharedFlow>
@required
@property (readonly) id _Nullable value __attribute__((swift_name("value")));
@end


/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP])
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Lifecycle_commonAtomicReference")))
@interface PKMPLifecycle_commonAtomicReference<V> : PKMPBase
- (instancetype)initWithValue:(V _Nullable)value __attribute__((swift_name("init(value:)"))) __attribute__((objc_designated_initializer));
- (BOOL)compareAndSetExpect:(V _Nullable)expect newValue:(V _Nullable)newValue __attribute__((swift_name("compareAndSet(expect:newValue:)")));
- (V _Nullable)get __attribute__((swift_name("get()")));
@end

__attribute__((swift_name("KotlinIterator")))
@protocol PKMPKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_datetimeInstant.Companion")))
@interface PKMPKotlinx_datetimeInstantCompanion : PKMPBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) PKMPKotlinx_datetimeInstantCompanion *shared __attribute__((swift_name("shared")));
- (PKMPKotlinx_datetimeInstant *)fromEpochMillisecondsEpochMilliseconds:(int64_t)epochMilliseconds __attribute__((swift_name("fromEpochMilliseconds(epochMilliseconds:)")));
- (PKMPKotlinx_datetimeInstant *)fromEpochSecondsEpochSeconds:(int64_t)epochSeconds nanosecondAdjustment:(int32_t)nanosecondAdjustment __attribute__((swift_name("fromEpochSeconds(epochSeconds:nanosecondAdjustment:)")));
- (PKMPKotlinx_datetimeInstant *)fromEpochSecondsEpochSeconds:(int64_t)epochSeconds nanosecondAdjustment_:(int64_t)nanosecondAdjustment __attribute__((swift_name("fromEpochSeconds(epochSeconds:nanosecondAdjustment_:)")));
- (PKMPKotlinx_datetimeInstant *)now __attribute__((swift_name("now()"))) __attribute__((unavailable("Use Clock.System.now() instead")));
- (PKMPKotlinx_datetimeInstant *)parseInput:(id)input format:(id<PKMPKotlinx_datetimeDateTimeFormat>)format __attribute__((swift_name("parse(input:format:)")));
- (id<PKMPKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@property (readonly) PKMPKotlinx_datetimeInstant *DISTANT_FUTURE __attribute__((swift_name("DISTANT_FUTURE")));
@property (readonly) PKMPKotlinx_datetimeInstant *DISTANT_PAST __attribute__((swift_name("DISTANT_PAST")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinEnumCompanion")))
@interface PKMPKotlinEnumCompanion : PKMPBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) PKMPKotlinEnumCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((swift_name("Kotlinx_coroutines_coreFlowCollector")))
@protocol PKMPKotlinx_coroutines_coreFlowCollector
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)emitValue:(id _Nullable)value completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("emit(value:completionHandler:)")));
@end

__attribute__((swift_name("Kotlinx_datetimeDateTimeFormat")))
@protocol PKMPKotlinx_datetimeDateTimeFormat
@required
- (NSString *)formatValue:(id _Nullable)value __attribute__((swift_name("format(value:)")));
- (id<PKMPKotlinAppendable>)formatToAppendable:(id<PKMPKotlinAppendable>)appendable value:(id _Nullable)value __attribute__((swift_name("formatTo(appendable:value:)")));
- (id _Nullable)parseInput:(id)input __attribute__((swift_name("parse(input:)")));
- (id _Nullable)parseOrNullInput:(id)input __attribute__((swift_name("parseOrNull(input:)")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerializationStrategy")))
@protocol PKMPKotlinx_serialization_coreSerializationStrategy
@required
- (void)serializeEncoder:(id<PKMPKotlinx_serialization_coreEncoder>)encoder value:(id _Nullable)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<PKMPKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreDeserializationStrategy")))
@protocol PKMPKotlinx_serialization_coreDeserializationStrategy
@required
- (id _Nullable)deserializeDecoder:(id<PKMPKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
@property (readonly) id<PKMPKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreKSerializer")))
@protocol PKMPKotlinx_serialization_coreKSerializer <PKMPKotlinx_serialization_coreSerializationStrategy, PKMPKotlinx_serialization_coreDeserializationStrategy>
@required
@end

__attribute__((swift_name("KotlinAppendable")))
@protocol PKMPKotlinAppendable
@required
- (id<PKMPKotlinAppendable>)appendValue:(unichar)value __attribute__((swift_name("append(value:)")));
- (id<PKMPKotlinAppendable>)appendValue_:(id _Nullable)value __attribute__((swift_name("append(value_:)")));
- (id<PKMPKotlinAppendable>)appendValue:(id _Nullable)value startIndex:(int32_t)startIndex endIndex:(int32_t)endIndex __attribute__((swift_name("append(value:startIndex:endIndex:)")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreEncoder")))
@protocol PKMPKotlinx_serialization_coreEncoder
@required
- (id<PKMPKotlinx_serialization_coreCompositeEncoder>)beginCollectionDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor collectionSize:(int32_t)collectionSize __attribute__((swift_name("beginCollection(descriptor:collectionSize:)")));
- (id<PKMPKotlinx_serialization_coreCompositeEncoder>)beginStructureDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("beginStructure(descriptor:)")));
- (void)encodeBooleanValue:(BOOL)value __attribute__((swift_name("encodeBoolean(value:)")));
- (void)encodeByteValue:(int8_t)value __attribute__((swift_name("encodeByte(value:)")));
- (void)encodeCharValue:(unichar)value __attribute__((swift_name("encodeChar(value:)")));
- (void)encodeDoubleValue:(double)value __attribute__((swift_name("encodeDouble(value:)")));
- (void)encodeEnumEnumDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)enumDescriptor index:(int32_t)index __attribute__((swift_name("encodeEnum(enumDescriptor:index:)")));
- (void)encodeFloatValue:(float)value __attribute__((swift_name("encodeFloat(value:)")));
- (id<PKMPKotlinx_serialization_coreEncoder>)encodeInlineDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("encodeInline(descriptor:)")));
- (void)encodeIntValue:(int32_t)value __attribute__((swift_name("encodeInt(value:)")));
- (void)encodeLongValue:(int64_t)value __attribute__((swift_name("encodeLong(value:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNotNullMark __attribute__((swift_name("encodeNotNullMark()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNull __attribute__((swift_name("encodeNull()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNullableSerializableValueSerializer:(id<PKMPKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableValue(serializer:value:)")));
- (void)encodeSerializableValueSerializer:(id<PKMPKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableValue(serializer:value:)")));
- (void)encodeShortValue:(int16_t)value __attribute__((swift_name("encodeShort(value:)")));
- (void)encodeStringValue:(NSString *)value __attribute__((swift_name("encodeString(value:)")));
@property (readonly) PKMPKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerialDescriptor")))
@protocol PKMPKotlinx_serialization_coreSerialDescriptor
@required
- (NSArray<id<PKMPKotlinAnnotation>> *)getElementAnnotationsIndex:(int32_t)index __attribute__((swift_name("getElementAnnotations(index:)")));
- (id<PKMPKotlinx_serialization_coreSerialDescriptor>)getElementDescriptorIndex:(int32_t)index __attribute__((swift_name("getElementDescriptor(index:)")));
- (int32_t)getElementIndexName:(NSString *)name __attribute__((swift_name("getElementIndex(name:)")));
- (NSString *)getElementNameIndex:(int32_t)index __attribute__((swift_name("getElementName(index:)")));
- (BOOL)isElementOptionalIndex:(int32_t)index __attribute__((swift_name("isElementOptional(index:)")));
@property (readonly) NSArray<id<PKMPKotlinAnnotation>> *annotations __attribute__((swift_name("annotations")));
@property (readonly) int32_t elementsCount __attribute__((swift_name("elementsCount")));
@property (readonly) BOOL isInline __attribute__((swift_name("isInline")));
@property (readonly) BOOL isNullable __attribute__((swift_name("isNullable")));
@property (readonly) PKMPKotlinx_serialization_coreSerialKind *kind __attribute__((swift_name("kind")));
@property (readonly) NSString *serialName __attribute__((swift_name("serialName")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreDecoder")))
@protocol PKMPKotlinx_serialization_coreDecoder
@required
- (id<PKMPKotlinx_serialization_coreCompositeDecoder>)beginStructureDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("beginStructure(descriptor:)")));
- (BOOL)decodeBoolean __attribute__((swift_name("decodeBoolean()")));
- (int8_t)decodeByte __attribute__((swift_name("decodeByte()")));
- (unichar)decodeChar __attribute__((swift_name("decodeChar()")));
- (double)decodeDouble __attribute__((swift_name("decodeDouble()")));
- (int32_t)decodeEnumEnumDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)enumDescriptor __attribute__((swift_name("decodeEnum(enumDescriptor:)")));
- (float)decodeFloat __attribute__((swift_name("decodeFloat()")));
- (id<PKMPKotlinx_serialization_coreDecoder>)decodeInlineDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeInline(descriptor:)")));
- (int32_t)decodeInt __attribute__((swift_name("decodeInt()")));
- (int64_t)decodeLong __attribute__((swift_name("decodeLong()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)decodeNotNullMark __attribute__((swift_name("decodeNotNullMark()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (PKMPKotlinNothing * _Nullable)decodeNull __attribute__((swift_name("decodeNull()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id _Nullable)decodeNullableSerializableValueDeserializer:(id<PKMPKotlinx_serialization_coreDeserializationStrategy>)deserializer __attribute__((swift_name("decodeNullableSerializableValue(deserializer:)")));
- (id _Nullable)decodeSerializableValueDeserializer:(id<PKMPKotlinx_serialization_coreDeserializationStrategy>)deserializer __attribute__((swift_name("decodeSerializableValue(deserializer:)")));
- (int16_t)decodeShort __attribute__((swift_name("decodeShort()")));
- (NSString *)decodeString __attribute__((swift_name("decodeString()")));
@property (readonly) PKMPKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreCompositeEncoder")))
@protocol PKMPKotlinx_serialization_coreCompositeEncoder
@required
- (void)encodeBooleanElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(BOOL)value __attribute__((swift_name("encodeBooleanElement(descriptor:index:value:)")));
- (void)encodeByteElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int8_t)value __attribute__((swift_name("encodeByteElement(descriptor:index:value:)")));
- (void)encodeCharElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(unichar)value __attribute__((swift_name("encodeCharElement(descriptor:index:value:)")));
- (void)encodeDoubleElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(double)value __attribute__((swift_name("encodeDoubleElement(descriptor:index:value:)")));
- (void)encodeFloatElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(float)value __attribute__((swift_name("encodeFloatElement(descriptor:index:value:)")));
- (id<PKMPKotlinx_serialization_coreEncoder>)encodeInlineElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("encodeInlineElement(descriptor:index:)")));
- (void)encodeIntElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int32_t)value __attribute__((swift_name("encodeIntElement(descriptor:index:value:)")));
- (void)encodeLongElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int64_t)value __attribute__((swift_name("encodeLongElement(descriptor:index:value:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNullableSerializableElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index serializer:(id<PKMPKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableElement(descriptor:index:serializer:value:)")));
- (void)encodeSerializableElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index serializer:(id<PKMPKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableElement(descriptor:index:serializer:value:)")));
- (void)encodeShortElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int16_t)value __attribute__((swift_name("encodeShortElement(descriptor:index:value:)")));
- (void)encodeStringElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(NSString *)value __attribute__((swift_name("encodeStringElement(descriptor:index:value:)")));
- (void)endStructureDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("endStructure(descriptor:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)shouldEncodeElementDefaultDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("shouldEncodeElementDefault(descriptor:index:)")));
@property (readonly) PKMPKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerializersModule")))
@interface PKMPKotlinx_serialization_coreSerializersModule : PKMPBase

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)dumpToCollector:(id<PKMPKotlinx_serialization_coreSerializersModuleCollector>)collector __attribute__((swift_name("dumpTo(collector:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<PKMPKotlinx_serialization_coreKSerializer> _Nullable)getContextualKClass:(id<PKMPKotlinKClass>)kClass typeArgumentsSerializers:(NSArray<id<PKMPKotlinx_serialization_coreKSerializer>> *)typeArgumentsSerializers __attribute__((swift_name("getContextual(kClass:typeArgumentsSerializers:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<PKMPKotlinx_serialization_coreSerializationStrategy> _Nullable)getPolymorphicBaseClass:(id<PKMPKotlinKClass>)baseClass value:(id)value __attribute__((swift_name("getPolymorphic(baseClass:value:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<PKMPKotlinx_serialization_coreDeserializationStrategy> _Nullable)getPolymorphicBaseClass:(id<PKMPKotlinKClass>)baseClass serializedClassName:(NSString * _Nullable)serializedClassName __attribute__((swift_name("getPolymorphic(baseClass:serializedClassName:)")));
@end

__attribute__((swift_name("KotlinAnnotation")))
@protocol PKMPKotlinAnnotation
@required
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerialKind")))
@interface PKMPKotlinx_serialization_coreSerialKind : PKMPBase
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreCompositeDecoder")))
@protocol PKMPKotlinx_serialization_coreCompositeDecoder
@required
- (BOOL)decodeBooleanElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeBooleanElement(descriptor:index:)")));
- (int8_t)decodeByteElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeByteElement(descriptor:index:)")));
- (unichar)decodeCharElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeCharElement(descriptor:index:)")));
- (int32_t)decodeCollectionSizeDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeCollectionSize(descriptor:)")));
- (double)decodeDoubleElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeDoubleElement(descriptor:index:)")));
- (int32_t)decodeElementIndexDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeElementIndex(descriptor:)")));
- (float)decodeFloatElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeFloatElement(descriptor:index:)")));
- (id<PKMPKotlinx_serialization_coreDecoder>)decodeInlineElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeInlineElement(descriptor:index:)")));
- (int32_t)decodeIntElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeIntElement(descriptor:index:)")));
- (int64_t)decodeLongElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeLongElement(descriptor:index:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id _Nullable)decodeNullableSerializableElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index deserializer:(id<PKMPKotlinx_serialization_coreDeserializationStrategy>)deserializer previousValue:(id _Nullable)previousValue __attribute__((swift_name("decodeNullableSerializableElement(descriptor:index:deserializer:previousValue:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)decodeSequentially __attribute__((swift_name("decodeSequentially()")));
- (id _Nullable)decodeSerializableElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index deserializer:(id<PKMPKotlinx_serialization_coreDeserializationStrategy>)deserializer previousValue:(id _Nullable)previousValue __attribute__((swift_name("decodeSerializableElement(descriptor:index:deserializer:previousValue:)")));
- (int16_t)decodeShortElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeShortElement(descriptor:index:)")));
- (NSString *)decodeStringElementDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeStringElement(descriptor:index:)")));
- (void)endStructureDescriptor:(id<PKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("endStructure(descriptor:)")));
@property (readonly) PKMPKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinNothing")))
@interface PKMPKotlinNothing : PKMPBase
@end


/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
__attribute__((swift_name("Kotlinx_serialization_coreSerializersModuleCollector")))
@protocol PKMPKotlinx_serialization_coreSerializersModuleCollector
@required
- (void)contextualKClass:(id<PKMPKotlinKClass>)kClass provider:(id<PKMPKotlinx_serialization_coreKSerializer> (^)(NSArray<id<PKMPKotlinx_serialization_coreKSerializer>> *))provider __attribute__((swift_name("contextual(kClass:provider:)")));
- (void)contextualKClass:(id<PKMPKotlinKClass>)kClass serializer:(id<PKMPKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("contextual(kClass:serializer:)")));
- (void)polymorphicBaseClass:(id<PKMPKotlinKClass>)baseClass actualClass:(id<PKMPKotlinKClass>)actualClass actualSerializer:(id<PKMPKotlinx_serialization_coreKSerializer>)actualSerializer __attribute__((swift_name("polymorphic(baseClass:actualClass:actualSerializer:)")));
- (void)polymorphicDefaultBaseClass:(id<PKMPKotlinKClass>)baseClass defaultDeserializerProvider:(id<PKMPKotlinx_serialization_coreDeserializationStrategy> _Nullable (^)(NSString * _Nullable))defaultDeserializerProvider __attribute__((swift_name("polymorphicDefault(baseClass:defaultDeserializerProvider:)"))) __attribute__((deprecated("Deprecated in favor of function with more precise name: polymorphicDefaultDeserializer")));
- (void)polymorphicDefaultDeserializerBaseClass:(id<PKMPKotlinKClass>)baseClass defaultDeserializerProvider:(id<PKMPKotlinx_serialization_coreDeserializationStrategy> _Nullable (^)(NSString * _Nullable))defaultDeserializerProvider __attribute__((swift_name("polymorphicDefaultDeserializer(baseClass:defaultDeserializerProvider:)")));
- (void)polymorphicDefaultSerializerBaseClass:(id<PKMPKotlinKClass>)baseClass defaultSerializerProvider:(id<PKMPKotlinx_serialization_coreSerializationStrategy> _Nullable (^)(id))defaultSerializerProvider __attribute__((swift_name("polymorphicDefaultSerializer(baseClass:defaultSerializerProvider:)")));
@end

__attribute__((swift_name("KotlinKDeclarationContainer")))
@protocol PKMPKotlinKDeclarationContainer
@required
@end

__attribute__((swift_name("KotlinKAnnotatedElement")))
@protocol PKMPKotlinKAnnotatedElement
@required
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.1")
*/
__attribute__((swift_name("KotlinKClassifier")))
@protocol PKMPKotlinKClassifier
@required
@end

__attribute__((swift_name("KotlinKClass")))
@protocol PKMPKotlinKClass <PKMPKotlinKDeclarationContainer, PKMPKotlinKAnnotatedElement, PKMPKotlinKClassifier>
@required

/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.1")
*/
- (BOOL)isInstanceValue:(id _Nullable)value __attribute__((swift_name("isInstance(value:)")));
@property (readonly) NSString * _Nullable qualifiedName __attribute__((swift_name("qualifiedName")));
@property (readonly) NSString * _Nullable simpleName __attribute__((swift_name("simpleName")));
@end

#pragma pop_macro("_Nullable_result")
#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
