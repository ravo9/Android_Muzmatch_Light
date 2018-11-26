package development.dreamcatcher.muzmatchlight.Activities

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import development.dreamcatcher.muzmatchlight.Adapters.MessageAdapter
import development.dreamcatcher.muzmatchlight.Models.Message
import development.dreamcatcher.muzmatchlight.R
import kotlinx.android.synthetic.main.activity_chat.*
import java.text.SimpleDateFormat


class ChatActivity : AppCompatActivity() {

    private val dateTimeInstance    = SimpleDateFormat.getDateTimeInstance()
    private val messages            = ArrayList<Message>()
    private val messagesAdapter     = MessageAdapter(messages, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        uploadFakeMessages()

        recyclerView_messages.adapter           = messagesAdapter
        recyclerView_messages.layoutManager     = LinearLayoutManager(this)

        editText_messageInput.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                addNewMessage()
                return@OnKeyListener true
            }
            false
        })
    }

    private fun addNewMessage() {
        val messageText: String = editText_messageInput.text.toString()
        val currentDate = dateTimeInstance.format(java.util.Date())
        messagesAdapter.addItem(Message(0, messageText, currentDate, true))
        editText_messageInput.text.clear()
        editText_messageInput.clearFocus()
        hideKeyboard()
    }

    private fun hideKeyboard() {
        val view: View? = currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun uploadFakeMessages() {
        val currentDate = dateTimeInstance.format(java.util.Date())
        messages.add(Message(0, "Hi ;)", currentDate, false))
        messages.add(Message(1, "Hello :)", currentDate, true))
        messages.add(Message(2, "How are you today...? ;)", currentDate, false))
        messages.add(Message(3, "Not bad :P How about yourself?", currentDate, true))
    }
}
