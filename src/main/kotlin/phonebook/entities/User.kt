package phonebook.entities

import jakarta.persistence.*


@Entity
@Table(name="users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val username: String = "",

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val phonebookEntries: List<PhonebookEntry> = mutableListOf()
)