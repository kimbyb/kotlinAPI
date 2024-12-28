package phonebook


import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class PhonebookControllerTest {

    private val mockService: PhonebookService = mock(PhonebookService::class.java)
    private val controller = PhonebookController(mockService)

    @Test
    fun `test get all entries`() {
        val mockEntries = listOf(
            PhonebookEntry(id = 1, name = "Peter Poo", phoneNumber = "123124"),
            PhonebookEntry(id = 2, name = "asd asd", phoneNumber = "35676")
        )
        `when`(mockService.getAllEnries()).thenReturn(mockEntries)

        val response = controller.getAllEntries()

        assertEquals(200, response.statusCodeValue)
        assertEquals(mockEntries, response.body)
    }

    @Test
    fun `test update entry`() {
        val existingEntry = PhonebookEntry(id = 1, name = "John Damn", phoneNumber = "1234345")
        val updatedEntry = PhonebookEntry(id = 1, name = "John Smart", phoneNumber = "6578")
        `when`(mockService.getEntryById(1L)).thenReturn(existingEntry)
        `when`(mockService.saveEntry(updatedEntry)).thenReturn(updatedEntry)

        val response = controller.updateEntry(1L, updatedEntry)

        assertEquals(200, response.statusCodeValue)
        assertEquals(updatedEntry, response.body)
    }

    @Test
    fun `test entry not found`() {
        `when`(mockService.getEntryById(99L)).thenReturn(null)

        val response = controller.updateEntry(99L, PhonebookEntry(name = "Test", phoneNumber = "1212"))

        assertEquals(404, response.statusCodeValue)
    }
}