package com.example.lotr.ui.character

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lotr.logic.network.LOTRApi
import kotlinx.coroutines.launch
import com.example.lotr.logic.entity.Character
import com.example.lotr.logic.network.CharacterApiFilter

class CharacterListViewModel : ViewModel() {

    enum class CharacterApiStatus { LOADING, ERROR, DONE }

    private val token = "Bearer yX_dU4qFrxA3UQeIT-ic"

    private val _properties = MutableLiveData<List<Character>>()

    val properties: LiveData<List<Character>>
        get() = _properties


    private val _status = MutableLiveData<CharacterApiStatus>()

    val status: LiveData<CharacterApiStatus>
        get() = _status


    init {
        getCharacterProperties(CharacterApiFilter.SHOW_ALL)
    }

    private val _navigateToSelectedProperty = MutableLiveData<Character>()
    val navigateToSelectedProperty: LiveData<Character>
        get() = _navigateToSelectedProperty

    private fun getCharacterProperties(filter: CharacterApiFilter) {
        viewModelScope.launch {
            try {
                _properties.value = LOTRApi.retrofitService.getCharacters(filter.value, token).docs as List<Character>?
                _status.value = CharacterApiStatus.DONE
            } catch (e: Exception) {
                _status.value = CharacterApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    fun updateFilter(filter: CharacterApiFilter) {
        getCharacterProperties(filter)
        Log.d("Filter", filter.toString())
    }

}
