package activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.qrather.R
import fragments.HomeFragment

class UserAdapter(val c : FragmentActivity, val userList :ArrayList<UserData>) :RecyclerView.Adapter<UserAdapter.UserViewHolder>()
{


    inner class UserViewHolder(val v:View):RecyclerView.ViewHolder(v){
        val nname = v.findViewById<TextView>(R.id.title_text)
        val mbNum = v.findViewById<TextView>(R.id.discription_text)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list_items,parent,false)
        return  UserViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newList = userList[position]
        holder.nname.text = newList.tv_Title
        holder.mbNum.text = newList.tv_description
    }

    override fun getItemCount(): Int {
        return  userList.size
    }
}