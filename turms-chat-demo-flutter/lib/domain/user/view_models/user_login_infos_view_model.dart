import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../infra/sqlite/app_database.dart';

final userLoginInfosViewModel =
    StateProvider<List<UserLoginInfoTableData>>((ref) => []);
