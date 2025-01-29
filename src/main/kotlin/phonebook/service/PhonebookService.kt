package phonebook.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import phonebook.entities.PhonebookEntity
import phonebook.repo.PhonebookEntryRepository
import phonebook.repo.UserRepository

@Service
class PhonebookService(
    private val userRepository: UserRepository,
    private val phonebookEntryRepository: PhonebookEntryRepository
) {

    @Transactional
    fun addPhonenumberToUser(request: PhonebookEntity) {
        val user = userRepository.findById(request.id).orElseThrow {
            throw IllegalArgumentException("User with ID ${request.id} not found")
        }
        val phoneNumber = PhonebookEntity(
            name = request.name,
            phoneNumber = request.phoneNumber,
            user = user
        )
        phonebookEntryRepository.save(phoneNumber)
    }

    fun getAllPhonesByUserId(userId: Long): List<PhonebookEntity> {
        return phonebookEntryRepository.findAllByUserId(userId)
    }

    @Transactional
    fun updatePhonebookEntry(id: Long, updatedPhonebook: PhonebookEntity): PhonebookEntity {
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

    fun searchByUsername(username: String): List<PhonebookEntity> {
        return phonebookEntryRepository.findByUserUsernameContainingIgnoreCase(username)
    }

    fun searchByPhoneNumber(phoneNumber: String): List<PhonebookEntity> {
        return phonebookEntryRepository.findByPhoneNumber(phoneNumber)
    }

}