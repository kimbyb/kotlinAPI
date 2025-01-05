package phonebook.dto

data class PhonebookOfUser(
    val id: Long,
    val name: String,
    val phoneNumber: String,
    val userName: String?
)

data class PhonebookNumber(
    val id: Long = 0,
    val name: String,
    val phoneNumber: String,
    val userId: Long
)

data class UpdatePhonebookRequest(
    val name: String,
    val phoneNumber: String
)
