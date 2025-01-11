package phonebook.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import phonebook.dto.PhonebookEntryResponse
import phonebook.dto.PhonebookNumber
import phonebook.dto.PhonebookNumberUpdate
import phonebook.entities.Phonebook
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
    fun updatePhonebookEntry(id: Long, updatedPhonebook: PhonebookNumberUpdate): PhonebookEntryResponse {
        val existing = phonebookEntryRepository.findById(id).orElseThrow {
            IllegalArgumentException("Phonebook entry with ID $id not found")
        }
        val updatedEntity = existing.copy(
            name = updatedPhonebook.name,
            phoneNumber = updatedPhonebook.phoneNumber
        )
        return PhonebookEntryResponse(
            id = updatedEntity.id,
            name = updatedEntity.name,
            phoneNumber = updatedEntity.phoneNumber
        )
    }

    @Transactional
    fun deletePhoneNumberById(id: Long): Boolean {
        phonebookEntryRepository.delete(phonebookEntryRepository.findById(id).orElse(null) ?: return false)
        return true
    }

    fun searchByUsername(username: String): List<Map<String, Any?>> {
        val entries = phonebookEntryRepository.findByUsername(username)
        return entries.map { entry ->
            mapOf(
                "id" to entry.id,
                "name" to (entry.user?.username),
                "phonebook" to listOf(
                    mapOf(
                        "name" to entry.name,
                        "phoneNumber" to entry.phoneNumber
                    )
                )
            )
        }
    }

    fun searchByPhoneNumber(phoneNumber: String): List<Map<String, Any>> {
        val entries = phonebookEntryRepository.findByPhoneNumber(phoneNumber)
        return entries.map { entry ->
            mapOf(
                "id" to entry.id,
                "name" to entry.name,
                "phoneNumber" to entry.phoneNumber
            )
        }
    }

}