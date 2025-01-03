package phonebook.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import phonebook.dto.PhonebookEntryResponse
import phonebook.dto.UserResponse
import phonebook.dto.UserWithPhonebookRequest
import phonebook.entities.PhonebookEntry
import phonebook.entities.User
import phonebook.repo.PhonebookEntryRepository
import phonebook.repo.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository,
    private val phonebookEntryRepository: PhonebookEntryRepository
) {


    @Transactional
    fun createUserWithPhonebook(request: UserWithPhonebookRequest) {
        val user = User(username = request.username)
        val savedUser = userRepository.save(user)

        val phonebookEntities = request.phonebookEntries.map { entry ->
            PhonebookEntry(
                name = entry.name,
                phoneNumber = entry.phoneNumber,
                user = savedUser
            )
        }
        phonebookEntryRepository.saveAll(phonebookEntities)
    }

    fun getAllUsersWithPhonebooks(): List<UserResponse> {
        return userRepository.findAll().map { user ->
            UserResponse(
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
  }