import '../../../ui/desktop/components/t_avatar/t_avatar.dart';
import 'user.dart';

class GroupMember extends User {
  GroupMember({
    required super.userId,
    required super.name,
    super.intro,
    super.imageUrl,
    super.imageBytes,
    required this.isAdmin,
    super.presence,
  });

  factory GroupMember.fromUser(User user, {bool isAdmin = false}) =>
      GroupMember(
        userId: user.userId,
        name: user.name,
        intro: user.intro,
        imageUrl: user.imageUrl,
        imageBytes: user.imageBytes,
        isAdmin: isAdmin,
        presence: user.presence,
      );

  final bool isAdmin;

  @override
  GroupMember? copyWith({UserPresence? presence}) => GroupMember(
    userId: userId,
    name: name,
    intro: intro,
    imageUrl: imageUrl,
    imageBytes: imageBytes,
    isAdmin: isAdmin,
    presence: presence ?? this.presence,
  );
}
