package com.grey.ym.reply.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grey.ym.reply.data.Email
import com.grey.ym.reply.data.MailboxType
import com.grey.ym.reply.ui.utils.ReplyNavigationType
import com.grey.ym.reply.ui.utils.ReplyContentType

@Composable
fun ReplyApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val viewModel: ReplyViewModel = viewModel()
    val replyUiState = viewModel.uiState.collectAsState().value

    val replyNavigationType: ReplyNavigationType
    val replyContentType: ReplyContentType

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            replyNavigationType = ReplyNavigationType.BOTTOM_NAVIGATION
            replyContentType = ReplyContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium -> {
            replyNavigationType = ReplyNavigationType.NAVIGATION_RAIL
            replyContentType = ReplyContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Expanded -> {
            replyNavigationType = ReplyNavigationType.PERMANENT_NAVIGATION_DRAWER
            replyContentType = ReplyContentType.LIST_AND_DETAIL
        }
        else -> {
            replyNavigationType = ReplyNavigationType.BOTTOM_NAVIGATION
            replyContentType = ReplyContentType.LIST_ONLY
        }
    }


    HomeScreen(
        modifier = modifier,
        replyUiState = replyUiState,
        navigationType = replyNavigationType,
        contentType = replyContentType,
        onTabPressed = { mailboxType: MailboxType ->
            viewModel.updateCurrentMailbox(mailboxType = mailboxType)
            viewModel.resetHomeScreenStates()
        },
        onEmailCardPressed = { email: Email ->
            viewModel.updateDetailsScreenStates(email = email)
        },
        onDetailScreenBackPressed = {
            viewModel.resetHomeScreenStates()
        }
    )
}