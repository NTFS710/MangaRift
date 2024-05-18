package com.sephirita.mangarift.ui.component.dialog

import android.opengl.Visibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.sephirita.mangarift.R

@Composable
fun ReadingStyleDialog(
    changeDialogVisibility: (Boolean) -> Unit,
    changeReadingStyle: (Boolean) -> Unit
) {
    Dialog(onDismissRequest = { changeDialogVisibility(false) } ) {
        Box {
            Column {
                Button(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .width(120.dp)
                        .align(Alignment.Start),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(start = 8.dp),
                    onClick = {
                        changeReadingStyle(true)
                        changeDialogVisibility(false)
                    }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(24.dp),
                            painter = painterResource(id = R.drawable.ic_read_type_horizontal),
                            contentDescription = "Horizontal Reader Type"
                        )
                        VerticalDivider(
                            modifier = Modifier
                                .height(24.dp)
                                .padding(horizontal = 8.dp),
                            color = Color.White
                        )
                        Text(text = "Padrão")
                    }
                }
                Button(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .width(120.dp),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    onClick = {
                        changeReadingStyle(false)
                        changeDialogVisibility(false)
                    }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_read_type_vertical),
                            contentDescription = "Vertical Reader Type"
                        )
                        VerticalDivider(
                            modifier = Modifier
                                .height(24.dp)
                                .padding(horizontal = 8.dp),
                            color = Color.White
                        )
                        Text(text = "Webtoon")
                    }
                }
            }
        }
    }
}