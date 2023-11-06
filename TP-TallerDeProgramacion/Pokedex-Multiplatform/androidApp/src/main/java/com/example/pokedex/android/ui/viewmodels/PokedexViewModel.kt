package com.example.pokedex.android.ui.viewmodels

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.android.ui.activities.PokedexScreenState
import com.example.pokedex.data.IPokedexRepository
import com.example.pokedex.data.PokedexDBRepository
import kotlinx.coroutines.launch

class PokedexViewModel(
    pokedexRepository: IPokedexRepository,
    pokedexDBRepository: PokedexDBRepository,
    context: Context,
) : ViewModel() {

    private val _pokedex = MutableLiveData<PokedexScreenState>(PokedexScreenState.Loading)
    val pokedex: LiveData<PokedexScreenState> = _pokedex

    init {
        viewModelScope.launch {
            if (checkNetwork(context)) {
                val response = pokedexRepository.getPokedex()
                _pokedex.value = PokedexScreenState.ShowPokedex(response)
                if (pokedexDBRepository.getAllPokemon().isEmpty()) {

                    pokedexRepository.getPokedex().forEach {
                        pokedexDBRepository.addPokemon(it)
                    }
                }
            } else {
                if (pokedexDBRepository.getAllPokemon().isNotEmpty()) {

                    _pokedex.value =
                        PokedexScreenState.ShowPokedex(pokedexDBRepository.getAllPokemon())
                } else {
                    _pokedex.value = PokedexScreenState.Error
                }
            }
        }
    }

    private fun checkNetwork(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val networkinfo =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        return networkinfo.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || networkinfo.hasTransport(
            NetworkCapabilities.TRANSPORT_CELLULAR,
        )
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
