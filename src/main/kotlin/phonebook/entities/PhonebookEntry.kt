package phonebook.entities

import jakarta.persistence.*

@Entity
@Table(name = "phonebook")
data class PhonebookEntry(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String = "",

    @Column(nullable = false, unique = true)
    val phoneNumber: String = "",

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User? = null
)
