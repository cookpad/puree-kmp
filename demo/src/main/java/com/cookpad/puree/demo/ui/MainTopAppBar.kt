package com.cookpad.puree.demo.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cookpad.puree.demo.Puree
import com.cookpad.puree.demo.log.model.MenuLog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainTopAppBar(
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text("puree-kmp")
        },
        actions = {
            IconButton(
                onClick = { Puree.send(MenuLog("add")) },
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                )
            }
        },
    )
}
