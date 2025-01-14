package phonebook.dto

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

