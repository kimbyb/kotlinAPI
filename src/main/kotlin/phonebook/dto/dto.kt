package phonebook.dto

data class UserWithPhonebookRequest(
    val username: String,
    val phonebookEntries: List<PhonebookEntryRequest>
)

data class PhonebookEntryRequest(
    val name: String,
    val phoneNumber: String
)

data class UserWithPhonebookResponse(
    val id: Long,
    val username: String,
    val phonebookEntries: List<PhonebookEntryResponse>
)


data class UserWithoutPhonebookResponse(
    val id: Long,
    val username: String
)

data class AllUsersResponse(
    val id: Long,
    val username: String
)

data class PhonebookEntryResponse(
    val name: String,
    val phoneNumber: String
)