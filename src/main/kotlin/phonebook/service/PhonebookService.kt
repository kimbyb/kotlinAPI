package phonebook.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import phonebook.data.PhonebookEntry
import phonebook.data.PhonebookRepository

@Service
class PhonebookService(  @Autowired private val phonebookRepository: PhonebookRepository) {

    fun saveEntry(entry: PhonebookEntry): PhonebookEntry {
        return phonebookRepository.save(entry)
    }

    fun getAllEnries(): List<PhonebookEntry> {
        return  phonebookRepository.findAll()
    }

    fun getEntryById(id: Long): PhonebookEntry? {
        return  phonebookRepository.findById(id).orElse(null)
    }

    fun deleteEntry(id: Long) {
        phonebookRepository.deleteById(id)
    }
}
