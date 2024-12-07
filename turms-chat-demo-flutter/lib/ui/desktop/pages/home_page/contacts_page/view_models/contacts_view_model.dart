import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../../../domain/user/models/index.dart';
import '../../../../../../infra/data/t_async_data.dart';

final contactsDataViewModel =
    StateProvider<TAsyncData<List<Contact>>>((ref) => const TAsyncData());

final userContactsViewModel = StateProvider<List<UserContact>>((ref) =>
    ref
        .watch(contactsDataViewModel)
        .value
        ?.where((element) => element is UserContact)
        .cast<UserContact>()
        .toList() ??
    []);
