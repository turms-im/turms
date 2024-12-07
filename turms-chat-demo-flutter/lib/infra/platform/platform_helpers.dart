import 'dart:io';

import 'package:flutter/foundation.dart';

final isDesktop =
    !kIsWeb && (Platform.isMacOS || Platform.isWindows || Platform.isLinux);
