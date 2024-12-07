import 'package:fixnum/fixnum.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../infra/core/comparable_utils.dart';
import '../../../ui/desktop/components/index.dart';
import '../../../ui/desktop/pages/app.dart';
import '../../../ui/l10n/app_localizations.dart';
import '../../../ui/l10n/view_models/app_localizations_view_model.dart';
import '../../common/fixtures/fixtures.dart';
import '../fixtures/contacts.dart';
import '../fixtures/relationship_groups.dart';
import '../models/index.dart';
import '../view_models/logged_in_user_info_view_model.dart';

class UserService {
  const UserService(this._loggedInUser);

  final User _loggedInUser;

  Future<List<RelationshipGroup>> queryRelationshipGroups() async {
    final appLocalizations = readGlobalState(appLocalizationsViewModel);
    final locale = appLocalizations.localeName;
    await Future<void>.delayed(const Duration(seconds: 3));
    return ComparableUtils.sortByStrings(
          locale,
          Fixtures.instance.userRelationshipGroups,
          (item) => item.name,
        ) +
        [
          Fixtures.instance
              .getGroupRelationshipGroup(_loggedInUser, appLocalizations)
        ];
  }

  Future<List<Contact>> queryContacts() async {
    final locale = readGlobalState(appLocalizationsViewModel).localeName;
    await Future<void>.delayed(const Duration(seconds: 3));
    final contacts = Fixtures.instance.getContacts(_loggedInUser);
    return ComparableUtils.sortByStrings(
        locale, contacts, (contact) => contact.name);
  }

  List<Contact> getSystemContacts(AppLocalizations appLocalizations) => [
        SystemContact.forRequestNotification(appLocalizations),
        SystemContact.forFileTransfer(appLocalizations),
      ];

  Future<void> acceptFriendRequest(Int64 id) async {
    await Future<void>.delayed(const Duration(seconds: 3));
  }

  static Future<User> login(Int64 userId) async {
    await Future<void>.delayed(const Duration(seconds: 1));
    return User(
        userId: userId, name: 'James Chen', presence: UserPresence.available);
  }

  User queryUsers(Int64 senderId) => Fixtures.instance.userContacts
      .firstWhere((element) => element.userId == senderId);

  Future<List<UserContact>> searchUserContacts(
      Int64 userId, String searchText) async {
    await Future<void>.delayed(const Duration(seconds: 3));
    return [
      UserContact(
        userId: userId,
        name: 'a fake user name: $searchText' * 10,
        intro: 'a fake user intro',
        relationshipGroupId: Int64(-1),
      )
    ];
  }

  Future<void> sendFriendRequest(Int64 userId, String content) async {
    await Future<void>.delayed(const Duration(seconds: 3));
  }

  void updatePresence(UserPresence presence) {
    final loggedInUser = readGlobalState(loggedInUserViewModel)!;
    if (loggedInUser.presence == presence) {
      return;
    }
    readGlobalState(loggedInUserViewModel.notifier).state =
        loggedInUser.copyWith(presence: presence);
  }
}

final userServiceProvider = StateProvider<UserService?>(
  (ref) => null,
);
