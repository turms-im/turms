import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../themes/index.dart';
import '../index.dart';
import '../t_loading_indicator/t_loading_indicator.dart';
import 'client/client.dart';
import 'client/models/gif.dart';
import 'client/models/response.dart';
import 'client/models/type.dart';

const crossAxisCount = 5;
const limit = crossAxisCount * 10;

final _queryTextViewModel = StateProvider<String>((ref) => '');

class GiphyPicker extends ConsumerWidget {
  const GiphyPicker({super.key, required this.onSelected});

  final ValueChanged<GiphyGif> onSelected;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    return Column(
      spacing: 8,
      children: [
        TSearchBar(
          hintText: appLocalizations.searchStickers,
          onSubmitted: (value) {
            ref.read(_queryTextViewModel.notifier).state = value;
          },
        ),
        Expanded(
          child: GiphyPickerBody(
            type: GiphyType.stickers,
            scrollController: ScrollController(),
            onSelected: onSelected,
          ),
        ),
      ],
    );
  }
}

class GiphyPickerBody extends ConsumerStatefulWidget {
  GiphyPickerBody(
      {Key? key,
      required this.type,
      required this.scrollController,
      required this.onSelected})
      : super(key: key);

  final GiphyType type;
  final ScrollController scrollController;
  final ValueChanged<GiphyGif> onSelected;

  @override
  _GiphyPickerBodyState createState() => _GiphyPickerBodyState();
}

class _GiphyPickerBodyState extends ConsumerState<GiphyPickerBody> {
  GiphyResponse? _lastResponse;

  final List<GiphyGif> _gifs = [];

  final Axis _scrollDirection = Axis.vertical;

  // Spacing between gifs in grid
  final double _spacing = 8.0;

  bool _isLoading = false;

  int _offset = 0;

  @override
  void initState() {
    super.initState();

    widget.scrollController.addListener(_loadMoreIfScrollToEnd);
    _loadMore();
  }

  @override
  void dispose() {
    widget.scrollController.removeListener(_loadMoreIfScrollToEnd);
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final gifs = _gifs;
    if (gifs.isEmpty) {
      final appLocalizations = ref.watch(appLocalizationsViewModel);
      return Center(
        child: TLoadingIndicator(text: appLocalizations.loading),
      );
    }
    return GridView.builder(
      gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
        crossAxisCount: crossAxisCount,
        mainAxisSpacing: _spacing,
        crossAxisSpacing: _spacing,
      ),
      padding: Sizes.paddingH8,
      scrollDirection: _scrollDirection,
      controller: widget.scrollController,
      itemCount: gifs.length,
      itemBuilder: (ctx, idx) => _buildItem(gifs[idx]),
    );
  }

  Widget _buildItem(GiphyGif gif) {
    final images = gif.images!;
    final image = images.fixedWidthSmall!;
    final _aspectRatio = double.parse(image.width) / double.parse(image.height);
    final url = image.url;
    return ClipRRect(
      borderRadius: Sizes.borderRadiusCircular8,
      child: MouseRegion(
        cursor: SystemMouseCursors.click,
        child: GestureDetector(
          onTap: () => widget.onSelected(gif),
          child: RepaintBoundary(
            child: LayoutBuilder(builder: (context, constraints) {
              final maxWidth = constraints.maxWidth;
              return Image.network(
                url,
                cacheWidth: maxWidth.toInt(),
                cacheHeight: maxWidth ~/ _aspectRatio,
                semanticLabel: gif.title,
                gaplessPlayback: true,
                fit: BoxFit.fill,
                headers: {'accept': 'image/*'},
              );
            }),
          ),
        ),
      ),
    );
  }

  Future<void> _loadMore() async {
    var response = _lastResponse;
    if (_isLoading || response?.pagination?.totalCount == _gifs.length) {
      return;
    }

    _isLoading = true;

    final client = ref.read(giphyClientProvider);

    _offset = response == null
        ? 0
        : response.pagination!.offset + response.pagination!.count;

    final type = widget.type;
    if (type == GiphyType.emoji) {
      response = await client.emojis(offset: _offset, limit: limit);
    } else {
      final queryText = ref.read(_queryTextViewModel);
      if (queryText.isNotEmpty) {
        response = await client.search(queryText,
            offset: _offset, type: type, limit: limit);
      } else {
        response =
            await client.trending(offset: _offset, type: type, limit: limit);
      }
    }

    _lastResponse = response;
    if (response.data.isNotEmpty && mounted) {
      _gifs.addAll(response.data);
      setState(() {});
    }

    _isLoading = false;
  }

  void _loadMoreIfScrollToEnd() {
    // TODO
    // if (widget.scrollController.positions.last.extentAfter.lessThan(500) &&
    //     !_isLoading) {
    //   _loadMore();
    // }
  }
}
