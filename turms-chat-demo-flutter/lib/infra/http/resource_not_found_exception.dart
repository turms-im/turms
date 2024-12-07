class ResourceNotFoundException implements Exception {
  ResourceNotFoundException(this.uri);

  final String uri;
}
