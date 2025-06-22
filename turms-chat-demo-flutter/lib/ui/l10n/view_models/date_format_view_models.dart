import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:intl/intl.dart';

import 'app_localizations_view_model.dart';

final dateFormatViewModel_yMd = StateProvider<DateFormat>(
  (ref) => DateFormat.yMd(ref.watch(appLocalizationsViewModel).localeName),
);

final dateFormatViewModel_yMdHm = StateProvider<DateFormat>(
  (ref) =>
      DateFormat.yMd(ref.watch(appLocalizationsViewModel).localeName).add_Hm(),
);

final dateFormatViewModel_yMdjm = StateProvider<DateFormat>(
  (ref) =>
      DateFormat.yMd(ref.watch(appLocalizationsViewModel).localeName).add_jm(),
);

final dateFormatViewModel_yMdjms = StateProvider<DateFormat>(
  (ref) =>
      DateFormat.yMd(ref.watch(appLocalizationsViewModel).localeName).add_jms(),
);

final dateFormatViewModel_Md = StateProvider<DateFormat>(
  (ref) => DateFormat.Md(ref.watch(appLocalizationsViewModel).localeName),
);

final dateFormatViewModel_Mdjm = StateProvider<DateFormat>(
  (ref) =>
      DateFormat.Md(ref.watch(appLocalizationsViewModel).localeName).add_jm(),
);

final dateFormatViewModel_Mdjms = StateProvider<DateFormat>(
  (ref) =>
      DateFormat.Md(ref.watch(appLocalizationsViewModel).localeName).add_jms(),
);

final dateFormatViewModel_jm = StateProvider<DateFormat>(
  (ref) => DateFormat.jm(ref.watch(appLocalizationsViewModel).localeName),
);
