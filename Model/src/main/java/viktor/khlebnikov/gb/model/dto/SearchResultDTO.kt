package viktor.khlebnikov.gb.model.dto

import com.google.gson.annotations.SerializedName

data class SearchResultDTO (
    @field:SerializedName("text")
    val text: String?,

    @field:SerializedName("meanings")
    val meanings: List<MeaningsDTO?>?
)