package development.dreamcatcher.muzmatchlight.Models

class Message {

    var id: Int? = null
    var messageText: String? = null
    var isOwnMessage: Boolean? = null

    constructor(id: Int, messageText: String, isOwnMessage: Boolean) {
        this.id = id
        this.messageText = messageText
        this.isOwnMessage = isOwnMessage
    }
}