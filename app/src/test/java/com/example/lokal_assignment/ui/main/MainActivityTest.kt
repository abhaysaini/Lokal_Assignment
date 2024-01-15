package com.example.lokal_assignment.ui.main

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.lokal_assignment.utils.Resource
import com.example.lokal_assignment.viewModels.HomeViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Mock
    lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        hiltRule.inject()
        `when`(viewModel.cryptoCurrencyList).thenReturn(MockLiveData())
    }

    @Test
    fun testViewsInitialization() {
        activityRule.launchActivity(null)
        val activity = activityRule.activity
        assertNotNull(activity.binding.rcvCryptoCurrencyList)
        assertNotNull(activity.binding.swipeRefreshLayout)
    }

    @Test
    fun testRecyclerViewAdapter() {
        activityRule.launchActivity(null)
        val activity = activityRule.activity
        assertNotNull(activity.CurrencyAdapter)
    }

    @Test
    fun testSwipeRefreshLayout() {
        activityRule.launchActivity(null)
        val activity = activityRule.activity
        activity.runOnUiThread {
            activity.binding.swipeRefreshLayout.isRefreshing = true
            activity.binding.swipeRefreshLayout.setOnRefreshListener(null)
        }
        assertTrue(activity.binding.swipeRefreshLayout.isRefreshing)
        assertNull(activity.binding.swipeRefreshLayout.onRefreshListener)
    }

    @Test
    fun testViewModelInteraction() {
        `when`(viewModel.cryptoCurrencyList).thenReturn(MockLiveData(Resource.Success(emptyList())))
        activityRule.launchActivity(null)
        val activity = activityRule.activity
    }

    @Test
    fun testItemTouchHelper() {
        activityRule.launchActivity(null)
        val activity = activityRule.activity
        val itemTouchHelper = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.DOWN) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    assertTrue(activity.binding.swipeRefreshLayout.isRefreshing)
                    assertNull(activity.binding.swipeRefreshLayout.onRefreshListener)
                }
            }
        )
        itemTouchHelper.attachToRecyclerView(activity.binding.rcvCryptoCurrencyList)
    }
}
