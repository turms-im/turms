import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../../../domain/user/models/contact.dart';

final selectedContactViewModel = StateProvider<Contact?>((ref) => null);
