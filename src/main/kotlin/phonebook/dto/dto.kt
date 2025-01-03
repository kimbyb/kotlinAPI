package phonebook.dto

data class UserWithPhonebookRequest(
    val username: String,
    val phonebookEntries: List<PhonebookEntryRequest>
)

data class PhonebookEntryRequest(
    val name: String,
    val phoneNumber: String
)

data class UserResponse(
    val id: Long,
    val username: String,
    val phonebookEntries: List<PhonebookEntryResponse>
)

data class PhonebookEntryResponse(
    val name: String,
    val phoneNumber: String
)