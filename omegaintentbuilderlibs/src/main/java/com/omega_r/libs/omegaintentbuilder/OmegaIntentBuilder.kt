/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * OmegaIntentBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */

package com.omega_r.libs.omegaintentbuilder

import android.app.Activity
import android.content.Context
import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.builders.*
import com.omega_r.libs.omegaintentbuilder.builders.share.EmailIntentBuilder
import com.omega_r.libs.omegaintentbuilder.builders.share.PlayStoreBuilder
import com.omega_r.libs.omegaintentbuilder.builders.share.ShareIntentBuilder
import com.omega_r.libs.omegaintentbuilder.types.CalendarActionTypes
import com.omega_r.libs.omegaintentbuilder.types.MapTypes

/**
 * OmegaIntentBuilder class for creating supports createdIntent builders.
 */
open class OmegaIntentBuilder(private val context: Context) {

  companion object {
    @JvmStatic
    @Suppress("NON_FINAL_MEMBER_IN_OBJECT")
    open fun from(context: Context): OmegaIntentBuilder = OmegaIntentBuilder(context)
  }

  /**
   * @param phoneNumber String for calling
   * @return CallIntentBuilder for creating call Intent
   */
  fun call(phoneNumber: String): CallIntentBuilder {
    return CallIntentBuilder(context, phoneNumber)
  }

  /**
   * @return ShareIntentBuilder for creating share Intent
   */
  fun share(): ShareIntentBuilder {
    return ShareIntentBuilder(context)
  }

  /**
   * @return ShareIntentBuilder for creating email Intent
   */
  fun email(): EmailIntentBuilder {
    return EmailIntentBuilder(context)
  }

  /**
   * @param urlAddress String
   * @return BrowserBuilder for creating intent to start web browser
   */
  fun web(urlAddress: String): BrowserBuilder {
    return BrowserBuilder(context, urlAddress)
  }

  /**
   * @param uri Uri
   * @return BrowserBuilder for creating intent to start web browser
   */
  fun web(uri: Uri): BrowserBuilder {
    return BrowserBuilder(context, uri)
  }

  /**
   * @return SettingsIntentBuilder for creating intent to start settings
   */
  fun settings(): SettingsIntentBuilder {
    return SettingsIntentBuilder(context)
  }

  /**
   * @return PlayStoreBuilder for creating intent to open PlayStore
   */
  fun playStore(): PlayStoreBuilder {
    return PlayStoreBuilder(context)
  }

  /**
   * @param actionType CalendarActionTypes
   * @return CalendarIntentBuilder for method chaining
   */
  @JvmOverloads
  fun calendar(actionType: CalendarActionTypes = CalendarActionTypes.VIEW_DATE): CalendarIntentBuilder {
    return CalendarIntentBuilder(context, actionType)
  }

  /**
   * @return SmsIntentBuilder for creating intent to send sms
   */
  fun sms(): SmsIntentBuilder {
    return SmsIntentBuilder(context)
  }

  /**
   * @return ActivityIntentBuilder for creating activity intent
   */
  fun <T: Activity> activity(activity: Class<T>): ActivityIntentBuilder<T> {
    return ActivityIntentBuilder(context, activity)
  }

  /**
   * @return MapIntentBuilder for creating intent to open Map application
   */
  fun map(type: MapTypes): MapIntentBuilder {
    return MapIntentBuilder(context, type)
  }

}