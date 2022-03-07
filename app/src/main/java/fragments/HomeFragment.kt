package fragments

import activities.HistoryActivity
import activities.ScanActivity
import activities.UserAdapter
import activities.UserData
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qrather.R
import com.example.qrather.databinding.FragmentHomeBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.firebase.firestore.auth.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

   // private lateinit var addsBtn: FloatingActionButton
    private lateinit var recv: RecyclerView
    private lateinit var userList: ArrayList<UserData>
    private lateinit var userAdapter: UserAdapter


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = FragmentHomeBinding.inflate(layoutInflater)


       userList = ArrayList()

        userAdapter = UserAdapter(requireActivity(),userList)
        val addsBtn = view.findViewById<ExtendedFloatingActionButton>(R.id.extended_add)
        recv = view.findViewById(R.id.mRecyclerView)
        val ic_scanner = view.findViewById<ImageButton>(R.id.ic_scanner)
        val ic_history = view.findViewById<ImageButton>(R.id.ic_history)

        addsBtn.setOnClickListener {
            addInfo()
             //Toast.makeText(requireActivity(), "add karte hai tanik ruk jao", Toast.LENGTH_SHORT).show()
        }

        ic_scanner.setOnClickListener {
        activity?.let {
            val intent = Intent(it, ScanActivity::class.java)
            it.startActivity(intent)
        }
        }
        ic_history.setOnClickListener {
            activity?.let {
                val intent = Intent(it, HistoryActivity::class.java)
                it.startActivity(intent)
            }
        }
    }

    private fun addInfo () {
        val inflter = LayoutInflater.from(requireActivity())
        val v = inflter.inflate(R.layout.add_item,null)

        recv.layoutManager = LinearLayoutManager(requireActivity())
        recv.adapter = userAdapter
       val addDialog = AlertDialog.Builder(requireActivity())
        val userName = view?.findViewById<EditText>(R.id.userTitle)
        val  userNo = view?.findViewById<EditText>(R.id.userDiscription)


        addDialog.setView(v)

        addDialog.setPositiveButton( "ADD"){
                dialog,_->
            val names = userName?.text.toString()
            val number = userNo?.text.toString()
             userList.add(UserData("$names","$number"))
            userAdapter.notifyDataSetChanged()
            dialog.dismiss()
            //Toast.makeText(requireActivity(),"Successfully added",Toast.LENGTH_SHORT).show()

        }
        addDialog.setNegativeButton( "Cancel") {
                dialog,_->
               dialog.dismiss()
               Toast.makeText(requireActivity(),"Cancel",Toast.LENGTH_SHORT).show()
        }
       addDialog.create()
        addDialog. show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}