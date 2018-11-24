package development.dreamcatcher.muzmatchlight.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import development.dreamcatcher.muzmatchlight.Adapters.MessageAdapter
import development.dreamcatcher.muzmatchlight.Models.Message
import development.dreamcatcher.muzmatchlight.R
import kotlinx.android.synthetic.main.activity_chat.*


class ChatActivity : AppCompatActivity() {

    val messages = ArrayList<Message>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        messages.add(Message(0, "Test test test", true))
        messages.add(Message(1, "Test test test number 2", false))
        messages.add(Message(2, "Test test test number 2", true))
        messages.add(Message(3, "Test test test number 2", false))

        recyclerView_messages.layoutManager = LinearLayoutManager(this)
        recyclerView_messages.adapter = MessageAdapter(messages, this)
    }
}
