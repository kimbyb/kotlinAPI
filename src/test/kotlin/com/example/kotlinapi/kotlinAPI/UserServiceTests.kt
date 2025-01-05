package com.example.kotlinapi.kotlinAPI

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.*
import phonebook.dto.AllUsersResponse
import phonebook.entities.UserEntity
import phonebook.repo.PhonebookEntryRepository
import phonebook.repo.UserRepository
import phonebook.service.UserService

class UserServiceTest {

    private val userRepository: UserRepository = mock(UserRepository::class.java)
    private val phonebookEntryRepository: PhonebookEntryRepository = mock(PhonebookEntryRepository::class.java)
    private val userService = UserService(userRepository, phonebookEntryRepository)

    @Test
    fun `getAllUsers returns a list of all users`() {

        val mockUsers = listOf(
            UserEntity(id = 1, username = "John"),
            UserEntity(id = 2, username = "Jane")
        )
        whenever(userRepository.findAll()).thenReturn(mockUsers)

        val result: List<AllUsersResponse> = userService.getAllUsers()

        assertEquals(2, result.size)
        assertEquals("John", result[0].username)
        assertEquals("Jane", result[1].username)
    }

    @Test
    fun `getUserById returns user when found`() {

        val mockUser = UserEntity(id = 1, username = "John")
        whenever(userRepository.findById(1)).thenReturn(java.util.Optional.of(mockUser))

        val result = userService.getUserById(1)

        assertNotNull(result)
        assertEquals("John", result?.username)
    }

    @Test
    fun `getUserById returns null when user not found`() {

        whenever(userRepository.findById(1)).thenReturn(java.util.Optional.empty())

        val result = userService.getUserById(1)

        assertNull(result)
    }

    @Test
    fun `updateUsername updates the username`() {

        whenever(userRepository.findById(1))
    }
}
