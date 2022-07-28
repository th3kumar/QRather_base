package activities

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.qrather.R
import com.example.qrather.db.DBHelperI
import com.example.qrather.db.entities.QrResult
import fragments.ScannedHistoryFragment

class HistoryActivity : AppCompatActivity() {

   // private var resultType : ResultListType? = null
    private lateinit var arguments : Bundle
    //private var resultType : ScannedHistoryFragment.ResultListType? = null

    private lateinit var mView : View
    private lateinit var dbHelperI : DBHelperI

//    enum class ResultListType {
//        ALL_RESULT
//    }

//    companion object {
//        private const val ARGUMENT_RESULT_LIST_TYPE = "ArgumentResultListType"
//
//        fun newInstance(screenType : HistoryActivity.ResultListType): HistoryActivity {
//
//            val bundle = Bundle()
//            bundle.putSerializable(ARGUMENT_RESULT_LIST_TYPE,screenType)
//            val fragment = HistoryActivity()
//            fragment.arguments = bundle
//            return fragment
//        }
//    }


     override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)


//        // Inflate the layout for this fragment
//        mView = inflater.inflate(R.layout.fragment_scanned_history, container, false)
//        // init()
//        setSwipeRefresh()
//        // onClicks()
//        showListOfResults()
//        return mView.rootView
            val scannedHistoryFragment = ScannedHistoryFragment()


         makeCurrentFragment(scannedHistoryFragment)


    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.history_fl_wrapper,fragment)
            commit()
        }

    }

//    private fun showListOfResults() {
//        showAllResults()
//    }

    private fun showAllResults() {
        val tvHeaderText = mView.findViewById<TextView>(R.id.tvHeaderText)

        var listAllResult = dbHelperI.getAllScannedResult()
        showResults(listAllResult)
        tvHeaderText.text = "Recent Scanned History"
    }

    private fun showResults(listAllResult : List<QrResult>) {

    }

    private fun showAllResults(listOfQRResults : List<QrResult>) {


        if (listOfQRResults.isEmpty()) {
            showEmptyState()
        }else{
            initRecyclerView(listOfQRResults)
        }
    }

    private fun initRecyclerView(listOfQRResults : List<QrResult>) {
        //val noResultFound = mView.findViewById<ImageView>(R.id.noResultFound)
        val scannedHistoryRecyclerView = mView.findViewById<RecyclerView>(R.id.scannedHistoryRecyclerView)

       // scannedHistoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
       // scannedHistoryRecyclerView.adapter = ScannedResultListAdapter(dbHelperI,requireContext(),listOfQRResults.toMutableList())


    }

    private fun showEmptyState() {
        val noResultFound = mView.findViewById<ImageView>(R.id.noResultFound)
        val scannedHistoryRecyclerView = mView.findViewById<RecyclerView>(R.id.scannedHistoryRecyclerView)

        scannedHistoryRecyclerView.visibility = View.GONE
        noResultFound.visibility = View.VISIBLE
    }

    private fun setSwipeRefresh(){
        val swipeRefresh = mView.findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        swipeRefresh.isRefreshing = false
        //showListOfResults()
    }


}