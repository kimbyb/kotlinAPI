package phonebook.repo

import org.springframework.data.jpa.repository.JpaRepository
import phonebook.entities.UserEntity

interface UserRepository : JpaRepository<UserEntity, Long>