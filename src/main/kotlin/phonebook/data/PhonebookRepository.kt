package phonebook.data

import org.springframework.data.jpa.repository.JpaRepository

interface PhonebookRepository : JpaRepository<PhonebookEntry, Long> {
}