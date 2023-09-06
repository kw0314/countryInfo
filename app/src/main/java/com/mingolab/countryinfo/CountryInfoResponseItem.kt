package com.mingolab.countryinfo

import com.squareup.moshi.Json

data class Country(
    @field:Json(name = "capital")
    val capital: String?,
    @field:Json(name = "code")
    val code: String?,
    @field:Json(name = "currency")
    val currency: Currency?,
    @field:Json(name = "demonym")
    val demonym: String?,
    @field:Json(name = "flag")
    val flag: String?,
    @field:Json(name = "language")
    val language: Language?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "region")
    val region: String?
)

data class Currency(
    @field:Json(name = "code")
    val code: String?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "symbol")
    val symbol: String?
)


data class Language(
    @field:Json(name = "code")
    val code: String?,
    @field:Json(name = "iso639_2")
    val iso6392: String?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "nativeName")
    val nativeName: String?
)


