package com.cookpad.puree.kmp.output

import com.cookpad.puree.kmp.type.JsonObject

/**
 * Interface for log output destinations in the Puree-KMP logging system.
 *
 * PureeOutput defines the contract for components that can receive and process
 * serialized logs. Implementations of this interface handle the actual delivery
 * of logs to their final destinations, such as console, file, network services,
 * or other logging systems.
 *
 * Outputs are registered with the PureeLogger through the Puree builder and are
 * associated with specific log types. When a log is posted, it will be routed to
 * all outputs that have been registered for its type.
 */
interface PureeOutput {
    /**
     * Emits a log to its destination.
     *
     * This method is called by the PureeLogger when a log needs to be sent to this output.
     * Implementations should handle the actual delivery of the log to its final destination,
     * such as writing to a file, sending to a network service, or displaying in a console.
     *
     * @param log The log in JSON format, ready for output processing
     */
    fun emit(log: JsonObject)
}
