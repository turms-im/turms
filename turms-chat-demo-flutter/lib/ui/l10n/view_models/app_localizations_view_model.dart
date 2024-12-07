import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../app_localizations.dart';
import 'system_locale_info_view_model.dart';

final appLocalizationsViewModel = StateProvider<AppLocalizations>((ref) {
  final localeInfo = ref.watch(localeInfoViewModel);
  return lookupAppLocalizations(localeInfo.locale);
});
