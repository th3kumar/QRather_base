package Tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qrather.R


class TaskListFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        //so that android knows we are showing options(sorting by _)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }


}