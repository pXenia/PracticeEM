package com.example.practiceem.practice3.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.practiceem.practice3.model.Currency
import com.example.practiceem.practice3.repository.DiscountRepository1
import com.example.practiceem.practice3.repository.DiscountRepository2
import com.example.practiceem.practice3.repository.RepositoryProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel() {
    private val repository = RepositoryProvider.provideSearchRepository()
    private val compositeDisposable = CompositeDisposable()

    private val _currencies = BehaviorSubject.create<List<Currency>>()
    val currencies: Observable<List<Currency>> = _currencies.hide()

    private val _selectedItemPosition = PublishSubject.create<Int>()
    val selectedItemPosition: Observable<Int> = _selectedItemPosition.hide()

    private val _textInput = PublishSubject.create<String>()

    init {
        loadCardsWithZip()
        loadCardsWithMerge()
    }

    fun getCurrency() {
        compositeDisposable.add(
            repository.getCurrency()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        _currencies.onNext(result.valute.values.toList())
                    },
                    { error ->
                        Log.e("Currency", "Ошибка при загрузке валют: ${error.message}")
                    }
                )
        )
    }

    fun onItemSelected(position: Int) {
        _selectedItemPosition.onNext(position)
    }

    fun onTextChanged(text: String) {
        _textInput.onNext(text)
    }

    fun observeTextChanges(): Observable<String> {
        return _textInput.debounce(3, TimeUnit.SECONDS).distinctUntilChanged()
    }

    // Если 1 из запросов падает, то все равно выводить
    fun loadCardsWithMerge() {
        compositeDisposable.add(
            Observable.merge(
                DiscountRepository1.getCards().onErrorReturn { emptyList() },
                DiscountRepository2.getCards().onErrorReturn { emptyList() }
            ).toList()
                .subscribe(
                    { cards ->
                        Log.d("DiscountCards", "Список карт (Merge): ${cards.flatten().joinToString()}")
                    },
                    { error ->
                        Log.e("DiscountCards", "Ошибка при загрузке карт (Merge): ${error.message}")
                    }
                )
        )
    }

    // Если 1 из запросов падает, то не выводить ничего
    fun loadCardsWithZip() {
        compositeDisposable.add(
            Observable.zip(
                DiscountRepository1.getCards(), DiscountRepository2.getCards()
            ) { list1, list2 -> list1 + list2 }
                .subscribe(
                    { cards ->
                        Log.d("DiscountCards", "Список карт (Zip): ${cards.joinToString()}")
                    },
                    { error ->
                        Log.e("DiscountCards", "Ошибка при загрузке карт (Zip): ${error.message}")
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}