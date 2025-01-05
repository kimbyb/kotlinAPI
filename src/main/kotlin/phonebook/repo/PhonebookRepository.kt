package phonebook.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import phonebook.entities.PhonebookEntity

interface PhonebookEntryRepository : JpaRepository<PhonebookEntity, Long> {

    @Query("SELECT p FROM PhonebookEntity p WHERE p.user.id = :userId")
    fun findAllByUserId(@Param("userId") userId: Long): List<PhonebookEntity>

    @Query("""
    SELECT p 
    FROM PhonebookEntity p 
    WHERE LOWER(p.user.username) LIKE LOWER(CONCAT('%', :username, '%'))
""")
    fun findByUsername(@Param("username") username: String): List<PhonebookEntity>


    @Query("""
    SELECT p 
    FROM PhonebookEntity p 
    WHERE p.phoneNumber LIKE %:phoneNumber%
""")
    fun findByPhoneNumber(@Param("phoneNumber") phoneNumber: String): List<PhonebookEntity>


}

