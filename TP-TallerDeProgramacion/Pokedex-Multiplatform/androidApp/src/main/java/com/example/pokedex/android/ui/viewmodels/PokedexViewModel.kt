package com.example.pokedex.android.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.android.ui.activities.PokedexScreenState
import com.example.pokedex.data.IPokedexRepository
import kotlinx.coroutines.launch

class PokedexViewModel(
    private val pokedexRepository: IPokedexRepository,
) : ViewModel() {

    private val _pokedex = MutableLiveData<PokedexScreenState>(PokedexScreenState.Loading)
    val pokedex: LiveData<PokedexScreenState> = _pokedex

    init {
        getPokemonList()
    }

    fun getPokemonList() {
        viewModelScope.launch {
            val response = pokedexRepository.getPokedex()
            _pokedex.value = PokedexScreenState.ShowPokedex(response)
        }
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
