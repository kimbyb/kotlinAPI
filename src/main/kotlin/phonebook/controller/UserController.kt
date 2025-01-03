package phonebook.controller

import org.apache.catalina.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import phonebook.dto.UserResponse
import phonebook.dto.UserWithPhonebookRequest
import phonebook.service.UserService

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {


    @GetMapping
    fun getAllEntries(): List<UserResponse> {
        return userService.getAllUsersWithPhonebooks()

    }

    @PostMapping
    fun createUserWithPhonebook(@RequestBody request: UserWithPhonebookRequest): ResponseEntity<String> {
        userService.createUserWithPhonebook(request)
        return ResponseEntity.ok("User ${request.username} with ${request.phonebookEntries.count()} phonenumbers added ")
    }
}