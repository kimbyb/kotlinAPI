package phonebook.dto

data class Phonebook(
    val id: Long,
    val name: String,
    val phoneNumber: String,
    val userName: String?
)

data class PhonebookEntry(
    val name: String,
    val phoneNumber: String,
    val username: String?
)

data class PhonebookNumber(
    val id: Long = 0,
    val name: String,
    val phoneNumber: String,
    val userId: Long
)

data class PhonebookNumberUpdate(
    val id: Long,
    val name: String,
    val phoneNumber: String
)

