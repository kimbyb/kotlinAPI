package phonebook.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import phonebook.dto.PhonebookEntryResponse
import phonebook.dto.PhonebookNumber
import phonebook.dto.PhonebookNumberUpdate
import phonebook.dto.PhonebookOfUser
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


    @GetMapping("/searchByUsername")
    fun searchByUsername(@RequestParam username: String): ResponseEntity<List<Map<String, Any?>>> {
        val results = phonebookService.searchByUsername(username)
        return if (results.isNotEmpty()) {
            ResponseEntity.ok(results)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(emptyList())
        }
    }

    @GetMapping("/search")
    fun searchByPhoneNumber(@RequestParam phoneNumber: String): ResponseEntity<List<Map<String, Any>>> {
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
    ): ResponseEntity<PhonebookEntryResponse> {
        return try {
            val updatedEntry = phonebookService.updatePhonebookEntry(id, updatedPhonebook)
            ResponseEntity.ok(updatedEntry)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

    @DeleteMapping("/{id}")
    fun deletePhonenumberById(@PathVariable id: Long): ResponseEntity<String> {
        return if(phonebookService.deletePhoneNumberById(id)) {
            ResponseEntity.ok("Phonenumber with id $id was deleted")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Phone number with id $id not found")
        }
    }
}


