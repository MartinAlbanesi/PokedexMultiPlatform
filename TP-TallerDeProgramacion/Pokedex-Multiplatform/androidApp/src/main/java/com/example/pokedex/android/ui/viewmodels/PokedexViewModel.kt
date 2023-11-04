package com.example.pokedex.android.ui.viewmodels

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.PokedexResults
import com.example.pokedex.data.PokedexService
import kotlinx.coroutines.launch

class PokedexViewModel() : ViewModel() {

    private val pokedexService = PokedexService()
    val pokedex = MutableLiveData<List<PokedexResults>>()

    fun getPokemonList() {
        viewModelScope.launch {
            pokedex.value = pokedexService.getPokedex()
        }
    }

    init {
        getPokemonList()
    }
    /*
        val pokedex = MutableLiveData<Pokedex>()

        private val _screenState: MutableStateFlow<PokedexScreenState> = MutableStateFlow(
            PokedexScreenState.Loading)
        val screenState: Flow<PokedexScreenState> = _screenState

        private val coroutineExceptionHandler =
            CoroutineExceptionHandler { coroutineContext, throwable ->
                Log.d("PokedexViewModel", "Error retrieving pokedex: ${throwable.message}")
            }

        init {
            viewModelScope.launch(coroutineExceptionHandler) {
                kotlin.runCatching {
                    pokedexRepository.getPokedex()
                }.onSuccess {
                    if (it.body() != null) {
                        pokedex.postValue(it.body()!!)
                        _screenState.value = PokedexScreenState.ShowPokedex(it.body()!!)
                    } else {
                        _screenState.value = PokedexScreenState.Error
                    }
                }.onFailure {
                    Log.d("PokedexViewModel", "Error retrieving pokedex: ${it.message}")
                    _screenState.value = PokedexScreenState.Error
                }

            }
        }
    */
}
