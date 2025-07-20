import 'dart:async';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../../domain/user/models/index.dart';
import '../../../../../domain/user/models/setting_action_on_close.dart';
import '../../../../../domain/user/models/setting_locale.dart';
import '../../../../../domain/user/repositories/user_setting_repository.dart';
import '../../../../../domain/user/view_models/logged_in_user_info_view_model.dart';
import '../../../../../domain/user/view_models/user_settings_view_model.dart';
import '../../../../../infra/app/app_config.dart';
import '../../../../../infra/autostart/autostart_manager.dart';
import '../../../../../infra/keyboard/shortcut_extensions.dart';
import '../../../../../infra/shortcut/shortcut.dart';
import '../../../../l10n/app_localizations.dart';
import '../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../l10n/view_models/system_locale_info_view_model.dart';
import '../../../../themes/app_theme_extension.dart';

import '../../../components/index.dart';
import '../action_to_shortcut_view_model.dart';
import '../home_page_action.dart';
import 'setting_form_field_groups.dart';

class SettingsPane extends ConsumerStatefulWidget {
  const SettingsPane({
    super.key,
    required this.onSettingFormFieldGroupScrolled,
  });

  final void Function(int index) onSettingFormFieldGroupScrolled;

  @override
  ConsumerState<SettingsPane> createState() => _SettingsPaneState();
}

class _SettingsPaneState extends ConsumerState<SettingsPane> {
  late AppLocalizations _appLocalizations;
  late Map<HomePageAction, Shortcut> _actionToShortcut;

  late bool _useSystemLocale;

  final GlobalKey _scrollViewKey = GlobalKey();
  final AutostartManager _autostartManager = AutostartManager.create(
    appName: AppConfig.packageInfo.appName,
    appPath: Platform.resolvedExecutable,
    args: [],
  );

  late UserSettings _userSettings;

  @override
  Widget build(BuildContext context) {
    _appLocalizations = ref.watch(appLocalizationsViewModel);
    _actionToShortcut = ref.watch(actionToShortcutViewModel);
    final localInfo = ref.watch(localeInfoViewModel);
    _useSystemLocale = localInfo.isSystemLocale;
    _userSettings = ref.watch(userSettingsViewModel)!;

    return _SettingsPaneView(this);
  }

  Future<void> _updateActionOnClose(SettingActionOnClose value) async {
    final userSettingsController = ref.read(userSettingsViewModel.notifier);
    await userSettingRepository.upsert(
      ref.read(loggedInUserViewModel)!.userId,
      UserSetting.actionOnClose,
      value,
    );
    userSettingsController.state!.actionOnClose = value;
    userSettingsViewModelRef.notifyListeners();
  }

  Future<void> _updateCheckForUpdatesAutomatically(bool value) async {
    await userSettingRepository.upsert(
      ref.read(loggedInUserViewModel)!.userId,
      UserSetting.checkForUpdatesAutomatically,
      value,
    );
    ref
            .read(userSettingsViewModel.notifier)
            .state!
            .checkForUpdatesAutomatically =
        value;
    userSettingsViewModelRef.notifyListeners();
  }

  Future<void> _updateLaunchOnStartup(bool value) async {
    final userSettingsController = ref.read(userSettingsViewModel.notifier);
    try {
      if (value) {
        await _autostartManager.enable();
      } else {
        await _autostartManager.disable();
      }
    } catch (e) {
      unawaited(
        TToast.showToast(
          context,
          _appLocalizations.failedToUpdateSettings(e.toString()),
        ),
      );
    }
    userSettingsController.state!.launchOnStartup = value;
    userSettingsViewModelRef.notifyListeners();
  }

  Future<void> _updateLocale(SettingLocale value) async {
    final Locale locale;
    if (value == SettingLocale.system) {
      locale = ref.read(localeInfoViewModel.notifier).useSystemLocale().locale;
      await userSettingRepository.delete(
        ref.read(loggedInUserViewModel)!.userId,
        UserSetting.locale,
      );
      ref.read(userSettingsViewModel.notifier).state!.locale = null;
    } else {
      final newLocale = ref
          .read(localeInfoViewModel.notifier)
          .updateLocaleIfSupported(value.name)
          ?.locale;
      assert(newLocale != null, 'Unsupported locale: ${value.name}');
      locale = newLocale!;
      await userSettingRepository.upsert(
        ref.read(loggedInUserViewModel)!.userId,
        UserSetting.locale,
        locale,
      );
      ref.read(userSettingsViewModel.notifier).state!.locale = locale;
    }
    userSettingsViewModelRef.notifyListeners();
  }

