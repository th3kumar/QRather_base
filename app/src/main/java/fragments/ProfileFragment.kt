package fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.qrather.R

import com.example.qrather.main.LoginAcitivity
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {




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
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      var preferences : SharedPreferences = requireActivity().getSharedPreferences("SHARED_PREF",Context.MODE_PRIVATE)




        val tv_user_id  = view.findViewById<TextView>(R.id.tv_user_id)
        val tv_email_id  = view.findViewById<TextView>(R.id.tv_email_id)
        val btn_logout  = view.findViewById<TextView>(R.id.btn_logout)
        val ic_edit_proflile = view.findViewById<ImageView>(R.id.ic_edit_profile)
        val intent =  Intent()
        val userId  = intent.getStringExtra("user_id")
        val emailId = intent.getStringExtra("email_id")

        tv_user_id.text = "User ID :: $userId"
        tv_email_id.text = "Email ID :: $emailId"

        ic_edit_proflile.setOnClickListener {
            activity?.let {

                Toast.makeText(
                    requireActivity(),
                    "Gallery khulega, koi mast si fotu lagaane kaa",
                    Toast.LENGTH_LONG
                ).show()
            }
        }


        btn_logout.setOnClickListener {
            activity?.let {

            val editor: SharedPreferences.Editor = preferences.edit()
                editor.clear()
                editor.apply()

                //logout from app.
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(it, LoginAcitivity::class.java)
                it.startActivity(intent)
            }
        }

    }







    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}