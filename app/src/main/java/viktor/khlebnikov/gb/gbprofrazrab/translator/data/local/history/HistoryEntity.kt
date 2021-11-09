package viktor.khlebnikov.gb.gbprofrazrab.translator.data.local.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.AppState
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataModel

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