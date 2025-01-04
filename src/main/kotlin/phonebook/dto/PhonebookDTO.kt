package phonebook.dto

data class PhonebookOfUser(
    val id: Long,
    val name: String,
    val phoneNumber: String,
    val userName: String?
)