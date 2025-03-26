package com.cookpad.puree.kmp

/**
 * Exception thrown when attempting to post a log of a type that hasn't been registered with the logger.
 *
 * This exception is thrown by [PureeLogger.postLog] when the log type is not found in the
 * registered logs map. To fix this, ensure that the log type is properly registered using
 * the appropriate filter and output configuration methods before posting logs of that type.
 */
class LogNotRegisteredException : Exception()

/**
 * Exception thrown when a log is skipped due to a filter returning null.
 *
 * This exception is used internally by [PureeLogger] to signal that a log should be
 * skipped and not sent to any outputs. This typically happens when a filter decides
 * that a log should not be processed further based on its content or other criteria.
 */
class SkippedLogException : Exception()
