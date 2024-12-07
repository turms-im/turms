import 'dart:math';

import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:material_symbols_icons/material_symbols_icons.dart';

import '../../../../../../domain/conversation/models/conversation.dart';
import '../../../../../../domain/conversation/models/conversation_settings.dart';
import '../../../../../../domain/conversation/services/conversation_service.dart';
import '../../../../../../domain/message/models/message_type.dart';
import '../../../../../../domain/message/repositories/message_repository.dart';
import '../../../../../../domain/user/models/contact.dart';
import '../../../../../../domain/user/view_models/logged_in_user_info_view_model.dart';
import '../../../../../../infra/built_in_types/built_in_type_helpers.dart';
import '../../../../../../infra/collection/list_holder.dart';
import '../../../../../l10n/app_localizations.dart';
import '../../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../../l10n/view_models/date_format_view_models.dart';
import '../../../../../themes/index.dart';
import '../../../../components/index.dart';
import '../chat_session_pane/message.dart';
import '../view_models/conversations_view_model.dart';

const _messageIconSize = 16.0;
const _fontWeightBold = FontWeight.w600;

class ConversationTile extends ConsumerStatefulWidget {
  ConversationTile({
    super.key,
    required this.item,
    this.conversationSettings,
    this.highlighted = false,
    this.selected = false,
    required this.onTap,
    required this.onSecondaryTap,
    required this.onDeleted,
  }) : isSearching = item is ConversationTileItemForSearchMode;

  final ConversationTileItem item;
  final ConversationSettings? conversationSettings;

  final bool highlighted;
  final bool selected;

  final GestureTapCallback onTap;
  final GestureTapCallback onSecondaryTap;
  final VoidCallback onDeleted;

  final bool isSearching;

  @override
  ConsumerState<ConversationTile> createState() => _ConversationTileState();
}

class _ConversationTileState extends ConsumerState<ConversationTile> {
  bool _useBoldText = false;

  @override
  Widget build(BuildContext context) {
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    final appThemeExtension = context.appThemeExtension;
    _useBoldText = !widget.isSearching &&
        (widget.item as ConversationTileItemForNormalMode)
                .conversation
                .unreadMessageCount >
            0;
    final conversationId = widget.item.conversationId;
    final settings = widget.conversationSettings;
    final pinned = settings?.pinned ?? false;
    final enableNewMessageNotification =
        settings?.enableNewMessageNotification ?? false;
    return Stack(
      children: [
        TListTile(
          onTap: widget.onTap,
          onSecondaryTapUp: (details) {
            _showConversationContextMenu(
                context,
                details.globalPosition,
                appLocalizations,
                conversationId,
                pinned,
                enableNewMessageNotification);
          },
          focused: widget.selected,
          backgroundColor: widget.highlighted
              ? appThemeExtension.tileBackgroundHighlightedColor
              : appThemeExtension.tileBackgroundColor,
          padding:
              // use more right padding to reserve space for scrollbar
              // TODO: adapt the padding to not hide part of text (e.g. contact name).
              const EdgeInsets.only(left: 10, right: 14, top: 12, bottom: 12),
          child: Row(mainAxisSize: MainAxisSize.min, spacing: 8, children: [
            _buildAvatar(),
            Expanded(
                child: _buildConversation(context, appThemeExtension,
                    appLocalizations, enableNewMessageNotification))
          ]),
        ),
        if (widget.conversationSettings?.pinned ?? false)
          const Positioned(
              top: 2,
              left: 2,
              child: CustomPaint(
                painter: _PinnedConversationMarkerPainter(),
                size: Size.square(12),
              )),
      ],
    );
  }

