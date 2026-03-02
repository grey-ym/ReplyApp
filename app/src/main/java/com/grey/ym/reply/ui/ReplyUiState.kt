package com.grey.ym.reply.ui

import com.grey.ym.reply.data.Email
import com.grey.ym.reply.data.MailboxType
import com.grey.ym.reply.data.local.LocalEmailsDataProvider

data class ReplyUiState(
    val mailBoxes: Map<MailboxType, List<Email>> = emptyMap(),
    val currentMailbox: MailboxType = MailboxType.Inbox,
    val currentSelectedEmail: Email = LocalEmailsDataProvider.defaultEmail,
    val isShowingHomepage: Boolean = true
) {
    val currentMailboxEmails: List<Email> by lazy { mailBoxes[currentMailbox]!! }
}