package development.dreamcatcher.muzmatchlight.Models

class Message( val id: Int,
               var messageText: String? = null,
               val timestamp: String,
               val isOwnMessage: Boolean)