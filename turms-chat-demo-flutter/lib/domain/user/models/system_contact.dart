part of 'contact.dart';

class SystemContact extends PrivateContact {
  factory SystemContact.forFileTransfer(AppLocalizations appLocalizations) =>
      SystemContact(
          type: SystemContactType.fileTransfer,
          name: appLocalizations.fileTransfer,
          icon: Symbols.drive_file_move_rounded);

  factory SystemContact.forRequestNotification(
          AppLocalizations appLocalizations) =>
      SystemContact(
          type: SystemContactType.requestNotification,
          name: appLocalizations.requestNotification,
          icon: Symbols.person_add_rounded);

  SystemContact(
      {required this.type,
      required super.name,
      super.intro,
      super.imageUrl,
      super.imageBytes,
      super.icon})
      : super(recordId: 'system:$type', id: Int64(type.id));

  final SystemContactType type;
}

enum SystemContactType {
  fileTransfer,
  requestNotification;

  int get id => index;
}
