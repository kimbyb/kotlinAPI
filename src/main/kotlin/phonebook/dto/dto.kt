package phonebook.dto

data class UserWithPhonebookRequest(
    val username: String,
    val phonebookEntries: List<PhonebookEntryRequest>
)

data class PhonebookEntryRequest(
    val name: String,
    val phoneNumber: String
)
