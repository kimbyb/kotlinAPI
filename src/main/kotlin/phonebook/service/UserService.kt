package phonebook.service

import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import phonebook.entities.PhonebookEntity
import phonebook.entities.UserEntity
import phonebook.repo.PhonebookEntryRepository
import phonebook.repo.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository,
    private val phonebookEntryRepository: PhonebookEntryRepository
) {

    @Transactional
    fun createUserWithPhonebook(request: UserEntity) {
        val user = UserEntity(username = request.username)
        val savedUser = userRepository.save(user)

        val phonebookEntries = request.phonebookEntries.map { entry ->
            PhonebookEntity(
                name = entry.name,
                phoneNumber = entry.phoneNumber,
                user = savedUser
            )
        }
        phonebookEntryRepository.saveAll(phonebookEntries)
    }

    fun getAllUsers(): List<UserEntity> {
        return userRepository.findAll()
    }

    fun getUserById(id: Long): UserEntity? {
        return userRepository.findByIdOrNull(id)
    }

    @Transactional
    fun updateUsername(id: Long, newUsername: String) {
        val user = userRepository.findById(id).orElseThrow {
            throw NoSuchElementException("User with ID $id not found")
        }
        if (newUsername.isBlank()) {
            throw IllegalArgumentException("Username cannot be empty")
        }
        val updatedUser = user.copy(username = newUsername)
        userRepository.save(updatedUser)
    }

    @Transactional
    fun deleteUserById(id: Long): Boolean {
        val user = userRepository.findById(id).orElseThrow {
            throw NoSuchElementException("User with ID $id not found")
        }
        userRepository.delete(user)
        return true
    }
}