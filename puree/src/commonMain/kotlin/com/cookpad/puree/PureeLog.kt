package com.cookpad.puree

import kotlinx.serialization.Serializable

/**
 * Log classes that are registered to PureeLogger must implement this interface.
 */
@Serializable
sealed interface PureeLog
