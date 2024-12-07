import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../../themes/index.dart';
import '../../../../components/index.dart';
import 'friend_requests_page/friend_requests_page.dart';
import 'group_membership_requests_page/group_membership_requests_page.dart';

class RequestNotificationsPage extends ConsumerStatefulWidget {
  const RequestNotificationsPage({super.key});

  @override
  ConsumerState<RequestNotificationsPage> createState() =>
      _RequestNotificationsPageState();
}

class _RequestNotificationsPageState
    extends ConsumerState<RequestNotificationsPage>
    with SingleTickerProviderStateMixin<RequestNotificationsPage> {
  late TabController _tabController;

  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: 2, vsync: this);
  }

  @override
  void dispose() {
    _tabController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    return Column(children: [
      Stack(
        children: [
          const Positioned.fill(
              child: TWindowControlZone(
            toggleMaximizeOnDoubleTap: true,
          )),
          Align(
            alignment: Alignment.centerLeft,
            child: Padding(
              padding: Sizes.paddingV16,
              child: TabBar(
                isScrollable: true,
                tabAlignment: TabAlignment.start,
                padding: const EdgeInsets.symmetric(horizontal: 8),
                dividerHeight: 0,
                controller: _tabController,
                tabs: [
                  Tab(
                    text: appLocalizations.friendRequests,
                    height: 40,
                  ),
                  Tab(
                    text: appLocalizations.groupMembershipRequests,
                    height: 40,
                  )
                ],
              ),
            ),
          ),
        ],
      ),
      Expanded(
        child: Padding(
          padding: const EdgeInsets.only(
              bottom: 16,
              left: 16,
              // Used to avoid the scrollbar aligning to the right exactly.
              right: 16),
          child: TabBarView(controller: _tabController, children: [
            const FriendRequestsPage(),
            const GroupMembershipRequestsPage(),
          ]),
        ),
      )
    ]);
  }
}