  Future<void> _updateNewMessageNotification(bool value) async {
    await userSettingRepository.upsert(
      ref.read(loggedInUserViewModel)!.userId,
      UserSetting.newMessageNotification,
      value,
    );
    ref.read(userSettingsViewModel.notifier).state!.newMessageNotification =
        value;
    userSettingsViewModelRef.notifyListeners();
  }

  Future<void> _updateShortcut({
    bool notify = true,
    bool resetConflictedShortcuts = true,
    required HomePageAction action,
    ShortcutActivator? shortcutActivator,
  }) async {
    final userSetting = action.userSetting;
    final userId = ref.read(loggedInUserViewModel)!.userId;
    final userSettingsController = ref.read(userSettingsViewModel.notifier);
    if (shortcutActivator == null) {
      await userSettingRepository.upsert(userId, userSetting, null);
    } else {
      if (resetConflictedShortcuts) {
        for (final homePageAction in HomePageAction.values) {
          if (action != homePageAction &&
              (_actionToShortcut[homePageAction]!.shortcutActivator
                      ?.hasSameKeys(shortcutActivator) ??
                  false)) {
            await _updateShortcut(notify: false, action: homePageAction);
          }
        }
      }
      await userSettingRepository.upsert(
        userId,
        userSetting,
        shortcutActivator,
      );
    }
    final userSettings = userSettingsController.state!;
    switch (action) {
      case HomePageAction.showChatPage:
        userSettings.shortcutShowChatPage = Shortcut(shortcutActivator, true);
        break;
      case HomePageAction.showContactsPage:
        userSettings.shortcutShowContactsPage = Shortcut(
          shortcutActivator,
          true,
        );
        break;
      case HomePageAction.showFilesPage:
        userSettings.shortcutShowFilesPage = Shortcut(shortcutActivator, true);
        break;
      case HomePageAction.showSettingsDialog:
        userSettings.shortcutShowSettingsDialog = Shortcut(
          shortcutActivator,
          true,
        );
        break;
      case HomePageAction.showAboutDialog:
        userSettings.shortcutShowAboutDialog = Shortcut(
          shortcutActivator,
          true,
        );
        break;
    }
    if (notify) {
      userSettingsViewModelRef.notifyListeners();
    }
  }

  Future<void> _updateThemeMode(ThemeMode value) async {
    final userSettingsController = ref.read(userSettingsViewModel.notifier);
    await userSettingRepository.upsert(
      ref.read(loggedInUserViewModel)!.userId,
      UserSetting.theme,
      value,
    );
    userSettingsController.state!.theme = value;
    userSettingsViewModelRef.notifyListeners();
  }

  bool _hasAnyShortcutChanged() {
    for (final homePageAction in HomePageAction.values) {
      final hasSameKeys =
          _actionToShortcut[homePageAction]?.shortcutActivator?.hasSameKeys(
            homePageAction.defaultShortcutActivator,
          ) ??
          false;
      if (!hasSameKeys) {
        return true;
      }
    }
    return false;
  }

  Future<void> _resetShortcuts() async {
    for (final homePageAction in HomePageAction.values) {
      await _updateShortcut(
        notify: false,
        resetConflictedShortcuts: false,
        action: homePageAction,
        shortcutActivator: homePageAction.defaultShortcutActivator,
      );
    }
    userSettingsViewModelRef.notifyListeners();
  }
}

class _SettingsPaneView extends StatelessWidget {
  const _SettingsPaneView(this._settingsPaneController);

  final _SettingsPaneState _settingsPaneController;

  @override
  Widget build(BuildContext context) => SingleChildScrollView(
    key: _settingsPaneController._scrollViewKey,
    child: Padding(
      padding: const EdgeInsets.only(bottom: 16, left: 16, right: 16),
      child: TForm(formData: TFormData(groups: _buildFormGroups(context))),
    ),
  );

