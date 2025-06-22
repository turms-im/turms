import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../../../domain/user/models/user.dart';
import '../../../../../l10n/app_localizations.dart';
import '../../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../../themes/sizes.dart';
import '../../../../components/t_menu/t_context_menu.dart';
import 'user_profile_image.dart';

class UserProfile extends ConsumerStatefulWidget {
  const UserProfile({super.key, required this.user, this.onEditTap});

  final User user;
  final VoidCallback? onEditTap;

  @override
  ConsumerState<UserProfile> createState() => _UserProfileState();
}

class _UserProfileState extends ConsumerState<UserProfile> {
  @override
  Widget build(BuildContext context) {
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    return _buildProfile(appLocalizations);
  }

  Widget _buildProfile(AppLocalizations appLocalizations) {
    final user = widget.user;
    return IntrinsicHeight(
      child: Row(
        spacing: 16,
        children: [
          UserProfileImage(user: user, onEditTap: widget.onEditTap),
          Expanded(
            child: Padding(
              padding: Sizes.paddingV4,
              child: SelectionArea(
                contextMenuBuilder: buildContextMenuForSelectableRegion,
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      user.name,
                      overflow: TextOverflow.ellipsis,
                      maxLines: 1,
                      softWrap: false,
                    ),
                    Text('${appLocalizations.userId}: ${user.userId}'),
                  ],
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}
