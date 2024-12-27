class EnvVars {
  EnvVars._();

  static const String windowTitle = String.fromEnvironment('WINDOW_TITLE');
  static const bool databaseLogStatements =
      bool.fromEnvironment('DATABASE_LOG_STATEMENTS');
  static const bool secureStorage = bool.fromEnvironment('SECURE_STORAGE');
  static const bool showFocusTracker =
      bool.fromEnvironment('SHOW_FOCUS_TRACKER');
  static const int messageImageMaxDownloadableSizeBytes =
      int.fromEnvironment('MESSAGE_IMAGE_MAX_DOWNLOADABLE_SIZE_BYTES');
  static final double messageImageMaxCachedSizeWidth = double.parse(
      const String.fromEnvironment('MESSAGE_IMAGE_MAX_CACHED_SIZE_WIDTH'));
  static final double messageImageMaxCachedSizeHeight = double.parse(
      const String.fromEnvironment('MESSAGE_IMAGE_MAX_CACHED_SIZE_HEIGHT'));
  static final double messageImageThumbnailSizeWidth = double.parse(
      const String.fromEnvironment('MESSAGE_IMAGE_THUMBNAIL_SIZE_WIDTH'));
  static final double messageImageThumbnailSizeHeight = double.parse(
      const String.fromEnvironment('MESSAGE_IMAGE_THUMBNAIL_SIZE_HEIGHT'));
  static const String giphyApiKey = String.fromEnvironment('GIPHY_API_KEY');
}
