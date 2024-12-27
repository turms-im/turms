import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../infra/collection/list_holder.dart';
import '../models/conversation_setting.dart';
import '../models/conversation_settings.dart';

class IdToConversationSettingsViewModelNotifier
    extends Notifier<Map<IntListHolder, ConversationSettings>> {
  void update(
    IntListHolder id, {
    bool? pinned,
    bool? enableNewMessageNotification,
  }) {
    var settings = state[id];
    if (settings == null) {
      settings = ConversationSettings({
        ConversationSetting.pinned: pinned,
        ConversationSetting.enableNewMessageNotification:
            enableNewMessageNotification,
      });
      state[id] = settings;
    } else {
      if (pinned != null) {
        settings.pinned = pinned;
      }
      if (enableNewMessageNotification != null) {
        settings.enableNewMessageNotification = enableNewMessageNotification;
      }
    }
    ref.notifyListeners();
  }

  @override
  Map<IntListHolder, ConversationSettings> build() => {};
}

final idToConversationSettingsViewModel = NotifierProvider<
        IdToConversationSettingsViewModelNotifier,
        Map<IntListHolder, ConversationSettings>>(
    IdToConversationSettingsViewModelNotifier.new);
