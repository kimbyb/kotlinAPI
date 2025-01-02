//package phonebook.controller
//
//import org.springframework.http.HttpStatus
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.*
//import phonebook.entities.PhonebookEntry
//import phonebook.service.PhonebookService
//
//@RestController
//@RequestMapping("/api/phonebook")
//class PhonebookController(private val phonebookService : PhonebookService) {
//
//
//    @GetMapping
//    fun getAllEntries(): ResponseEntity<List<PhonebookEntry>> {
//        val entries = phonebookService.getAllEnries()
//        return ResponseEntity.ok(entries)
//    }
//
//    @GetMapping("/{id}")
//    fun getEntryById(@PathVariable id: Long): ResponseEntity<PhonebookEntry> {
//        val entry = phonebookService.getEntryById(id)
//        return if (entry != null) ResponseEntity.ok(entry) else ResponseEntity.notFound().build()
//    }
//
//    @PostMapping
//    fun createEntry(@RequestBody entry: PhonebookEntry): ResponseEntity<PhonebookEntry> {
//        val newEntry = phonebookService.saveEntry(entry)
//        return ResponseEntity.status(HttpStatus.CREATED).body(newEntry)
//    }
//
//    @PutMapping("/{id}")
//    fun updateEntry(@PathVariable id: Long,  @RequestBody updatedEntry: PhonebookEntry): ResponseEntity<PhonebookEntry> {
//        val existingEntry = phonebookService.getEntryById(id)
//        return if (existingEntry != null) {
//            val entryToSave = updatedEntry.copy(id = existingEntry.id)
//            val savedEntry = phonebookService.saveEntry(entryToSave)
//            ResponseEntity.ok(savedEntry)
//        } else {
//            ResponseEntity.notFound().build()
//        }
//    }
//
//
//    @DeleteMapping("/{id}")
//    fun deleteEntry(@PathVariable id: Long): ResponseEntity<Void> {
//        return if (phonebookService.getEntryById(id) != null) {
//            phonebookService.deleteEntry(id)
//            ResponseEntity.noContent().build()
//        } else {
//            ResponseEntity.notFound().build()
//        }
//    }
//}
//
