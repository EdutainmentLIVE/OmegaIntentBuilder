/*
 * Copyright (c) 2017 Omega-r
 *
 * NavigationIntentBuilder
 * NavigationIntentBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 29, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler
import com.omega_r.libs.omegaintentbuilder.types.MapTypes
import com.omega_r.libs.omegaintentbuilder.types.MapTypes.*

/**
 * MapIntentBuilder is a helper for open Maps applications
 */
class MapIntentBuilder(val context: Context) : BaseBuilder(context) {

  private var latitude: Double? = null
  private var longitude: Double? = null
  private var address: String? = null
  private var type: MapTypes? = null

  /**
   * Set a latitude.
   *
   * @param latitude Double
   * @return This MapIntentBuilder for method chaining
   */
  fun latitude(latitude: Double): MapIntentBuilder {
    this.latitude = latitude
    return this
  }

  /**
   * Set a longitude.
   *
   * @param longitude Double
   * @return This MapIntentBuilder for method chaining
   */
  fun longitude(longitude: Double): MapIntentBuilder {
    this.longitude = longitude
    return this
  }

  /**
   * Set latitude and longitude.
   *
   * @param latitude Double
   * @param longitude Double
   * @return This MapIntentBuilder for method chaining
   */
  fun latitude_longitude(latitude: Double, longitude: Double): MapIntentBuilder {
    this.latitude = latitude
    this.longitude = longitude
    return this
  }

  /**
   * Set a searching address.
   *
   * @param address String
   * @return This MapIntentBuilder for method chaining
   */
  fun address(address: String): MapIntentBuilder {
    this.address = address
    return this
  }

  /**
   * Set a map application type.
   *
   * @param type MapTypes
   * @return This MapIntentBuilder for method chaining
   */
  fun type(type: MapTypes): MapIntentBuilder {
    this.type = type
    return this
  }

  override fun createIntent(): Intent {
    if (latitude == null || longitude == null) {
      throw RuntimeException("You can't call createIntent with empty latitude, longitude")
    }
    if (type == null) {
      throw RuntimeException("You can't call createIntent before you call type")
    }
    if (address == null) address = ""

    val uri: Uri = getFormattedUri()
    val intent = Intent(Intent.ACTION_VIEW, uri)

    when (type) {
      GOOGLE_MAP -> intent.setPackage(GOOGLE_MAP.packageName)
      YANDEX_MAP -> intent.setPackage(YANDEX_MAP.packageName)
      KAKAO_MAP -> intent.setPackage(KAKAO_MAP.packageName)
      NAVER_MAP -> intent.setPackage(NAVER_MAP.packageName)
    }

    return intent
  }

  private fun getFormattedUri(): Uri {
    val sb = StringBuilder()
    when (type) {
      GOOGLE_MAP -> {
        sb.append("geo:")
            .append(latitude, ",", longitude)
            .append("?q=")
            .append(Uri.encode(address))
      }
      YANDEX_MAP -> {
        sb.append("yandexmaps://")
            .append(YANDEX_MAP.packageName, "/?pt=")
            .append(longitude, ",", latitude)
            .append("&text=", address)
      }
      KAKAO_MAP -> {
        sb.append("daummaps://look")
            .append("?p=")
            .append(latitude, ",", longitude)
      }
      NAVER_MAP -> {
        sb.append("geo:")
            .append(latitude, ",", longitude)
            .append("?q=")
            .append(Uri.encode(address))
      }
    }
    return Uri.parse(sb.toString())
  }

  override fun createIntentHandler(): ContextIntentHandler {
    return super.createIntentHandler()
        .failIntentHandler(
            OmegaIntentBuilder.from(context)
                .playStore()
                .packageName(type!!.packageName)
                .createIntentHandler()
        )
  }

}