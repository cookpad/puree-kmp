package com.cookpad.puree.demo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cookpad.puree.demo.Puree
import com.cookpad.puree.demo.log.model.ClickLog
import com.cookpad.puree.demo.log.model.PeriodicLog
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
) {
    var periodicLogSequence by remember { mutableIntStateOf(0) }
    var isSendLogPerSecond by remember { mutableStateOf(false) }

    if (isSendLogPerSecond) {
        LaunchedEffect(true) {
            while (isActive) {
                periodicLogSequence++
                Puree.send(PeriodicLog(periodicLogSequence))
                delay(1000)
            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            MainTopAppBar(
                modifier = Modifier.fillMaxWidth(),
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.CenterVertically,
            ),
        ) {
            Button(
                onClick = { Puree.send(ClickLog("button")) },
            ) {
                Text("Send log")
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Log every second")

                Switch(
                    checked = isSendLogPerSecond,
                    onCheckedChange = { isSendLogPerSecond = it },
                )
            }
        }
    }
}
