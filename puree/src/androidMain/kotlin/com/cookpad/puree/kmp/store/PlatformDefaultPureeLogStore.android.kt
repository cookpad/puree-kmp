package com.cookpad.puree.kmp.store

import android.content.Context
import com.cookpad.puree.kmp.store.internal.db.getPureeDatabase

class PlatformDefaultPureeLogStore(context: Context, dbName: String) : PureeLogStore by DefaultPureeLogStore(
    dao = getPureeDatabase(context, dbName).pureeLogDao(),
)
