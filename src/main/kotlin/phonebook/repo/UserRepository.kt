package phonebook.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import phonebook.entities.PhonebookEntity
import phonebook.entities.UserEntity

interface UserRepository : JpaRepository<UserEntity, Long>