package com.example.alarmmanagerguide

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alarmmanagerguide.models.Person
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val realm = AlarmApplication.realm

    val persons = realm
        .query<Person>()
        .asFlow()
        .map { results ->
            results.list.toList()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

//    init {
//        createSampleEntries()
//    }

    fun createSampleEntries() {
        viewModelScope.launch {
            realm.write {
                val person1 = Person().apply {
                    name = "John"
                    timesVaxed = 0
                }

                copyToRealm(person1, updatePolicy = UpdatePolicy.ALL)
            }
        }
    }

    fun incrementTimesVaxed(person: Person) {
        viewModelScope.launch {
            realm.write {
                val record = query<Person>(
                    "name == $0",
                    "John"
                )
                    .first()
                    .find()!!
                record.timesVaxed++
            }
        }
    }
}