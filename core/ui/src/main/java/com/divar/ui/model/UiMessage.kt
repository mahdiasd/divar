package com.divar.ui.model

enum class MessageType { Network, System }
enum class MessageStatus { Success, Failure }

sealed class UiMessageContent {
    data class IntMessage(val value: Int) : UiMessageContent()
    data class StringMessage(val value: String) : UiMessageContent()
}

data class UiMessage(
    val messageType: MessageType,
    val status: MessageStatus,
    val content: UiMessageContent
) {
    constructor(
        messageType: MessageType = MessageType.Network,
        status: MessageStatus = MessageStatus.Failure,
        intValue: Int
    ) : this(
        messageType = messageType,
        status = status,
        content = UiMessageContent.IntMessage(intValue)
    )

    constructor(
        messageType: MessageType = MessageType.Network,
        status: MessageStatus = MessageStatus.Failure,
        stringValue: String
    ) : this(
        messageType = messageType,
        status = status,
        content = UiMessageContent.StringMessage(stringValue)
    )
}