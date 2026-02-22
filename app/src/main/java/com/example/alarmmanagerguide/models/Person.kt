package com.example.alarmmanagerguide.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Person: RealmObject {
    var name: String = ""
    var timesVaxed: Int = 0
}