import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../../../domain/user/models/index.dart';
import '../../../../../l10n/app_localizations.dart';
import '../../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../components/t_avatar/t_avatar.dart';
import '../../../../components/t_image_viewer/t_image_viewer.dart';

class UserProfileImage extends ConsumerStatefulWidget {
  const UserProfileImage({super.key, required this.user, this.onEditTap});

  final User user;
  final VoidCallback? onEditTap;

  @override
  ConsumerState<UserProfileImage> createState() => _UserProfileImageState();
}

class _UserProfileImageState extends ConsumerState<UserProfileImage> {
  double _imageOpacity = 0;

  @override
  Widget build(BuildContext context) {
    final user = widget.user;
    final image = user.image;
    final avatar = TAvatar(
      id: user.userId,
      size: TAvatarSize.large,
      name: user.name,
      image: image,
    );
    if (widget.onEditTap case final onEditTap?) {
      return _buildEditableAvatar(
        ref.watch(appLocalizationsViewModel),
        onEditTap,
        avatar,
      );
    }
    return image == null
        ? avatar
        : GestureDetector(
            onTap: () {
              showImageViewerDialog(context, image);
            },
            child: MouseRegion(cursor: SystemMouseCursors.click, child: avatar),
          );
  }

  Widget _buildEditableAvatar(
    AppLocalizations appLocalizations,
    VoidCallback onEditTap,
    TAvatar avatar,
  ) => GestureDetector(
    onTap: onEditTap,
    child: MouseRegion(
      cursor: SystemMouseCursors.click,
      onEnter: (_) {
        setState(() {
          _imageOpacity = 1;
        });
      },
      onExit: (_) {
        setState(() {
          _imageOpacity = 0;
        });
      },
      child: Stack(
        children: [
          avatar,
          Positioned.fill(
            child: Align(
              alignment: Alignment.bottomCenter,
              child: SizedBox(
                height: 20,
                width: double.infinity,
                child: AnimatedOpacity(
                  opacity: _imageOpacity,
                  duration: const Duration(milliseconds: 100),
                  child: DecoratedBox(
                    decoration: const BoxDecoration(
                      color: Color.fromARGB(128, 0, 0, 0),
                    ),
                    child: Center(
                      child: Text(
                        appLocalizations.edit,
                        style: const TextStyle(
                          color: Colors.white,
                          fontSize: 14,
                        ),
                      ),
                    ),
                  ),
                ),
              ),
            ),
          ),
        ],
      ),
    ),
  );
}
