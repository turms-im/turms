import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../../../domain/user/models/index.dart';
import '../../../../../../infra/data/t_async_data.dart';

final relationshipGroupsDataViewModel =
    StateProvider<TAsyncData<List<RelationshipGroup>>>(
        (ref) => const TAsyncData());
