# Flutter Audio Player

A cross-platform Flutter audio player with a glass-style UI, local library import, playlists, and background/notification playback controls.

## Platforms
- ✅ Android
- ✅ iOS
- ✅ Web
- ✅ Windows
- ✅ macOS
- ✅ Linux

> Note: Background playback + media notification controls are implemented via `audio_service` (Android notification channel configured). Platform-specific background behavior may require additional setup on iOS/desktop.

## Features
- 🎵 Play / pause / seek + position & duration tracking
- ⏭️ Next / previous track
- 🔁 Playback modes: order / repeat all / repeat one / shuffle
- 📂 Import audio files and folders (via `file_picker`)
- 🏷️ Metadata parsing (title/artist/album/artwork, lyrics when available)
- 🗂️ Playlists + local persistence (Hive)
- 🎨 Themes & backgrounds

## Tech Stack
- Flutter
- `audioplayers` for playback
- `audio_service` for media session / background controls
- `hive_flutter` for local storage
- `flutter_media_metadata` + `dart_tags` for metadata/tags

## Try the app (APK)
If you just want to test the app without building it locally, you can download the Android APK here:
https://drive.google.com/file/d/1teSIfJoSoTt0Dr1jT3n45P4VI-SUIzkb/view?usp=sharing

> Note: On some devices you may need to allow installing apps from unknown sources.
