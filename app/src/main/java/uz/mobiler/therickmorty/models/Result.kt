package uz.mobiler.therickmorty.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Result :Serializable {

    var created: String? = null
    //var episode: List<String>?=null
    var gender: String? = null
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var image: String? = null
    //var location: Location?=null
    var name: String? = null
    //var origin: Origin?=null
    var species: String? = null
    var status: String? = null
    var type: String? = null
    var url: String? = null

    constructor()

    constructor(
        created: String?,
        gender: String?,
        id: Int?,
        image: String?,
        name: String?,
        species: String?,
        status: String?,
        type: String?,
        url: String?
    ) {
        this.created = created
        this.gender = gender
        this.id = id
        this.image = image
        this.name = name
        this.species = species
        this.status = status
        this.type = type
        this.url = url
    }

}