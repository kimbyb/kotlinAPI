package phonebook.service

import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import phonebook.dto.PhonebookOfUser
import phonebook.entities.PhonebookEntity
import phonebook.repo.PhonebookEntryRepository
import phonebook.repo.UserRepository
import java.util.*

@Service
class PhonebookService(
    private val userRepository: UserRepository,
    private val phonebookEntryRepository: PhonebookEntryRepository
) {

    @Transactional
    fun getAllPhonesByUserId(userId: Long): List<PhonebookEntity> {
        return phonebookEntryRepository.findAllByUserId(userId)
    }
}