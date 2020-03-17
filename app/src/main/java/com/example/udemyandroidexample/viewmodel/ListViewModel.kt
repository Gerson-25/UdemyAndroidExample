package com.example.udemyandroidexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.udemyandroidexample.model.DogBreed

class ListViewModel:ViewModel() {

    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        val dog1 = DogBreed(breedId = "1", dogBreed = "Corgi", lifeSpan = "15 years", breedGroup = "breedGroup", breedFor = "breedFor",imageUrl = "", temperament = "temperament")
        val dog2 = DogBreed(breedId = "2", dogBreed = "Labrador", lifeSpan = "25 years", breedGroup = "breedGroup", breedFor = "breedFor",imageUrl = "", temperament = "temperament")
        val dog3 = DogBreed(breedId = "3", dogBreed = "Rotwailer", lifeSpan = "20 years", breedGroup = "breedGroup", breedFor = "breedFor",imageUrl = "", temperament = "temperament")
        val doglist = arrayListOf<DogBreed>(dog1, dog2, dog3)

        dogs.value = doglist
        dogsLoadError.value = false
        loading.value = false
    }
}