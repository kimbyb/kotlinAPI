package phonebook.repo

import org.springframework.data.jpa.repository.JpaRepository
import phonebook.entities.PhonebookEntity

interface PhonebookEntryRepository : JpaRepository<PhonebookEntity, Long>

