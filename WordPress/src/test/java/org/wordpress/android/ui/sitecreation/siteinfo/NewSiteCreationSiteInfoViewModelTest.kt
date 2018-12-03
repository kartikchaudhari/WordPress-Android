package org.wordpress.android.ui.sitecreation.siteinfo

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.wordpress.android.ui.sitecreation.verticals.NewSiteCreationSiteInfoViewModel
import org.wordpress.android.ui.sitecreation.verticals.NewSiteCreationSiteInfoViewModel.SiteInfoUiState

private const val BUSINESS_NAME = "Test Business Name"
private const val TAG_LINE = "Test Tag Line"

private val EMPTY_UI_STATE = SiteInfoUiState(businessName = "", tagLine = "")

@RunWith(MockitoJUnitRunner::class)
class NewSiteCreationSiteInfoViewModelTest {
    @Rule
    @JvmField val rule = InstantTaskExecutorRule()
    @Mock private lateinit var uiStateObserver: Observer<SiteInfoUiState>

    private lateinit var viewModel: NewSiteCreationSiteInfoViewModel

    @Before
    fun setUp() {
        viewModel = NewSiteCreationSiteInfoViewModel()
        viewModel.uiState.observeForever(uiStateObserver)
    }

    @Test
    fun verifyInitialUiStateIsEmpty() {
        assertThat(viewModel.uiState.value).isEqualToComparingFieldByField(EMPTY_UI_STATE)
    }

    @Test
    fun verifyUpdateBusinessName() {
        viewModel.updateBusinessName(BUSINESS_NAME)
        val updatedUiState = EMPTY_UI_STATE.copy(businessName = BUSINESS_NAME)
        assertThat(viewModel.uiState.value).isEqualToComparingFieldByField(updatedUiState)
    }

    @Test
    fun verifyUpdateTagLine() {
        viewModel.updateTagLine(TAG_LINE)
        val updatedUiState = EMPTY_UI_STATE.copy(tagLine = TAG_LINE)
        assertThat(viewModel.uiState.value).isEqualToComparingFieldByField(updatedUiState)
    }
}
