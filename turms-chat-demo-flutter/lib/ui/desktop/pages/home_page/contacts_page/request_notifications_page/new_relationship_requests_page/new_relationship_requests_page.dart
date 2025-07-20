import 'dart:collection';

import 'package:fixnum/fixnum.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../../../../domain/common/models/new_relationship_request.dart';
import '../../../../../../../domain/common/models/request_status.dart';
import '../../../../../../../infra/built_in_types/built_in_type_helpers.dart';
import '../../../../../../l10n/app_localizations.dart';
import '../../../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../../../l10n/view_models/date_format_view_models.dart';
import '../../../../../../themes/sizes.dart';
import '../../../../../components/index.dart';
import 'new_relationship_request_tile.dart';

class NewRelationshipRequestsPage extends ConsumerStatefulWidget {
  const NewRelationshipRequestsPage({
    super.key,
    required this.requests,
    required this.onRequestStatusChange,
    required this.onStartConversationTap,
  });

  final List<NewRelationshipRequest> requests;
  final Future<void> Function(
    NewRelationshipRequest request,
    RequestStatus requestStatus,
  )
  onRequestStatusChange;
  final ValueChanged<NewRelationshipRequest> onStartConversationTap;

  @override
  ConsumerState createState() => _NewRelationshipRequestsPageState();
}

class _NewRelationshipRequestsPageState
    extends ConsumerState<NewRelationshipRequestsPage> {
  @override
  Widget build(BuildContext context) {
    final requests = widget.requests
      // Sort it to display the most recent first.
      ..sort((a, b) => b.creationDate.compareTo(a.creationDate));
    final creationDateToRequests = requests.groupByAsLinkedHashMap((request) {
      final creationDate = request.creationDate;
      return DateTime(creationDate.year, creationDate.month, creationDate.day);
    });
    final groupCount = creationDateToRequests.length;
    final creationDateAndRequests = creationDateToRequests.entries.toList();
    final requestGroups = creationDateToRequests.values;
    final groupIdToIndex = {
      for (var i = 0; i < groupCount; i++)
        requestGroups.elementAt(i).first.id: i,
    };
    return _buildFriendRequestGroups(
      ref,
      creationDateAndRequests,
      groupIdToIndex,
    );
  }

  Widget _buildFriendRequestGroups(
    WidgetRef ref,
    List<MapEntry<DateTime, List<NewRelationshipRequest>>>
    creationDateAndRequests,
    Map<Int64, int> groupIdToIndex,
  ) {
    final now = DateTime.now();
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    return ListView.separated(
      itemCount: creationDateAndRequests.length,
      // Prevent the scrollbar from overlapping children.
      padding: const EdgeInsets.only(right: 24),
      findChildIndexCallback: (key) =>
          groupIdToIndex[(key as ValueKey<Int64>).value],
      separatorBuilder: (context, index) => Sizes.sizedBoxH16,
      itemBuilder: (context, index) {
        final entry = creationDateAndRequests[index];
        return _buildRequestGroupOfSameDay(
          entry.key,
          now,
          appLocalizations,
          ref,
          entry.value,
        );
      },
    );
  }

  Widget _buildRequestGroupOfSameDay(
    DateTime creationDate,
    DateTime now,
    AppLocalizations appLocalizations,
    WidgetRef ref,
    List<NewRelationshipRequest> requests,
  ) => Column(
    key: ValueKey(requests.first.id),
    children: [
      if (DateUtils.isSameDay(creationDate, now))
        Text(appLocalizations.today)
      else if (creationDate.year == now.year)
        Text(ref.watch(dateFormatViewModel_Md).format(creationDate))
      else
        Text(ref.watch(dateFormatViewModel_yMd).format(creationDate)),
      Sizes.sizedBoxH8,
      const THorizontalDivider(),
      Sizes.sizedBoxH12,
      ...requests.indexed.expand((item) {
        final (requestIndex, request) = item;
        return [
          if (requestIndex > 0) const SizedBox(height: 16),
          NewRelationshipRequestTile(
            key: Key(request.id.toString()),
            request: request,
            onAccept: () async =>
                widget.onRequestStatusChange(request, RequestStatus.accepted),
            onStartConversation: () => widget.onStartConversationTap(request),
          ),
        ];
      }),
    ],
  );
}
