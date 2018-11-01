package com.alexlee1987.kotlinmanual.recyclerview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup

import com.alexlee1987.kotlinmanual.R
import com.alexlee1987.kotlinmanual.utils.SPUtils
import com.alexlee1987.kotlinmanual.utils.SharedPreferencesConstants

/**
 * A simple [Fragment] subclass.
 *
 */
class RecyclerViewFragment : Fragment() {
    private lateinit var currentLayoutManagerType: LayoutManagerType
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var dataset: Array<String>

    enum class LayoutManagerType { GRID_LAYOUT_MANAGER, LINEAR_LAYOUT_MANAGER }

    companion object {
        private const val TAG = "RecyclerViewFragment"
        private const val KEY_LAYOUT_MANAGER = "layoutManager"
        private const val SPAN_COUNT = 3
        private const val DATASET_COUNT = 60
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataSet()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_recycler_view, container, false)
        recyclerView = rootView.findViewById(R.id.recyclerView)

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        layoutManager = LinearLayoutManager(activity)

        val type = SPUtils.getInt(SharedPreferencesConstants.NAME_ACCOUNT_TOKEN_XML, SharedPreferencesConstants.KEY_LAYOUTMANAGERTYPE)
        Log.i(TAG, "onCreateView type: $type")
        currentLayoutManagerType = if (type == 0) LayoutManagerType.LINEAR_LAYOUT_MANAGER else LayoutManagerType.GRID_LAYOUT_MANAGER

        val radioGroup: RadioGroup = rootView.findViewById(R.id.group_layout_manager)
        val id = if (type == 0) R.id.linear_layout_rb else R.id.grid_layout_rb
        radioGroup.check(id) // 重置保存的布局管理器
        rootView.findViewById<RadioButton>(R.id.linear_layout_rb).setOnClickListener{
            setRecyclerViewLayoutManager(LayoutManagerType.LINEAR_LAYOUT_MANAGER)
            // 记录布局管理器状态
            SPUtils.putInt(SharedPreferencesConstants.NAME_ACCOUNT_TOKEN_XML, SharedPreferencesConstants.KEY_LAYOUTMANAGERTYPE, 0)
        }
        rootView.findViewById<RadioButton>(R.id.grid_layout_rb).setOnClickListener{
            setRecyclerViewLayoutManager(LayoutManagerType.GRID_LAYOUT_MANAGER)
            // 记录布局管理器状态
            SPUtils.putInt(SharedPreferencesConstants.NAME_ACCOUNT_TOKEN_XML, SharedPreferencesConstants.KEY_LAYOUTMANAGERTYPE, 1)
        }

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            currentLayoutManagerType = savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER) as LayoutManagerType
        }
        setRecyclerViewLayoutManager(currentLayoutManagerType)

        // Set CustomAdapter as the adapter for RecyclerView.
        recyclerView.adapter = CustomAdapter(dataset)

        return rootView
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, currentLayoutManagerType)
        super.onSaveInstanceState(savedInstanceState)
    }

    private fun setRecyclerViewLayoutManager(layoutManagerType: LayoutManagerType) {
        var scrollPosition = 0

        // If a layout manager has already been set, get current scroll position.
        if (recyclerView.layoutManager != null) {
            scrollPosition = (recyclerView.layoutManager as LinearLayoutManager)
                    .findFirstCompletelyVisibleItemPosition()
        }

        when (layoutManagerType) {
            LayoutManagerType.GRID_LAYOUT_MANAGER -> {
                layoutManager = GridLayoutManager(activity, SPAN_COUNT)
                currentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER
            }
            LayoutManagerType.LINEAR_LAYOUT_MANAGER -> {
                layoutManager = LinearLayoutManager(activity)
                currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER
            }
        }

        with(recyclerView) {
            // Set CustomAdapter as the adapter for RecyclerView.
            layoutManager = this@RecyclerViewFragment.layoutManager
            adapter = CustomAdapter(dataset)
            scrollToPosition(scrollPosition)
        }

    }

    private fun initDataSet() {
        dataset = Array(DATASET_COUNT) { i -> "Element $i"}
    }
}
