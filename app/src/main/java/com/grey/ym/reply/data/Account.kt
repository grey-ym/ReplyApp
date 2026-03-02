package com.grey.ym.reply.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Account(
    /** Unique ID of a user  **/
    val id: Long,
    /** User's first name resource id **/
    @StringRes val firstName: Int,
    /** User's last name resource id **/
    @StringRes val lastName: Int,
    /** User's email address resource id **/
    @StringRes val email: Int,
    /** User's avatar image resource id **/
    @DrawableRes val avatar: Int
)