package com.mommydndn.app.ui.deprecated.components.modal.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.deprecated.models.dialog.DialogTitle
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.theme.paragraph400

@Composable
fun DialogTitleWrapper(title: DialogTitle) {
    when (title) {
        is DialogTitle.Default -> DefaultDialogTitle(title)
        is DialogTitle.Check -> CheckDialogTitle(title)
        is DialogTitle.Location -> LocationDialogTitle(title)
        is DialogTitle.Refresh -> RefreshDialogTitle(title)
    }
}

@Composable
fun DefaultDialogTitle(title: DialogTitle.Default) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = title.text,
            style = MaterialTheme.typography.paragraph400.copy(
                fontWeight = FontWeight.Bold,
                color = Grey700
            ),
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun CheckDialogTitle(title: DialogTitle.Check) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { title.checkAction(!title.isChecked) },
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(id = if (title.isChecked) R.drawable.icon_checked_checkbox else R.drawable.icon_unchecked_checkbox),
            contentDescription = "",
            modifier = Modifier.size(26.dp)
        )
        Text(
            text = title.text,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.paragraph300.copy(
                fontWeight = FontWeight.Medium,
                color = Grey600
            )
        )
    }
}

@Composable
fun RefreshDialogTitle(title: DialogTitle.Refresh) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title.text,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.paragraph400.copy(
                fontWeight = FontWeight.Bold,
                color = Grey700
            )
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.clickable {
            title.refreshAction()
        }) {
            Image(
                painter = painterResource(id = R.drawable.icon_rewind),
                contentDescription = "",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "초기화하기",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.caption200.copy(
                    fontWeight = FontWeight.Medium,
                    color = Grey600
                )
            )
        }
    }
}

@Composable
fun LocationDialogTitle(title: DialogTitle.Location) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title.text,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.paragraph400.copy(
                fontWeight = FontWeight.Bold,
                color = Grey700
            )
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = title.locationText,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.caption200.copy(
                    fontWeight = FontWeight.Medium,
                    color = Grey600
                )
            )
            Icon(
                painter = painterResource(id = R.drawable.icon_arrow_right),
                contentDescription = "",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}



