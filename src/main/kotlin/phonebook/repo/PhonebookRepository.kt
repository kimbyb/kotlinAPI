package phonebook.repo

import org.springframework.data.jpa.repository.JpaRepository
import phonebook.entities.PhonebookEntry
import phonebook.entities.User

interface PhonebookEntryRepository : JpaRepository<PhonebookEntry, Long>

interface UserRepository : JpaRepository<User, Long>
