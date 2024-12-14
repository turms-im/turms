import 'dart:async';

import 'package:collection/collection.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:path/path.dart' as p;

import '../../../../domain/user/view_models/user_settings_view_model.dart';
import '../../../../infra/app/app_config.dart';
import '../../../../infra/env/env_vars.dart';
import '../../../../infra/github/github_client.dart';
import '../../../../infra/logging/logger.dart';
import '../../../../infra/rust/api/system.dart';
import '../../../../infra/task/task_utils.dart';
import '../../../../infra/units/file_size_extensions.dart';
import '../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../themes/index.dart';
import '../../components/index.dart';
import 'action_to_shortcut_view_model.dart';
import 'chat_page/chat_page.dart';
import 'contacts_page/contacts_page.dart';
import 'files_page/files_page.dart';
import 'home_page_action.dart';
import 'home_page_tab.dart';
import 'main_navigation_rail/main_navigation_rail.dart';
import 'shared_view_models/current_home_page_tab_view_model.dart';

const _taskIdCheckDiskSpace = 'checkDiskSpace';
const _taskIdCheckForUpdates = 'checkForUpdates';
final _diskWarningThreshold = BigInt.from(100).MB;

class HomePageLandscape extends ConsumerStatefulWidget {
  const HomePageLandscape({Key? key}) : super(key: key);

  @override
  ConsumerState<HomePageLandscape> createState() => _HomePageLandscapeState();
}

class _HomePageLandscapeState extends ConsumerState<HomePageLandscape> {
  @override
  Widget build(BuildContext context) {
    final tab = ref.watch(currentHomePageTabViewModel);
    final actionToShortcut = ref.watch(actionToShortcutViewModel);
    final bindings = <ShortcutActivator, VoidCallback>{};
    for (final action in HomePageAction.values) {
      final shortcut = actionToShortcut[action]?.shortcutActivator;
      if (shortcut != null) {
        bindings[shortcut] = () {
          hideAllPopups();
          action.trigger(context: context, ref: ref);
        };
      }
    }
    final child = CallbackShortcuts(
      bindings: bindings,
      child: FocusScope(
        debugLabel: 'HomePageLandscape',
        autofocus: true,
        child: Stack(
          children: [
            Row(
              children: [
                const SizedBox(
                  width: Sizes.mainNavigationRailWidth,
                  child: MainNavigationRail(),
                ),
                Expanded(
                    child: TLazyIndexedStack(
                  index: switch (tab) {
                    HomePageTab.chat => 0,
                    HomePageTab.contacts => 1,
                    HomePageTab.files => 2,
                  },
                  children: [
                    const RepaintBoundary(child: ChatPage()),
                    const RepaintBoundary(child: ContactsPage()),
                    const RepaintBoundary(child: FilesPage()),
                  ],
                ))
              ],
            ),
            const TTitleBar(),
          ],
        ),
      ),
    );
    if (EnvVars.showFocusTracker) {
      // TODO
      // return TFocusTracker(
      //   child: child,
      // );
    }
    return child;
  }

  @override
  void initState() {
    super.initState();
    TaskUtils.addPeriodicTask(
        id: _taskIdCheckForUpdates,
        duration: const Duration(hours: 1),
        callback: () async {
          final checkForUpdates =
              ref.read(userSettingsViewModel)?.checkForUpdatesAutomatically ??
                  false;
          if (!checkForUpdates) {
            return true;
          }
          try {
            final file = await GithubUtils.downloadLatestApp();
            // TODO: pop up a dialog to notify user.
          } catch (e, s) {
            logger.warn('Failed to download latest application', e, s);
          }
          return true;
        });
    TaskUtils.addPeriodicTask(
      id: _taskIdCheckDiskSpace,
      duration: const Duration(minutes: 1),
      callback: () async {
        final diskSpaceInfos = getDiskSpaceInfos();
        final diskSpace = diskSpaceInfos.firstWhereOrNull(
            (info) => p.isWithin(info.path, AppConfig.appDir));
        if (diskSpace != null && diskSpace.available < _diskWarningThreshold) {
          final appLocalizations = ref.read(appLocalizationsViewModel);
          unawaited(showAlertDialog(
            context,
            title: appLocalizations.lowDiskSpace,
            content: appLocalizations.lowDiskSpacePrompt(100),
            onTapConfirm: () {
              Navigator.of(context).pop();
            },
          ));
        }
        return true;
      },
    );
  }

  @override
  void dispose() {
    TaskUtils.removeTask(_taskIdCheckForUpdates);
    TaskUtils.removeTask(_taskIdCheckDiskSpace);
    super.dispose();
  }
}
