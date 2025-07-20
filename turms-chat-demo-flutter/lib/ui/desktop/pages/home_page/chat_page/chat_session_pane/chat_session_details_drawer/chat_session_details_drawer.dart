import 'dart:async';

import 'package:fixnum/fixnum.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../../../../../../../domain/conversation/models/conversation.dart';
import '../../../../../../../domain/conversation/services/conversation_service.dart';
import '../../../../../../../domain/conversation/view_models/id_to_conversation_settings_view_model.dart';
import '../../../../../../../domain/group/services/group_service.dart';
import '../../../../../../../domain/user/models/group_member.dart';
import '../../../../../../../domain/user/models/user.dart';
import '../../../../../../../domain/user/view_models/logged_in_user_info_view_model.dart';
import '../../../../../../../infra/built_in_types/built_in_type_helpers.dart';
import '../../../../../../../infra/core/comparable_utils.dart';
import '../../../../../../../infra/ui/text_utils.dart';
import '../../../../../../l10n/view_models/index.dart';
import '../../../../../../themes/index.dart';
import '../../../../../components/index.dart';
import '../../../../../components/t_menu/t_context_menu.dart';
import '../../../create_group_page/create_group_page.dart';
import '../../../shared_components/user_profile_popup.dart';

part 'chat_session_details_group_conversation.dart';

part 'chat_session_details_user_conversation.dart';

class ChatSessionDetailsDrawer extends StatelessWidget {
  const ChatSessionDetailsDrawer({super.key, required this.conversation});

  final Conversation conversation;

  @override
  Widget build(BuildContext context) {
    final theme = context.theme;
    final appThemeExtension = theme.appThemeExtension;
    return SizedBox(
      width: Sizes.chatSessionDetailsDrawerWidth,
      height: double.infinity,
      child: DecoratedBox(
        decoration: BoxDecoration(
          color: appThemeExtension.chatSessionDetailsDrawerBackgroundColor,
          border: Border(left: BorderSide(color: theme.dividerColor)),
        ),
        child: Padding(
          padding: const EdgeInsets.only(left: 16, top: 8, right: 16),
          child: switch (conversation) {
            final UserConversation c => ChatSessionDetailsUserConversation(
              conversation: c,
            ),
            final GroupConversation c => ChatSessionDetailsGroupConversation(
              conversation: c,
            ),
            SystemConversation() => throw UnsupportedError(''),
          },
        ),
      ),
    );
  }
}