  List<TFormFieldGroup> _buildFormGroups(BuildContext context) {
    final appLocalizations = _settingsPaneController._appLocalizations;
    return [
      for (final entry in formFieldGroupToContext.entries)
        switch (entry.key) {
          SettingFormFieldGroup.launchAndExit => _buildLaunchAndExitFieldGroup(
            entry.value,
            appLocalizations,
          ),
          SettingFormFieldGroup.shortcuts => _buildShortcutsFieldGroup(
            entry.value,
            appLocalizations,
            _settingsPaneController._actionToShortcut,
            context,
          ),
          SettingFormFieldGroup.status => _buildStatusFieldGroup(
            entry.value,
            appLocalizations,
          ),
          SettingFormFieldGroup.notifications => _buildNotificationsFieldGroup(
            entry.value,
            appLocalizations,
          ),
          SettingFormFieldGroup.appearance => _buildAppearanceFieldGroup(
            entry.value,
            appLocalizations,
          ),
          SettingFormFieldGroup.update => _buildUpdateFieldGroup(
            entry.value,
            appLocalizations,
          ),
        },
    ];
  }

  TFormFieldGroup _buildLaunchAndExitFieldGroup(
    SettingFormFieldGroupContext context,
    AppLocalizations appLocalizations,
  ) => TFormFieldGroup(
    title: context.getTitle(appLocalizations),
    titleKey: context.key,
    fields: [
      TFormFieldCheckbox(
        label: appLocalizations.launchOnStartup,
        onChanged: _settingsPaneController._updateLaunchOnStartup,
        value: _settingsPaneController._userSettings.launchOnStartup ?? false,
      ),
      TFormFieldRadioGroup<SettingActionOnClose>(
        label: appLocalizations.actionOnClose,
        groupValue:
            _settingsPaneController._userSettings.actionOnClose ??
            SettingActionOnClose.minimizeToTray,
        onChanged: _settingsPaneController._updateActionOnClose,
        radios: [
          TFormFieldRadio(
            value: SettingActionOnClose.minimizeToTray,
            label: appLocalizations.minimizeToTray,
          ),
          TFormFieldRadio(
            value: SettingActionOnClose.exit,
            label: appLocalizations.exit,
          ),
        ],
      ),
    ],
  );

  TFormFieldGroup _buildShortcutsFieldGroup(
    SettingFormFieldGroupContext formFieldGroupContext,
    AppLocalizations appLocalizations,
    Map<HomePageAction, Shortcut> actionToShortcut,
    BuildContext context,
  ) {
    final appThemeExtension = context.appThemeExtension;
    return TFormFieldGroup(
      title: formFieldGroupContext.getTitle(appLocalizations),
      titleKey: formFieldGroupContext.key,
      titleSuffix: _settingsPaneController._hasAnyShortcutChanged()
          ? TTextButton(
              text: appLocalizations.reset,
              addContainer: false,
              textStyle: appThemeExtension.linkTextStyle,
              textStyleHovered: appThemeExtension.linkHoveredTextStyle,
              onTap: _settingsPaneController._resetShortcuts,
            )
          : null,
      fields: [
        TFormFieldShortcutTextField(
          label: '${appLocalizations.goToChatPage}:',
          initialKeys: actionToShortcut[HomePageAction.showChatPage]!
              .shortcutActivator
              ?.keyList,
          onShortcutChanged: (List<LogicalKeyboardKey> keys) {
            _settingsPaneController._updateShortcut(
              action: HomePageAction.showChatPage,
              shortcutActivator: keys.isEmpty
                  ? null
                  : LogicalKeySet.fromSet(keys.toSet()),
            );
          },
        ),
        TFormFieldShortcutTextField(
          label: '${appLocalizations.goToContactsPage}:',
          initialKeys: actionToShortcut[HomePageAction.showContactsPage]!
              .shortcutActivator
              ?.keyList,
          onShortcutChanged: (List<LogicalKeyboardKey> keys) =>
              _settingsPaneController._updateShortcut(
                action: HomePageAction.showContactsPage,
                shortcutActivator: keys.isEmpty
                    ? null
                    : LogicalKeySet.fromSet(keys.toSet()),
              ),
        ),
        TFormFieldShortcutTextField(
          label: '${appLocalizations.goToFilesPage}:',
          initialKeys: actionToShortcut[HomePageAction.showFilesPage]!
              .shortcutActivator
              ?.keyList,
          onShortcutChanged: (List<LogicalKeyboardKey> keys) =>
              _settingsPaneController._updateShortcut(
                action: HomePageAction.showFilesPage,
                shortcutActivator: keys.isEmpty
                    ? null
                    : LogicalKeySet.fromSet(keys.toSet()),
              ),
        ),
        TFormFieldShortcutTextField(
          label: '${appLocalizations.openSettingsDialog}:',
          initialKeys: actionToShortcut[HomePageAction.showSettingsDialog]!
              .shortcutActivator
              ?.keyList,
          onShortcutChanged: (List<LogicalKeyboardKey> keys) =>
              _settingsPaneController._updateShortcut(
                action: HomePageAction.showSettingsDialog,
                shortcutActivator: keys.isEmpty
                    ? null
                    : LogicalKeySet.fromSet(keys.toSet()),
              ),
        ),
        TFormFieldShortcutTextField(
          label: '${appLocalizations.openAboutDialog}:',
          initialKeys: actionToShortcut[HomePageAction.showAboutDialog]!
              .shortcutActivator
              ?.keyList,
          onShortcutChanged: (List<LogicalKeyboardKey> keys) =>
              _settingsPaneController._updateShortcut(
                action: HomePageAction.showAboutDialog,
                shortcutActivator: keys.isEmpty
                    ? null
                    : LogicalKeySet.fromSet(keys.toSet()),
              ),
        ),
      ],
    );
  }

