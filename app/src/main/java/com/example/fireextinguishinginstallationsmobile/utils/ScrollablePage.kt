package com.example.fireextinguishinginstallationsmobile.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.fireextinguishinginstallationsmobile.models.CheckedModel
import kotlin.math.roundToInt



@Composable
fun ScrollableColumn(content: @Composable () -> Unit) {
    val scrollState = rememberScrollState() // This is your "Scroller"
    val density = LocalDensity.current

    Row(modifier = Modifier.fillMaxSize()) {
        // 1. The Scrollable Content
        Column(
            modifier = Modifier
                .weight(0.95f)
                .verticalScroll(scrollState) // Standard scrolling
        ) {
            content()
        }

        // 2. The Scrollbar (Logic is way simpler here!)
        Box(modifier = Modifier.weight(0.015f).fillMaxHeight()) {
            val viewPortHeight = scrollState.viewportSize.toFloat()
            val totalHeight = scrollState.maxValue.toFloat() + viewPortHeight

            if (totalHeight > viewPortHeight) {
                // How big is the thumb
                val thumbHeight = (viewPortHeight / totalHeight * viewPortHeight)
                    .coerceAtLeast(50f)

                // Where is the thumb (0.0 to 1.0)
                val scrollFraction = scrollState.value.toFloat() / scrollState.maxValue
                val thumbTop = (viewPortHeight - thumbHeight) * scrollFraction

                Box(
                    modifier = Modifier
                        .offset { IntOffset(0, thumbTop.roundToInt()) }
                        .height(with(density) { thumbHeight.toDp() })
                        .fillMaxWidth()
                        .background(Color.DarkGray, RoundedCornerShape(10.dp))
                )
            }
        }
    }
}

@Composable
private fun Scroll(models: ArrayList<CheckedModel>, content: @Composable (CheckedModel) -> Unit) {
    val listState = rememberLazyListState()
    val density = LocalDensity.current
    var containerHeightPx by remember { mutableIntStateOf(0) }

    // ТОВА Е КЛЮЧЪТ: Изчисляваме всичко на едно място
    val scrollbarInfo by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val viewportHeight = layoutInfo.viewportSize.height.toFloat()
            val totalItems = layoutInfo.totalItemsCount
            val visibleItems = layoutInfo.visibleItemsInfo

            if (visibleItems.isEmpty() || viewportHeight <= 0) return@derivedStateOf Pair(0f, 0f)

            // 1. Колко е голям средно един елемент
            val avgItemHeight = visibleItems.sumOf { it.size }.toFloat() / visibleItems.size
            val estimatedContentHeight = avgItemHeight * totalItems

            // 2. Колко голяма да е "дръжката" (thumb)
            val thumbHeight = (viewportHeight / estimatedContentHeight * viewportHeight)
                .coerceIn(50f, viewportHeight) // минимум 50 пиксела да се вижда

            // 3. Къде точно се намира скролът
            val currentScroll =
                (listState.firstVisibleItemIndex * avgItemHeight) + listState.firstVisibleItemScrollOffset
            val maxScroll = (estimatedContentHeight - viewportHeight).coerceAtLeast(1f)
            val scrollRatio = (currentScroll / maxScroll).coerceIn(0f, 1f)

            val thumbTop = (viewportHeight - thumbHeight) * scrollRatio

            Pair(thumbHeight, thumbTop)
        }
    }

    val (thumbHeightPx, thumbTopPx) = scrollbarInfo

    Row(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(0.95f)
                .fillMaxHeight()
                .onGloballyPositioned { containerHeightPx = it.size.height }
        ) {
            items(models, key = {  }) { model ->
                content(model) // Тук си викаш Columns(model)
            }
        }
        // 1. Проверяваме дали списъкът се скролва в момента
        val isScrolling = listState.isScrollInProgress

// 2. Анимираме прозрачността (Alpha)
// Когато се скролва -> 1f (видимо), когато спре -> 0f (невидимо)
        val alpha by animateFloatAsState(
            targetValue = if (isScrolling) 1f else 0.2f,
            animationSpec = tween(durationMillis = 500), // Плавна анимация от половин секунда
            label = "scrollbar_alpha"
        )

        // Жълтата лента (ScrollBar Track)
        Box(
            modifier = Modifier
                .weight(0.03f)
                .fillMaxHeight()
                .background(Color.LightGray.copy(0.4f))
                .graphicsLayer {
                    //  this.alpha = alpha
                }
        ) {
            Box(
                modifier = Modifier
                    .offset { IntOffset(0, thumbTopPx.roundToInt()) }
                    .height(with(density) { thumbHeightPx.toDp() })
                    .fillMaxWidth()
                    .background(Color.DarkGray, RoundedCornerShape(10.dp))
            )
        }
    }
}

