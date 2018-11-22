package development.dreamcatcher.muzmatchlight.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.EditText
import android.widget.ListView
import butterknife.BindView
import butterknife.ButterKnife
import development.dreamcatcher.muzmatchlight.Adapters.MessageAdapter
import development.dreamcatcher.muzmatchlight.Models.Message
import development.dreamcatcher.muzmatchlight.R
import kotlinx.android.synthetic.main.activity_chat.*


class ChatActivity : AppCompatActivity() {

    @BindView(R.id.listView_messages) internal var messages: ListView? = null
    @BindView(R.id.editText_messageInput) internal var messageInput: EditText? = null

    private var messagesList = ArrayList<Message>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        ButterKnife.bind(this)

        messagesList.add(Message(0, "Test test test", true))
        messagesList.add(Message(1, "Test test test number 2", true))

        Log.d("FlagTest01", messagesList.size.toString())
        Log.d("FlagTest02", messagesList[0].messageText)

        var messageAdapter = MessageAdapter(this, messagesList)
        listView_messages.adapter = messageAdapter
    }
}
