package phonebook.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import phonebook.entities.UserEntity
import phonebook.service.UserService

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllUsers(): List<UserEntity> {
        return userService.getAllUsers()
    }

    @GetMapping("/all")
    fun getAllUsersWithPhonebooks(): List<UserEntity> {
        return userService.getAllUsersWithPhonebooks()
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
        val updated = userService.updateUsername(id, updatedUsername.toString())
        return if (updated) {
            ResponseEntity.ok("User with ID $id has been updated with a new username: ${updatedUsername.toString()}")
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