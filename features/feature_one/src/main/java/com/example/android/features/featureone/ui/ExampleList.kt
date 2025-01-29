package com.example.android.features.featureone.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.android.features.featureone.viewmodel.UiItem
import com.example.android.ui.components.lists.SingleLineItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun ExampleList(
    items: ImmutableList<UiItem>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(items) {
            SingleLineItem(
                modifier = Modifier.fillMaxWidth(),
                text = it.name,
            )
        }
    }
}

@Composable
@Preview
internal fun RecipesPreview() =
    ExampleList(
        items =
            listOf(
                UiItem(name = "Mercedes"),
                UiItem(name = "Lexus"),
                UiItem(name = "Cadillac"),
            ).toImmutableList(),
    )
