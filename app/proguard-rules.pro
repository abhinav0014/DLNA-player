# ── Google Cast SDK ────────────────────────────────────────────────────────────
-keep class com.google.android.gms.cast.** { *; }
-keep class com.google.android.gms.cast.framework.** { *; }
-dontwarn com.google.android.gms.cast.**

# UPDATED: Points to your new package
-keep class com.dlna.cast.CastOptionsProvider { *; }

# ── jUPnP / Cling / DLNA ───────────────────────────────────────────────────────
# Since you are using DM-UPnP-Android (Cling fork), we need to keep org.fourthline
-keep class org.fourthline.cling.** { *; }
-keep class org.jupnp.** { *; }
-dontwarn org.fourthline.cling.**
-dontwarn org.jupnp.**
-dontwarn org.slf4j.**

# ── ExoPlayer / Media3 ─────────────────────────────────────────────────────────
-keep class androidx.media3.** { *; }
-dontwarn androidx.media3.**

# Retain custom FFMPEG decoder renderer
-keep class com.google.android.exoplayer2.ext.ffmpeg.** { *; }

# ── Room ───────────────────────────────────────────────────────────────────────
-keep class * extends androidx.room.RoomDatabase { *; }
-keep @androidx.room.Entity class * { *; }
-keep @androidx.room.Dao interface * { *; }

# ── Gson / Retrofit ────────────────────────────────────────────────────────────
-keep class com.google.gson.** { *; }
-keepattributes Signature
-keepattributes *Annotation*
# UPDATED: Points to your new data models
-keep class com.dlna.data.model.** { *; }

# ── Hilt ───────────────────────────────────────────────────────────────────────
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-dontwarn dagger.hilt.**

# UPDATED: Points to your specific ViewModels
-keep class com.dlna.viewmodel.** { *; }

# ── General App Maintenance ──────────────────────────────────────────────────
# Prevent ProGuard from stripping your MainActivity which the Manifest needs
-keep class com.dlna.MainActivity { *; }
