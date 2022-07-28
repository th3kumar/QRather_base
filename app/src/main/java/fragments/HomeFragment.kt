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
//import com.example.qrather.databinding.FragmentHomeBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton


class HomeFragment : Fragment() {

   // private lateinit var binding: FragmentHomeBinding

   // private lateinit var addsBtn: FloatingActionButton
    private lateinit var recv: RecyclerView
    private lateinit var userList: ArrayList<UserData>
    private lateinit var userAdapter: UserAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        //binding = FragmentHomeBinding.inflate(layoutInflater)

       //set list
        userList = ArrayList()
        //find Ids
        recv = view.findViewById(R.id.mRecyclerView)
        val addsBtn = view.findViewById<ExtendedFloatingActionButton>(R.id.extended_add)
        val ic_scanner = view.findViewById<ImageButton>(R.id.ic_scanner)
        val ic_history = view.findViewById<ImageButton>(R.id.ic_history)
        //set adaptors
        userAdapter = UserAdapter(requireActivity(),userList)
        //setRecycler view Adapter
        recv.layoutManager = LinearLayoutManager(requireActivity())
        recv.adapter = userAdapter


       // val dialog_scan_button = view.findViewById<Button>(R.id.dialog_scan_button)
       // val dialog_gallery_button = view.findViewById<Button>(R.id.dialog_gallery_button)

//        dialog_scan_button.setOnClickListener {
//           activity?.let {
//               val intent = Intent(it,ScanActivity::class.java)
//               it.startActivity(intent)
//           }
//            //Toast.makeText(requireActivity(), "add karte hai tanik ruk jao", Toast.LENGTH_SHORT).show()
//        }
//        dialog_gallery_button.setOnClickListener {
//
//            Toast.makeText(requireActivity(), "1 sec, abhi khole de rahe hai gallery bhi", Toast.LENGTH_SHORT).show()
//        }

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

                val intent = Intent(requireActivity(), HistoryActivity::class.java)
                startActivity(intent)

        }
    }

    private fun addInfo () {
        val inflter = LayoutInflater.from(requireActivity())
        val v = inflter.inflate(R.layout.add_item,null)

        //set view
        val addDialog = AlertDialog.Builder(requireActivity())
        val userTitle = v.findViewById<EditText>(R.id.userTitle)
        val  userDiscription = v.findViewById<EditText>(R.id.userDiscription)


        addDialog.setView(v)

        addDialog.setPositiveButton( "ADD"){
                dialog,_->
            val title = userTitle.text.toString()
            val discription = userDiscription.text.toString()
             userList.add(UserData("$title","$discription"))
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


}