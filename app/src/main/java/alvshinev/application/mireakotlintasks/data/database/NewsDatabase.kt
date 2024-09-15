package alvshinev.application.mireakotlintasks.data.database

import alvshinev.application.mireakotlintasks.data.database.dao.ArticlesDao
import alvshinev.application.mireakotlintasks.data.database.models.ArticleDBO
import alvshinev.application.mireakotlintasks.data.database.utils.Converters
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [ArticleDBO::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun articlesDao(): ArticlesDao

}

fun NewsDatabase(applicationContext: Context): NewsDatabase {
    val newsDatabase = Room.databaseBuilder(
        checkNotNull(applicationContext.applicationContext),
        NewsDatabase::class.java,
        "news"
    ).build()
    return newsDatabase
}
