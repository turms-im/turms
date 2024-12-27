import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../../../../domain/common/fixtures/fixtures.dart';
import '../../../../../../../domain/user/fixtures/friend_requests.dart';
import '../../../../../../../domain/user/models/friend_request.dart';
import '../../../../../../../infra/built_in_types/built_in_type_helpers.dart';

class FriendRequestsViewModelNotifier extends Notifier<List<FriendRequest>> {
  @override
  List<FriendRequest> build() => Fixtures.instance.friendRequests;

  void replace(FriendRequest oldRequest, FriendRequest newRequest) {
    state.replace(oldRequest, newRequest);
    ref.notifyListeners();
  }
}

final friendRequestsViewModel =
    NotifierProvider<FriendRequestsViewModelNotifier, List<FriendRequest>>(
        FriendRequestsViewModelNotifier.new);
