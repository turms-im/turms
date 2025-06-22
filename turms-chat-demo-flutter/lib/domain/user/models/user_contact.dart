part of 'contact.dart';

class UserContact extends PrivateContact implements User {
  UserContact({
    required this.userId,
    required super.name,
    super.intro,
    super.imageUrl,
    super.imageBytes,
    this.relationshipGroupId,
    this.presence = UserPresence.none,
  }) : super(recordId: 'user:$userId', id: userId);

  factory UserContact.fromUser(User user, Int64 relationshipGroupId) =>
      UserContact(
        userId: user.userId,
        name: user.name,
        intro: user.intro,
        imageUrl: user.imageUrl,
        imageBytes: user.imageBytes,
        presence: user.presence,
        relationshipGroupId: relationshipGroupId,
      );

  @override
  final Int64 userId;
  final Int64? relationshipGroupId;
  @override
  final UserPresence presence;

  @override
  UserContact? copyWith({UserPresence? presence}) => UserContact(
    userId: userId,
    name: name,
    intro: intro,
    imageUrl: imageUrl,
    imageBytes: imageBytes,
    relationshipGroupId: relationshipGroupId,
    presence: presence ?? this.presence,
  );
}
