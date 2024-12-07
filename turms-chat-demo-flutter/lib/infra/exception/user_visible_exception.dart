class UserVisibleException<T> implements Exception {
  UserVisibleException(this.cause, this.messageProvider);

  final T cause;
  final String Function(T cause) messageProvider;

  String get message => messageProvider(cause);
}
