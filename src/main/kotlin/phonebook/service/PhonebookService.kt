package phonebook.service

import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import phonebook.dto.PhonebookNumber
import phonebook.dto.PhonebookOfUser
import phonebook.entities.PhonebookEntity
import phonebook.repo.PhonebookEntryRepository
import phonebook.repo.UserRepository
import java.util.*

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

        val phonenumber = PhonebookEntity(
            name = request.name,
            phoneNumber = request.phoneNumber,
            user = user
        )

        phonebookEntryRepository.save(phonenumber)
    }

    @Transactional
    fun getAllPhonesByUserId(userId: Long): List<PhonebookEntity> {
        return phonebookEntryRepository.findAllByUserId(userId)
    }

    @Transactional
    fun updatePhonebookEntry(id: Long, updatedPhonebook: PhonebookNumber): PhonebookEntity {
        val existing = phonebookEntryRepository.findById(id).orElseThrow {
            throw IllegalArgumentException("Phonebook entry with ID $id not found")
        }

        existing.name = updatedPhonebook.name
        existing.phoneNumber = updatedPhonebook.phoneNumber

        return phonebookEntryRepository.save(existing)
    }

    @Transactional
    fun deletePhoneNumberById(id: Long): Boolean {
        val number = phonebookEntryRepository.findById(id).orElse(null) ?: return false
        phonebookEntryRepository.delete(number)
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