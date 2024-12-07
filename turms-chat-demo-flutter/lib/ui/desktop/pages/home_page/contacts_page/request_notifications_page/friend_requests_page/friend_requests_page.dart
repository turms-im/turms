import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../../../../domain/common/models/request_status.dart';
import '../../../../../../../domain/user/models/contact.dart';
import '../../../../../../../domain/user/models/friend_request.dart';
import '../../../../../../../domain/user/services/user_service.dart';
import '../../../chat_page/view_models/selected_conversation_view_model.dart';
import '../new_relationship_requests_page/new_relationship_requests_page.dart';
import 'friend_requests_view_model.dart';

class FriendRequestsPage extends ConsumerStatefulWidget {
  const FriendRequestsPage({super.key});

  @override
  ConsumerState<FriendRequestsPage> createState() => _FriendRequestsPageState();
}

class _FriendRequestsPageState extends ConsumerState<FriendRequestsPage> {
  @override
  Widget build(BuildContext context) {
    final friendRequests = ref.watch(friendRequestsViewModel);
    return NewRelationshipRequestsPage(
      requests: friendRequests,
      onRequestStatusChange: (request, requestStatus) async {
        switch (requestStatus) {
          case RequestStatus.accepted:
            return _acceptFriendRequest(request as FriendRequest);
          default:
            return;
        }
      },
      onStartConversationTap: (value) {
        _startConversation(value as FriendRequest);
      },
    );
  }

  Future<void> _acceptFriendRequest(FriendRequest request) async {
    final notifier = ref.read(friendRequestsViewModel.notifier);
    await ref.read(userServiceProvider)!.acceptFriendRequest(request.id);
    notifier.replace(request, request.copyWith(status: RequestStatus.accepted));
  }

  void _startConversation(FriendRequest friendRequest) {
    ref.read(selectedConversationViewModel.notifier).selectByContact(
        UserContact(
            userId: friendRequest.sender.userId,
            name: friendRequest.sender.name));
  }
}
