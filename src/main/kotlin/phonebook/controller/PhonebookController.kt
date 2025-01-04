package phonebook.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import phonebook.dto.PhonebookOfUser
import phonebook.service.PhonebookService

@RestController
@RequestMapping("/api/phonebook")
class PhonebookController(private val phonebookService: PhonebookService) {

    @GetMapping("/{id}")
    fun getAllPhonesByUserId(@PathVariable id: Long): ResponseEntity<List<PhonebookOfUser>> {
        val numbers = phonebookService.getAllPhonesByUserId(id)
        return if (numbers.isNotEmpty()) {
            val response = numbers.map { phonebookEntity ->
                PhonebookOfUser(
                    id = phonebookEntity.id,
                    name = phonebookEntity.name,
                    phoneNumber = phonebookEntity.phoneNumber,
                    userName = phonebookEntity.user?.username
                )
            }
            ResponseEntity.ok(response)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }
}


