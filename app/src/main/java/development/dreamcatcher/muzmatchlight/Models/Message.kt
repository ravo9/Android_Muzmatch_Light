package development.dreamcatcher.muzmatchlight.Models

class Message {

    val id: Int
    var messageText: String? = null
    val isOwnMessage: Boolean

    constructor(id: Int, messageText: String, isOwnMessage: Boolean) {
        this.id = id
        this.messageText = messageText
        this.isOwnMessage = isOwnMessage
    }
}