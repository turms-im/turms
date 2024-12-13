import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../../../domain/user/models/contact.dart';
import '../../../../../../infra/built_in_types/built_in_type_helpers.dart';
import '../../../../l10n/app_localizations.dart';
import '../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../themes/sizes.dart';
import '../../../components/index.dart';
import '../chat_page/view_models/selected_conversation_view_model.dart';
import 'request_notifications_page/request_notifications_page.dart';
import 'view_models/selected_contact_view_model.dart';

class ContactProfilePage extends ConsumerStatefulWidget {
  const ContactProfilePage({super.key});

  @override
  ConsumerState createState() => _ContactProfilePageState();
}

class _ContactProfilePageState extends ConsumerState<ContactProfilePage> {
  @override
  Widget build(BuildContext context) {
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    final selectedContact = ref.watch(selectedContactViewModel);
    if (selectedContact == null) {
      return const TWindowControlZone(
          toggleMaximizeOnDoubleTap: true, child: TEmpty());
    }
    if (selectedContact is SystemContact &&
        selectedContact.type == SystemContactType.requestNotification) {
      return const RequestNotificationsPage();
    }
    final intro = selectedContact.intro;
    return Stack(
      children: [
        _buildProfile(appLocalizations, selectedContact, intro),
        const TWindowControlZone(
          toggleMaximizeOnDoubleTap: true,
          child: SizedBox(
            height: Sizes.homePageHeaderHeight,
            width: double.infinity,
          ),
        ),
      ],
    );
  }

  Padding _buildProfile(AppLocalizations appLocalizations,
          Contact selectedContact, String intro) =>
      Padding(
        padding: const EdgeInsets.symmetric(vertical: 120),
        child: Align(
          alignment: Alignment.topCenter,
          child: SizedBox(
            width: 300,
            child: Column(
              children: [
                IntrinsicHeight(
                  child: Row(
                    crossAxisAlignment: CrossAxisAlignment.stretch,
                    spacing: 16,
                    children: [
                      TAvatar(
                        id: selectedContact.id,
                        name: selectedContact.name,
                        image: selectedContact.image,
                        icon: selectedContact.icon,
                        size: TAvatarSize.large,
                      ),
                      Expanded(
                        child: Padding(
                          padding: Sizes.paddingV2,
                          child: Column(
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text(
                                selectedContact.name,
                                overflow: TextOverflow.ellipsis,
                                maxLines: 1,
                                softWrap: false,
                              ),
                              // TODO: Add more details
                              if (selectedContact is UserContact)
                                Text(
                                    '${appLocalizations.userId}: ${selectedContact.userId}')
                              else if (selectedContact is GroupContact)
                                Text(
                                    '${appLocalizations.groupId}: ${selectedContact.groupId}')
                            ],
                          ),
                        ),
                      )
                    ],
                  ),
                ),
                if (intro.isNotBlank) ...[
                  const SizedBox(
                    height: 16,
                  ),
                  Text(selectedContact.intro)
                ],
                const SizedBox(
                  height: 32,
                ),
                TTextButton(
                  text: appLocalizations.messages,
                  onTap: () {
                    ref
                        .read(selectedConversationViewModel.notifier)
                        .selectByContact(selectedContact);
                  },
                )
              ],
            ),
          ),
        ),
      );
}
