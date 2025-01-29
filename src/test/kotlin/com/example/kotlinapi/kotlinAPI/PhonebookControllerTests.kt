package com.example.kotlinapi.kotlinAPI

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.http.HttpStatus
import phonebook.controller.PhonebookController
import phonebook.entities.PhonebookEntity
import phonebook.service.PhonebookService

class PhonebookEntityControllerTest {

    private val phonebookService = mock(PhonebookService::class.java)
    private val phonebookController = PhonebookController(phonebookService)

    @Test
    fun checkIfUserCanUpdatePhonebook() {
        val id = 1L
        val updatedPhonebook = PhonebookEntity(id, "123456789")
        `when`(phonebookService.updatePhonebookEntry(id, updatedPhonebook)).thenReturn(updatedPhonebook)

        val response = phonebookController.updatePhonebookEntry(id, updatedPhonebook)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(updatedPhonebook, response.body)
    }

    @Test
    fun userCanNotUpdateNonExistingPhonebook() {
        val id = 1L
        val updatedPhonebook = PhonebookEntity(id, "123456789")
        `when`(phonebookService.updatePhonebookEntry(id, updatedPhonebook)).thenThrow(IllegalArgumentException::class.java)

        val response = phonebookController.updatePhonebookEntry(id, updatedPhonebook)

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        assertNull(response.body)
    }

    @Test
    fun userCanDeletePhonebookById() {
        val id = 1L
        `when`(phonebookService.deletePhoneNumberById(id)).thenReturn(true)

        val response = phonebookController.deletePhonenumberById(id)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals("Phonenumber with id $id was deleted", response.body)
    }

    @Test
    fun userCanNotDeleteNonExisitngPhoneNumber() {
        val id = 1L
        `when`(phonebookService.deletePhoneNumberById(id)).thenReturn(false)

        val response = phonebookController.deletePhonenumberById(id)

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        assertEquals("Phone number with id $id not found", response.body)
    }
}