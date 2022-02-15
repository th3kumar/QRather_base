package fragments

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.qrather.R
import com.example.qrather.databinding.FragmentGenerateBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GenerateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GenerateFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(requireActivity(),R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(requireActivity(),R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(requireActivity(),R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(requireActivity(),R.anim.to_bottom_anim) }

//    private lateinit var binding: FragmentGenerateBinding
    private lateinit var qrcode : ImageView
    private lateinit var txt_content : EditText
    private lateinit var btn_generate : Button





    private var clicked = false

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
        return inflater.inflate(R.layout.fragment_generate, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding = FragmentGenerateBinding.inflate(layoutInflater)

        val btn_generate = view.findViewById<Button>(R.id.btn_generate)

        qrcode = view.findViewById(R.id.qrcode)
        txt_content = view.findViewById(R.id.txt_content)

        val regular_share = view.findViewById<FloatingActionButton>(R.id.regular_share)
        val share_whatsapp = view.findViewById<FloatingActionButton>(R.id.share_whatsapp)
        val share_mail = view.findViewById<FloatingActionButton>(R.id.share_mail)
        val share_other = view.findViewById<FloatingActionButton>(R.id.share_other)

        btn_generate.setOnClickListener {


            val data = txt_content.text.toString().trim()

            if (data.isEmpty()){
                Toast.makeText(requireActivity(),"Field is Empty",Toast.LENGTH_SHORT).show()
            }else{
                val writer = QRCodeWriter()
                try {
                    val bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE,512,512)
                    val width = bitMatrix.width
                    val height = bitMatrix.height
                    val bmp  = Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565)
                    for(x in 0  until width){
                        for(y in 0 until height){
                            bmp.setPixel(x,y, if(bitMatrix[x,y]) Color.BLACK else Color.WHITE)
                        }
                    }
                    qrcode.setImageBitmap(bmp)

                }catch (e:WriterException){
                    e.printStackTrace()
                }
            }

        }




        regular_share.setOnClickListener {
           onAddButtonClicked()
        }

        share_mail.setOnClickListener {
            Toast.makeText(requireActivity(),"will be directed to mail app",Toast.LENGTH_SHORT).show()
        }
        share_other.setOnClickListener {
            Toast.makeText(requireActivity(),"will be directed to list of apps",Toast.LENGTH_SHORT).show()
        }
        share_whatsapp.setOnClickListener {
            Toast.makeText(requireActivity(),"will be directed to whatsapp",Toast.LENGTH_SHORT).show()
        }







    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked: Boolean) {

        var share_whatsapp = view?.findViewById<FloatingActionButton>(R.id.share_whatsapp)
        val share_mail = view?.findViewById<FloatingActionButton>(R.id.share_mail)
        val share_other = view?.findViewById<FloatingActionButton>(R.id.share_other)




        if(!clicked){
            share_mail?.visibility = View.VISIBLE
            share_whatsapp?.visibility = View.VISIBLE
            share_other?.visibility = View.VISIBLE
        }else{
            share_mail?.visibility = View.INVISIBLE
            share_whatsapp?.visibility = View.INVISIBLE
            share_other?.visibility = View.INVISIBLE
        }
    }

    private fun setVisibility(clicked: Boolean) {

        var share_whatsapp = view?.findViewById<FloatingActionButton>(R.id.share_whatsapp)
        val share_mail = view?.findViewById<FloatingActionButton>(R.id.share_mail)
        val share_other = view?.findViewById<FloatingActionButton>(R.id.share_other)
        var regular_share = view?.findViewById<FloatingActionButton>(R.id.regular_share)
        if (!clicked){
            share_mail?.startAnimation(fromBottom)
            share_other?.startAnimation(fromBottom)
            share_whatsapp?.startAnimation(fromBottom)
            regular_share?.startAnimation(rotateOpen)
        }else{
            share_mail?.startAnimation(toBottom)
            share_other?.startAnimation(toBottom)
            share_whatsapp?.startAnimation(toBottom)
            regular_share?.startAnimation(rotateClose)
        }



    }


}