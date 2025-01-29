package phonebook.repo

import org.springframework.data.jpa.repository.JpaRepository
import phonebook.entities.PhonebookEntity

interface PhonebookEntryRepository : JpaRepository<PhonebookEntity, Long> {

    fun findAllByUserId(userId: Long): List<PhonebookEntity>

    fun findByUserUsernameContainingIgnoreCase(username: String): List<PhonebookEntity>

    fun findByPhoneNumber(phoneNumber: String): List<PhonebookEntity>

}

