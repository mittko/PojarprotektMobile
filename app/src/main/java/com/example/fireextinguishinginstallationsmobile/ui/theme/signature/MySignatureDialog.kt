import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Path
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignatureBottomSheet(
    onDismiss: () -> Unit,
    onConfirm: (Bitmap) -> Unit
) {
    var signatureBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val isEmpty = signatureBitmap == null

    // Use a key to reset the canvas
    var resetKey by remember { mutableStateOf(0) }

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Draw your signature",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
            ) {
                key(resetKey) {
                    SignatureCanvas(
                        modifier = Modifier.fillMaxSize(),
                        onSignatureChanged = { signatureBitmap = it }
                    )
                }

                if (isEmpty) {
                    Text(
                        text = "Sign here",
                        color = Color.LightGray,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        signatureBitmap = null
                        resetKey++ // clears the canvas
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Clear")
                }

                Button(
                    onClick = { signatureBitmap?.let(onConfirm) },
                    enabled = !isEmpty,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Confirm")
                }
            }
        }
    }
}

@Composable
fun SignatureCanvas(
    modifier: Modifier = Modifier,
    onSignatureChanged: (Bitmap?) -> Unit
) {
    // Use regular mutableListOf — invalidateTick drives recomposition
    val paths = remember { mutableListOf<Path>() }
    var invalidateTick by remember { mutableIntStateOf(0) }
    var canvasWidth by remember { mutableIntStateOf(0) }
    var canvasHeight by remember { mutableIntStateOf(0) }

    val paint = remember {
        Paint().apply {
            color = android.graphics.Color.BLACK
            strokeWidth = 6f
            style = Paint.Style.STROKE
            isAntiAlias = true
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
        }
    }

    Canvas(
        modifier = modifier
            .background(Color.White)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        paths.add(Path().apply { moveTo(offset.x, offset.y) })
                        invalidateTick++
                    },
                    onDrag = { change, _ ->
                        paths.lastOrNull()?.lineTo(change.position.x, change.position.y)
                        invalidateTick++

                        if (canvasWidth > 0 && canvasHeight > 0) {
                            val bmp = Bitmap.createBitmap(
                                canvasWidth,
                                canvasHeight,
                                Bitmap.Config.ARGB_8888
                            )
                            android.graphics.Canvas(bmp).apply {
                                drawColor(android.graphics.Color.WHITE)
                                paths.forEach { drawPath(it, paint) }
                            }
                            onSignatureChanged(bmp)
                        }
                    },
                    onDragEnd = { }
                )
            }
    ) {
        @Suppress("UNUSED_EXPRESSION")
        invalidateTick // just reading it triggers recomposition

        canvasWidth = size.width.toInt()
        canvasHeight = size.height.toInt()

        drawIntoCanvas { canvas ->
            paths.forEach { path ->
                canvas.nativeCanvas.drawPath(path, paint)
            }
        }
    }
}