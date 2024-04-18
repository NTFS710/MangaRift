package com.sephirita.mangarift.ui.components.text

import android.graphics.Color
import android.graphics.Paint.Style
import android.graphics.Paint.Join
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp

@Composable
fun StrokedText(
    modifier: Modifier = Modifier,
    text: String = "",
    textColor: Int = Color.WHITE,
    textStyle: Style = Style.FILL,
    size: Float = 64f,
    textStrokeColor: Int = Color.BLACK,
    textStrokeStyle: Style = Style.STROKE,
    textStrokeWidth: Float = 12f,
    textStrokeMiter: Float = 10f,
    textStrokeJoin: Join = Join.ROUND
) {

    val textPaintStroke = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        style = textStrokeStyle
        textSize = size
        color = textStrokeColor
        strokeWidth = textStrokeWidth
        strokeMiter = textStrokeMiter
        strokeJoin = textStrokeJoin
    }

    val textPaint = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        style = textStyle
        textSize = size
        color = textColor
    }

    Canvas(
        modifier = modifier.fillMaxWidth() ,
        onDraw = {
            drawIntoCanvas {
                it.nativeCanvas.drawText(
                    text,
                    0f,
                    0f,
                    textPaintStroke
                )
                it.nativeCanvas.drawText(
                    text,
                    0f,
                    0f,
                    textPaint
                )
            }
        }
    )
}