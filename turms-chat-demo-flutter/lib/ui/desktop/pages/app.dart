import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';
import 'package:flutter/services.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:window_manager/window_manager.dart';

import '../../../domain/user/view_models/logged_in_user_info_view_model.dart';
import '../../../infra/app/app_config.dart';
import '../../../infra/io/global_keyboard_listener.dart';
import '../../../infra/window/window_utils.dart';
import '../../l10n/app_localizations.dart';
import '../../l10n/view_models/app_localizations_view_model.dart';
import '../../l10n/view_models/system_locale_info_view_model.dart';
import '../../themes/app_theme_extension.dart';
import '../../themes/app_theme_view_model.dart';
import '../../themes/sizes.dart';
import '../components/t_dialog/t_dialog.dart';
import 'home_page/home_page.dart';
import 'login_page/login_page.dart';

ProviderContainer? _appContainer;
GlobalKey<NavigatorState> _navigatorKey = GlobalKey();

T readGlobalState<T>(ProviderListenable<T> provider) =>
    _appContainer!.read(provider);

void popTopIfNameMatched(String name) {
  final currentState = _navigatorKey.currentState;
  if (currentState == null || !currentState.mounted) {
    return;
  }
  String? currentPath;
  currentState.popUntil((route) {
    currentPath = route.settings.name;
    return true;
  });
  if (currentPath == name) {
    currentState.pop();
  }
}

class App extends ConsumerStatefulWidget {
  App({super.key, required this.container}) {
    _appContainer = container;
  }

  final ProviderContainer container;

  @override
  ConsumerState<ConsumerStatefulWidget> createState() => _AppState();
}

class _AppState extends ConsumerState<App> with WindowListener {
  bool _shouldDisplayLoginPage = true;
  late bool _isWindowMaximized;
  bool _isWindowSettingUp = false;

  @override
  void initState() {
    super.initState();
    windowManager.addListener(this);
    // show windows in the next frame to ensure the UI is ready.
    // Otherwise the UI will jitter because it is painting.
    SchedulerBinding.instance.addPostFrameCallback((_) async {
      await WindowUtils.show();
    });
  }

  @override
  void dispose() {
    windowManager.removeListener(this);
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final locale = ref.watch(localeInfoViewModel).locale;
    final themeData = ref.watch(themeViewModel);
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    _isWindowMaximized = ref.watch(isWindowMaximizedViewModel);

    ref.listen(loggedInUserViewModel,
        (previousLoggedInUser, currentLoggedInUser) {
      final displayLoginPage = currentLoggedInUser == null;
      if (_shouldDisplayLoginPage != displayLoginPage && !_isWindowSettingUp) {
        _hideAndResize(displayLoginPage).then((value) {
          _shouldDisplayLoginPage = displayLoginPage;
          if (displayLoginPage) {
            // todo
            // final providerContainer = widget.container;
            // for (final value in providerContainer.getAllProviderElements()) {
            //   providerContainer.invalidate(value.provider);
            // }
          }
          setState(() {});
          SchedulerBinding.instance.addPostFrameCallback((_) {
            WindowUtils.show();
          });
        });
      }
    });
    return _buildView(themeData, locale, appLocalizations);
  }

  @override
  void onWindowFocus() {
    setState(() {});
  }

  Future<void> _hideAndResize(bool resizeForLoginPage) async {
    _isWindowSettingUp = true;
    // Hide first to resize and paint.
    await WindowUtils.hide();
    // When hide() returns, the window may be hided or hiding (animation),
    // so we wait to ensure the window is hided.
    await WindowUtils.waitUntilInvisible();
    if (resizeForLoginPage) {
      // Note that: We must set the min size first and then resize
      // because if setting the min size and resizing in one call,
      // the previous min size will restrict the current resize
      // on window_manager (0.3.7).
      await WindowUtils.setupWindow(
          minimumSize: AppConfig.defaultWindowSizeForLoginScreen);
      await WindowUtils.setupWindow(
          size: AppConfig.defaultWindowSizeForLoginScreen,
          backgroundColor: Colors.transparent);
    } else {
      await WindowUtils.setupWindow(
          minimumSize: AppConfig.minWindowSizeForHomeScreen);
      await WindowUtils.setupWindow(
          size: AppConfig.defaultWindowSizeForHomeScreen,
          resizable: true,
          backgroundColor: Colors.white);
    }
    _isWindowSettingUp = false;
  }

  bool _onKeyEvent(KeyEvent event) {
    var hasRouteRemoved = false;
    if (event is KeyDownEvent &&
        event.logicalKey == LogicalKeyboardKey.escape) {
      final currentState = _navigatorKey.currentState;
      if (currentState != null && currentState.mounted) {
        currentState.popUntil((route) {
          // Check "!hasRouteRemoved" to only remove the first TDialog route.
          if (!hasRouteRemoved && isTDialogRoute(route) && route.isActive) {
            hasRouteRemoved = true;
            return false;
          }
          return true;
        });
      }
    }
    return hasRouteRemoved;
  }

  Locale? _resolveLocale(
          List<Locale>? locales, Iterable<Locale> supportedLocales) =>
      ref.read(localeInfoViewModel).locale;
}

extension _AppView on _AppState {
  Widget _buildView(
      ThemeData themeData, Locale locale, AppLocalizations appLocalizations) {
    final appThemeExtension = themeData.appThemeExtension;
    final themeMode = appThemeExtension.themeMode;
    return MaterialApp(
        locale: locale,
        debugShowCheckedModeBanner: false,
        navigatorKey: _navigatorKey,
        themeMode: themeMode,
        theme: themeMode == ThemeMode.light ? themeData : null,
        darkTheme: themeMode == ThemeMode.dark ? themeData : null,
        localizationsDelegates: const [
          AppLocalizations.delegate,
          GlobalCupertinoLocalizations.delegate,
          GlobalMaterialLocalizations.delegate,
          GlobalWidgetsLocalizations.delegate,
        ],
        supportedLocales: AppLocalizations.supportedLocales,
        localeListResolutionCallback: _resolveLocale,
        home: Material(
          child: GlobalKeyboardListener(
            onKeyEvent: _onKeyEvent,
            child: _shouldDisplayLoginPage
                ? const ClipRRect(
                    borderRadius: Sizes.borderRadiusCircular8,
                    child: LoginPage(),
                  )
                : ClipRRect(
                    borderRadius: _isWindowMaximized
                        ? Sizes.borderRadius0
                        : Sizes.borderRadiusCircular8,
                    child: const HomePage(),
                  ),
          ),
        ));
  }
}
