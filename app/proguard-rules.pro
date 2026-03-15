# Proguard Rules for Tasbeeh Counter

# Keep Room entities
-keep class com.tasbeeh.counter.data.database.** { *; }
-keep class com.tasbeeh.counter.domain.model.** { *; }

# Keep Compose
-dontwarn androidx.compose.**
