package com.cookpad.puree.output

import com.cookpad.puree.type.JsonObject

/**
 * The output that emits posted logs.
 */
interface PureeOutput {
    /**
     * Emits the log.
     *
     * @param log The log serialized in JSON format
     */
    fun emit(log: JsonObject)
}
