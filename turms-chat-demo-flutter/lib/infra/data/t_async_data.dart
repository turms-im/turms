class TAsyncData<T> {
  const TAsyncData({
    this.value,
    this.isLoading = false,
    this.lastException,
  });

  final T? value;
  final bool isLoading;
  final Exception? lastException;

  bool get isInitialized => value != null;

  static Stream<TAsyncData<T>> fromFuture<T>(
      Future<T> Function() provider) async* {
    yield TAsyncData<T>(isLoading: true);
    try {
      yield TAsyncData<T>(value: await provider());
    } on Exception catch (e) {
      yield TAsyncData<T>(lastException: e);
    }
  }

  TAsyncData<T> copyWith({
    T? value,
    bool? isLoading,
    Exception? lastException,
  }) =>
      TAsyncData<T>(
        value: value ?? this.value,
        isLoading: isLoading ?? this.isLoading,
        lastException: lastException ?? this.lastException,
      );
}
