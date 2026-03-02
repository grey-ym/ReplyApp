package com.grey.ym.reply.ui

import androidx.lifecycle.ViewModel
import com.grey.ym.reply.data.Email
import com.grey.ym.reply.data.MailboxType
import com.grey.ym.reply.data.local.LocalEmailsDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ReplyViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ReplyUiState())
    val uiState: StateFlow<ReplyUiState> = _uiState

    init {
        initializeUiState()
    }

    private fun initializeUiState() {
        val mailBoxes: Map<MailboxType, List<Email>> = LocalEmailsDataProvider.allEmails.groupBy { it.mailbox }
        _uiState.value = ReplyUiState(
            mailBoxes = mailBoxes,
            currentSelectedEmail = mailBoxes[MailboxType.Inbox]?.get(0)
                ?: LocalEmailsDataProvider.defaultEmail
        )
    }

    fun updateDetailsScreenStates(email: Email) {
        _uiState.update {
            it.copy(currentSelectedEmail = email, isShowingHomepage = false)
        }
    }

    fun resetHomeScreenStates() {
        _uiState.update {
            it.copy(
                currentSelectedEmail = it.mailBoxes[it.currentMailbox]?.get(0)
                    ?: LocalEmailsDataProvider.defaultEmail,
                isShowingHomepage = true
            )
        }
    }

    fun updateCurrentMailbox(mailboxType: MailboxType) {
        _uiState.update {
            it.copy(currentMailbox = mailboxType)
        }
    }
}