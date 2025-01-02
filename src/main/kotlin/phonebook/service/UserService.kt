package phonebook.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
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
  }