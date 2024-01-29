package com.mommydndn.app.ui.neighborhood

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.Coordinates
import com.mommydndn.app.ui.signup.components.SearchBar
import com.mommydndn.app.ui.signup.components.SearchByCurrentLocationButton
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300

data class NeighborhoodSearchResultUiModel(
    val id: Int,
    val address: String,
)

@Composable
fun SearchNeighborhoodScreen(
    query: String,
    onValueChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onClearQueryClick: () -> Unit,
    onGetCurrentLocationResult: (Coordinates) -> Unit,
    headerText: String,
    searchResults: List<NeighborhoodSearchResultUiModel>,
    selectedSearchResult: NeighborhoodSearchResultUiModel?,
    onSearchResultClick: (NeighborhoodSearchResultUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        SearchBar(
            query = query,
            onValueChange = onValueChange,
            onBackClick = onBackClick,
            onSearchClick = onSearchClick,
            onClearQueryClick = onClearQueryClick,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        )

        Divider(
            modifier = Modifier,
            color = Grey100,
            thickness = 1.dp,
        )

        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Spacer(modifier = Modifier.height(16.dp))

            SearchByCurrentLocationButton(
                onResult = onGetCurrentLocationResult,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = headerText,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
                color = Grey700,
                style = MaterialTheme.typography.paragraph300.merge(
                    fontWeight = FontWeight.Bold,
                )
            )

            Divider(
                modifier = Modifier,
                color = Grey100,
                thickness = 1.dp,
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        LazyColumn(
            modifier = Modifier.weight(1F),
            contentPadding = PaddingValues(start = 24.dp, top = 6.dp, end = 24.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            // todo: empty_result
            items(searchResults) { searchResult ->
                with(searchResult) {
                    NeighborhoodSearchResultItem(
                        selected = selectedSearchResult?.id == id,
                        address = address,
                        onClick = {
                            onSearchResultClick(this)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun NeighborhoodSearchResultItem(
    selected: Boolean,
    address: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .run {
                if (selected) {
                    background(Grey50, RoundedCornerShape(12.dp))
                } else {
                    this
                }
            }
            .padding(horizontal = 12.dp, vertical = 16.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            ),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(
                id = if (selected) {
                    R.drawable.icon_selected_radio
                } else {
                    R.drawable.icon_unselected_radio
                }
            ),
            contentDescription = "NeighborhoodSearchResult_Radio",
            modifier = Modifier.size(32.dp),
            tint = Color.Unspecified,
        )

        Text(
            text = address,
            modifier = Modifier.weight(1F),
            color = Grey700,
            maxLines = 1,
            style = MaterialTheme.typography.paragraph300.merge(
                fontWeight = FontWeight.Normal,
            )
        )
    }
}

@Preview
@Composable
private fun SearchNeighborhoodScreenPreview() {
    var selectedSearchResult by remember {
        mutableStateOf(NeighborhoodSearchResultUiModel(id = 0, address = "서울 서초구 서초1동"))
    }

    val searchResults = buildList {
        add(selectedSearchResult)

        repeat(3) {
            add(
                NeighborhoodSearchResultUiModel(id = it + 1, address = "서울 서초구 서초${it + 2}동")
            )
        }

        repeat(4) {
            add(
                NeighborhoodSearchResultUiModel(id = it + 4, address = "서울 서초구 반포${it + 1}동")
            )

        }
    }

    var query by remember {
        mutableStateOf("서초구")
    }

    var coordinates by remember {
        mutableStateOf<Coordinates?>(null)
    }

    SearchNeighborhoodScreen(
        query = query,
        onValueChange = {
            query = it
        },
        onBackClick = {},
        onSearchClick = {},
        onClearQueryClick = {},
        onGetCurrentLocationResult = {
            coordinates = it
        },
        headerText = "'$query' 검색결과",
        searchResults = searchResults,
        selectedSearchResult = selectedSearchResult,
        onSearchResultClick = {
            selectedSearchResult = it
        },
        modifier = Modifier
            .background(White)
            .fillMaxSize(),
    )
}