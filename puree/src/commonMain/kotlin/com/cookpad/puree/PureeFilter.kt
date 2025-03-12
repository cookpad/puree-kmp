package com.cookpad.puree

import kotlinx.serialization.json.JsonObject

/**
 * The filter to be applied to logs.
 * The type of logs on which this filter will be applied are determined when creating the [PureeLogger]
 * object through [PureeLogger.Builder].
 */
interface PureeFilter {
    /**
     * Called for each logs on which this filter is applied.
     *
     * @param log The log serialized into JSON format.
     *
     * @return The new log after applying the filter. The same log can be returned if no other processing is needed. If null is
     * returned, the log will be skipped instead and will not be processed by other registered filters and outputs.
     */
    fun applyFilter(log: JsonObject): JsonObject?
}
