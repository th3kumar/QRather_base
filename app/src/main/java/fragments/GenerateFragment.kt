package fragments

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.qrather.R
import com.example.qrather.databinding.FragmentGenerateBinding
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

    private lateinit var binding: FragmentGenerateBinding
    private lateinit var qrcode : ImageView
    private lateinit var txt_content : EditText
    private lateinit var btn_generate : Button



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

        binding = FragmentGenerateBinding.inflate(layoutInflater)

        val btn_generate = view.findViewById<Button>(R.id.btn_generate)

        qrcode = view.findViewById(R.id.qrcode)
        txt_content = view.findViewById(R.id.txt_content)


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


    }






}