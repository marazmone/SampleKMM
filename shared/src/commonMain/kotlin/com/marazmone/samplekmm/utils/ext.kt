package com.marazmone.samplekmm.utils

import io.realm.MutableRealm
import io.realm.RealmObject

val Int?.orZero get() = this ?: 0

val String?.orEmpty get() = this.orEmpty()

val Boolean?.orFalse get() = this ?: false

fun <T : RealmObject> MutableRealm.insertOrUpdate(instance: T) =
    copyToRealm(instance, MutableRealm.UpdatePolicy.ALL)