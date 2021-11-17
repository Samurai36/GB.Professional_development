package viktor.khlebnikov.gb.model.dto

import com.google.gson.annotations.SerializedName

data class MeaningsDTO(
    @field:SerializedName("translation")
    val translation: TranslationDTO?,

    @field:SerializedName("imageUrl")
    val imageUrl: String?
)