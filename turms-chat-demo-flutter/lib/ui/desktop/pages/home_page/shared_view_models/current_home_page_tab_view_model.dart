import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../home_page_tab.dart';

final currentHomePageTabViewModel = StateProvider<HomePageTab>(
  (ref) => HomePageTab.chat,
);
