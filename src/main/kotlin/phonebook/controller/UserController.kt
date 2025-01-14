package phonebook.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import phonebook.dto.AllUsersResponse
import phonebook.dto.UserWithPhonebookResponse
import phonebook.dto.UserWithPhonebookRequest
import phonebook.dto.UserWithoutPhonebookResponse
import phonebook.service.UserService

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllUsers(): List<AllUsersResponse> {
        return userService.getAllUsers()
    }

    @GetMapping("/all")
    fun getAllUsersWithPhonebooks(): List<UserWithPhonebookResponse> {
        return userService.getAllUsersWithPhonebooks()
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserWithoutPhonebookResponse>? {
        val user = userService.getUserById(id)
        return user?.let {
            val response = UserWithoutPhonebookResponse(
                id = user.id,
                username = user.username
            )
            ResponseEntity.ok(response)
        } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
    }

    //TODO: make ability to add more than one at a time
    @PostMapping
    fun createUserWithPhonebook(@RequestBody request: UserWithPhonebookRequest): ResponseEntity<String> {
        userService.createUserWithPhonebook(request)
        return ResponseEntity.ok("User ${request.username} with ${request.phonebookEntries.count()} phone numbers added.")
    }



    @PutMapping("/{id}")
    fun updateUsername(@PathVariable id: Long, @RequestBody updatedUsername: String): ResponseEntity<String> {
        val newUsername = updatedUsername
        if (newUsername.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Username can't be empty")
        }
        val updated = userService.updateUsername(id, newUsername)
        return if (updated) {
            ResponseEntity.ok("User with ID $id has been updated with a new username: $newUsername")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User with ID $id not found.")
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable id: Long): ResponseEntity<String> {
        return if (userService.deleteUserById(id)) {
            ResponseEntity.ok("User with ID $id and their phonebook have been deleted.")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID $id not found.")
        }
    }
}