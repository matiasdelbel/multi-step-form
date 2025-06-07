package com.matiasdelbel.multistepform

@OptIn(ExperimentalCoroutinesApi::class)
class FormViewModelTest {

    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var viewModel: FormViewModel

    @Before
    fun setup() {
        savedStateHandle = SavedStateHandle()
        viewModel = FormViewModel(savedStateHandle)
    }

    @Test
    fun `name and email are updated correctly`() = runTest {
        viewModel.onNameChanged("John")
        viewModel.onEmailChanged("john@example.com")

        val state = viewModel.formState.value
        assertEquals("John", state.name)
        assertEquals("john@example.com", state.email)
    }

    @Test
    fun `validateStep1 returns true for valid inputs`() = runTest {
        viewModel.onNameChanged("Jane")
        viewModel.onEmailChanged("jane@test.com")

        assertTrue(viewModel.validateStep1())
    }

    @Test
    fun `validateStep1 returns false for invalid email`() = runTest {
        viewModel.onNameChanged("Jane")
        viewModel.onEmailChanged("not-an-email")

        assertFalse(viewModel.validateStep1())
    }

    @Test
    fun `validateStep2 returns true when option is selected`() = runTest {
        viewModel.onOptionChanged("A")
        assertTrue(viewModel.validateStep2())
    }

    @Test
    fun `reset clears all form data`() = runTest {
        viewModel.onNameChanged("X")
        viewModel.onEmailChanged("x@x.com")
        viewModel.onOptionChanged("A")
        viewModel.onCommentChanged("Hello")
        viewModel.reset()

        val state = viewModel.formState.value
        assertEquals(FormState(), state)
    }
}
