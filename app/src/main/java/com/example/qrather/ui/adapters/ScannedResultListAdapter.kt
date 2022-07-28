package com.example.qrather.ui.adapters

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qrather.R
import com.example.qrather.db.DBHelperI
import com.example.qrather.db.entities.QrResult
import com.example.qrather.ui.dialogs.QrCodeResultDialog
import com.example.qrather.ui.utils.toFormatedDisplay
import java.nio.file.Files.delete

class ScannedResultListAdapter(
    var dbHelperI: DBHelperI,
    var context: Context,
    private var listOfScannedResult: MutableList<QrResult>
) :
    RecyclerView.Adapter<ScannedResultListAdapter.ScannedResultListViewHolder>() {

    private var resultDialog: QrCodeResultDialog =
        QrCodeResultDialog(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScannedResultListViewHolder {
        return ScannedResultListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_single_item_qr_result,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listOfScannedResult.size
    }

    override fun onBindViewHolder(holder: ScannedResultListViewHolder, position: Int) {
        holder.bind(listOfScannedResult[position], position)
    }

    inner class ScannedResultListViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        fun bind(qrResult: QrResult, position: Int) {

           val result = view.findViewById<TextView>(R.id.result)
            val tvTime = view.findViewById<TextView>(R.id.tvTime)


            result.text = qrResult.result!!
            tvTime.text = qrResult.calendar.toFormatedDisplay()

            setResultTypeIcon(qrResult.resultType)
            setFavourite(qrResult.favourite)
            onClicks(qrResult, position)
        }

        private fun setResultTypeIcon(resultType: String?) {

        }

        private fun setFavourite(isFavourite: Boolean) {
            val favouriteIcon = view.findViewById<ImageView>(R.id.favouriteIcon)

            if (isFavourite)
                favouriteIcon.visibility = View.VISIBLE
            else
                favouriteIcon.visibility = View.GONE
        }

       // onClicks(qrResult: QrResult, position: Int)
        private fun onClicks(qrResult: QrResult, position : Int) {
            view.setOnClickListener {
                resultDialog.show(qrResult)
            }

            view.setOnLongClickListener {
                showDeleteDialog(qrResult, position)
                return@setOnLongClickListener true
            }
        }

      private fun showDeleteDialog(qrResult: QrResult, position: Int) {
          AlertDialog.Builder(context, R.style.CustomAlertDialog).setTitle("Clear Record")
                .setMessage("Are you Really Want to Delete this Record")
                .setPositiveButton("Delete") { _, _ ->
                    deleteThisRecord(qrResult, position)
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }.show()
        }

       private fun deleteThisRecord(qrResult: QrResult, position: Int) {
            dbHelperI.deleteQrResult(qrResult.id!!)
            listOfScannedResult.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}