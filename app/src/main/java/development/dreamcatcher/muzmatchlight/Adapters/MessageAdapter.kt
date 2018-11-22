package development.dreamcatcher.muzmatchlight.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import butterknife.BindView
import development.dreamcatcher.muzmatchlight.Models.Message
import development.dreamcatcher.muzmatchlight.R


class MessageAdapter : BaseAdapter {

    @BindView(R.id.textView_messageText) internal var messageText: TextView? = null

    internal var layoutInflater: LayoutInflater

    private var messagesList = ArrayList<Message>()
    private var context: Context? = null

    constructor(context: Context, messagesList: ArrayList<Message>) : super() {
        this.messagesList = messagesList
        this.context = context
        this.layoutInflater = LayoutInflater.from(context)

        Log.d("FlagTest03", messagesList.size.toString())
        Log.d("FlagTest04", messagesList[0].messageText)
    }

    override fun getCount(): Int {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        var convertView = convertView
        val viewHolder: MessageViewHolder

        Log.d("FlagTest05", messagesList.size.toString())
        Log.d("FlagTest06", messagesList[0].messageText)

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listview_message_row, parent, false)
            viewHolder = MessageViewHolder(convertView)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as MessageViewHolder
        }

        try {
            viewHolder.messageText.text = messagesList[position].messageText
        } catch (e: Exception) {
            Log.e("Exception Message: ", e.message)
        }

        return convertView
    }
}