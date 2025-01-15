package phonebook.service

import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import phonebook.entities.Phonebook
import phonebook.entities.User
import phonebook.repo.PhonebookEntryRepository
import phonebook.repo.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository,
    private val phonebookEntryRepository: PhonebookEntryRepository
) {

    @Transactional
    fun createUserWithPhonebook(request: User) {
        val user = User(username = request.username)
        val savedUser = userRepository.save(user)

        request.phonebookEntries.forEach { entry ->
            val phonebook = Phonebook(
                name = entry.name,
                phoneNumber = entry.phoneNumber,
                user = savedUser
            )
            phonebookEntryRepository.save(phonebook)
        }
    }

    fun getAllUsers(): List<User> {
        return userRepository.findAll().map {user ->
            User(
                id = user.id,
                username = user.username
            )
        }
    }

    fun getUserById(id: Long): User? {
        return userRepository.findByIdOrNull(id)
    }

    fun getAllUsersWithPhonebooks(): List<User> {
        return userRepository.findAll()
    }

    @Transactional
    fun updateUsername(id: Long, newUsername: String): Boolean {
        val user = userRepository.findById(id).orElse(null) ?: return false
        val updatedUser = user.copy(username = newUsername)
        userRepository.save(updatedUser)
        return true
    }

    @Transactional
    fun deleteUserById(id: Long): Boolean {
        val user = userRepository.findById(id).orElse(null) ?: return false
        userRepository.delete(user)
        return true
    }

}