package phonebook.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import phonebook.dto.*
import phonebook.entities.Phonebook
import phonebook.service.PhonebookService

@RestController
@RequestMapping("/api/phonebook")
class PhonebookController(private val phonebookService: PhonebookService) {

    @PostMapping("/addPhoneNumber")
    fun addPhonenumberToUser(@RequestBody request: PhonebookNumber): ResponseEntity<String> {
        return try {
            phonebookService.addPhonenumberToUser(request)
            ResponseEntity.ok("Phone number ${request.phoneNumber} added to user ID ${request.userId}")
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        }
    }

    @GetMapping("/{id}")
    fun getAllPhonesByUserId(@PathVariable id: Long): ResponseEntity<List<phonebook.dto.Phonebook>> {
        val numbers = phonebookService.getAllPhonesByUserId(id)
        return if (numbers.isNotEmpty()) {
            val response = numbers.map { phonebookEntity ->
                phonebook.dto.Phonebook(
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

    @GetMapping("/searchByUsername")
    fun searchByUsername(@RequestParam username: String): List<Phonebook> {
        val response = phonebookService.searchByUsername(username)
        return response
    }

    @GetMapping("/search")
    fun searchByPhoneNumber(@RequestParam phoneNumber: String): ResponseEntity<List<Phonebook>> {
        val results = phonebookService.searchByPhoneNumber(phoneNumber)
        return if (results.isNotEmpty()) {
            ResponseEntity.ok(results)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(emptyList())
        }
    }

    @PutMapping("/{id}")
    fun updatePhonebookEntry(
        @PathVariable id: Long,
        @RequestBody updatedPhonebook: PhonebookNumberUpdate
    ): ResponseEntity<Phonebook> {
        return try {
            val updatedEntry = phonebookService.updatePhonebookEntry(id, updatedPhonebook)
            ResponseEntity.ok(updatedEntry)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

    @DeleteMapping("/{id}")
    fun deletePhonenumberById(@PathVariable id: Long): ResponseEntity<String> {
        return if (phonebookService.deletePhoneNumberById(id)) {
            ResponseEntity.ok("Phonenumber with id $id was deleted")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Phone number with id $id not found")
        }
    }
}


