package phonebook.repo

import org.springframework.data.jpa.repository.JpaRepository
import phonebook.dto.PhonebookEntry
import phonebook.dto.PhonebookEntryRequest
import phonebook.entities.Phonebook

interface PhonebookEntryRepository : JpaRepository<Phonebook, Long> {

    fun findAllByUserId(userId: Long): List<Phonebook>

    fun findByUserUsernameContainingIgnoreCase(username: String): List<Phonebook>

    fun findByPhoneNumber(phoneNumber: String): List<Phonebook>
}

