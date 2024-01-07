package com.mommydndn.app.ui.care.components.post

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Salmon200
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300

@Composable
internal fun AuthenticationPostSection(
    authentications: List<String>,
    onAuthenticateClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.dndn_authentication),
    subtitle: String = stringResource(id = R.string.optional),
) {
    PostSection(
        title = title,
        subtitle = subtitle,
        modifier = modifier,
    ) {
        if (authentications.isNotEmpty()) {
            val height = authentications.size * 20.dp + (authentications.size - 1) * 2.dp

            LazyColumn(
                modifier = Modifier.height(height),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                items(authentications) { authentication ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_certificate),
                            contentDescription = "Authentication_Certificate",
                            modifier = Modifier.size(16.dp),
                            tint = Color.Unspecified,
                        )

                        Text(
                            text = stringResource(R.string.authenticated, authentication),
                            color = Grey700,
                            style = MaterialTheme.typography.caption200.merge(
                                fontWeight = FontWeight.Medium,
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        Button(
            onClick = onAuthenticateClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Salmon200,
            ),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_plus),
                contentDescription = "Authentication_Plus",
                tint = Salmon600,
            )

            Text(
                text = stringResource(R.string.authenticate_more),
                style = MaterialTheme.typography.paragraph300.merge(
                    color = Salmon600,
                    fontWeight = FontWeight.Medium,
                )
            )
        }
    }
}

@Preview
@Composable
private fun AuthenticationPostSectionPreview() {
    AuthenticationPostSection(
        authentications = listOf(

        ),
        onAuthenticateClick = {},
        modifier = Modifier,
    )
}