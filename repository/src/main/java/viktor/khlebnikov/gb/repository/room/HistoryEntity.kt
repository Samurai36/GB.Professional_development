package viktor.khlebnikov.gb.repository.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["word"], unique = true)])
class HistoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "word")
    val word: String,

    @ColumnInfo(name = "description")
    val description: String?
) {
//    fun toDomainModel() = DataModel(
//        text = word,
//        meanings = null
//    )
//}
// fun AppState.toEntity() = when(this) {
//     is AppState.Success -> {
//         val searchResult = this.data
//         if (searchResult.isNullOrEmpty() || searchResult.first().text.isNullOrEmpty()) {
//             null
//         } else {
//             HistoryEntity(searchResult.first().text.orEmpty(), null)
//         }
//     }
//     else -> null
 }