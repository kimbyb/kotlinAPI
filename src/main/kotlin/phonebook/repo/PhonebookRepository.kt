package phonebook.repo

import org.springframework.data.jpa.repository.JpaRepository
import phonebook.entities.PhonebookEntry

interface PhonebookEntryRepository : JpaRepository<PhonebookEntry, Long>

