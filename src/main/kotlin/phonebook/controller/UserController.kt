package phonebook.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import phonebook.entities.UserEntity
import phonebook.service.UserService

@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllUsers(): List<UserEntity> {
        return userService.getAllUsers()
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserEntity>? {
        val user = userService.getUserById(id)
        return user?.let {
            ResponseEntity.ok(user)
        } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
    }

    //TODO: make ability to add more than one at a time
    @PostMapping
    fun createUserWithPhonebook(@RequestBody request: UserEntity): ResponseEntity<String> {
        userService.createUserWithPhonebook(request)
        return ResponseEntity.ok("User ${request.username} with ${request.phonebookEntries.count()} phone numbers added.")
    }

    @PutMapping("/{id}")
    fun updateUsername(@PathVariable id: Long, @RequestBody updatedUsername: String): ResponseEntity<String> {
        if (updatedUsername.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Username can't be empty")
        }
        return try {
            userService.updateUsername(id, updatedUsername) // Now we don't store the result
            ResponseEntity.ok("User with ID $id has been updated with a new username: $updatedUsername")
        } catch (e: NoSuchElementException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
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