package android.guide.everydayanimal.ui

import android.guide.everydayanimal.network.Animal
import android.guide.everydayanimal.network.AnimalApi
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

enum class AnimalApiStatus {LOADING, ERROR, DONE}

class AnimalViewModel: ViewModel(){
    val TAG = "AnimalViewModel"
    val _animal = MutableLiveData<Animal>()
    val animal: LiveData<Animal> = _animal

    val _animalList = MutableLiveData<List<Animal>>()
    val animalList: LiveData<List<Animal>> = _animalList

    val _status = MutableLiveData<AnimalApiStatus>()
    val status: LiveData<AnimalApiStatus> = _status


    init{
        getAnimalsList()
    }


    fun getAnimalsList(){
        viewModelScope.launch{
            try{
                _status.value = AnimalApiStatus.LOADING
                _animalList.value = AnimalApi.retrofitService.getAnimals()
                _status.value = AnimalApiStatus.DONE
            } catch (e: Exception){
                _status.value = AnimalApiStatus.ERROR
                _animalList.value = listOf()
            }
        }
    }
// helper method for formating some data
    fun onAnimalClicked(animal: Animal){
        _animal.value = animal
    }


    fun getLifeSpan(): Int {
        return _animal.value?.lifespan?.toInt()!!
    }

    fun getWeight(): Double{
        val temp = _animal.value?.weight_max?.toDouble()!! + _animal.value?.weight_min?.toDouble()!!
        return temp/2
    }

    fun getLength(): Double{
        val temp = _animal.value?.length_max?.toDouble()!! + _animal.value?.length_min?.toDouble()!!
        return temp/2
    }

    fun getGeoList(): List<String>{
        return _animal.value?.geo_range?.split(",","and")!!.toList()
    }

}