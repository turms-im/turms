import 'package:flutter/cupertino.dart';

import '../../../../l10n/app_localizations.dart';

enum SettingFormFieldGroup {
  launchAndExit,
  shortcuts,
  status,
  notifications,
  appearance,
  update,
  // TODO: network proxy
}

class SettingFormFieldGroupContext {
  const SettingFormFieldGroupContext({
    required this.key,
    required this.getTitle,
  });

  final GlobalKey key;
  final String Function(AppLocalizations appLocalizations) getTitle;
}

final formFieldGroupToContext =
    <SettingFormFieldGroup, SettingFormFieldGroupContext>{
      SettingFormFieldGroup.launchAndExit: SettingFormFieldGroupContext(
        key: GlobalKey(debugLabel: SettingFormFieldGroup.launchAndExit.name),
        getTitle: (appLocalizations) => appLocalizations.launchAndExit,
      ),
      SettingFormFieldGroup.shortcuts: SettingFormFieldGroupContext(
        key: GlobalKey(debugLabel: SettingFormFieldGroup.shortcuts.name),
        getTitle: (appLocalizations) => appLocalizations.shortcuts,
      ),
      SettingFormFieldGroup.status: SettingFormFieldGroupContext(
        key: GlobalKey(debugLabel: SettingFormFieldGroup.status.name),
        getTitle: (appLocalizations) => appLocalizations.status,
      ),
      SettingFormFieldGroup.notifications: SettingFormFieldGroupContext(
        key: GlobalKey(debugLabel: SettingFormFieldGroup.notifications.name),
        getTitle: (appLocalizations) => appLocalizations.notifications,
      ),
      SettingFormFieldGroup.appearance: SettingFormFieldGroupContext(
        key: GlobalKey(debugLabel: SettingFormFieldGroup.appearance.name),
        getTitle: (appLocalizations) => appLocalizations.appearance,
      ),
      SettingFormFieldGroup.update: SettingFormFieldGroupContext(
        key: GlobalKey(debugLabel: SettingFormFieldGroup.update.name),
        getTitle: (appLocalizations) => appLocalizations.update,
      ),
    };
