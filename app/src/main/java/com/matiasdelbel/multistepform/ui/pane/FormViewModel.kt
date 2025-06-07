package com.matiasdelbel.multistepform.ui.pane
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FormViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _formState = MutableStateFlow(
        savedStateHandle.get<FormState>("form") ?: FormState()
    )
    val formState: StateFlow<FormState> = _formState

    init {
        formState.collect {
            savedStateHandle["form"] = it
        }
    }

    fun onNameChanged(newName: String) {
        _formState.value = _formState.value.copy(name = newName)
    }

    fun onEmailChanged(newEmail: String) {
        _formState.value = _formState.value.copy(email = newEmail)
    }

    fun onOptionChanged(option: String) {
        _formState.value = _formState.value.copy(option = option)
    }

    fun onCommentChanged(comment: String) {
        _formState.value = _formState.value.copy(comment = comment)
    }

    fun validateStep1(): Boolean {
        val s = _formState.value
        return s.name.isNotBlank() && s.email.contains("@")
    }

    fun validateStep2(): Boolean {
        return _formState.value.option.isNotBlank()
    }

    fun reset() {
        _formState.value = FormState()
    }
}