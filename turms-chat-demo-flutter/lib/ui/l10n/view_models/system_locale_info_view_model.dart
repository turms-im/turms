import 'package:flutter/widgets.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../domain/user/view_models/user_settings_view_model.dart';
import '../app_localizations.dart';

class LocaleInfo {
  LocaleInfo({required this.isSystemLocale, required this.locale});

  final bool isSystemLocale;
  final Locale locale;
}

class LocaleInfoViewModelNotifier extends Notifier<LocaleInfo> {
  @override
  LocaleInfo build() {
    final observer = _LocaleObserver(ref);
    WidgetsBinding.instance.addObserver(observer);
    ref.onDispose(() => WidgetsBinding.instance.removeObserver(observer));

    final locale = ref.watch(userSettingsViewModel)?.locale;
    if (locale == null) {
      return _getDefaultLocaleInfo();
    }
    return AppLocalizations.delegate.isSupported(locale)
        ? LocaleInfo(isSystemLocale: false, locale: locale)
        : LocaleInfo(isSystemLocale: true, locale: _localeEn);
  }

  LocaleInfo useSystemLocale() {
    final localeInfo = _getDefaultLocaleInfo();
    ref.read(localeInfoViewModel.notifier).state = localeInfo;
    return localeInfo;
  }

  LocaleInfo? updateLocaleIfSupported(String name) {
    final locale = Locale(name);
    if (!AppLocalizations.delegate.isSupported(locale)) {
      return null;
    }
    final localeInfo = LocaleInfo(isSystemLocale: false, locale: locale);
    ref.read(localeInfoViewModel.notifier).state = localeInfo;
    return localeInfo;
  }
}

final localeInfoViewModel =
    NotifierProvider<LocaleInfoViewModelNotifier, LocaleInfo>(
        LocaleInfoViewModelNotifier.new);

const _localeEn = Locale('en');

class _LocaleObserver extends WidgetsBindingObserver {
  _LocaleObserver(this.ref);

  final Ref ref;

  @override
  void didChangeLocales(List<Locale>? locales) {
    final locale = ref.watch(userSettingsViewModel)?.locale;
    if (locale == null) {
      ref.read(localeInfoViewModel.notifier).state = _getDefaultLocaleInfo();
    }
  }
}

LocaleInfo _getDefaultLocaleInfo() {
  final systemLocale = WidgetsBinding.instance.platformDispatcher.locale;
  return LocaleInfo(
      isSystemLocale: true,
      locale: AppLocalizations.delegate.isSupported(systemLocale)
          ? systemLocale
          : _localeEn);
}
