package fragments

import activities.HistoryActivity
import activities.ScanActivity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.qrather.R
import com.example.qrather.databinding.FragmentHomeBinding
import com.example.qrather.ui.startAnimation

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


        val animation = AnimationUtils.loadAnimation(requireActivity(),R.anim.circle_explosion_anim ).apply {
            duration = 700
            interpolator = AccelerateDecelerateInterpolator()
        }

        binding.extendedAdd.setOnClickListener {
            binding.extendedAdd.isVisible = false
            binding.ccircle.startAnimation(animation){

              //display your fragment/activity
                binding.root.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.comp_yellow))
            }
        }



        val ic_scanner = view.findViewById<ImageButton>(R.id.ic_scanner)

        val ic_history = view.findViewById<ImageButton>(R.id.ic_history)

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