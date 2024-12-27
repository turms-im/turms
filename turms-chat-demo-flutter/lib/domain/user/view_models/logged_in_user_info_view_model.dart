import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../models/user.dart';

final loggedInUserViewModel = StateProvider<User?>((ref) => null);
