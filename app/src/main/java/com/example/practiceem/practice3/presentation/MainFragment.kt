package com.example.practiceem.practice3.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practiceem.databinding.FragmentMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

/*
    Результат 1 задания - полученный список валют.
    В 3 задании используется для обработки нажатия тот же RecyclerView
*/
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: CurrencyAdapter
    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupCurrenciesObserver()
        setupCurrenciesItemPositionObserver()
        setupTextInputObserver()
        startTimer()
        viewModel.getCurrency()
    }

    private fun setupRecyclerView() {
        adapter = CurrencyAdapter { position ->
            viewModel.onItemSelected(position + 1)
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@MainFragment.adapter
        }
    }

    private fun setupCurrenciesObserver() {
        disposables.addAll(
            viewModel.currencies.observeOn(AndroidSchedulers.mainThread()).subscribe { currencies ->
                    adapter.submitList(currencies)
                })
    }

    private fun setupCurrenciesItemPositionObserver() {
        disposables.add(
            viewModel.selectedItemPosition.observeOn(AndroidSchedulers.mainThread())
                .subscribe { position ->
                    Toast.makeText(
                        requireContext(), "Позиция: $position", Toast.LENGTH_SHORT
                    ).show()
                })
    }

    private fun startTimer() {
        disposables.add(
            Observable.timer(1, TimeUnit.SECONDS).repeat()
            .scan(0L) { acc, _ -> acc + 1 }.observeOn(AndroidSchedulers.mainThread())
            .subscribe { seconds ->
                binding.tvTimer.text = seconds.toString()
            })
    }

    private fun setupTextInputObserver() {
        disposables.add(
            viewModel.observeTextChanges().observeOn(AndroidSchedulers.mainThread())
                .subscribe { text ->
                    Log.d("EditTextDebounce", "Текст: $text")
                })

        binding.et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.onTextChanged(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }
}