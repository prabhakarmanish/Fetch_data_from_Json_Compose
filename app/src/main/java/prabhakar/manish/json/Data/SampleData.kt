package prabhakar.manish.json.Data

import com.google.gson.annotations.SerializedName

data class SampleData(
    val name: String,
    val age: Int,
    val occupation: String,
    val address: Address,
    val skills: List<String>,
    @SerializedName("is_active") val isActive: Boolean,
    val projects: List<Project>
)
