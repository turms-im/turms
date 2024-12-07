import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../../../../domain/common/models/new_relationship_request.dart';
import '../../../../../../../domain/common/models/request_status.dart';
import '../../../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../../../themes/index.dart';

import '../../../../../components/t_avatar/t_avatar.dart';
import '../../../../../components/t_button/t_text_button.dart';
import '../../../../../components/t_menu/t_context_menu.dart';

class NewRelationshipRequestTile extends ConsumerStatefulWidget {
  const NewRelationshipRequestTile(
      {Key? key,
      required this.request,
      required this.onAccept,
      required this.onStartConversation})
      : super(key: key);

  final NewRelationshipRequest request;
  final Future<void> Function() onAccept;
  final void Function() onStartConversation;

  @override
  _NewRelationshipRequestTileState createState() =>
      _NewRelationshipRequestTileState();
}

class _NewRelationshipRequestTileState
    extends ConsumerState<NewRelationshipRequestTile> {
  bool _isHandling = false;

  @override
  Widget build(BuildContext context) {
    final theme = context.theme;
    final appThemeExtension = theme.appThemeExtension;
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    final request = widget.request;
    final sender = request.sender;
    final message = request.message;
    final status = request.status;
    return Row(
      children: [
        TAvatar(
          id: sender.userId,
          name: sender.name,
          image: sender.image,
        ),
        Sizes.sizedBoxW16,
        Expanded(
          child: SelectionArea(
            contextMenuBuilder: buildContextMenuForSelectableRegion,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  sender.name,
                  maxLines: 1,
                  overflow: TextOverflow.ellipsis,
                ),
                Text(
                  message,
                  style: appThemeExtension.descriptionTextStyle,
                  strutStyle: StrutStyle.fromTextStyle(
                      appThemeExtension.descriptionTextStyle,
                      forceStrutHeight: true),
                  maxLines: 1,
                  overflow: TextOverflow.ellipsis,
                ),
              ],
            ),
          ),
        ),
        Sizes.sizedBoxW16,
        // TODO: support decline
        status == RequestStatus.accepted
            ? TTextButton.outlined(
                theme: theme,
                containerWidth: 80,
                containerPadding: Sizes.paddingV4H8,
                text: appLocalizations.messages,
                onTap: widget.onStartConversation,
              )
            : TTextButton(
                containerWidth: 80,
                containerPadding: Sizes.paddingV4H8,
                text: appLocalizations.accept,
                isLoading: _isHandling,
                onTap: () async {
                  _isHandling = true;
                  setState(() {});
                  await widget.onAccept();
                  _isHandling = false;
                  setState(() {});
                },
              ),
        Sizes.sizedBoxW8,
      ],
    );
  }
}
