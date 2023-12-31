package com.example.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel



const val CURRENT_INDEX_KEY = "com.example.geoquiz.CURRENT_INDEX_KEY"
const val IS_CHEATER_KEY = "com.example.geoquiz.IS_CHEATER_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {


    private var currentIndex: Int
        get() = savedStateHandle[CURRENT_INDEX_KEY] ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)


    private val questionBank = mutableListOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    var isCheater: Boolean
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val currentQuestionStatus: Boolean
        get() = questionBank[currentIndex].cheated

    fun updateCheatStatus() {
        questionBank[currentIndex] = questionBank[currentIndex].copy(cheated = true)
        isCheater = questionBank[currentIndex].cheated
    }

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious() {
        if (currentIndex > 0) {
            currentIndex--
        } else {
            currentIndex = questionBank.lastIndex
        }
    }
}