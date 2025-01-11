package phonebook.repo

import org.springframework.data.jpa.repository.JpaRepository
import phonebook.entities.User

interface UserRepository : JpaRepository<User, Long>