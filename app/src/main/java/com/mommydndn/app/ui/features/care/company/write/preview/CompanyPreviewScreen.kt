package com.mommydndn.app.ui.features.care.company.write.preview

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mommydndn.app.data.model.care.CompanyPreview
import com.mommydndn.app.ui.features.care.joboffer.write.preview.JobOfferPreviewViewModel

@Composable
fun CompanyPreviewScreen(
    companyPreiew: CompanyPreview?,
    navController: NavHostController,
    viewModel: JobOfferPreviewViewModel = hiltViewModel()
) {}