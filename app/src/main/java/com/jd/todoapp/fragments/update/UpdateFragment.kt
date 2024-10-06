package com.jd.todoapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jd.todoapp.R
import com.jd.todoapp.data.models.ToDoData
import com.jd.todoapp.data.viewmodel.ToDoViewModel
import com.jd.todoapp.databinding.FragmentUpdateBinding
import com.jd.todoapp.fragments.SharedViewModel
import kotlinx.android.synthetic.main.fragment_update.*

/**
 * 更新任务的Fragment
 * 允许用户修改现有任务的标题、描述和优先级，并确认删除任务
 */
class UpdateFragment : Fragment() {

    // 导航参数
    private val args by navArgs<UpdateFragmentArgs>()
    // 共享ViewModel，用于在Fragment之间共享数据和事件
    private val mSharedViewModel: SharedViewModel by viewModels()
    // ToDoViewModel，用于处理与数据库的交互
    private val mToDoViewModel: ToDoViewModel by viewModels()

    // Data Binding
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    /**
     * 初始化视图
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Data binding
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.args = args

        // 设置菜单
        setHasOptionsMenu(true)

        // 设置优先级选择器的监听器
        binding.currentPrioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener

        return binding.root
    }

    /**
     * 创建选项菜单
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    /**
     * 选项菜单选择事件处理
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save ->
                updateItem()
            R.id.menu_delete ->
                confirmItemRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * 更新任务项
     */
    private fun updateItem() {
        val title = current_title_et.text.toString()
        val description = current_description_et.text.toString()
        val getPriority = current_priorities_spinner.selectedItem.toString()

        val validation = mSharedViewModel.verifyDataFromUser(title, description)
        if (validation) {
            // 更新当前任务项
            val updatedItem = ToDoData(
                args.currentItem.id,
                title,
                mSharedViewModel.parsePriority(getPriority),
                description
            )
            mToDoViewModel.updateData(updatedItem)
            Toast.makeText(requireContext(), "Sucessfully updated!", Toast.LENGTH_SHORT).show()
            // 导航返回
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all the fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    /**
     * 显示确认对话框以删除任务项
     */
    private fun confirmItemRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mToDoViewModel.deleteItem(args.currentItem)
            Toast.makeText(
                requireContext(),
                "Sucessfully removed: ${args.currentItem.title}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete '${args.currentItem.title}'?")
        builder.setMessage("Are you sure you want to remove '${args.currentItem.title}'?")
        builder.create().show()
    }

    /**
     * 销毁视图时清理Data Binding
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}