package com.example.fabrapptrassessment.chat.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fabrapptrassessment.R
import com.example.fabrapptrassessment.chat.model.ChatLogMessageModel


class ChatAdapter
    (private  val chatMessageList : List<ChatLogMessageModel>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
        //
        inner class ChatViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
            lateinit var chatModel : ChatLogMessageModel
            val avatarImageView = itemView.findViewById<ImageView>(R.id.avatarImageView)
            val usernameTxtView = itemView.findViewById<TextView>(R.id.avatarUsername)
            val messageTextView = itemView.findViewById<TextView>(R.id.messageTextView)

            fun bind(chatList : ChatLogMessageModel){
                this.chatModel = chatList
                messageTextView.text = chatList.message
                usernameTxtView.text = chatList.userName

                Glide
                    .with(itemView)
                    .load(chatList.avatarUrl)
                    .circleCrop()
                    .placeholder(R.drawable.ic_avatar_placeholder)
                    .into(avatarImageView)
            }
        }
        // this is responsible for creating a view to display
        // wrapping the view in the viewHolder, and returning the result
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
            val msgList = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
            return ChatViewHolder(msgList)
        }
        //this is responsible for populating a given holder with the chat message list from a given POSITION
        override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
            val allMsgList = chatMessageList[position]
            holder.bind(allMsgList)
        }
        // this return the number of item in the list to answer the recycler request
        override fun getItemCount() = chatMessageList.size
}