  void _showConversationContextMenu(
      BuildContext context,
      Offset globalPosition,
      AppLocalizations appLocalizations,
      IntListHolder conversationId,
      bool pinned,
      bool enableNewMessageNotification) {
    showPopup(
      context: context,
      targetGlobalRect:
          Rect.fromLTWH(globalPosition.dx, globalPosition.dy, 0, 0),
      targetAnchor: Alignment.topLeft,
      followerAnchor: Alignment.topLeft,
      follower: TMenu(
        dense: true,
        padding: Sizes.paddingV8H16,
        entries: [
          TMenuEntry(
            value: 'pin',
            label: pinned ? appLocalizations.unpin : appLocalizations.pin,
            onSelected: () {
              ref.read(conversationServiceProvider)!.updateSettingPinned(
                    conversationId: conversationId,
                    newValue: !pinned,
                    contact: widget.item.contact,
                  );
              hideAllPopups();
            },
          ),
          TMenuEntry(
            value: 'enableNewMessageNotification',
            label: enableNewMessageNotification
                ? appLocalizations.disableNewMessageNotification
                : appLocalizations.enableNewMessageNotification,
            onSelected: () {
              ref
                  .read(conversationServiceProvider)!
                  .updateSettingEnableNewMessageNotification(
                    conversationId: conversationId,
                    newValue: !enableNewMessageNotification,
                    contact: widget.item.contact,
                  );
              hideAllPopups();
            },
          ),
          TMenuEntry.separator,
          TMenuEntry(
            value: 'deleteChat',
            label: appLocalizations.deleteChat,
            onSelected: () {
              hideAllPopups();
              showAlertTDialog(
                  routeName: 'deleteChat',
                  context: context,
                  contentTextProvider: (appLocalizations) =>
                      appLocalizations.deleteChat,
                  confirmAction: TDialogAction(
                    style: TDialogActionStyle.danger,
                    textProvider: (appLocalizations) => appLocalizations.delete,
                    onPressed: () {
                      final messageRepository =
                          ref.read(messageRepositoryProvider)!;
                      final item = widget.item;
                      final conversationId = item.conversationId;
                      final contact = item.contact;
                      ref
                          .read(conversationsDataViewModel.notifier)
                          .deleteConversation(conversationId);
                      switch (contact) {
                        case GroupContact():
                          messageRepository.delete(groupId: contact.groupId);
                        case UserContact():
                          messageRepository.delete(participantIds: [
                            ref.read(loggedInUserViewModel)!.userId,
                            contact.userId,
                          ]);
                        case SystemContact():
                          switch (contact.type) {
                            case SystemContactType.fileTransfer:
                              final userId =
                                  ref.read(loggedInUserViewModel)!.userId;
                              messageRepository.delete(participantIds: [
                                userId,
                                userId,
                              ]);
                            case SystemContactType.requestNotification:
                              throw UnsupportedError('unsupported');
                          }
                      }
                      widget.onDeleted();
                      return true;
                    },
                  ));
            },
          ),
        ],
      ),
    );
  }

  Stack _buildAvatar() {
    final contact = widget.item.contact;
    return Stack(clipBehavior: Clip.none, children: [
      TAvatar(
        id: contact.id,
        name: contact.name,
        image: contact.image,
        presence: contact is UserContact ? contact.presence : UserPresence.none,
      ),
    ]);
  }

  Column _buildConversation(
      BuildContext context,
      AppThemeExtension appThemeExtension,
      AppLocalizations appLocalizations,
      bool enableNewMessageNotification) {
    final item = widget.item;
    final String? draft;
    final latestMessage = item.latestMessage;
    if (item is ConversationTileItemForNormalMode) {
      draft = item.conversation.draft;
    } else {
      draft = null;
    }
    final now = DateTime.now();
    return Column(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Expanded(
              child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            spacing: 12,
            children: [
              Flexible(
                  child: Text.rich(
                TextSpan(children: item.nameTextSpans),
                style: _useBoldText
                    ? const TextStyle(fontWeight: _fontWeightBold)
                    : null,
                overflow: TextOverflow.ellipsis,
                maxLines: 1,
                softWrap: false,
              )),
              _buildDatetime(
                  latestMessage, now, appLocalizations, appThemeExtension)
            ],
          )),
          _buildMessage(draft, appThemeExtension, appLocalizations,
              latestMessage, enableNewMessageNotification)
        ]);
  }

  Text _buildDatetime(ChatMessage? latestMessage, DateTime now,
      AppLocalizations appLocalizations, AppThemeExtension appThemeExtension) {
    final timestamp = latestMessage?.timestamp;
    return Text(
      timestamp == null || widget.isSearching
          ? ''
          : DateUtils.isSameDay(now, timestamp)
              ? ref.watch(dateFormatViewModel_jm).format(timestamp)
              : DateUtils.isSameMonth(now, timestamp)
                  ? ref.watch(dateFormatViewModel_Md).format(timestamp)
                  : DateUtils.isSameDay(
                          now.subtract(const Duration(days: 1)), timestamp)
                      ? appLocalizations.yesterday
                      : ref.watch(dateFormatViewModel_yMd).format(timestamp),
      style: _useBoldText
          ? appThemeExtension.conversationTileTimestampTextStyle
              .copyWith(fontWeight: _fontWeightBold)
          : appThemeExtension.conversationTileTimestampTextStyle,
      strutStyle: const StrutStyle(fontSize: 14, forceStrutHeight: true),
    );
  }

  Row _buildMessage(
      String? draft,
      AppThemeExtension appThemeExtension,
      AppLocalizations localizations,
      ChatMessage? latestMessage,
      bool enableNewMessageNotification) {
    final children = draft?.isNotBlank ?? false
        ? [
            // Note: the draft is always a text instead of image, video, or etc as
            // we haven't supported embedded images, videos, and etc, into a message.
            TextSpan(
                text: '[${localizations.draft}]',
                style: _useBoldText
                    ? appThemeExtension.conversationTileHighlightedTextStyle
                        .copyWith(fontWeight: _fontWeightBold)
                    : appThemeExtension.conversationTileHighlightedTextStyle),
            TextSpan(text: draft),
          ]
        : latestMessage == null
            ? <TextSpan>[]
            : switch (latestMessage.type) {
                MessageType.text => [TextSpan(text: latestMessage.text)],
                MessageType.image => [
                    const WidgetSpan(
                        child: Icon(Symbols.image_rounded,
                            size: _messageIconSize)),
                    TextSpan(
                      text: localizations.image,
                    )
                  ],
                MessageType.file => [
                    const WidgetSpan(
                        child: Icon(Symbols.description_rounded,
                            size: _messageIconSize)),
                    TextSpan(
                      text: localizations.file,
                    )
                  ],
                MessageType.video => [
                    const WidgetSpan(
                        child: Icon(Symbols.video_file_rounded,
                            size: _messageIconSize)),
                    TextSpan(
                      text: localizations.video,
                    )
                  ],
                MessageType.audio => [
                    const WidgetSpan(
                        child: Icon(Symbols.audio_file_rounded,
                            size: _messageIconSize)),
                    TextSpan(
                      text: localizations.audio,
                    )
                  ],
                MessageType.youtube => [
                    const WidgetSpan(
                        child: Icon(Symbols.smart_display_rounded,
                            size: _messageIconSize)),
                    TextSpan(
                      text: localizations.youtube,
                    )
                  ],
              };
    final strutStyle = StrutStyle(
        fontSize: appThemeExtension.conversationTileMessageTextStyle.fontSize!,
        forceStrutHeight: true);
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Flexible(
          child: widget.isSearching
              ? Text.rich(
                  TextSpan(
                      children:
                          (widget.item as ConversationTileItemForSearchMode)
                              .messageTextSpans),
                  style: appThemeExtension.conversationTileMessageTextStyle,
                  strutStyle: strutStyle,
                  overflow: TextOverflow.ellipsis,
                  maxLines: 1,
                  softWrap: false,
                )
              : Text.rich(
                  TextSpan(children: children),
                  style: _useBoldText
                      ? appThemeExtension.conversationTileMessageTextStyle
                          .copyWith(fontWeight: _fontWeightBold)
                      : appThemeExtension.conversationTileMessageTextStyle,
                  strutStyle: strutStyle,
                  overflow: TextOverflow.ellipsis,
                  maxLines: 1,
                  softWrap: false,
                ),
        ),
        if (enableNewMessageNotification)
          Padding(
            padding: const EdgeInsets.only(left: 8),
            child: Icon(Symbols.notifications_off_rounded,
                size: 14,
                // TODO
                color:
                    appThemeExtension.conversationTileMessageTextStyle.color!),
          )
      ],
    );
  }
}

