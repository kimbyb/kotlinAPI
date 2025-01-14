package phonebook.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import phonebook.dto.*
import phonebook.entities.Phonebook
import phonebook.entities.User
import phonebook.repo.PhonebookEntryRepository
import phonebook.repo.UserRepository

@Service
class PhonebookService(
    private val userRepository: UserRepository,
    private val phonebookEntryRepository: PhonebookEntryRepository
) {

    @Transactional
    fun addPhonenumberToUser(request: PhonebookNumber) {
        val user = userRepository.findById(request.userId).orElseThrow {
            throw IllegalArgumentException("User with ID ${request.userId} not found")
        }
        val phonenumber = Phonebook(
            name = request.name,
            phoneNumber = request.phoneNumber,
            user = user
        )
        phonebookEntryRepository.save(phonenumber)
    }

    fun getAllPhonesByUserId(userId: Long): List<Phonebook> {
        return phonebookEntryRepository.findAllByUserId(userId)
    }

    @Transactional
    fun updatePhonebookEntry(id: Long, updatedPhonebook: PhonebookNumberUpdate): Phonebook {
        val existing = phonebookEntryRepository.findById(id).orElseThrow {
            IllegalArgumentException("Phonebook entry with ID $id not found")
        }
        existing.name = updatedPhonebook.name
        existing.phoneNumber = updatedPhonebook.phoneNumber

        return existing
    }

    @Transactional
    fun deletePhoneNumberById(id: Long): Boolean {
        phonebookEntryRepository.delete(phonebookEntryRepository.findById(id).orElse(null) ?: return false)
        return true
    }

    fun searchByUsername(username: String): List<Phonebook> {
        return phonebookEntryRepository.findByUserUsernameContainingIgnoreCase(username)
    }

    fun searchByPhoneNumber(phoneNumber: String): List<Phonebook> {
        return phonebookEntryRepository.findByPhoneNumber(phoneNumber)
    }

}