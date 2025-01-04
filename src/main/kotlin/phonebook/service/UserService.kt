package phonebook.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import phonebook.dto.AllUsersResponse
import phonebook.dto.PhonebookEntryResponse
import phonebook.dto.UserWithPhonebookResponse
import phonebook.dto.UserWithPhonebookRequest
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
    fun createUserWithPhonebook(request: UserWithPhonebookRequest) {
        val user = UserEntity(username = request.username)
        val savedUser = userRepository.save(user)

        val phonebookEntities = request.phonebookEntries.map { entry ->
            PhonebookEntity(
                name = entry.name,
                phoneNumber = entry.phoneNumber,
                user = savedUser
            )
        }
        phonebookEntryRepository.saveAll(phonebookEntities)
    }

    fun getAllUsers(): List<AllUsersResponse> {
        return userRepository.findAll().map {user ->
            AllUsersResponse(
                id = user.id,
                username = user.username
            )
        }
    }

    fun getAllUsersWithPhonebooks(): List<UserWithPhonebookResponse> {
        return userRepository.findAll().map { user ->
            UserWithPhonebookResponse(
                id = user.id,
                username = user.username,
                phonebookEntries = user.phonebookEntries.map { entry ->
                    PhonebookEntryResponse(
                        name = entry.name,
                        phoneNumber = entry.phoneNumber
                    )
                }
            )
        }
    }

    @Transactional
    fun updateUsername(id: Long, newUsername: String): Boolean {
        val user = userRepository.findById(id).orElse(null) ?: return false
        val updatedUser = user.copy(username = newUsername)
        userRepository.save(updatedUser)
        return true
    }

    @Transactional
    fun deleteUserById(id: Long): Boolean {
        val user = userRepository.findById(id).orElse(null) ?: return false
        userRepository.delete(user)
        return true
    }

}