const _leg = 2.0;
final _hypotenuse = sqrt(2 * _leg * _leg);

class _PinnedConversationMarkerPainter extends CustomPainter {
  const _PinnedConversationMarkerPainter();

  @override
  void paint(Canvas canvas, Size size) {
    final dimension = size.width;
    final height = _hypotenuse / 2;
    // radius = (height / 2) + (width^2 / (8 * height))
    final radius = Radius.circular(height / 2 + dimension / 8);

    canvas.drawPath(
        Path()
          ..moveTo(_leg, 0)
          ..arcToPoint(const Offset(0, _leg), radius: radius, clockwise: false)
          ..lineTo(0, dimension - _leg)
          ..arcToPoint(Offset(_leg, dimension),
              radius: radius, clockwise: false)
          ..lineTo(dimension, _leg)
          ..arcToPoint(Offset(dimension - _leg, 0),
              radius: radius, clockwise: false)
          ..lineTo(_leg, 0)
          ..close(),
        Paint()
          ..color = const Color.fromARGB(255, 170, 207, 244)
          ..style = PaintingStyle.fill);
  }

  @override
  bool shouldRepaint(covariant CustomPainter oldDelegate) => false;
}

sealed class ConversationTileItem {
  const ConversationTileItem(
      {required this.conversationId,
      required this.contact,
      required this.nameTextSpans,
      this.latestMessage});

  final IntListHolder conversationId;
  final Contact contact;
  final List<TextSpan> nameTextSpans;
  final ChatMessage? latestMessage;
}

class ConversationTileItemForNormalMode extends ConversationTileItem {
  ConversationTileItemForNormalMode({
    required super.nameTextSpans,
    required this.conversation,
  }) : super(
            conversationId: conversation.id,
            contact: conversation.contact,
            latestMessage: conversation.messages.lastOrNull);

  final Conversation conversation;
}

class ConversationTileItemForSearchMode extends ConversationTileItem {
  const ConversationTileItemForSearchMode(
      {required super.conversationId,
      required super.contact,
      required super.nameTextSpans,
      required super.latestMessage,
      required this.count,
      required this.messageTextSpans});

  final int count;
  final List<TextSpan> messageTextSpans;
}
