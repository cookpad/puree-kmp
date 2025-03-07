package com.cookpad.puree.output

import kotlinx.serialization.json.JsonObject

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