  TFormFieldGroup _buildStatusFieldGroup(
    SettingFormFieldGroupContext context,
    AppLocalizations appLocalizations,
  ) => TFormFieldGroup(
    title: context.getTitle(appLocalizations),
    titleKey: context.key,
    fields: [
      TFormFieldCheckbox(
        // TODO
        label: appLocalizations.newMessageNotification,
        value:
            _settingsPaneController._userSettings.newMessageNotification ??
            false,
        onChanged: _settingsPaneController._updateNewMessageNotification,
      ),
    ],
  );

  TFormFieldGroup _buildNotificationsFieldGroup(
    SettingFormFieldGroupContext context,
    AppLocalizations appLocalizations,
  ) => TFormFieldGroup(
    title: context.getTitle(appLocalizations),
    titleKey: context.key,
    fields: [
      TFormFieldCheckbox(
        label: appLocalizations.newMessageNotification,
        value:
            _settingsPaneController._userSettings.newMessageNotification ??
            false,
        onChanged: _settingsPaneController._updateNewMessageNotification,
      ),
    ],
  );

  TFormFieldGroup _buildAppearanceFieldGroup(
    SettingFormFieldGroupContext context,
    AppLocalizations appLocalizations,
  ) => TFormFieldGroup(
    title: context.getTitle(appLocalizations),
    titleKey: context.key,
    fields: [
      TFormFieldSelect(
        label: appLocalizations.language,
        value: _settingsPaneController._useSystemLocale
            ? SettingLocale.system
            : _nameToLocale[_settingsPaneController
                  ._appLocalizations
                  .localeName]!,
        entries: [
          TMenuEntry(
            label: appLocalizations.systemLanguage,
            value: SettingLocale.system,
          ),
          const TMenuEntry(label: 'English', value: SettingLocale.en),
          const TMenuEntry(label: '日本語', value: SettingLocale.ja),
          const TMenuEntry(label: '简体中文', value: SettingLocale.zhCn),
        ],
        onSelected: (SettingLocale value) async {
          await _settingsPaneController._updateLocale(value);
        },
      ),
      TFormFieldSelect(
        label: appLocalizations.theme,
        value: _settingsPaneController._userSettings.theme ?? ThemeMode.system,
        entries: [
          TMenuEntry(
            label: appLocalizations.systemTheme,
            value: ThemeMode.system,
          ),
          TMenuEntry(
            label: appLocalizations.lightTheme,
            value: ThemeMode.light,
          ),
          TMenuEntry(label: appLocalizations.darkTheme, value: ThemeMode.dark),
        ],
        onSelected: (ThemeMode value) async {
          await _settingsPaneController._updateThemeMode(value);
        },
      ),
    ],
  );

  TFormFieldGroup _buildUpdateFieldGroup(
    SettingFormFieldGroupContext context,
    AppLocalizations appLocalizations,
  ) => TFormFieldGroup(
    title: context.getTitle(appLocalizations),
    titleKey: context.key,
    fields: [
      TFormFieldCheckbox(
        label: appLocalizations.checkForUpdatesAutomatically,
        value:
            _settingsPaneController
                ._userSettings
                .checkForUpdatesAutomatically ??
            false,
        onChanged: _settingsPaneController._updateCheckForUpdatesAutomatically,
      ),
    ],
  );
}

final _nameToLocale = {
  for (SettingLocale locale in SettingLocale.values) locale.name: locale,
};
