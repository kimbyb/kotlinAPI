package phonebook.entities

import jakarta.persistence.*

@Entity
@Table(name = "phonebook")
data class Phonebook(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = false)
    var phoneNumber: String = "",

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User? = null
)
