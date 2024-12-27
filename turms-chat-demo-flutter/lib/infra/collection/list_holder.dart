final class ListHolder<T> {
  ListHolder(this.list);

  final List<T> list;
  int? _hashCode;

  @override
  String toString() => 'ListHolder(${list.join(', ')})';

  @override
  bool operator ==(Object other) {
    if (identical(this, other)) {
      return true;
    }
    if (other is! ListHolder) {
      return false;
    }
    final list2 = other.list;
    if (identical(list, list2)) {
      return true;
    }
    final length = list.length;
    if (length != list2.length) {
      return false;
    }
    for (var i = 0; i < length; i++) {
      if (list[i] != list2[i]) {
        return false;
      }
    }
    return true;
  }

  @override
  int get hashCode {
    _hashCode ??= Object.hashAll(list);
    return _hashCode!;
  }
}

typedef IntListHolder = ListHolder<int>;
