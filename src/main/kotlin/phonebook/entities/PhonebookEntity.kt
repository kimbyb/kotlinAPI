package phonebook.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
@Table(name = "phonebook")
data class PhonebookEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = false)
    var phoneNumber: String = "",

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    val user: UserEntity? = null
)  {
    @JsonProperty("username") // This adds `username` to JSON response
    fun getUsername(): String {
        return user?.username ?: ""
    }
}
