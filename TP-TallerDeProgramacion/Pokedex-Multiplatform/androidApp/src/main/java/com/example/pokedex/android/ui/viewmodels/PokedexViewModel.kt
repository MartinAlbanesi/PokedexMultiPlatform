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
}
