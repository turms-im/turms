import '../../../infra/built_in_types/built_in_type_helpers.dart';
import '../../../ui/l10n/app_localizations.dart';
import '../../common/fixtures/fixtures.dart';
import '../models/relationship_group.dart';
import '../models/user.dart';
import 'contacts.dart';

final _userRelationshipGroups = Fixtures.instance.userContacts
    .groupBy((c) => c.relationshipGroupId)
    .entries
    .map(
      (entry) => RelationshipGroup.forUser(
        id: entry.key!,
        name: 'fake-name',
        isBlocked: false,
        contacts: entry.value,
      ),
    )
    .toList();

extension FixturesExtensions on Fixtures {
  List<RelationshipGroup> get userRelationshipGroups => _userRelationshipGroups;

  RelationshipGroup getGroupRelationshipGroup(
    User loggedInUser,
    AppLocalizations appLocalizations,
  ) => RelationshipGroup.forGroup(
    name: appLocalizations.groups,
    isBlocked: false,
    contacts: Fixtures.instance.getFixtureGroupContacts(loggedInUser),
  );
}
