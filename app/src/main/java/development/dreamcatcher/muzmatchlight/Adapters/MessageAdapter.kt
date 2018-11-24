package development.dreamcatcher.muzmatchlight.Adapters

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import development.dreamcatcher.muzmatchlight.Models.Message
import development.dreamcatcher.muzmatchlight.R
import kotlinx.android.synthetic.main.listview_message.view.*


class MessageAdapter(val items : ArrayList<Message>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.listview_message,
                parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (items.get(position).isOwnMessage) {
            holder.layout?.background = ContextCompat.getDrawable(context, R.drawable.badge_shape_right)
        } else {
            holder.layout?.background = ContextCompat.getDrawable(context, R.drawable.badge_shape_left)
        }
        holder.messageText?.text = items.get(position).messageText
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val layout = view.layout_message
    val messageText = view.textView_messageText
}