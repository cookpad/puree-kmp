package com.cookpad.puree.kmp.store

import com.cookpad.puree.kmp.store.internal.db.getPureeDatabase

actual class PlatformDefaultPureeLogStore(dbName: String) : PureeLogStore by DefaultPureeLogStore(
    dao = getPureeDatabase(dbName).pureeLogDao(),
)
