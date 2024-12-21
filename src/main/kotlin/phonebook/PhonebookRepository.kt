package phonebook

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface PhonebookRepository : JpaRepository<PhonebookEntry, Long> {
}