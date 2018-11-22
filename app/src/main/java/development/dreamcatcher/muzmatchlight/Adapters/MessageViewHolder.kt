package development.dreamcatcher.muzmatchlight.Adapters

import android.view.View
import android.widget.TextView
import development.dreamcatcher.muzmatchlight.R

class MessageViewHolder(view: View?) {

    val messageText: TextView

    init {
        this.messageText = view?.findViewById<TextView>(R.id.textView_messageText) as TextView
    }
}