import 'package:flutter/widgets.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../../domain/user/models/contact.dart';
import '../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../themes/index.dart';
import '../../../components/index.dart';
import 'new_relationship_page.dart';

class RelationshipInfoTile extends ConsumerWidget {
  const RelationshipInfoTile({
    super.key,
    required this.isGroup,
    required this.contact,
    required this.onTap,
  });

  final bool isGroup;
  final Contact contact;
  final GestureTapCallback onTap;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    return TListTile(
      padding: const EdgeInsets.symmetric(
        vertical: 12,
        horizontal: safeAreaPaddingHorizontal,
      ),
      child: Row(
        mainAxisSize: MainAxisSize.min,
        spacing: 12,
        children: [
          TAvatar(id: contact.id, name: contact.name, image: contact.image),
          Expanded(
            child: Text(
              contact.name,
              overflow: TextOverflow.ellipsis,
              maxLines: 1,
              softWrap: false,
            ),
          ),
          TTextButton(
            text: isGroup
                ? appLocalizations.joinGroup
                : appLocalizations.addContact,
            containerPadding: Sizes.paddingV4H8,
            onTap: onTap,
          ),
        ],
      ),
    );
  }
}
