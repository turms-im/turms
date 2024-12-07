import 'package:flutter/material.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../../../../../../../infra/built_in_types/built_in_type_helpers.dart';
import '../../../../../../../infra/env/env_vars.dart';
import '../../../../../../themes/index.dart';

import '../../../../../components/giphy/client/models/gif.dart';
import '../../../../../components/index.dart';
import 'emoji_picker_pane.dart';

const _containerColorHovered = Color.fromARGB(255, 242, 242, 242);
final _isGiphyEnabled = EnvVars.giphyApiKey.isNotBlank;

class StickerPicker extends StatefulWidget {
  const StickerPicker(
      {super.key,
      required this.onGiphyGifSelected,
      required this.onEmojiSelected});

  final ValueChanged<GiphyGif> onGiphyGifSelected;
  final ValueChanged<String> onEmojiSelected;

  @override
  State<StickerPicker> createState() => _StickerPickerState();
}

class _StickerPickerState extends State<StickerPicker> {
  _Tab _currentTab = _Tab.emoji;

  @override
  Widget build(BuildContext context) => Material(
        color: Colors.transparent,
        child: SizedBox(
          width: Sizes.stickerPickerWidth,
          height: Sizes.stickerPickerHeight,
          child: DecoratedBox(
            decoration: context.appThemeExtension.popupDecoration,
            child: Padding(
              padding: const EdgeInsets.only(top: 16),
              child: _isGiphyEnabled
                  ? Column(
                      children: [
                        Flexible(
                          child: Padding(
                            padding: Sizes.paddingH16,
                            child: TLazyIndexedStack(
                              index: switch (_currentTab) {
                                _Tab.emoji => 0,
                                _Tab.giphy => 1,
                              },
                              children: [
                                EmojiPickerPane(
                                  onEmojiSelected: widget.onEmojiSelected,
                                ),
                                GiphyPicker(
                                  onSelected: widget.onGiphyGifSelected,
                                ),
                              ],
                            ),
                          ),
                        ),
                        const THorizontalDivider(
                          color: _containerColorHovered,
                        ),
                        Padding(
                          padding: const EdgeInsets.symmetric(
                              vertical: 4, horizontal: 16),
                          child: Row(
                            spacing: 16,
                            children: [
                              TIconButton(
                                iconData: Symbols.emoji_emotions_rounded,
                                containerSize: const Size.square(32),
                                containerColorHovered: _containerColorHovered,
                                containerPadding: EdgeInsets.zero,
                                onTap: () {
                                  _currentTab = _Tab.emoji;
                                  setState(() {});
                                },
                              ),
                              TIconButton(
                                iconData: Symbols.search_rounded,
                                containerSize: const Size.square(32),
                                containerColorHovered: _containerColorHovered,
                                containerPadding: EdgeInsets.zero,
                                onTap: () {
                                  _currentTab = _Tab.giphy;
                                  setState(() {});
                                },
                              )
                            ],
                          ),
                        )
                      ],
                    )
                  : EmojiPickerPane(
                      onEmojiSelected: widget.onEmojiSelected,
                    ),
            ),
          ),
        ),
      );
}

enum _Tab { emoji, giphy }
