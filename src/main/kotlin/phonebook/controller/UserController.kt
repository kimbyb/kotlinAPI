package phonebook.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import phonebook.dto.UserWithPhonebookRequest
import phonebook.service.UserService

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping
    fun createUserWithPhonebook(@RequestBody request: UserWithPhonebookRequest): ResponseEntity<String> {
        userService.createUserWithPhonebook(request)
        return ResponseEntity.ok("User and phonebook entries created successfully")
    }
}