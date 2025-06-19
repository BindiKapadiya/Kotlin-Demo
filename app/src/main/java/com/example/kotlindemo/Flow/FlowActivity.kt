package com.example.kotlindemo.Flow

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.kotlindemo.BaseActivity
import com.example.kotlindemo.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlin.time.measureTime

class FlowActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flow)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // TODO Cold stream - Doesn't emit anything until there's a collector - Like Netflix, all the consumer get output from starting individually
        // TODO Hot stream(Shared Flows) - Keep the latest value in the memory and emits it to new collector - Like Movie, all the consumer get same output at a same time, Produce data is lost before consumer join.

        // Cold Flow
//        integerFlow()
//        notesFlow()

        // Hot Flow
//        sharedFlow()
        stateFlow()

        SearchDataFromApi()

    }

    private fun integerFlow() {
        val job = GlobalScope.launch(Dispatchers.Main) {
            // TODO terminal operators - suspend function - we need terminal operator to start flow

            // TODO first
//            val first = producerInteger().first()
//            Log.e(TAG, "First - $first")

            // TODO toList
//            val toList: List<Int> = producerInteger().toList()
//            Log.e(TAG, "toList - ${toList.toString()}")

            // TODO non terminal operators
            val time = measureTime {

                try {
                    // Item will not emit before consumer collect data
                    val producer: Flow<Int> = producerInteger()
                    producer
                        .onStart {  // call when start producing value
                            Log.e(TAG, "Starting out")
                            emit(-1)
                        }
                        .onCompletion { // call when producer is completed
                            emit(11)
                            Log.e(TAG, "Completed")
                        }
                        .onEach {   // call for each item before emit function call
                            Log.e(TAG, "About to emit - $it")
                        }
                        .map {  // Convert one object to another object - form data to another form
                            delay(1000)
                            it * 2
//                            Log.e(TAG, "Map Thread - ${Thread.currentThread().name}")
                        }
                        .flowOn(Dispatchers.IO) // Upstream - all the above operators run on IO thread
                        .filter {   // Filter item
//                            Log.e(TAG, "Filter Thread - ${Thread.currentThread().name}")
                            delay(500)
                            it < 8
                        }
                        .buffer(3)
                        .flowOn(Dispatchers.Main)   // All the above operators run on Main thread
                        .collect {  // Consume items
                            delay(1500) // producer take less time but in case consumer take more to process data then use buffer to store produced data
                            Log.e(TAG, "val --------------------------- $it")
//                            Log.e(TAG, "Collector Thread - ${Thread.currentThread().name}")
//                            throw Exception("Error in collector")
                        }
                } catch (e: Exception) {
                    Log.e(TAG, "Exception - ${e.message}")
                }
            }
            Log.e(TAG, "Measure Time: ${time.toString()}")
        }

        // TODO Flow is automatically stop producing value when job is Cancel
//        GlobalScope.launch {
//            delay(4500)
//            job.cancel()
//        }

        // TODO Value will be receive from starting point even if producer is already generating values
//        GlobalScope.launch {
//            val producer: Flow<Int> = producer()
//            delay(3000)
//            producer.collect {
//                Log.e(TAG, "----------- " + it.toString())
//            }
//        }
    }

    fun producerInteger() = flow<Int> {
        val list = listOf(1, 2, 3, 4, 5) //, 6, 7, 8, 9, 10)
        list.forEach {
            delay(1000)
            emit(it)
//            Log.e(TAG, "Producer Thread - ${Thread.currentThread().name}")
            throw Exception("Error in Emitter")
        }
    }.catch {
        Log.e(TAG, "Catch - ${it.message}")
        emit(-2)    // we can pass callback from catch block
    }

    private fun sharedFlow() {
        GlobalScope.launch(Dispatchers.IO) {
            val result =
                produceSharedFlow()    // Item is emitted even if consumer collecting or not
//            delay(6000) // It will return last 2 value because replay=2
            result.collect {
                Log.e(TAG, "collect: $it")
            }

            result.collectLatest {
                Log.e(TAG, "collectLatest: $it")
            }
        }
//        GlobalScope.launch(Dispatchers.IO) {
//            val produceSharedFlow = produceSharedFlow()
//            delay(3000)
//            produceSharedFlow.collect {
//                Log.e(TAG, "sharedFlow2: $it")
//            }
//        }
    }

    // TODO We return Flow<Int> object - we don't allow other to change out value, basically do not provide other to emit value, value can be emit only from the particular function
    fun produceSharedFlow(): Flow<Int> {
        val mutableSharedFlow =
            MutableSharedFlow<Int>(replay = 0)   // replay - last 2 item will be saved
        GlobalScope.launch {
            val numbers = listOf<Int>(1, 2, 3, 4, 5)
            numbers.forEach {
                mutableSharedFlow.emit(it)
                Log.e(TAG, "Emitting: $it")
                delay(1000)
            }
        }
        return mutableSharedFlow
    }

    // TODO Just like sharedFlow - here we have multiple consumer, It maintain a state, it will retain last value from the flow, as a buffer last value will be saved
    private fun stateFlow() {
        GlobalScope.launch {
            val stateFlow: StateFlow<Int> = produceStateFlow()
            Log.e(TAG, "default or last value - ${stateFlow.value.toString()}")

            delay(6000) // It will return last item - stored in state
            stateFlow.collect {
                Log.e(TAG, "stateFlow: $it")
            }
        }
    }

    private fun produceStateFlow(): StateFlow<Int> {
        val mutableStateFlow = MutableStateFlow(value = 10)
        GlobalScope.launch {
            delay(2000)
            mutableStateFlow.emit(20)
            delay(2000)
            mutableStateFlow.emit(30)
        }
        return mutableStateFlow
    }


    private fun notesFlow() {
        CoroutineScope(Dispatchers.Main).launch {
            getNotes()
                .map {
                    FormattedNote(it.isActive, it.title.uppercase(), it.description)
                }
                .filter {
                    it.isActive
                }
                .collect {
                    Log.e(TAG, "${it.toString()}")
                }
        }
    }

    private fun getNotes(): Flow<Note> {
        val noteList = listOf(
            Note(1, true, "First", "First Description"),
            Note(2, true, "Second", "Second Description"),
            Note(3, false, "Third", "Third Description")
        )
        return noteList.asFlow()
    }

    data class Note(val id: Int, val isActive: Boolean, val title: String, val description: String)
    data class FormattedNote(val isActive: Boolean, val title: String, val description: String)


    fun SearchDataFromApi() {
        val text = MutableStateFlow("")
        val etSearch = findViewById<EditText>(R.id.etSearch)

        lifecycleScope.launch {
            text
                .debounce(5000) // Wait for a pause in emissions. Useful for search bars.
                .filter { it.isNotEmpty() }
                .distinctUntilChanged() // Skip consecutive duplicates
                .onEach {
                    Log.e(TAG, "Search query: $it")
                }
                .collect {
                    callApi(text.value)
                }
        }

//        searchQuery
//            .debounce(5000) // Wait for 5 seconds of no input
//            .filter { it.isNotEmpty() }
//            .collect { query ->
//                callApi(query)
//            }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                text.value = s.toString()
                Log.e(TAG, "afterTextChanged: $s")
            }
        })
    }

    fun callApi(query: String) {
        // Make your API call here
        Log.e(TAG, "Calling API for query: $query")
    }
}