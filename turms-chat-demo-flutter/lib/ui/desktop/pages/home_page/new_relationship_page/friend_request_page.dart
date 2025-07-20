import 'package:fixnum/fixnum.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../../domain/user/models/contact.dart';
import '../../../../../domain/user/services/user_service.dart';
import '../../../../../infra/built_in_types/built_in_type_helpers.dart';
import '../../../../l10n/app_localizations.dart';
import '../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../themes/app_theme_extension.dart';
import '../../../../themes/sizes.dart';
import '../../../components/index.dart';
import '../../app.dart';

class FriendRequestPage extends ConsumerStatefulWidget {
  const FriendRequestPage(this.contact, {super.key});

  final Contact contact;

  @override
  ConsumerState<FriendRequestPage> createState() => _FriendRequestPageState();
}

class _FriendRequestPageState extends ConsumerState<FriendRequestPage> {
  late TextEditingController _messageEditingController;
  bool _isSending = false;

  @override
  void initState() {
    super.initState();
    _messageEditingController = TextEditingController();
  }

  @override
  void dispose() {
    _messageEditingController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final theme = context.theme;
    final appThemeExtension = theme.appThemeExtension;
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    return _buildPage(theme, appThemeExtension, appLocalizations);
  }

  Widget _buildPage(
    ThemeData theme,
    AppThemeExtension appThemeExtension,
    AppLocalizations appLocalizations,
  ) {
    final contact = widget.contact;
    return SizedBox(
      width: Sizes.friendRequestDialogWidth,
      height: Sizes.friendRequestDialogHeight,
      child: Stack(
        children: [
          Positioned.fill(
            child: Padding(
              padding: Sizes.paddingV16H16,
              child: Column(
                children: [
                  Text(appLocalizations.addContact),
                  Sizes.sizedBoxH16,
                  Row(
                    spacing: 8,
                    children: [
                      TAvatar(
                        id: contact.id,
                        name: contact.name,
                        image: contact.image,
                      ),
                      Expanded(
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Text(
                              contact.name,
                              maxLines: 1,
                              overflow: TextOverflow.ellipsis,
                            ),
                            if (contact.intro.isNotBlank)
                              Text(
                                contact.intro,
                                style: appThemeExtension.descriptionTextStyle,
                                maxLines: 1,
                                overflow: TextOverflow.ellipsis,
                              ),
                          ],
                        ),
                      ),
                    ],
                  ),
                  Sizes.sizedBoxH8,
                  Align(
                    alignment: Alignment.centerLeft,
                    child: Text(appLocalizations.message),
                  ),
                  Sizes.sizedBoxH8,
                  Expanded(
                    child: TTextField(
                      autofocus: true,
                      expands: true,
                      textEditingController: _messageEditingController,
                    ),
                  ),
                  Sizes.sizedBoxH12,
                  Row(
                    mainAxisAlignment: MainAxisAlignment.end,
                    spacing: 16,
                    children: [
                      TTextButton.outlined(
                        theme: theme,
                        text: appLocalizations.cancel,
                        containerPadding: Sizes.paddingV4H8,
                        containerWidth: 64,
                        onTap: _close,
                      ),
                      TTextButton(
                        isLoading: _isSending,
                        text: appLocalizations.send,
                        containerPadding: Sizes.paddingV4H8,
                        containerWidth: 64,
                        onTap: () {
                          _sendFriendRequest(
                            (contact is UserContact)
                                ? contact.userId
                                : (contact as GroupContact).groupId,
                            _messageEditingController.text,
                          );
                        },
                      ),
                    ],
                  ),
                ],
              ),
            ),
          ),
          const TTitleBar(displayCloseOnly: true, popOnCloseTapped: true),
        ],
      ),
    );
  }

  Future<void> _sendFriendRequest(Int64 userId, String content) async {
    _isSending = true;
    setState(() {});
    await ref.read(userServiceProvider)!.sendFriendRequest(userId, content);
    _isSending = false;
    _close();
  }

  void _close() {
    popTopIfNameMatched(friendRequestDialogRouteName);
  }
}

const friendRequestDialogRouteName = '/friend-request-dialog';

Future<void> showFriendRequestDialog(BuildContext context, Contact contact) =>
    showCustomTDialog(
      routeName: friendRequestDialogRouteName,
      context: context,
      child: FriendRequestPage(contact),
    );
