package com.jd.todoapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jd.todoapp.R
import com.jd.todoapp.data.models.Priority
import com.jd.todoapp.data.models.ToDoData
import com.jd.todoapp.data.viewmodel.ToDoViewModel
import com.jd.todoapp.fragments.SharedViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

/**
 * A fragment for adding new ToDo items.
 */
class AddFragment : Fragment() {

    // ViewModel for ToDo operations.
    private val mTodoViewModel: ToDoViewModel by viewModels()
    // Shared ViewModel for data verification and priority parsing.
    private val mSharedViewModel: SharedViewModel by viewModels()
    
    /**
     * Creates and returns the View hierarchy associated with the fragment.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        setHasOptionsMenu(true)

        view.priorities_spinner.onItemSelectedListener = mSharedViewModel.listener
        return view
    }

    /**
     * Inflates the add_fragment_menu menu for this fragment.
     *
     * @param menu The Menu to be inflated.
     * @param inflater The MenuInflater used to inflate the menu.
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    /**
     * Handles menu item selections.
     *
     * @param item The selected menu item.
     * @return True if the menu item selection was handled, false otherwise.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Inserts data into the database by extracting values from the UI elements,
     * validating the data, creating a new ToDoData object, inserting the data into the ViewModel,
     * displaying a success message, and navigating to the listFragment if successful.
     */
    private fun insertDataToDb() {
        val mTitle = title_et.text.toString()
        val mPriority = priorities_spinner.selectedItem.toString()
        val mDescription = description_et.text.toString()

        val validation = mSharedViewModel.verifyDataFromUser(mTitle, mDescription)
        if (validation) {
            val newData = ToDoData(
                0,
                mTitle,
                mSharedViewModel.parsePriority(mPriority),
                mDescription
            )
            mTodoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Successfully Added!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }
}