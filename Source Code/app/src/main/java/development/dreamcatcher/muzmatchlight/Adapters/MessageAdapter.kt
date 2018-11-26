package development.dreamcatcher.muzmatchlight.Adapters

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import development.dreamcatcher.muzmatchlight.Models.Message
import development.dreamcatcher.muzmatchlight.R
import kotlinx.android.synthetic.main.listview_message.view.*
import java.text.SimpleDateFormat


class MessageAdapter(val items : ArrayList<Message>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    private val dateTimeInstance = SimpleDateFormat.getDateTimeInstance()

    fun addItem(message: Message) {
        items.add(message)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.listview_message,
                parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Check which style should the message have
        if (items[position].isOwnMessage) {
            holder.messageContainer.gravity  = Gravity.END
            holder.messageText?.setTextColor(ContextCompat.getColor(context, R.color.badgeTextRight))

            if (shouldHaveTail(position)) {
                holder.messageLayout?.background = ContextCompat.getDrawable(context,
                        R.drawable.badge_right_with_tail)
            } else {
                // Temporary solution - the padding will be eventually moved to xml.
                holder.messageText.setPadding(16, 12, 16, 18)
                holder.messageContainer.setPadding(0, 0, 0, 10)
                holder.messageLayout?.background = ContextCompat.getDrawable(context,
                        R.drawable.badge_right)
            }
        } else {
            holder.messageText?.setTextColor(ContextCompat.getColor(context, R.color.badgeTextLeft))

            if (shouldHaveTail(position)) {
                holder.messageLayout?.background = ContextCompat.getDrawable(context,
                        R.drawable.badge_left_with_tail)
            } else {
                // Temporary solution - the padding will be eventually moved to xml.
                holder.messageText.setPadding(16, 12, 16, 18)
                holder.messageContainer.setPadding(0, 0, 0, 10)
                holder.messageLayout?.background = ContextCompat.getDrawable(context,
                        R.drawable.badge_left)
            }
        }

        // Check if the message should take whole parent width
        if (shouldHaveFullWidth(position)) {
            val params = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            holder.messageLayout.layoutParams = params
        }

        // Set message text
        holder.messageText?.text = items[position].messageText

        // Set new message animation
        val animation = AnimationUtils.loadAnimation(context, R.anim.single_row_new_message_animation)
        holder.messageContainer?.startAnimation(animation)
    }

    private fun shouldHaveFullWidth(position: Int) :Boolean {
        val timeDifference = getTimeDifference(position)
        return (position==0 || (timeDifference!= null && timeDifference > 3600))
    }

    private fun shouldHaveTail(position: Int) :Boolean {
        val timeDifference = getTimeDifference(position)
        return (!hasSameOwnerAsPreviousMessage(position) || timeDifference!= null && timeDifference > 20)
    }

    private fun getTimeDifference(position: Int) :Long? {

        // Time difference between current and previous message
        if (position > 0) {
            val previousItemTime = items[position - 1].timestamp
            val currentItemTime = items[position].timestamp

            val previousItemTimeValue = dateTimeInstance.parse(previousItemTime)
            val currentItemTimeValue = dateTimeInstance.parse(currentItemTime)
            val timeDifferenceSeconds = (currentItemTimeValue.getTime() - previousItemTimeValue.getTime()) / 1000
            return timeDifferenceSeconds
        }
        else return null
    }

    private fun hasSameOwnerAsPreviousMessage(position: Int) :Boolean {
        return (position > 0 && items[position-1].isOwnMessage == items[position].isOwnMessage)
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val messageText         = view.textView_messageText
    val messageLayout       = view.message_layout
    val messageContainer    = view.message_container
}