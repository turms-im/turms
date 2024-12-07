extension FormattedMessage on Exception {
  String get message => toString().startsWith('Exception: ')
      ? toString().substring(11)
      : toString();
}
